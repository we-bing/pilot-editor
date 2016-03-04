package com.github.webing.pilot.model;

/**
 * Created by kd4 on 2016. 3. 1..
 */
public class CandidacyMember {
    private int candidacy_id;
    private String name;
    private String birth;
    private String party;
    private int district_code;
    private String district_name;
    private String candidacy_status;

    public int getCandidacy_id() {
        return candidacy_id;
    }

    public void setCandidacy_id(int candidacy_id) {
        this.candidacy_id = candidacy_id;
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

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(int district_code) {
        this.district_code = district_code;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getCandidacy_status() {
        return candidacy_status;
    }

    public void setCandidacy_status(String candidacy_status) {
        this.candidacy_status = candidacy_status;
    }
}
