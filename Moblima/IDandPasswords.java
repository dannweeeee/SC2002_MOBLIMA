package Moblima;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String,String> logininfo = new HashMap<String,String>();

    IDandPasswords(){
        logininfo.put("dexter","ahgui");
        logininfo.put("ian","dimsum");
        logininfo.put("marcus","khoolkid");
        logininfo.put("dann","weeeee");
        logininfo.put("ngiah","nguyen");
    }

    protected HashMap getLoginInfo(){
        return logininfo;
    }

}
