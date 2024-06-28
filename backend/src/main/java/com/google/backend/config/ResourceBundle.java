package com.google.backend.config;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.MissingResourceException;

@Component("resourceBundle")
public class ResourceBundle {

    public static String location = "fa"; //LocaleContextHolder.getLocale();

    public ResourceBundle() {
    }

    public static String getMessageByKey(String key) {
        try {
            java.util.ResourceBundle resourceBundle = java.util.ResourceBundle.getBundle("messages", Locale.forLanguageTag(location));
            return resourceBundle.getString(key);
        } catch (MissingResourceException ex) {
            return ex.getMessage();
        }
    }
}
