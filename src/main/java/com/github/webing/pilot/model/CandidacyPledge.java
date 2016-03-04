package com.github.webing.pilot.model;

/**
 * Created by kd4 on 2016. 3. 4..
 */
public class CandidacyPledge {
    private int candidacy_id;
    private int pledge_id;
    private String pledge_title;
    private String pledge_description;

    public int getCandidacy_id() {
        return candidacy_id;
    }

    public void setCandidacy_id(int candidacy_id) {
        this.candidacy_id = candidacy_id;
    }

    public int getPledge_id() {
        return pledge_id;
    }

    public void setPledge_id(int pledge_id) {
        this.pledge_id = pledge_id;
    }

    public String getPledge_title() {
        return pledge_title;
    }

    public void setPledge_title(String pledge_title) {
        this.pledge_title = pledge_title;
    }

    public String getPledge_description() {
        return pledge_description;
    }

    public void setPledge_description(String pledge_description) {
        this.pledge_description = pledge_description;
    }
}
