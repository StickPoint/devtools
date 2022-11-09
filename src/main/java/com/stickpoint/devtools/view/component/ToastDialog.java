package com.stickpoint.devtools.view.component;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.view.control.BottomCenterController;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * description: ToastController
 *
 * @ClassName : ToastController
 * @Date 2022/11/9 12:10
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class ToastDialog {

    private static final Logger log = LoggerFactory.getLogger(ToastDialog.class);

    /**
     * 展示吐司
     * @param toastType 吐司类型
     * @param control 控件
     * @param content 内容
     */
    public void showToast(int toastType, Control control, String content){
        Stage dialog = new Stage();
        dialog.initOwner(control.getScene().getWindow());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.TRANSPARENT);
        double dialogX = dialog.getOwner().getX();
        double dialogY = dialog.getOwner().getY();
        double dialogW = dialog.getOwner().getWidth();
        double dialogH = dialog.getOwner().getHeight();
        double posX = dialogX + dialogW/2.3;
        double posY = dialogY + dialogH/6 *5;
        dialog.setX(posX);
        dialog.setY(posY);
        FXMLLoader loader = SysCache.PAGE_MAP.get(PageEnums.COMPONENT_TOAST.getRouterId());
        Parent rootNode = loader.getRoot();
        Label  textToast = (Label) loader.getNamespace().get("textToast");
        Region icon = (Region) loader.getNamespace().get("icon");
        textToast.setText(content);
        Pane containerToast = (Pane) loader.getNamespace().get("containerToast");
            switch (toastType) {
                case 0 -> {
                    containerToast.setStyle("-fx-background-color: #009933;-fx-border-color:#009933;-fx-border-radius: 8;-fx-background-radius: 8;");
                    icon.setStyle("-fx-background-color: #ffffff;-fx-shape: 'M878.08 731.274667a32 32 0 0 1-54.88-32.938667A360.789333 360.789333 0 0 0 874.666667 512c0-200.298667-162.368-362.666667-362.666667-362.666667S149.333333 311.701333 149.333333 512s162.368 362.666667 362.666667 362.666667a360.789333 360.789333 0 0 0 186.314667-51.445334 32 32 0 0 1 32.928 54.88A424.778667 424.778667 0 0 1 512 938.666667C276.362667 938.666667 85.333333 747.637333 85.333333 512S276.362667 85.333333 512 85.333333s426.666667 191.029333 426.666667 426.666667c0 78.293333-21.152 153.568-60.586667 219.274667zM374.581333 489.450667l84.341334 83.989333 190.432-190.72a32 32 0 0 1 45.290666 45.226667l-213.013333 213.333333a32 32 0 0 1-45.226667 0.064l-106.986666-106.549333a32 32 0 1 1 45.162666-45.344z'");
                    textToast.setStyle("-fx-text-fill:#ffffff");
                }
                case 1 -> {
                    containerToast.setStyle("-fx-background-color: #fae813;-fx-border-color:#fae813;-fx-border-radius: 8;-fx-background-radius: 8;");
                    icon.setStyle("-fx-background-color: #575757;-fx-shape: 'M511.994 64C264.592 64 64 264.574 64 511.994 64 759.426 264.592 960 511.994 960 759.42 960 960 759.426 960 511.994 960 264.56 759.42 64 512.006 64h-0.012zM511 895.987c-212.07 0-384-171.942-384-384C127 299.903 298.93 128 510.987 128 723.071 128 895 299.916 895 512c0 212.07-171.93 384-384.013 384l0.013-0.013zM511.323 228c-25.166 0-45.568 20.402-45.568 45.568 0 0.574 0.01 1.148 0.033 1.722l11.332 299.804c0.695 18.39 15.806 32.942 34.21 32.942 18.405 0 33.516-14.551 34.21-32.942l11.321-299.804c0.95-25.15-18.668-46.308-43.818-47.258a45.57 45.57 0 0 0-1.72-0.032z m-0.734 440.016c-35.426 0-63.589 28.252-63.589 63.583 0 34.56 26.537 64.401 63.59 64.401 35.392 0 64.41-28.268 64.41-64.401C575 696.284 545.982 668 510.59 668v0.016z'");
                    textToast.setStyle("-fx-text-fill: #575757");
                }
                case 2 -> {
                    containerToast.setStyle("-fx-background-color: #f00000;-fx-border-color:#f00000;-fx-border-radius: 8;-fx-background-radius: 8;");
                    icon.setStyle("-fx-background-color: #ffffff;-fx-shape: 'M336.549959 1022.139145C331.959614 1022.139145 327.263338 1021.256386 322.778924 1019.349628 126.700579 936.052524 0.007062 744.211421 0.007062 530.513214 0.007062 238.002317 237.116028 0.010593 528.567614 0.010593 820.0192 0.010593 1057.128166 238.002317 1057.128166 530.513214 1057.128166 741.492524 936.437407 933.192386 749.645683 1018.925903 731.99051 1026.976662 710.945545 1019.279007 702.859476 1001.553214 694.702786 983.827421 702.471062 962.853076 720.196855 954.731697 881.988855 880.474041 986.507476 713.950455 986.507476 530.513214 986.507476 276.949628 781.07189 70.631283 528.567614 70.631283 276.063338 70.631283 70.627752 276.949628 70.627752 530.513214 70.627752 715.821903 180.442924 882.168938 350.356303 954.307972 368.329269 961.935007 376.697821 982.69749 369.070786 1000.635145 363.35051 1014.053076 350.285683 1022.139145 336.549959 1022.139145M743.759448 778.518952C734.72 778.518952 725.680552 775.058538 718.795034 768.173021L285.431172 334.844469C271.624828 321.038124 271.624828 298.686676 285.431172 284.915641 299.237517 271.109297 321.553655 271.109297 335.36 284.915641L768.723862 718.244193C782.530207 732.050538 782.530207 754.366676 768.723862 768.173021 761.838345 775.058538 752.798897 778.518952 743.759448 778.518952M309.276248 779.655945C300.2368 779.655945 291.197352 776.195531 284.311834 769.310014 270.50549 755.503669 270.50549 733.187531 284.311834 719.381186L718.805628 284.887393C732.576662 271.116359 754.92811 271.116359 768.734455 284.887393 782.50549 298.693738 782.50549 321.045186 768.734455 334.816221L334.240662 769.310014C327.355145 776.195531 318.315697 779.655945 309.276248 779.655945'");
                    textToast.setStyle("-fx-text-fill:#ffffff");
                }
                default -> containerToast.setStyle("-fx-background-color: tranparent;-fx-border-color:tranparent;-fx-border-radius: 8;-fx-background-radius: 8;");
            }
        if (Objects.isNull(rootNode.getScene())){
            Scene rootScene = new Scene(rootNode);
            rootScene.setFill(Color.TRANSPARENT);
            dialog.setScene(rootScene);
            log.info("scene不存在~");
        }else {
            dialog.setScene(rootNode.getScene());
            log.info("scene已经存在~");
        }
        dialog.show();
        new Timeline(new KeyFrame(Duration.millis(1500), ae -> dialog.close())).play();
    }
}
