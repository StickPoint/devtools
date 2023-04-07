package com.sinsy;

import com.stickpoint.devtools.common.entity.SystemInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * @author puye(0303)
 * @since 2023/2/17
 */
@Slf4j
public class SimpleTest {

    @Test
    public void testSimple(){
        IApplicationService applicationService = new ApplicationServiceImpl();
        try {
            SystemInfoEntity systemInfoEntity = applicationService.checkSystemInfo();
            log.info(String.valueOf(systemInfoEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
