package com.ketchup.moblima.admin;

import java.util.Properties;

public class Settings {
    public static final String SETTINGS_FILE_PATH = "src/settings.ini";

    private static Settings instance = null;

    private Properties entries;

    private Settings() {
        entries = new Properties();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public String getEntry(String entryName) {
        return entries.getProperty(entryName);
    }

    public void load() {
        try {
            entries.load(new java.io.FileInputStream(SETTINGS_FILE_PATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void save() {
        try {
            entries.store(new java.io.FileOutputStream(SETTINGS_FILE_PATH), null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
