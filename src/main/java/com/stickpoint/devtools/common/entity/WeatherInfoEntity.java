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
    /**
     * 城市
     */
    private int baro;
    private String cap;
    private String capAbbr;
    private String daytime;
    private int dewPt;
    private int feels;
    private int rh;
    private int icon;
    private String symbol;
    private String pvdrIcon;
    private String urlIcon;
    private String wx;
    private String sky;
    private int temp;
    private int uv;
    private String uvDesc;
    private int vis;
    private int windDir;
    private int windSpd;
    private Date created;
    private String pvdrCap;
    private String pvdrWindDir;
    private String pvdrWindSpd;
    private int aqi;
    private String aqiSeverity;
    private int aqLevel;
    private String primaryPollutant;
    private Date aqiValidTime;
    private int cloudCover;
    private Date valid;
    private int dayOfMonth;

    public int getBaro() {
        return baro;
    }

    public void setBaro(int baro) {
        this.baro = baro;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCapAbbr() {
        return capAbbr;
    }

    public void setCapAbbr(String capAbbr) {
        this.capAbbr = capAbbr;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public int getDewPt() {
        return dewPt;
    }

    public void setDewPt(int dewPt) {
        this.dewPt = dewPt;
    }

    public int getFeels() {
        return feels;
    }

    public void setFeels(int feels) {
        this.feels = feels;
    }

    public int getRh() {
        return rh;
    }

    public void setRh(int rh) {
        this.rh = rh;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPvdrIcon() {
        return pvdrIcon;
    }

    public void setPvdrIcon(String pvdrIcon) {
        this.pvdrIcon = pvdrIcon;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public String getUvDesc() {
        return uvDesc;
    }

    public void setUvDesc(String uvDesc) {
        this.uvDesc = uvDesc;
    }

    public int getVis() {
        return vis;
    }

    public void setVis(int vis) {
        this.vis = vis;
    }

    public int getWindDir() {
        return windDir;
    }

    public void setWindDir(int windDir) {
        this.windDir = windDir;
    }

    public int getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(int windSpd) {
        this.windSpd = windSpd;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPvdrCap() {
        return pvdrCap;
    }

    public void setPvdrCap(String pvdrCap) {
        this.pvdrCap = pvdrCap;
    }

    public String getPvdrWindDir() {
        return pvdrWindDir;
    }

    public void setPvdrWindDir(String pvdrWindDir) {
        this.pvdrWindDir = pvdrWindDir;
    }

    public String getPvdrWindSpd() {
        return pvdrWindSpd;
    }

    public void setPvdrWindSpd(String pvdrWindSpd) {
        this.pvdrWindSpd = pvdrWindSpd;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getAqiSeverity() {
        return aqiSeverity;
    }

    public void setAqiSeverity(String aqiSeverity) {
        this.aqiSeverity = aqiSeverity;
    }

    public int getAqLevel() {
        return aqLevel;
    }

    public void setAqLevel(int aqLevel) {
        this.aqLevel = aqLevel;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public Date getAqiValidTime() {
        return aqiValidTime;
    }

    public void setAqiValidTime(Date aqiValidTime) {
        this.aqiValidTime = aqiValidTime;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Date getValid() {
        return valid;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
