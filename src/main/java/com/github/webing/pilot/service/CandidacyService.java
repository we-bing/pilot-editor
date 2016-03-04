package com.github.webing.pilot.service;

import com.github.webing.pilot.model.CandidacyKeyword;
import com.github.webing.pilot.model.CandidacyMember;
import com.github.webing.pilot.model.CandidacyPledge;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */
public interface CandidacyService {
    List<CandidacyMember> getAllCandidacyMembers();

    List<CandidacyMember> getCandidacyMemberByName(String name);

    CandidacyMember getCandidacyMemberByCandidacyId(int candidacyId);

    List<CandidacyMember> getCandidacyMembersByDistrictCode(int districtCode);

    List<CandidacyKeyword> getCandidacyKeywordsWithCandidacyId(int candidacyId);

    List<CandidacyPledge> getCandidacyPledgesWithCandidacyId(int candidacyId);

    void resetKeywordsWithCandidacyId(int candidacyId, List<String> keywords);

    void resetCandidacyPledges(int candidacyId, List<CandidacyPledge> pledges);

    void updateCandidacyStatusWithCandidacyId(CandidacyMember candidacyMember);
}
