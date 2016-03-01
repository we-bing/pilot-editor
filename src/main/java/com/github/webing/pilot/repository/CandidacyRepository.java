package com.github.webing.pilot.repository;

import com.github.webing.pilot.model.CandidacyMember;
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
}
