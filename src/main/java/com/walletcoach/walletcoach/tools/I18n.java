package com.walletcoach.walletcoach.tools;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Localization
 * @author Michael Le <lemichael@mail.muni.cz>
 */
public class I18n {
    private static ResourceBundle resourceBundle;
    
    private static void loadResourceBundle() {
        if(resourceBundle != null) return;
        resourceBundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
    }
    
    public static String get(String key) {
        loadResourceBundle();
        return resourceBundle.getString(key);
    }
}
