package com.stickpoint.devtools.common.entity;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.Serial;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

/**
 * description: SmallApplicationEntity
 *
 * @ClassName : SmallApplicationEntity
 * @Date 2022/11/7 10:00
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.common.entity
 */

public class SmallApplicationEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2199449609400160702L;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用路由id
     */
    private String applicationRouterId;
    /**
     * 应用控制器
     */
    private Class<?>  applicationController;
    /**
     * 应用stage
     */
    private Stage applicationStage;
    /**
     * 应用图标
     */
    private ImageView applicationIcon;
    /**
     * 应用软路由
     */
    private URL applicationUrl;

    public static final SmallApplicationEntity.Builder BUILDER = new Builder();

    public SmallApplicationEntity(Builder builder) {
        this.applicationController = builder.getApplicationController();
        this.applicationIcon = builder.getApplicationIcon();
        this.applicationRouterId = builder.getApplicationRouterId();
        this.applicationStage = builder.getApplicationStage();
        this.applicationUrl = builder.getApplicationUrl();
        this.applicationName = builder.getApplicationName();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationRouterId() {
        return applicationRouterId;
    }

    public void setApplicationRouterId(String applicationRouterId) {
        this.applicationRouterId = applicationRouterId;
    }

    public Class<?> getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(Class<?> applicationController) {
        this.applicationController = applicationController;
    }

    public Stage getApplicationStage() {
        return applicationStage;
    }

    public void setApplicationStage(Stage applicationStage) {
        this.applicationStage = applicationStage;
    }

    public ImageView getApplicationIcon() {
        return applicationIcon;
    }

    public void setApplicationIcon(ImageView applicationIcon) {
        this.applicationIcon = applicationIcon;
    }

    public URL getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(URL applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    private static class Builder {
        /**
         * 应用名称
         */
        private String applicationName;
        /**
         * 应用路由id
         */
        private String applicationRouterId;
        /**
         * 应用控制器
         */
        private Class<?>  applicationController;
        /**
         * 应用stage
         */
        private Stage applicationStage;
        /**
         * 应用图标
         */
        private ImageView applicationIcon;
        /**
         * 应用软路由
         */
        private URL applicationUrl;

        public Builder() {

        }

        public String getApplicationName() {
            return applicationName;
        }

        public Builder setApplicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public String getApplicationRouterId() {
            return applicationRouterId;
        }

        public Builder setApplicationRouterId(String applicationRouterId) {
            this.applicationRouterId = applicationRouterId;
            return this;
        }

        public Class<?> getApplicationController() {
            return applicationController;
        }

        public Builder setApplicationController(Class<?> applicationController) {
            this.applicationController = applicationController;
            return this;
        }

        public Stage getApplicationStage() {
            return applicationStage;
        }

        public Builder setApplicationStage(Stage applicationStage) {
            this.applicationStage = applicationStage;
            return this;
        }

        public ImageView getApplicationIcon() {
            return applicationIcon;
        }

        public Builder setApplicationIcon(ImageView applicationIcon) {
            this.applicationIcon = applicationIcon;
            return this;
        }

        public URL getApplicationUrl() {
            return applicationUrl;
        }

        public Builder setApplicationUrl(URL applicationUrl) {
            this.applicationUrl = applicationUrl;
            return this;
        }

        public SmallApplicationEntity build(){
            return new SmallApplicationEntity(this);
        }
    }

    @Override
    public String toString() {
        return "SmallApplicationEntity{" +
                "applicationName='" + applicationName + '\'' +
                ", applicationRouterId='" + applicationRouterId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmallApplicationEntity that = (SmallApplicationEntity) o;
        return applicationName.equals(that.applicationName) && applicationRouterId.equals(that.applicationRouterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationName, applicationRouterId);
    }
}
