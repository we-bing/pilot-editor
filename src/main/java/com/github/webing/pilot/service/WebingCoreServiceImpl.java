package com.github.webing.pilot.service;

import com.github.webing.pilot.exception.InvalidUserException;
import com.github.webing.pilot.model.*;
import com.github.webing.pilot.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by KD4 on 16. 2. 25..
 */

@Service
public class WebingCoreServiceImpl implements WebingCoreService {

    @Autowired
    UserService userService;

    @Autowired
    RegionService regionService;

    @Autowired
    CandidacyService candidacyService;

    @Override
    public User getUserByIdentity(String identity) {
        User selectedUser = userService.getUserByIdentity(identity);
        if (selectedUser == null) {
            throw new InvalidUserException("등록된 유저 정보가 없거나 탈퇴한 회원입니다.");
        }
        return selectedUser;
    }

    @Override
    public User getUserByName(String name) {
        User selectedUser = userService.getUserByName(name);
        if (selectedUser == null) {
            throw new InvalidUserException();
        }
        return selectedUser;
    }

    @Override
    public void addUser(User user) {
        if (user.getIsOAuth().equals("F")) {
            userService.insertNormalUser(user);
        } else {
            userService.insertOAuthUser(user);
        }
    }

    @Override
    public User existUserByIdentity(String identity) {
        return userService.getUserByIdentity(identity);
    }

    @Override
    public void securityLogin(User user) {
        SecurityUtil.logInUser(user);
    }

    @Override
    public List<City> getAllCites() {
        return regionService.getAllCities();
    }

    @Override
    public City getCityByCode(int cityCode) {
        return regionService.getCityByCode(cityCode);
    }

    @Override
    public City getCityByName(String cityName) {
        return regionService.getCityByName(cityName);
    }

    @Override
    public List<District> getAllDistricts() {
        return regionService.getAllDistricts();
    }

    @Override
    public District getDistrictByDistrictCode(int districtCode) {
        return regionService.getDistrictByDistrictCode(districtCode);
    }

    @Override
    public District getDistrictByDistrictName(String districtName) {
        return regionService.getDistrictByDistrictName(districtName);
    }

    @Override
    public List<District> getDistrictsByCityCode(int cityCode) {
        return regionService.getDistrictsByCityCode(cityCode);
    }

    @Override
    public List<CandidacyMember> getAllCandidacyMembers() {
        return candidacyService.getAllCandidacyMembers();
    }

    @Override
    public List<CandidacyMember> getCandidacyMemberByName(String name) {
        return candidacyService.getCandidacyMemberByName(name);
    }

    @Override
    public CandidacyMember getCandidacyMemberByCandidacyId(int candidacyId) {
        return candidacyService.getCandidacyMemberByCandidacyId(candidacyId);
    }

    @Override
    public List<CandidacyMember> getCandidacyMembersByDistrictCode(int districtCode) {
        return candidacyService.getCandidacyMembersByDistrictCode(districtCode);
    }

    @Override
    public List<District> getDistrictsWithTerms(int cityCode, String query) {
        return regionService.getDistrictsWithTerms(cityCode, query);
    }

    @Override
    public int getDistrictWithTerms(int cityCode, String districtName) {
        return regionService.getDistrictWithTerms(cityCode, districtName);
    }

    @Override
    public List<CandidacyKeyword> getCandidacyKeywordsWithCandidacyId(int candidacyId) {
        return candidacyService.getCandidacyKeywordsWithCandidacyId(candidacyId);
    }

    @Override
    public List<CandidacyPledge> getCandidacyPledgesWithCandidacyId(int candidacyId) {
        return candidacyService.getCandidacyPledgesWithCandidacyId(candidacyId);
    }

    @Override
    public void resetKeywordsWithCandidacyId(int candidacyId, List<String> keywords) {
        candidacyService.resetKeywordsWithCandidacyId(candidacyId, keywords);
    }

    @Override
    public void resetCandidacyPledges(int candidacyId, List<CandidacyPledge> pledges) {
        candidacyService.resetCandidacyPledges(candidacyId, pledges);
    }

    @Override
    public void updateCandidacyStatusWithCandidacyId(CandidacyMember candidacyMember) {
        candidacyService.updateCandidacyStatusWithCandidacyId(candidacyMember);
    }


}
