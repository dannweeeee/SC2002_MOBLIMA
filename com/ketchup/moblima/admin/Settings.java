package com.ketchup.moblima.admin;

import java.util.Properties;

public class Settings extends Properties {
    public static final String SETTINGS_FILE_PATH = "com/ketchup/moblima/admin/settings.ini";

    private static Settings instance = null;

    private Settings() {
        super();
        load();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public void load() {
        try {
            super.load(new java.io.FileInputStream(SETTINGS_FILE_PATH));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void save() {
        try {
            super.store(new java.io.FileOutputStream(SETTINGS_FILE_PATH), null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        System.out.println("Property names          Property values");
        for (String key : stringPropertyNames()) {
            System.out.printf("%-24s%-24s\n",key, getProperty(key));
        }
    }
}
