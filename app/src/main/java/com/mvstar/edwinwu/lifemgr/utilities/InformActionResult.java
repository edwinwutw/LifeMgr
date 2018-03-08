package com.mvstar.edwinwu.lifemgr.utilities;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by edwinwu on 2018/3/5.
 */

public class InformActionResult {

    public static void BySnackBar(View parent, final String messageTitle) {
        Snackbar mySnackbar = Snackbar.make(parent, messageTitle, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    public static void BySnackBar(View parent, final String messageTitle, final String messageCode,
                                  final String message) {
        Snackbar mySnackbar = Snackbar.make(parent, messageTitle + ":   " +
                        "Message Code[" + messageCode + "], " +
                        "Message[" + message + "]"
                , Snackbar.LENGTH_INDEFINITE);
        mySnackbar.show();
    }

    public static void BySnackBar(View parent, final String messageTitle, final String description) {
        Snackbar mySnackbar = Snackbar.make(parent, messageTitle + ":   " + description
                , Snackbar.LENGTH_INDEFINITE);
        mySnackbar.show();
    }

}
