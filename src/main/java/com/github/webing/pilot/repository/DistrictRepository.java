package com.github.webing.pilot.repository;

import com.github.webing.pilot.model.District;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */

@Repository
public interface DistrictRepository {

    List<District> findAll();

    District findDistrictByDistrictName(String districtName);

    District findDistrictByDistrictCode(int districtCode);

    List<District> findDistrictsByCityCode(int cityCode);

    List<District> findDistrictsWithTerms(District district);

    District findDistrictWithTerms(District district);
}
