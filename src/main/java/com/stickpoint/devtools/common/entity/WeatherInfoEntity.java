package com.stickpoint.devtools.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author puye(0303)
 * @since 2023/1/11
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
