package com.github.webing.pilot.service;

import com.github.webing.pilot.model.CandidacyKeyword;
import com.github.webing.pilot.model.CandidacyMember;
import com.github.webing.pilot.model.CandidacyPledge;
import com.github.webing.pilot.repository.CandidacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */
@Service
public class CandidacyServiceImpl implements CandidacyService {

    @Autowired
    CandidacyRepository candidacyRepository;

    @Override
    public List<CandidacyMember> getAllCandidacyMembers() {
        return candidacyRepository.findAll();
    }

    @Override
    public List<CandidacyMember> getCandidacyMemberByName(String name) {
        return candidacyRepository.findByName(name);
    }

    @Override
    public CandidacyMember getCandidacyMemberByCandidacyId(int candidacyId) {
        return candidacyRepository.findByCandidacyId(candidacyId);
    }

    @Override
    public List<CandidacyMember> getCandidacyMembersByDistrictCode(int districtCode) {
        return candidacyRepository.findAllByDistrictCode(districtCode);
    }

    @Override
    public List<CandidacyKeyword> getCandidacyKeywordsWithCandidacyId(int candidacyId) {
        return candidacyRepository.findKeywordsByCandidacyId(candidacyId);
    }

    @Override
    public List<CandidacyPledge> getCandidacyPledgesWithCandidacyId(int candidacyId) {
        return candidacyRepository.findPledgesByCandidacyId(candidacyId);
    }

    @Override
    public void resetKeywordsWithCandidacyId(int candidacyId, List<String> keywords) {
        candidacyRepository.deleteKeywordsWithCandidacyId(candidacyId);
        CandidacyKeyword candidacyKeyword = new CandidacyKeyword();
        candidacyKeyword.setCandidacy_id(candidacyId);
        for (String keyword : keywords) {
            candidacyKeyword.setKeyword_name(keyword);
            candidacyRepository.insertKeywordWithCandidacyId(candidacyKeyword);
        }
    }

    @Override
    public void resetCandidacyPledges(int candidacyId, List<CandidacyPledge> pledges) {
        candidacyRepository.deletePledgesWithCandidacyId(candidacyId);

        for (CandidacyPledge pledge : pledges) {
            pledge.setCandidacy_id(candidacyId);
            candidacyRepository.insertPledgeWithCandidacyId(pledge);
        }
    }

    @Override
    public void updateCandidacyStatusWithCandidacyId(CandidacyMember candidacyMember) {
        candidacyRepository.updateStatusByCandidacyId(candidacyMember);
    }
}
