package com.github.webing.pilot.repository;

import com.github.webing.pilot.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */
@Repository
public interface CityRepository {

    List<City> findAll();

    City findCityByCode(int cityCode);

    City findCityByName(String cityName);
}
