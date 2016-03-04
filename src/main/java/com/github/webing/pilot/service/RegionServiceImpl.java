package com.github.webing.pilot.service;

import com.github.webing.pilot.model.City;
import com.github.webing.pilot.model.District;
import com.github.webing.pilot.repository.CityRepository;
import com.github.webing.pilot.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityByCode(int cityCode) {
        return cityRepository.findCityByCode(cityCode);
    }

    @Override
    public City getCityByName(String cityName) {
        return cityRepository.findCityByName(cityName);
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictByDistrictCode(int districtCode) {
        return districtRepository.findDistrictByDistrictCode(districtCode);
    }

    @Override
    public District getDistrictByDistrictName(String districtName) {
        return districtRepository.findDistrictByDistrictName(districtName);
    }

    @Override
    public List<District> getDistrictsByCityCode(int cityCode) {
        return districtRepository.findDistrictsByCityCode(cityCode);
    }

    @Override
    public List<District> getDistrictsWithTerms(int cityCode, String query) {
        District district = new District();
        district.setCity_code(cityCode);
        district.setDistrict_name(query);
        return districtRepository.findDistrictsWithTerms(district);
    }

    @Override
    public int getDistrictWithTerms(int cityCode, String districtName) {
        District district = new District();
        district.setCity_code(cityCode);
        district.setDistrict_name(districtName);
        return districtRepository.findDistrictWithTerms(district).getDistrict_code();
    }
}
