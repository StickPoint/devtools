package com.stickpoint.devtools.view.control;

import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @BelongsProject: devtools
 * @BelongsPackage: com.stickpoint.devtools.view.control
 * @Author: fntp
 * @CreateTime: 2022-10-29  10:40
 * @Description: TODO
 * @Version: 1.0
 */
public class BottomCenterController {

    private static final IApplicationService applicationService = new ApplicationServiceImpl();

    public Label infoLabel;

    public Label ipAddress;

    @FXML
    public void initialize(){
        IpInfoEntity localIpInfo = applicationService.getLocalIpInfo();
        ipAddress.setText("当前IP：".concat(localIpInfo.getIpv4Address()));
    }
}
