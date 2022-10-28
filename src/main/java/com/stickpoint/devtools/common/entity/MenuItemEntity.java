package com.stickpoint.devtools.common.entity;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import java.util.Objects;

/**
 * description: MenuItemEntity
 *
 * @ClassName : MenuItemEntity
 * @Date 2022/10/28 13:19
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.common.entity
 */
public class MenuItemEntity extends AnchorPane {

    private Region icon;

    private Label name;

    private Integer menuId;

    public MenuItemEntity() {
    }

    public MenuItemEntity(MenuItemEntityBuilder menuItemEntityBuilder) {
            this.menuId = menuItemEntityBuilder.getId();
            this.name = menuItemEntityBuilder.getName();
            this.icon = menuItemEntityBuilder.getIcon();
    }

    public MenuItemEntity(Region icon, Label name, Integer menuId) {
        this.icon = icon;
        this.name = name;
        this.menuId = menuId;
    }

    public Region getIcon() {
        return icon;
    }

    public MenuItemEntity setIcon(Region icon) {
        this.icon = icon;
        return this;
    }

    public Label getName() {
        return name;
    }

    public MenuItemEntity setName(Label name) {
        this.name = name;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public MenuItemEntity setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    @Override
    public String toString(){
        if (Objects.nonNull(name.getText())&&Objects.nonNull(menuId)) {
            return name.getText();
        }
        return "null";
    }

    @Override
    public int hashCode() {
        return Objects.requireNonNull(name.getText()).hashCode()
                & Objects.requireNonNull(menuId).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.nonNull(obj)&&obj.getClass()==this.getClass()) {
            MenuItemEntity entity = (MenuItemEntity) obj;
            return entity.hashCode() == this.hashCode();
        }
        return false;
    }

    /**
     * 构建内部类 链式builder构建者模式
     */
    private static final class MenuItemEntityBuilder {
        private Region icon;

        private Label name;

        private Integer id;

        public Region getIcon() {
            return icon;
        }

        public MenuItemEntityBuilder setIcon(Region icon) {
            this.icon = icon;
            return this;
        }

        public Label getName() {
            return name;
        }

        public MenuItemEntityBuilder setName(Label name) {
            this.name = name;
            return this;
        }

        public Integer getId() {
            return id;
        }

        public MenuItemEntityBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        /**
         * 构建一个Album对象
         * @return 返回一个专辑对象
         */
        public MenuItemEntity build(){
            return new MenuItemEntity(this);
        }
    }
}
