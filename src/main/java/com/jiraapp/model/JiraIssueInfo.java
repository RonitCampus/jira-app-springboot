package com.jiraapp.model;

/**
 * Created by matth on 5/15/2017.
 */
public class JiraIssueInfo {

    private String issueType;
    private String issueId;

    public JiraIssueInfo(String issueType, String issueId) {
        this.issueType = issueType;
        this.issueId = issueId;
    }

    public JiraIssueInfo() {
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    @Override
    public String toString() {
        return "JiraIssue{" +
                "issueType='" + issueType + '\'' +
                ", issueId='" + issueId + '\'' +
                '}';
    }
}
