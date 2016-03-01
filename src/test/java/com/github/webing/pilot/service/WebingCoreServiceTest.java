package com.github.webing.pilot.service;

import com.github.webing.pilot.model.CandidacyMember;
import com.github.webing.pilot.model.City;
import com.github.webing.pilot.model.District;
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
        Assert.assertEquals(cityCode4900.getCityCode(), jejuCity.getCityCode());
    }

    @Test
    public void getAllDistricts() {
        List<District> districts = webingCoreService.getAllDistricts();
        Assert.assertNotNull(districts);
    }

    @Test
    public void getDistrictWithTerms() {
        District district2110301 = webingCoreService.getDistrictByDistrictCode(2110301);
        District yongSanDistrict = webingCoreService.getDistrictByDistrictName("용산구");
        Assert.assertEquals(district2110301.getCityCode(), yongSanDistrict.getCityCode());
        Assert.assertEquals(district2110301.getDistrictName(), yongSanDistrict.getDistrictName());

        List<District> districtsOnJeju = webingCoreService.getDistrictsByCityCode(4900);
        Assert.assertThat(districtsOnJeju.get(0).getCityCode(), is(4900));

        for (District district : districtsOnJeju) {
            System.out.println(district.getDistrictName());
        }
    }

    @Test
    public void getAllCandidacyMembers() {
        List<CandidacyMember> candidacyMembers = webingCoreService.getAllCandidacyMembers();
        Assert.assertNotNull(candidacyMembers);
    }

    @Test
    public void getCandidacyMemberWithTerms() {
        CandidacyMember candidacyMemberWithName = webingCoreService.getCandidacyMemberByName("나경원").get(0);
        CandidacyMember candidacyMemberWithId = webingCoreService.getCandidacyMemberByCandidacyId(candidacyMemberWithName.getCandidacyId());

        Assert.assertThat(candidacyMemberWithName.getCandidacyId(), is(candidacyMemberWithId.getCandidacyId()));

        List<CandidacyMember> candidacyMembersWithDistrictCode = webingCoreService.getCandidacyMembersByDistrictCode(2110301);

        for (CandidacyMember candidacyMember : candidacyMembersWithDistrictCode) {
            Assert.assertThat(candidacyMember.getDistrictCode(), is(2110301));
            Assert.assertThat(candidacyMember.getDistrictName(), is("용산구"));
        }
    }
}
