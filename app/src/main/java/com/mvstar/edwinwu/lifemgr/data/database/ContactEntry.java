package com.mvstar.edwinwu.lifemgr.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;


/**
 * Defines the schema of a table in {@link Room} for a single contact. Indexes
 * allow for fast lookup for the column.
 */
@Entity(tableName = "contact", indices = {@Index(value = {"email"}, unique = true)})
public class ContactEntry {

    @PrimaryKey
    @NonNull
    private String email;
    private String nickName;
    private String mobileNumber;
    private String info;

    public ContactEntry(String email, String nickName, String mobileNumber, String info) {
        this.email = email;
        this.nickName = nickName;
        this.mobileNumber = mobileNumber;
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getInfo() {
        return info;
    }
}
