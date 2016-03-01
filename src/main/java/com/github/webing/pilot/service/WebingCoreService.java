package com.github.webing.pilot.service;

import com.github.webing.pilot.model.City;
import com.github.webing.pilot.model.District;
import com.github.webing.pilot.model.User;

import java.util.List;

/**
 * Created by KD4 on 16. 2. 25..
 */
public interface WebingCoreService {
    User getUserByIdentity(String identity);

    User getUserByName(String name);

    void addUser(User user);

    User existUserByIdentity(String identity);

    void securityLogin(User user);

    List<City> getAllCites();

    City getCityByCode(int cityCode);

    City getCityByName(String cityName);

    List<District> getAllDistricts();

    District getDistrictByDistrictCode(int districtCode);

    District getDistrictByDistrictName(String districtName);

    List<District> getDistrictsByCityCode(int cityCode);
}
