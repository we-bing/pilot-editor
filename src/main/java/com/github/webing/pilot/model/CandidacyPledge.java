package com.github.webing.pilot.model;

/**
 * Created by kd4 on 2016. 3. 4..
 */
public class CandidacyPledge {
    private int candidacyId;
    private int pledgeId;
    private String pledgeTitle;
    private String pledgeDescription;

    public int getCandidacyId() {
        return candidacyId;
    }

    public void setCandidacyId(int candidacyId) {
        this.candidacyId = candidacyId;
    }

    public int getPledgeId() {
        return pledgeId;
    }

    public void setPledgeId(int pledgeId) {
        this.pledgeId = pledgeId;
    }

    public String getPledgeTitle() {
        return pledgeTitle;
    }

    public void setPledgeTitle(String pledgeTitle) {
        this.pledgeTitle = pledgeTitle;
    }

    public String getPledgeDescription() {
        return pledgeDescription;
    }

    public void setPledgeDescription(String pledgeDescription) {
        this.pledgeDescription = pledgeDescription;
    }
}
