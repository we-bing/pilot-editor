package com.github.webing.pilot.model;

/**
 * Created by kd4 on 2016. 3. 3..
 */
public class CandidacyKeyword {
    private int keywordId;
    private String keywordName;
    private int candidacyId;
    private String keywodStatus;

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public int getCandidacyId() {
        return candidacyId;
    }

    public void setCandidacyId(int candidacyId) {
        this.candidacyId = candidacyId;
    }

    public String getKeywodStatus() {
        return keywodStatus;
    }

    public void setKeywodStatus(String keywodStatus) {
        this.keywodStatus = keywodStatus;
    }
}
