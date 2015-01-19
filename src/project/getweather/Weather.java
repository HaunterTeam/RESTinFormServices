/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.getweather;

/**
 *
 * @author luca
 */
public class Weather {   
    
    public static final int WEATER_NO_TYPE = 0;
    public static final int WEATHER_GOOD = 1;
    public static final int WEATHER_BAD = 2;
    
    public static final String CLOUD = "Clouds";
    public static final String CLEAR = "Clear";
    
    private String location;
    private double temp;
    private String weather;
    private int type;

    public Weather(String location, double temp, String weather) {
        this.location = location;
        this.temp = temp;
        this.weather = weather;
        this.type = calcType();
    }
    
    public Weather(String location, double temp, String weather,int type) {
        this.type = type;
        this.location = location;
        this.temp = temp;
        this.weather = weather;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int calcType() {
        if(weather.equalsIgnoreCase(CLEAR) || weather.equalsIgnoreCase(CLOUD)){
            return WEATHER_GOOD;
        }else{
            return WEATHER_BAD;
        }
    }
    
    
}
