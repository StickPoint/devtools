package com.stickpoint.devtools.view.control;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.common.enums.DevToolsCodeEnums;
import com.stickpoint.devtools.common.exception.DevToolsException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/1/11
 */
public class SaWeatherController {

    /**
     * 今日天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeather;
    /**
     * 明日天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeatherAfter1;
    /**
     * 后天天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeatherAfter2;
    /**
     * 大后天天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeatherAfter3;
    /**
     * 大大后天天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeatherAfter4;
    /**
     * 大大大后天天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeatherAfter5;
    /**
     * 大大大大后天天气图片
     */
    @SuppressWarnings("public")
    public ImageView todayWeatherAfter6;
    /**
     * 今天
     */
    @SuppressWarnings("public")
    public Label today;
    /**
     * 明天
     */
    @SuppressWarnings("public")
    public Label todayAfter1;
    /**
     * 后天
     */
    @SuppressWarnings("public")
    public Label todayAfter2;
    /**
     * 大后天
     */
    @SuppressWarnings("public")
    public Label todayAfter3;
    /**
     * 大大后天
     */
    @SuppressWarnings("public")
    public Label todayAfter4;
    /**
     * 大大大后天
     */
    @SuppressWarnings("public")
    public Label todayAfter5;
    /**
     * 大大大大后天
     */
    @SuppressWarnings("public")
    public Label todayAfter6;
    /**
     * 今日温度数字详情
     */
    @SuppressWarnings("public")
    public Label todayWeatherNumber;
    /**
     * 今日温度详情：多云之类的
     */
    @SuppressWarnings("public")
    public Label todayWeatherWord;
    /**
     * 今日风级
     */
    @SuppressWarnings("public")
    public Label todayWind;
    /**
     * 今日空气湿度
     */
    @SuppressWarnings("public")
    public Label todayHumidity;
    /**
     * 今日空气质量
     */
    @SuppressWarnings("public")
    public Label todayAirQuality;
    /**
     * 定位位置
     */
    @SuppressWarnings("public")
    public Label address;

    public void initAllData(List<WeatherInfoEntity> weatherInfoList){
        // 检查一下数据长度问题
        if (weatherInfoList.size()< AppEnums.COMMON_NUMBER_SEVEN.getNumberInfo()||weatherInfoList.isEmpty()) {
            throw new DevToolsException(DevToolsCodeEnums.ERROR_DATA_ACCESS);
        }
        // 今日数据
        WeatherInfoEntity todayInfo = weatherInfoList.get(0);
        address.setText(todayInfo.getAddress());
        todayAirQuality.setText("空气质量："+todayInfo.getAqiSeverity().substring(todayInfo.getAqiSeverity().indexOf("量")+1));
        todayWind.setText(todayInfo.getPvdrWindDir().concat(todayInfo.getPvdrWindSpd()));
        todayWeatherWord.setText(todayInfo.getCap());
        todayHumidity.setText("空气湿度："+todayInfo.getRh()+"%");
        todayWeatherNumber.setText(String.valueOf(todayInfo.getTemp()).concat("℃"));
        today.setText(String.valueOf(todayInfo.getDayOfMonth()).concat("日"));
        todayWeather.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayInfo.getCap()).concat(".png")))));
        // 未来七天数据
        WeatherInfoEntity todayAfter1Info = weatherInfoList.get(1);
        todayAfter1.setText(String.valueOf(todayAfter1Info.getDayOfMonth()).concat("日"));
        todayWeatherAfter1.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayAfter1Info.getCap()).concat(".png")))));
        WeatherInfoEntity todayAfter2Info = weatherInfoList.get(2);
        todayAfter2.setText(String.valueOf(todayAfter2Info.getDayOfMonth()).concat("日"));
        todayWeatherAfter2.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayAfter2Info.getCap()).concat(".png")))));
        WeatherInfoEntity todayAfter3Info = weatherInfoList.get(3);
        todayAfter3.setText(String.valueOf(todayAfter3Info.getDayOfMonth()).concat("日"));
        todayWeatherAfter3.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayAfter3Info.getCap()).concat(".png")))));
        WeatherInfoEntity todayAfter4Info = weatherInfoList.get(4);
        todayAfter4.setText(String.valueOf(todayAfter4Info.getDayOfMonth()).concat("日"));
        todayWeatherAfter4.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayAfter4Info.getCap()).concat(".png")))));
        WeatherInfoEntity todayAfter5Info = weatherInfoList.get(5);
        todayAfter5.setText(String.valueOf(todayAfter5Info.getDayOfMonth()).concat("日"));
        todayWeatherAfter5.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayAfter5Info.getCap()).concat(".png")))));
        WeatherInfoEntity todayAfter6Info = weatherInfoList.get(6);
        todayAfter6.setText(String.valueOf(todayAfter6Info.getDayOfMonth()).concat("日"));
        todayWeatherAfter6.setImage(new Image(String.valueOf(SaWeatherController.class.getResource("/img/".concat(todayAfter6Info.getCap()).concat(".png")))));
    }
}
