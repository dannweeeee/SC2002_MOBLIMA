package com.ketchup.moblima.admin;

import java.util.Scanner;

public final class SettingsForm extends Form {

    private SettingsController controller;
    
    public SettingsForm(SettingsController controller) {
        super();
        this.controller = controller;
    }
    
    public Object show() {
        while (!exitFlag) {
            System.out.println("Settings");
            System.out.println("========");
            controller.print();
            System.out.println("To edit a setting or add new setting, enter the name of the setting, follow by its value");
            System.out.println("E.g. property_name property_value");
            System.out.println("To delete an entry, enter the name of the setting, followed by a dash (-)");
            System.out.println("E.g. property_name -");
            System.out.println("To exit, enter exit");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("exit")) {
                exit();
            } else {
                controller.manageSettings(input);
            }
        }
        return null;
    }
}
