package com.mvstar.edwinwu.lifemgr.utilities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mvstar.edwinwu.lifemgr.R;

/**
 * Created by edwinwu on 2018/3/5.
 */

public class InformActionResult {

    public static void OKBySnackBar(View parent, final String message) {
        Snackbar mySnackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    public static void ErrorBySnackBar(View parent, final String messageTitle, final String message) {
        Snackbar mySnackbar = Snackbar.make(parent, messageTitle + "\r\n" + message
                , Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }
}
