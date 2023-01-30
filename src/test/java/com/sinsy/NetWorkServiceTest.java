package com.sinsy;

import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/1/11
 */
public class NetWorkServiceTest {

    @Test
    public void test1(){
        IApplicationService applicationService = new ApplicationServiceImpl();
        List<WeatherInfoEntity> infoList = applicationService.getWeatherInfo("杭州市西湖区");
        infoList.forEach(System.out::println);
    }
}
