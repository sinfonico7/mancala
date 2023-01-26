package org.bolcom.app.domain.enums;

public enum MatchStatus {

    RUNNING("RUNNING"),
    FINISHED("FINISHED");
    private String status;

    MatchStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
