/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import getweather.Weather;
import java.util.ArrayList;
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
public class WeatherPlan {
    
    ArrayList<Phrase> phrase;
    ArrayList<Weather> weather;

    public WeatherPlan(ArrayList<Phrase> phrase, ArrayList<Weather> weather) {
        this.phrase = phrase;
        this.weather = weather;
    }

    public WeatherPlan() {
    }

    public ArrayList<Phrase> getPhrase() {
        return phrase;
    }

    public void setPhrase(ArrayList<Phrase> phrase) {
        this.phrase = phrase;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
    
    
    
}
