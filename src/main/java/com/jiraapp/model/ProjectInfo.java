package com.jiraapp.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@JsonPropertyOrder({"projectid","projectname"})
public class ProjectInfo
{
    private String projectid;
    private String projectname;

    public ProjectInfo (final String projectid, final String projectname)
    {
        this.projectid = projectid;
        this.projectname = projectname;
    }

    public String getProjectid ()
    {
        return projectid;
    }

    public void setProjectid (final String projectid)
    {
        this.projectid = projectid;
    }

    public String getProjectname ()
    {
        return projectname;
    }

    public void setProjectname (final String projectname)
    {
        this.projectname = projectname;
    }
}
