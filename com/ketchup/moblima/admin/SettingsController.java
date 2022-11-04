package com.ketchup.moblima.admin;

public class SettingsController {

    private SettingsForm settingsForm;
    private Settings settings;

    public SettingsController(SettingsForm settingsForm) {
        this.settingsForm = settingsForm;
        settings = Settings.getInstance();
    }

    public void launch() {
        SettingsForm settingsUI = new SettingsForm(this);
        settingsUI.show();
    }

    public void manageSettings(String command) {
        String[] inputTokens = command.split(" ");
        if (inputTokens.length == 2) {
            if (inputTokens[1].equals("-")) {
                settings.remove(inputTokens[0]);
            } else {
                settings.setProperty(inputTokens[0], inputTokens[1]);
            }
            settings.save();
        } else {
            System.out.println("Invalid input.");
        }
    }

    public void print() {
        settings.print();
    }
}
