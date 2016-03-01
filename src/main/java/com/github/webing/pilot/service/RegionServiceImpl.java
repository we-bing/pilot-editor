package com.github.webing.pilot.service;

import com.github.webing.pilot.model.City;
import com.github.webing.pilot.repository.CityRepository;
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
}
