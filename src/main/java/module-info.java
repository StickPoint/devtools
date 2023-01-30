open module com.stickpoint.devtools {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.swing;
    requires rxcontrols;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;
    requires java.sql;
    requires com.google.gson;
    requires okhttps.gson;
    requires okhttps;
    requires org.xerial.sqlitejdbc;
    exports com.stickpoint.devtools;
}