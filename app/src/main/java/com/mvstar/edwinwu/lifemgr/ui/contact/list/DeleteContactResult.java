package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import com.google.auto.value.AutoValue;

/**
 * Created by edwinwu on 2018/2/12.
 */

@AutoValue
public abstract class DeleteContactResult {
    abstract boolean status();
    abstract String messageCode();
    abstract String message();

    public static DeleteContactResult create(boolean status, String messageCode, String message) {
        return new AutoValue_DeleteContactResult(status, messageCode, message);
    }
}