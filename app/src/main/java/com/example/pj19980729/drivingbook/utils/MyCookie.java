package com.example.pj19980729.drivingbook.utils;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.example.pj19980729.drivingbook.application.AppVariables;
import com.example.pj19980729.drivingbook.entity.User;

public class MyCookie {

    void setCookie(String url) {
        String StringCookie = "JSESSIONID=5E4F72FD18642F00EC32984A09EB84AE";
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookies(null);
            cookieManager.flush();
        } else {
            cookieManager.removeSessionCookie();
            CookieSyncManager.getInstance().sync();
        }
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, StringCookie);
    }
}
