package com.github.webing.pilot.service;

import com.github.webing.pilot.model.*;

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

    List<CandidacyMember> getAllCandidacyMembers();

    List<CandidacyMember> getCandidacyMemberByName(String name);

    CandidacyMember getCandidacyMemberByCandidacyId(int candidacyId);

    List<CandidacyMember> getCandidacyMembersByDistrictCode(int districtCode);

    List<District> getDistrictsWithTerms(int cityCode, String query);

    int getDistrictWithTerms(int cityCode, String districtName);

    List<CandidacyKeyword> getCandidacyKeywordsWithCandidacyId(int candidacyId);

    List<CandidacyPledge> getCandidacyPledgesWithCandidacyId(int candidacyId);

    void resetKeywordsWithCandidacyId(int candidacyId, List<String> keywords);

    void resetCandidacyPledges(int candidacyId, List<CandidacyPledge> pledges);

    void updateCandidacyStatusWithCandidacyId(CandidacyMember candidacyMember);

}
