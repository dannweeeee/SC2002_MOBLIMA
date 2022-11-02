package com.ketchup.moblima.admin;

public abstract class Form {
    protected boolean exitFlag = false;

    public abstract Object show();

    protected void exit() {
        exitFlag = true;
    }
}
