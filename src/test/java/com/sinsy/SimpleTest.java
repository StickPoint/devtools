package com.sinsy;
import com.stickpoint.devtools.common.entity.SystemInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author puye(0303)
 * @since 2023/2/17
 */

public class SimpleTest {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(SimpleTest.class);

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

    @Test
    public void testTransform(){
        try {
            String dataTime = "2023-04-14T00:00:00+08:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Date date = format.parse(dataTime);
            LocalDateTime todayTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            int dayOfMonth = todayTime.getDayOfMonth();
            log.info(dayOfMonth+"");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
