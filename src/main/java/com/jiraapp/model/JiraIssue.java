package com.jiraapp.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    private String startdate;
    private String enddate;
    private String assignedto;
    private String statuscolor;

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

    public String getStartdate ()
    {
        return startdate;
    }

    public void setStartdate (final String startdate)
    {
        this.startdate = startdate;
    }

    public String getEnddate ()
    {
        return enddate;
    }

    public void setEnddate (final String enddate)
    {
        this.enddate = enddate;
    }

    public String getAssignedto ()
    {
        return assignedto;
    }

    public void setAssignedto (final String assignedto)
    {
        this.assignedto = assignedto;
    }

    public String getStatuscolor ()
    {
        int spent = this.timespent==null?0:Integer.parseInt(this.timespent);
        int orignalestimate = this.timeoriginalestimate==null?0:Integer.parseInt(this.timeoriginalestimate);

        if(spent > orignalestimate){
            return "flag-color-red";
        }
        else{
            return "flag-color-green";
        }
    }

    @Override
    public String toString ()
    {
        return "JiraIssue{" +
                "id='" + id + '\'' +
                ", project='" + project + '\'' +
                ", creator='" + creator + '\'' +
                ", issuetype='" + issuetype + '\'' +
                ", summary='" + summary + '\'' +
                ", priority='" + priority + '\'' +
                ", created='" + created + '\'' +
                ", timeoriginalestimate='" + timeoriginalestimate + '\'' +
                ", timeestimate='" + timeestimate + '\'' +
                ", timespent='" + timespent + '\'' +
                ", issuestatus='" + issuestatus + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", assignedto='" + assignedto + '\'' +
                '}';
    }
}
