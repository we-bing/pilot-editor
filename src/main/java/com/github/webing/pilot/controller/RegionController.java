package com.github.webing.pilot.controller;

import com.github.webing.pilot.model.CandidacyMember;
import com.github.webing.pilot.model.District;
import com.github.webing.pilot.service.WebingCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 3..
 */

@Controller
public class RegionController {


    @Autowired
    WebingCoreService webingCoreService;

    @RequestMapping(method = RequestMethod.GET, value = "/cities/{cityCode}/districts")
    @ResponseBody
    public List<District> returnDistricts(@PathVariable("cityCode") int cityCode, @RequestParam(value = "q", required = false, defaultValue = "") String query) {
        List<District> districts = webingCoreService.getDistrictsWithTerms(cityCode, query);
        if (districts.size() < 1) {
            return webingCoreService.getDistrictsByCityCode(cityCode);
        } else {
            return districts;
        }

    }

}
