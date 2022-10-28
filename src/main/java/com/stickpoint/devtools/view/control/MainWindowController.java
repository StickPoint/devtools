package com.stickpoint.devtools.view.control;

import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.entity.MenuItemEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.view.page.MainWindowApplication;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindowController {

    private static final Logger log = LoggerFactory.getLogger(MainWindowApplication.class);

    @FXML
    public RXAvatar userAvatar;

    public AnchorPane mainWindowHeaderPane;

    public BorderPane mainPane;

    public ScrollPane scrollPane;
    /**
     * 当前时间
     */
    public Label currentTime;
    /**
     * ip地址
     */
    public Label ipAddress;

    private double oldStageX;

    private double oldStageY;

    private double oldScreenX;

    private double oldScreenY;

    @FXML
    public void initialize() {
        // 拖拽header拖动
        mainWindowHeaderPane.setOnMousePressed(mouseEvent -> {
            Stage primaryStage = getCurrentStage();
            oldStageX = primaryStage.getX();
            oldStageY = primaryStage.getY();
            oldScreenX = mouseEvent.getScreenX();
            oldScreenY = mouseEvent.getScreenY();
        });
        mainWindowHeaderPane.setOnMouseDragged(mouseEvent -> {
            Stage primaryStage = getCurrentStage();
            primaryStage.setX(mouseEvent.getScreenX() - oldScreenX + oldStageX);
            primaryStage.setY(mouseEvent.getScreenY() - oldScreenY + oldStageY);
        });
        //FXMLLoader toolCenter = SysCache.PAGE_MAP.get(PageEnums.TOOL_CENTER.getRouterId());
        //Parent root = toolCenter.getRoot();
        //if (Objects.isNull(scrollPane)) {
        //    log.info("1111");
        //}
        //scrollPane.setContent(root);
        FXMLLoader functionCenterLoader = SysCache.PAGE_MAP.get(PageEnums.FUNCTION_CENTER.getRouterId());
        Parent functionCenterRoot = functionCenterLoader.getRoot();
        ObservableMap<String, Object> namespace = functionCenterLoader.getNamespace();
        TreeView<MenuItemEntity> menuTree = (TreeView<MenuItemEntity>) namespace.get(AppEnums.FUNCTION_CENTER_MENU_PANE_FX_ID_INFO.getInfoValue());
        TreeItem<MenuItemEntity> rootNode = new TreeItem<>();
        for (int i = 0; i < 20; i++) {
            MenuItemEntity menuItemEntity = new MenuItemEntity();
            Region region = new Region();
            region.setStyle("-fx-shape: \"M932.497495 179.162496c-14.09195-14.053064-32.82357-21.792329-52.743248-21.792329-160.710238 0-311.412542-132.151809-312.899406-133.471874-13.673417-12.409634-31.441083-19.244296-50.02637-19.244296-18.399045 0-36.132942 6.78452-49.929156 19.102057-0.38067 0.338714-38.763862 34.235732-96.928586 67.585281-52.535517 30.122041-132.652206 66.028832-215.986169 66.028832-41.180911 0-74.683956 33.370015-74.683956 74.386174l0 218.717374c0 198.798718 118.086465 365.582279 394.850883 557.683459 12.514012 8.695033 27.27111 13.291724 42.676985 13.291724 15.409968 0 30.16502-4.596691 42.670845-13.291724C836.263733 816.053947 954.349175 649.269363 954.349175 450.472692L954.349175 231.754295C954.350199 211.892945 946.590468 193.21556 932.497495 179.162496zM877.707634 450.471668c0 120.378671-42.910299 273.204335-360.879164 494.098281C198.92919 723.661677 156.030148 570.842153 156.030148 450.471668L156.030148 233.794767c43.726897-0.24457 89.77056-8.302083 136.888693-23.960715 38.246069-12.709463 77.286224-30.42801 116.034736-52.66343 59.390645-34.08019 100.050694-68.589145 107.872846-75.40641 7.818059 6.817266 48.459688 41.326221 107.850333 75.40641 38.748513 22.23542 77.793784 39.952944 116.055203 52.66343 47.13553 15.658631 93.208868 23.716144 136.973628 23.960715L877.707634 450.471668zM704.678836 553.044812l-26.700105-15.557324c1.426489-9.017374 2.122337-17.401322 2.122337-25.487487 0-8.02886-0.697895-16.410762-2.127454-25.484418l26.699082-15.546068c16.399505-9.538237 22.048155-30.750351 12.588713-47.285956l-26.729781-46.694485c-6.126534-10.698666-17.559934-17.344017-29.839609-17.344017-6.021134 0-11.971659 1.610684-17.21201 4.65809l-26.978445 15.708773c-13.631462-11.027148-28.170596-19.640316-43.346227-25.678846l0-17.568121c0-19.09694-15.439644-34.633798-34.417881-34.633798l-53.472865 0c-18.971074 0-34.404578 15.536858-34.404578 34.633798l0 17.5804c-15.180747 6.03546-29.724998 14.649652-43.360554 25.679869l-26.981515-15.71082c-5.237281-3.046383-11.186783-4.657067-17.204847-4.657067-12.273535 0-23.703865 6.64535-29.829376 17.34197l-26.744107 46.681182c-9.463536 16.525372-3.816932 37.732369 12.583596 47.272653l26.697035 15.545044c-1.426489 9.016351-2.122337 17.398252-2.122337 25.486464 0 8.027837 0.697895 16.409738 2.127454 25.485441l-26.693965 15.554254c-16.411785 9.529027-22.060435 30.730908-12.592806 47.264467l26.742061 46.691415c6.121418 10.698666 17.554818 17.344017 29.837562 17.342993 6.018064 0 11.965519-1.608637 17.201777-4.65502l26.976398-15.708773c13.620206 11.028171 28.16548 19.645432 43.3626 25.690102l0 17.582447c0 19.09694 15.434527 34.634822 34.404578 34.634822l53.472865 0c18.978237 0 34.417881-15.537881 34.417881-34.634822l0-17.571191c15.20019-6.054903 29.740348-14.671141 43.348274-25.689079l26.979468 15.71082c5.233188 3.044336 11.181667 4.653997 17.199731 4.653997 12.280698 0 23.717168-6.644327 29.849842-17.34197l26.727735-46.690392C726.728014 583.789023 721.081411 562.587142 704.678836 553.044812zM486.813876 636.553761c-26.423813-5.405103-50.941206-19.715017-69.035306-40.291658l-4.168949-4.742001-42.586934 24.793686c-0.880043 0.51063-1.831718 0.77055-2.827395 0.77055-2.049682 0-3.888564-1.076518-4.922103-2.882654l-13.372565-23.347754c-1.598404-2.788509-0.657986-6.360871 2.095731-7.963369l42.671868-24.837688-1.963725-5.915733c-4.454452-13.416568-6.712889-26.921139-6.712889-40.136116 0-13.169951 2.263553-26.680663 6.728238-40.155559l1.959631-5.913687-42.680055-24.834618c-2.75167-1.601474-3.690042-5.177929-2.093684-7.969509l13.355169-23.341614c1.019213-1.777483 2.911306-2.88163 4.938476-2.88163 0.991584 0 1.935072 0.25685 2.805906 0.76441l42.608423 24.813129 4.168949-4.745071c18.075681-20.570501 42.583864-34.883485 69.010747-40.304961l6.132674-1.257643 0-35.929304c0-3.200902 2.555196-5.804193 5.696746-5.804193l26.730805 0c3.140527 0 5.695722 2.603291 5.695722 5.804193l0 35.932374 6.136767 1.255597c26.424836 5.406127 50.942229 19.715017 69.035306 40.292682l4.168949 4.740978 42.606377-24.796756c0.873903-0.509606 1.821485-0.76748 2.816139-0.76748 2.053776 0 3.895727 1.076518 4.928243 2.882654l13.368472 23.342638c1.599428 2.794649 0.659009 6.381337-2.076288 7.982812L629.346103 465.945081l1.962701 5.915733c4.455475 13.430894 6.713912 26.939559 6.713912 40.152489 0 13.202697-2.258437 26.708292-6.713912 40.139186l-1.962701 5.915733 42.683125 24.835641c2.750647 1.601474 3.690042 5.177929 2.095731 7.965415l-13.373589 23.350824c-1.031493 1.803066-2.873444 2.87856-4.926196 2.87856-0.99363 0-1.940189-0.258896-2.815115-0.76748l-42.591027-24.796756-4.168949 4.738931c-18.107403 20.580734-42.62582 34.891671-69.037353 40.294728l-6.136767 1.255597 0 35.932374c0 3.200902-2.555196 5.804193-5.696746 5.804193l-26.729781 0c-3.14155 0-5.696746-2.603291-5.696746-5.804193l0-35.9467L486.813876 636.553761zM586.525855 512c0-41.395806-33.429367-75.072813-74.520227-75.072813-41.089837 0-74.519204 33.67803-74.519204 75.072813 0 41.394782 33.428344 75.071789 74.519204 75.071789C553.081139 587.071789 586.511529 553.395806 586.525855 512zM512.005628 479.235782c17.880229 0 32.426527 14.697747 32.426527 32.764218 0 18.065448-14.547321 32.764218-32.426527 32.764218-17.879206 0-32.425504-14.697747-32.425504-32.764218C479.580125 493.933529 494.126422 479.235782 512.005628 479.235782z\"");
            region.setStyle("-fx-background-color: gray");
            region.setLayoutX(20);
            region.setLayoutY(5);
            region.setPrefWidth(25);
            region.setPrefHeight(30);
            menuItemEntity.setIcon(region);
            menuItemEntity.setMenuId(i);
            Label label = new Label();
            label.setText("系统工具");
            label.setLayoutX(59);
            label.setLayoutY(8);
            label.setFont(new Font(18));
            menuItemEntity.setName(label);
            TreeItem<MenuItemEntity> child = new TreeItem<>();
            menuItemEntity.setPrefWidth(153);
            menuItemEntity.setPrefHeight(40);
            menuItemEntity.getChildren().add(0,region);
            menuItemEntity.getChildren().add(1,label);
            child.setValue(menuItemEntity);
            rootNode.getChildren().add(child);
        }
        menuTree.setRoot(rootNode);
        mainPane.setLeft(functionCenterRoot);
    }

    public void showUserInfoCard(MouseEvent mouseEvent) {

    }

    public void closeUserInfoCard(MouseEvent mouseEvent) {

    }

    /**
     * 获得当前stage
     * @return stage
     */
    private Stage getCurrentStage(){
       Parent rootNode =  SysCache.PAGE_MAP.get(PageEnums.MAIN_WINDOWS.getRouterId()).getRoot();
       return (Stage) rootNode.getScene().getWindow();
    }
}