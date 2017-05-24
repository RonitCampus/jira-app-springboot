package com.jiraapp.repository;
import com.jiraapp.model.JiraIssue;
import com.jiraapp.model.JiraIssueInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Infocepts India in 2017.
 */
@Repository
public class JiraGenerateExcel
{
    @Autowired
    private JiraRepository jiraRepository;

    public String GenerateExcel(final String projectId) throws IOException
    {
        try
        {

            String projectName = this.getProjectName(projectId);
            List<JiraIssue> jiraIssues = new ArrayList<>();

            for(JiraIssueInfo jiraIssueInfo : this.jiraRepository.getJiraIssuesForProject(projectId)){
                JiraIssue jiraIssue = this.jiraRepository.getIssueDetails(jiraIssueInfo.getIssueId());
                jiraIssue.setIssuetype(jiraIssueInfo.getIssueType());
                jiraIssues.add(jiraIssue);
            }

            //Create blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            //Create a blank sheet
            XSSFSheet spreadsheet = workbook.createSheet(projectName);
            //Create row object
            XSSFRow row;

            List<String> header = new ArrayList<>();
            header.add("Epic");
            header.add("Story");
            header.add("Task");
            header.add("Summary");
            header.add("Issue Type");
            header.add("Assignee");
            header.add("Assigned To");
            header.add("Estimated Start Date");
            header.add("Estimated End Date");
            header.add("Actual Start Date");
            header.add("Actual End Date");
            header.add("Time Estimated");
            header.add("Time Worked");
            header.add("Time Remaining");
            header.add("Status");

            int rowid = 0;
            int cellid = 0;
            row = spreadsheet.createRow(rowid);

            for (String key : header)
            {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue(key);

            }

            rowid=1;


            String epic="";
            String story="";
            String task="";

            String epicSummary="";
            String storySummary="";
            String taskSummary="";

            for (JiraIssue jiraIssue : jiraIssues){
                cellid=0;

                if(jiraIssue.getIssuetype().equalsIgnoreCase("epic")){
                    if( ! epic.equalsIgnoreCase(jiraIssue.getId())){
                        epic = jiraIssue.getId();
                        epicSummary = jiraIssue.getSummary();

                        story="";
                        task="";
                        storySummary="";
                        taskSummary="";
                    }
                }
                else if(jiraIssue.getIssuetype().equalsIgnoreCase("story")){
                    if(! story.equalsIgnoreCase(jiraIssue.getId())){
                        story = jiraIssue.getId();
                        storySummary = jiraIssue.getSummary();

                        task="";
                        taskSummary="";
                    }
                }
                else if(jiraIssue.getIssuetype().equalsIgnoreCase("subtask")){
                    if(! task.equalsIgnoreCase(jiraIssue.getId())){
                        task=jiraIssue.getId();
                        taskSummary=jiraIssue.getSummary();
                    }
                }

                //System.out.println(String.format("Epic = %s : Story = %s : Task = %s ",epic,story,task));

                //System.out.println(jiraIssue.getTimeoriginalestimate() + "\t"+jiraIssue.getTimespent()+"\t"+ jiraIssue.getTimeestimate());

                row = spreadsheet.createRow(rowid++);

                Cell epicCell = row.createCell(cellid++);
                epicCell.setCellValue(epicSummary);

                Cell storyCell = row.createCell(cellid++);
                storyCell.setCellValue(storySummary);

                Cell taskCell = row.createCell(cellid++);
                taskCell.setCellValue(taskSummary);

                Cell summaryCell = row.createCell(cellid++);
                summaryCell.setCellValue(jiraIssue.getSummary());

                Cell issuetypeCell = row.createCell(cellid++);
                issuetypeCell.setCellValue(jiraIssue.getIssuetype());

                Cell assigneeCell= row.createCell(cellid++);
                assigneeCell.setCellValue(jiraIssue.getCreator());

                Cell assignedtoCell = row.createCell(cellid++);
                assignedtoCell.setCellValue(jiraIssue.getAssignedto());

                Cell estimatedstartdateCell = row.createCell(cellid++);
                estimatedstartdateCell.setCellValue(jiraIssue.getEstimatedstartdate());

                Cell estimatedenddateCell = row.createCell(cellid++);
                estimatedenddateCell.setCellValue(jiraIssue.getEstimatedenddate());

                Cell actualstartdateCell = row.createCell(cellid++);
                actualstartdateCell.setCellValue(jiraIssue.getActualstartdate());

                Cell actualenddateCell = row.createCell(cellid++);
                actualenddateCell.setCellValue(jiraIssue.getActualenddate());

                Cell timeestimatedCell = row.createCell(cellid++);
                Integer timeorignalestimated = jiraIssue.getTimeoriginalestimate()==null?0:Integer.parseInt(jiraIssue.getTimeoriginalestimate());
                timeestimatedCell.setCellValue(timeorignalestimated/3200);

                Cell timeworkedCell = row.createCell(cellid++);
                Integer timespent = jiraIssue.getTimespent()==null?0:Integer.parseInt(jiraIssue.getTimespent());
                timeworkedCell.setCellValue(timespent/3200);

                Cell timeremainingCell = row.createCell(cellid++);
                Integer timeestimated = jiraIssue.getTimeestimate()==null?0:Integer.parseInt(jiraIssue.getTimeestimate());
                timeremainingCell.setCellValue(timeestimated/3200);

                Cell statusCell = row.createCell(cellid++);
                statusCell.setCellValue(jiraIssue.getIssuestatus());
            }


            //Write the workbook in file system

            String fileName = projectName+".xlsx";
            String directory = System.getProperty("java.io.tmpdir");
            String filePath = directory+fileName;
            System.out.println(filePath);
            FileOutputStream out = new FileOutputStream(new File(filePath));
            workbook.write(out);
            out.close();
            System.out.println("Writesheet.xlsx written successfully");

            return filePath;
        }
        catch (Exception ex){
            throw new IOException(ex.getMessage());
        }
    }

    public String getProjectName(final String projectId) throws SQLException
    {
        String sql = String.format("select pname from project where id = %s",projectId);

        Statement statement = dbconfig.getInstance().createStatement();

        ResultSet resultSet = statement.executeQuery(sql);
        if(resultSet.next()){
            return resultSet.getString("pname");
        }
        return "project-data";
    }
}
