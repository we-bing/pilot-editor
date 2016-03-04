package com.github.webing.pilot.model;

/**
 * Created by kd4 on 2016. 3. 3..
 */
public class CandidacyKeyword {
    private int keyword_id;
    private String keyword_name;
    private int candidacy_id;
    private String keyword_status;

    public int getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(int keyword_id) {
        this.keyword_id = keyword_id;
    }

    public String getKeyword_name() {
        return keyword_name;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }

    public int getCandidacy_id() {
        return candidacy_id;
    }

    public void setCandidacy_id(int candidacy_id) {
        this.candidacy_id = candidacy_id;
    }

    public String getKeyword_status() {
        return keyword_status;
    }

    public void setKeyword_status(String keyword_status) {
        this.keyword_status = keyword_status;
    }
}
