package com.mvstar.edwinwu.lifemgr.utilities;

import com.google.auto.value.AutoValue;

/**
 * Created by edwinwu on 2018/3/6.
 */

@AutoValue
public abstract class ViewModelActionResult {
    public abstract boolean status();
    public abstract String messageCode();
    public abstract String message();

    public static ViewModelActionResult create(boolean status, String messageCode, String message) {
        return new AutoValue_ViewModelActionResult(status, messageCode, message);
    }
}