package com.github.webing.pilot.controller;


import com.github.webing.pilot.model.User;
import com.github.webing.pilot.service.WebingCoreService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by KD4 on 16. 2. 7..
 */

@Controller
@RequestMapping("/auth")
@PropertySource({"classpath:/custom.properties", "classpath:/local.properties"})
public class SocialController {

    @Autowired
    WebingCoreService webingCoreService;

    @Resource
    private Environment environment;


    private void progressSignIn(User user) {
        User existedUser = webingCoreService.existUserByIdentity(user.getIdentity());
        if (existedUser == null) {
            webingCoreService.addUser(user);
            webingCoreService.securityLogin(user);
        } else {
            webingCoreService.securityLogin(user);
        }
    }

    @RequestMapping(value = "/facebook")
    public String accessFacebook() {
        return "redirect:http://www.facebook.com/dialog/oauth?client_id=" + environment.getProperty("facebook.app.id") + "&redirect_uri=" + environment.getProperty("callback.host") + "/auth/facebook_success&scope=public_profile,email";
    }

    @RequestMapping(value = "/facebook_success")
    public String progressFacebook(HttpSession session, HttpServletRequest request) throws ClientProtocolException, IOException {
        String accessToken = getFBAccessToken(request.getParameter("code"));

        User user = getFBUserInfo(accessToken);

        progressSignIn(user);

        return "redirect:/";
    }

    private String getFBAccessToken(String code) throws IOException {
        String accessToken = "";
        if (StringUtils.isNotEmpty(code)) {
            HttpGet get = new HttpGet("https://graph.facebook.com/oauth/access_token" + "?client_id=" + environment.getProperty("facebook.app.id")
                    + "&client_secret=" + environment.getProperty("facebook.app.secret") + "&redirect_uri=" + environment.getProperty("callback.host") + "/auth/facebook_success"
                    + "&code=" + code);

            HttpClient http = HttpClientBuilder.create().build();
            String result = http.execute(get, new BasicResponseHandler());
            accessToken = result.substring(result.indexOf("=") + 1);
        }
        return accessToken;
    }

    private User getFBUserInfo(String accessToken) throws IOException {
        URL url = null;
        URLConnection urlConnection = null;
        String sUrl = "https://graph.facebook.com/me?fields=id,name,email,picture.type(large)&access_token=";

        url = new URL(sUrl + accessToken);

        urlConnection = url.openConnection();

        InputStream ist = urlConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(ist);
        BufferedReader br = new BufferedReader(isr);
        String nextLine = br.readLine();
        JSONObject jObject = new JSONObject(nextLine);

        User user = new User();
        user.setIdentity("FB" + jObject.getString("id"));
        user.setEmail(jObject.getString("email"));
        user.setName(jObject.getString("name"));
        user.setRoles("ROLE_USER");
        user.setIsOAuth("T");
        user.setPassword("");
        return user;
    }


    @RequestMapping(value = "/kakao")
    public String accessKakao() {
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=" + environment.getProperty("kakao.app.id") + "&redirect_uri=" + environment.getProperty("kakao.callback") + "&response_type=code";
    }

    @RequestMapping(value = "/kakao_success")
    public String progressKakao(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");

        String accessToken = getKakaoAccessToken(code);

        User user = getKakaoUserInfo(accessToken);

        progressSignIn(user);

        return "redirect:/";
    }

    private User getKakaoUserInfo(String accessToken) throws IOException {
        BufferedReader rd = null;
        InputStreamReader isr = null;
        User user = new User();
        try {


            HttpGet get = new HttpGet("https://kapi.kakao.com/v1/user/me");
            get.setHeader("Authorization", "Bearer " + accessToken);
            HttpClient http = HttpClientBuilder.create().build();


            final HttpResponse responseForUserInfo = http.execute(get);
            final int responseCodeForUserInfo = responseForUserInfo.getStatusLine().getStatusCode();

            System.out.println("Response Code : " + responseCodeForUserInfo);


            isr = new InputStreamReader(responseForUserInfo.getEntity().getContent());
            rd = new BufferedReader(isr);
            final StringBuffer bufferForUserInfo = new StringBuffer();
            String lineForUserInfo;
            while ((lineForUserInfo = rd.readLine()) != null) {
                bufferForUserInfo.append(lineForUserInfo);
            }

            System.out.println(bufferForUserInfo);
            JSONObject jObjectForUserInfo = new JSONObject(bufferForUserInfo.toString());
            System.out.println(jObjectForUserInfo);
            user.setIdentity("KA" + String.valueOf(jObjectForUserInfo.getInt("id")));
            user.setEmail(String.valueOf(jObjectForUserInfo.getInt("id")));
            user.setName(jObjectForUserInfo.getJSONObject("properties").getString("nickname"));
            user.setRoles("ROLE_USER");
            user.setIsOAuth("T");
            user.setPassword("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // clear resources
            if (rd != null) {
                try {
                    rd.close();
                } catch (Exception ignore) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception ignore) {
                }
            }
            return user;
        }

    }

    private String getKakaoAccessToken(String code) throws IOException {
        BufferedReader rd = null;
        InputStreamReader isr = null;
        String accessToken = "";
        try {
            final HttpPost post = new HttpPost("https://kauth.kakao.com/oauth/token");

            final ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
            postParameters.add(new BasicNameValuePair("client_id", environment.getProperty("kakao.app.id")));
            postParameters.add(new BasicNameValuePair("redirect_uri", environment.getProperty("kakao.callback")));
            postParameters.add(new BasicNameValuePair("code", code));

            post.setEntity(new UrlEncodedFormEntity(postParameters));

            HttpClient client = HttpClientBuilder.create().build();


            final HttpResponse response = client.execute(post);


            final int responseCode = response.getStatusLine().getStatusCode();


            isr = new InputStreamReader(response.getEntity().getContent());
            rd = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                buffer.append(line);
            }

            JSONObject jObject = new JSONObject(buffer.toString());

            accessToken = jObject.getString("access_token");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // clear resources
            if (rd != null) {
                try {
                    rd.close();
                } catch (Exception ignore) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception ignore) {
                }
            }
            return accessToken;
        }
    }

}