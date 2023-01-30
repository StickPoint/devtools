package com.stickpoint.devtools.common.entity;

import java.util.Date;

/**
 * @author puye(0303)
 * @since 2023/1/11
 */
public class WeatherInfoMetaEntity {

    private Date sunrise;

    private Date sunset;

    private String precipitation;

    private String alert;

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getAlert() {
        return alert;
    }

    @Override
    public String toString() {
        return "WeatherInfoMetaEntity{" +
                "sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", precipitation='" + precipitation + '\'' +
                ", alert='" + alert + '\'' +
                '}';
    }
}
