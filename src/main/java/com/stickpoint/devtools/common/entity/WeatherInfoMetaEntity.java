package com.stickpoint.devtools.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class WeatherInfoMetaEntity {

    private Date sunrise;

    private Date sunset;

    private String precipitation;

    private String alert;

}
