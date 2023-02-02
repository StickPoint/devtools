package com.stickpoint.devtools.common.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author puye(0303)
 * @since 2023/1/11
 */
public class WeatherInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8209584321083573403L;

    private String city;

    private Date lastUpdateTime;

    private Date date;

    private String weather;

    private int temp;

    private String humidity;

    private String wind;

    private int dayOfMonth;

    private int pm25;

    private int pm10;

    private int low;

    private int high;

    private String airData;

    private String airQuality;

    private long dateLong;

    private int weatherType;

    private int windLevel;

    private String province;

    private WeatherInfoMetaEntity moreData;

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWind() {
        return wind;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public int getPm10() {
        return pm10;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getLow() {
        return low;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getHigh() {
        return high;
    }

    public void setAirData(String airData) {
        this.airData = airData;
    }

    public String getAirData() {
        return airData;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setDateLong(long dateLong) {
        this.dateLong = dateLong;
    }

    public long getDateLong() {
        return dateLong;
    }

    public void setWeatherType(int weatherType) {
        this.weatherType = weatherType;
    }

    public int getWeatherType() {
        return weatherType;
    }

    public void setWindLevel(int windLevel) {
        this.windLevel = windLevel;
    }

    public int getWindLevel() {
        return windLevel;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setMoreData(WeatherInfoMetaEntity moreData) {
        this.moreData = moreData;
    }

    public WeatherInfoMetaEntity getMoreData() {
        return moreData;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String toString() {
        return "WeatherInfoEntity{" +
                "city='" + city + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", date=" + date +
                ", weather='" + weather + '\'' +
                ", temp=" + temp +
                ", humidity='" + humidity + '\'' +
                ", wind='" + wind + '\'' +
                ", dayOfMonth=" + dayOfMonth +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", low=" + low +
                ", high=" + high +
                ", airData='" + airData + '\'' +
                ", airQuality='" + airQuality + '\'' +
                ", dateLong=" + dateLong +
                ", weatherType=" + weatherType +
                ", windLevel=" + windLevel +
                ", province='" + province + '\'' +
                ", moreData=" + moreData +
                '}';
    }
}
