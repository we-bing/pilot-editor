package com.github.webing.pilot.controller;

import com.github.webing.pilot.model.CandidacyKeyword;
import com.github.webing.pilot.model.CandidacyMember;
import com.github.webing.pilot.model.CandidacyPledge;
import com.github.webing.pilot.service.WebingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by kd4 on 2016. 3. 3..
 */


@Controller
@RequestMapping("/candidacies")
public class CandidacyController {

    @Autowired
    WebingCoreService webingCoreService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showCandidacies(@RequestParam(value = "cityCode", required = false, defaultValue = "0") int cityCode,
                                        @RequestParam(value = "districtName", required = false, defaultValue = "none") String districtName) {
        ModelAndView mav = new ModelAndView("candidacies");
        if (cityCode == 0) {
            return mav;
        }
        if(districtName.equals("none")){
            List<CandidacyMember> candidacyMembers = webingCoreService.getCandidacyMembersByCityCode(cityCode);
            mav.addObject("candidacyMembers", candidacyMembers);
        }else {
            int districtCode = webingCoreService.getDistrictWithTerms(cityCode, districtName);
            List<CandidacyMember> candidacyMembers = webingCoreService.getCandidacyMembersByDistrictCode(districtCode);
            mav.addObject("candidacyMembers", candidacyMembers);
        }
        return mav;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{candidacyId}")
    @ResponseBody
    public CandidacyMember returnCandidacy(@PathVariable(value = "candidacyId") int candidacyId) {
        return webingCoreService.getCandidacyMemberByCandidacyId(candidacyId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{candidacyId}/keywords")
    @ResponseBody
    public List<CandidacyKeyword> returnCandidacyKeywords(@PathVariable(value = "candidacyId") int candidacyId) {
        return webingCoreService.getCandidacyKeywordsWithCandidacyId(candidacyId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{candidacyId}/pledges")
    @ResponseBody
    public List<CandidacyPledge> returnCandidacyPledges(@PathVariable(value = "candidacyId") int candidacyId) {
        return webingCoreService.getCandidacyPledgesWithCandidacyId(candidacyId);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,
            consumes = "application/json",
            value = "/{candidacyId}/keywords")
    public String updateCandidacyKeywords(@PathVariable("candidacyId") int candidacyId, @RequestBody List<String> keywords) {
        webingCoreService.resetKeywordsWithCandidacyId(candidacyId, keywords);
        return "success";
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,
            consumes = "application/json",
            value = "/{candidacyId}/pledges")
    public String updateCandidacyPledges(@PathVariable("candidacyId") int candidacyId, @RequestBody List<CandidacyPledge> pledges) {
        webingCoreService.resetCandidacyPledges(candidacyId, pledges);
        return "success";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,
            consumes = "application/json",
            value = "/{candidacyId}/status")
    public String updateCandidacyStatus(@PathVariable("candidacyId") int candidacyId, @RequestBody CandidacyMember candidacyMember) {
        candidacyMember.setCandidacy_id(candidacyId);
        webingCoreService.updateCandidacyStatusWithCandidacyId(candidacyMember);
        return "success";
    }

}