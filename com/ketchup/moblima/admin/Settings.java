package com.ketchup.moblima.admin;

import java.util.Properties;

/**
 * This class handles the settings of the admin module.
 * @author Nghia Nguyen
 * @version 1.0
 */
public class Settings extends Properties {
    public static final String SETTINGS_FILE_PATH = "com/ketchup/moblima/admin/settings.ini";

    private static Settings instance = null;

    /**
     * Constructor for Settings.
     */
    private Settings() {
        super();
        load();
    }

    /**
     * Get the instance of the single Settings class.
     * @return the instance of the Settings class.
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    /**
     * Load the settings from the settings file.
     */
    public void load() {
        try {
            super.load(new java.io.FileInputStream(SETTINGS_FILE_PATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save the settings to the settings file.
     */
    public void save() {
        try {
            super.store(new java.io.FileOutputStream(SETTINGS_FILE_PATH), null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print to the screen all settings.
     */
    public void print() {
        System.out.println("Property names          Property values");
        for (String key : stringPropertyNames()) {
            System.out.printf("%-24s%-24s\n",key, getProperty(key));
        }
    }
}
