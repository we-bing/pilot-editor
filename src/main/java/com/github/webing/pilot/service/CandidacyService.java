package com.github.webing.pilot.service;

import com.github.webing.pilot.model.CandidacyMember;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */
public interface CandidacyService {
    List<CandidacyMember> getAllCandidacyMembers();

    List<CandidacyMember> getCandidacyMemberByName(String name);

    CandidacyMember getCandidacyMemberByCandidacyId(int candidacyId);

    List<CandidacyMember> getCandidacyMembersByDistrictCode(int districtCode);
}
