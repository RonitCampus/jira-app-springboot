package com.jiraapp.controller;

import com.jiraapp.model.JiraIssue;
import com.jiraapp.model.JiraIssueInfo;
import com.jiraapp.model.ProjectInfo;
import com.jiraapp.service.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class JiraappController {

    @Autowired
    private JiraService jiraService;

    @GetMapping("/serverIp")
    public ResponseEntity<String> getServerIp() throws UnknownHostException {
        InetAddress ipAddr = InetAddress.getLocalHost();
        return new ResponseEntity<>(ipAddr.getHostAddress(),HttpStatus.OK);
    }

    @GetMapping("/IssuesforProjct/{projectId}")
    public ResponseEntity<List<JiraIssueInfo>> getJiraIssuesForProject(@PathVariable("projectId") String projectId) throws SQLException {
        return new ResponseEntity<>(this.jiraService.getJiraIssuesForProject(projectId), HttpStatus.OK);
    }

    @GetMapping("/GetIssueDetails/{issueId}")
    public ResponseEntity<JiraIssue> getIssueDetails(@PathVariable("issueId") String issueId){
        return new ResponseEntity<JiraIssue>(this.jiraService.getIssueDetails(issueId),HttpStatus.OK);
    }

    @GetMapping("/GetAllProjects")
    public ResponseEntity<List<ProjectInfo>> getAllProjets(){
        return new ResponseEntity<>(this.jiraService.getAllProjets(),HttpStatus.OK);
    }

    @GetMapping("/getAllissueDetailsforProject/{projectId}")
    public ResponseEntity<List<JiraIssue>> getAllissueDetailsforProject(@PathVariable("projectId") String projectId) throws SQLException
    {
        List<JiraIssue> jiraIssues = new ArrayList<>();

        for(JiraIssueInfo jiraIssueInfo : this.jiraService.getJiraIssuesForProject(projectId)){
            JiraIssue jiraIssue = this.jiraService.getIssueDetails(jiraIssueInfo.getIssueId());
            jiraIssue.setIssuetype(jiraIssueInfo.getIssueType());
            jiraIssues.add(jiraIssue);
        }

        return new ResponseEntity<>(jiraIssues,HttpStatus.OK);
    }

    @GetMapping("/downloadProjectInfo/{projectId}")
    public void getExcelforProject(HttpServletRequest request, HttpServletResponse response, @PathVariable("projectId") String projectId) throws IOException
    {

        Path file = Paths.get(jiraService.GenerateExcel(projectId));

        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment; filename="+file.getFileName());

        Files.copy(file, response.getOutputStream());
        response.getOutputStream().flush();

    }
}
