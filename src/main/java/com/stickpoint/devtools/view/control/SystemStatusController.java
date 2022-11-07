package com.stickpoint.devtools.view.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * description: ToolCenterController
 *
 * @ClassName : ToolCenterController
 * @Date 2022/10/27 13:28
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class SystemStatusController {
    /**
     * 分布式文件存储饼图概况一览
     */
    public PieChart systemFileAnalyseChart;
    /**
     * 根节点
     */
    public AnchorPane rootPane;

    @FXML
    public void initialize(){
       initChart();
    }



    private void initChart(){
        ObservableList<Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("JSON文件", 13),
                        new PieChart.Data("压缩文件", 25),
                        new PieChart.Data("jar文件", 10),
                        new PieChart.Data("excel文件", 22),
                        new PieChart.Data("文本文件", 30));
        systemFileAnalyseChart.setData(pieChartData);
        systemFileAnalyseChart.setClockwise(true);
        systemFileAnalyseChart.setTitle("系统文件存储概况：（24小时刷新一次）");
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        for (PieChart.Data data : pieChartData) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
                    e -> {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(data.getPieValue() + "%");
                    });
        }
    }
}
