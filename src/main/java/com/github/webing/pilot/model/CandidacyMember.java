package com.github.webing.pilot.model;

/**
 * Created by kd4 on 2016. 3. 1..
 */
public class CandidacyMember {
    private int candidacyId;
    private String name;
    private String birth;
    private String party;
    private int districtCode;
    private String districtName;
    private String candidacyStatus;

    public int getCandidacyId() {
        return candidacyId;
    }

    public void setCandidacyId(int candidacyId) {
        this.candidacyId = candidacyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public int getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(int districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCandidacyStatus() {
        return candidacyStatus;
    }

    public void setCandidacyStatus(String candidacyStatus) {
        this.candidacyStatus = candidacyStatus;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
}
