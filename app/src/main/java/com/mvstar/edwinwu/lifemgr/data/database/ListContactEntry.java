package com.mvstar.edwinwu.lifemgr.data.database;

/**
 * Simplified {@link ContactEntry} which only contains the details needed for the contact list in
 * the {@link com.edwin.androidskelton.ui.list.ForecastAdapter}
 */
public class ListContactEntry {

    private int id;
    private String email;
    private String nickName;
    private int telNumber;
    private String summary;

    public ListContactEntry(int id, String email, String nickName, int telNumber, String summary) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.telNumber = telNumber;
        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public String getEmailAddress() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public int getTelNumber() {
        return telNumber;
    }

    public String getSummary() {
        return summary;
    }
}

