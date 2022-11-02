package com.ketchup.moblima.admin;

import java.util.Scanner;

public class StaffAccountFileIO_I implements StaffAccountFileIO {
    private String path;
    public static final String SEPARATOR = "|<<@@sep@@>>|";

    public StaffAccountFileIO_I() {
        path = Settings.getInstance().getEntry("staff_account_file_path");
    }

    public String getPassword(String username) {
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream(path);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(SEPARATOR);
                if (tokens[0].equals(username)) {
                    sc.close();
                    fis.close();
                    return tokens[1];
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
