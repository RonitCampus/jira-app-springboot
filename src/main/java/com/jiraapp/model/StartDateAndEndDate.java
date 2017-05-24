package com.jiraapp.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class StartDateAndEndDate
{
    String startdate;
    String enddate;

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
}
