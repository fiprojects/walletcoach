package com.walletcoach.walletcoach.tools;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class represents a Localization.
 * 
 * @author Michael Le <lemichael@mail.muni.cz>
 */
public class I18n {
    private static ResourceBundle resourceBundle;
    
    /**
     * Loads resource bundle corresponding to system locale.
     */
    private static void loadResourceBundle() {
        if(resourceBundle != null) return;
        resourceBundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
    }
    
    /**
     * Get localized phrase.
     * 
     * @param key Phrase to be localized
     * @return Localized phrase
     */
    public static String get(String key) {
        loadResourceBundle();
        return resourceBundle.getString(key);
    }
}
