package com.github.webing.pilot.service;

import com.github.webing.pilot.model.City;
import com.github.webing.pilot.model.District;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */
public interface RegionService {
    List<City> getAllCities();

    City getCityByCode(int cityCode);

    City getCityByName(String cityName);

    List<District> getAllDistricts();

    District getDistrictByDistrictCode(int districtCode);

    District getDistrictByDistrictName(String districtName);

    List<District> getDistrictsByCityCode(int cityCode);
}
