package com.github.webing.pilot.service;

import com.github.webing.pilot.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by kd4 on 2016. 3. 1..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-root.xml")
public class WebingCoreServiceTest {

    @Autowired
    WebingCoreService webingCoreService;

    @Test
    public void getAllCites() {
        List<City> cities = webingCoreService.getAllCites();
        Assert.assertNotNull(cities);
        Assert.assertThat("서울특별시", is(cities.get(0).getCityName()));
    }

    @Test
    public void getCityWithTerms() {
        City cityCode4900 = webingCoreService.getCityByCode(4900);
        City jejuCity = webingCoreService.getCityByName("제주특별자치도");
        Assert.assertEquals(cityCode4900.getCityCode(),jejuCity.getCityCode());
    }
}
