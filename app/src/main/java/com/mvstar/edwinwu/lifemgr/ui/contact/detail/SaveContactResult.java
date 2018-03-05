package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import com.google.auto.value.AutoValue;

/**
 * Created by edwinwu on 2018/2/12.
 */

@AutoValue
public abstract class SaveContactResult {
    abstract boolean status();
    abstract String messageCode();
    abstract String message();

    public static SaveContactResult create(boolean status, String messageCode, String message) {
        return new AutoValue_SaveContactResult(status, messageCode, message);
    }
}