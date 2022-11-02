package com.ketchup.moblima.admin;

public interface StaffAccount {
    public abstract void setUsername(String username);
    public abstract void setPassword(String password);
    public abstract boolean validate();
}
