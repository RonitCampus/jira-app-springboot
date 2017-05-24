package com.jiraapp.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JiraIssue
{
    private String id;
    private String project;
    private String creator;
    private String issuetype;
    private String summary;
    private String priority;
    private String created;
    private String timeoriginalestimate;
    private String timeestimate;
    private String timespent;
    private String issuestatus;
    private String actualstartdate;
    private String actualenddate;
    private String estimatedstartdate;
    private String estimatedenddate;
    private String assignedto;
    private String effortoverrun;
    private String scheduleoverrun;

    public String getId ()
    {
        return id;
    }

    public void setId (final String id)
    {
        this.id = id;
    }

    public String getProject ()
    {
        return project;
    }

    public void setProject (final String project)
    {
        this.project = project;
    }

    public String getCreator ()
    {
        return creator;
    }

    public void setCreator (final String creator)
    {
        this.creator = creator;
    }

    public String getIssuetype ()
    {
        return issuetype;
    }

    public void setIssuetype (final String issuetype)
    {
        this.issuetype = issuetype;
    }

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (final String summary)
    {
        this.summary = summary;
    }

    public String getPriority ()
    {
        return priority;
    }

    public void setPriority (final String priority)
    {
        this.priority = priority;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (final String created)
    {
        this.created = created.substring(0,10);
    }

    public String getTimespent ()
    {
        return timespent;
    }

    public void setTimespent (final String timespent)
    {
        this.timespent = timespent;
    }

    public String getTimeoriginalestimate ()
    {
        return timeoriginalestimate;
    }

    public void setTimeoriginalestimate (final String timeoriginalestimate)
    {
        this.timeoriginalestimate = timeoriginalestimate;
    }

    public String getIssuestatus ()
    {
        return issuestatus;
    }

    public void setIssuestatus (final String issuestatus)
    {
        this.issuestatus = issuestatus;
    }

    public String getTimeestimate ()
    {
        return timeestimate;
    }

    public void setTimeestimate (final String timeestimate)
    {
        this.timeestimate = timeestimate;
    }

    public String getActualstartdate ()
    {
        return actualstartdate;
    }

    public void setActualstartdate (final String actualstartdate)
    {
        this.actualstartdate = actualstartdate;
    }

    public String getActualenddate ()
    {
        return actualenddate;
    }

    public void setActualenddate (final String actualenddate)
    {
        this.actualenddate = actualenddate;
    }

    public String getAssignedto ()
    {
        return assignedto;
    }

    public void setAssignedto (final String assignedto)
    {
        this.assignedto = assignedto;
    }

    public String getEstimatedstartdate ()
    {
        return estimatedstartdate;
    }

    public void setEstimatedstartdate (final String estimatedstartdate)
    {
        this.estimatedstartdate = estimatedstartdate;
    }

    public String getEstimatedenddate ()
    {
        return estimatedenddate;
    }

    public void setEstimatedenddate (final String estimatedenddate)
    {
        this.estimatedenddate = estimatedenddate;
    }


    public String getEffortoverrun ()
    {
        int spent = this.timespent==null?0:Integer.parseInt(this.timespent);
        int orignalestimate = this.timeoriginalestimate==null?0:Integer.parseInt(this.timeoriginalestimate);
        int timeremaining = this.timeestimate == null ? 0 : Integer.parseInt(this.timeestimate);

        if ((spent + timeremaining) > orignalestimate)
        {
            return "flag-color-red";
        }
        else{
            return "flag-color-green";
        }
    }

    public String getScheduleoverrun () throws ParseException
    {

        if (null != this.getEstimatedenddate() && null != this.getActualenddate())
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
            Date estimateEndDt = sdf.parse(this.getEstimatedenddate());
            Date actualEndDt = sdf.parse(this.getActualenddate());

            if (actualEndDt.after(estimateEndDt))
            {
                return "flag-color-red";
            }
            else
            {
                return "flag-color-green";
            }
        }

        return "";
    }
}
