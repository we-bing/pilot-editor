package com.github.webing.pilot.repository;

import com.github.webing.pilot.model.CandidacyKeyword;
import com.github.webing.pilot.model.CandidacyMember;
import com.github.webing.pilot.model.CandidacyPledge;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kd4 on 2016. 3. 1..
 */

@Repository
public interface CandidacyRepository {
    List<CandidacyMember> findAll();

    List<CandidacyMember> findByName(String name);

    CandidacyMember findByCandidacyId(int candidacyId);

    List<CandidacyMember> findAllByDistrictCode(int districtCode);

    List<CandidacyKeyword> findKeywordsByCandidacyId(int candidacyId);

    List<CandidacyPledge> findPledgesByCandidacyId(int candidacyId);

    void deleteKeywordsWithCandidacyId(int candidacyId);

    void insertKeywordWithCandidacyId(CandidacyKeyword candidacyId);

    void deletePledgesWithCandidacyId(int candidacyId);

    void insertPledgeWithCandidacyId(CandidacyPledge pledge);

    void updateStatusByCandidacyId(CandidacyMember candidacyMember);
}
