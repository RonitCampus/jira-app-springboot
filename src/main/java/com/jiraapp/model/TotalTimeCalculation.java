package com.jiraapp.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Infocepts India in 2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TotalTimeCalculation
{
    private String timeoriginalestimate;
    private String timeestimate;
    private String timespent;

    public TotalTimeCalculation ()
    {
    }

    public TotalTimeCalculation (final String timeoriginalestimate, final String timeestimate, final String timespent)
    {
        this.timeoriginalestimate = timeoriginalestimate;
        this.timeestimate = timeestimate;
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

    public String getTimeestimate ()
    {
        return timeestimate;
    }

    public void setTimeestimate (final String timeestimate)
    {
        this.timeestimate = timeestimate;
    }

    public String getTimespent ()
    {
        return timespent;
    }

    public void setTimespent (final String timespent)
    {
        this.timespent = timespent;
    }
}
