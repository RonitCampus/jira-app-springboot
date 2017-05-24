package com.jiraapp.service;

import com.jiraapp.model.JiraIssue;
import com.jiraapp.model.JiraIssueInfo;
import com.jiraapp.model.ProjectInfo;
import com.jiraapp.repository.JiraGenerateExcel;
import com.jiraapp.repository.JiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@Service
public class JiraService {

    @Autowired
    private JiraRepository jiraRepository;

    @Autowired
    private JiraGenerateExcel jiraGenerateExcel;

    public List<JiraIssueInfo> getJiraIssuesForProject(String projectId) throws SQLException {
        return this.jiraRepository.getJiraIssuesForProject(projectId);
    }

    public JiraIssue getIssueDetails(String issueId){
        return this.jiraRepository.getIssueDetails(issueId);
    }

    public List<ProjectInfo> getAllProjets(){
        return this.jiraRepository.getAllProjets();
    }

    public String GenerateExcel(String projectId) throws IOException
    {
        return this.jiraGenerateExcel.GenerateExcel(projectId);
    }
}
