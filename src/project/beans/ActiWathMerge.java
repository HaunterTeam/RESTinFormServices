/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.beans;

import project.getweather.Weather;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import project.models.Phrase;

/**
 *
 * @author luca
 */
@XmlRootElement(name="weatherplan")
@XmlAccessorType(XmlAccessType.PROPERTY)
    
public class ActiWathMerge {
    
    Phrase activityplan;
    Weather weather;

    public ActiWathMerge(Phrase p, Weather w) {
        this.activityplan = p;
        this.weather = w;
    }

    public ActiWathMerge() {
    }

    public Phrase getActivityplan() {
        return activityplan;
    }

    public void setActivityplan(Phrase activityplan) {
        this.activityplan = activityplan;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    
    
    
    
}
