package com.mvstar.edwinwu.lifemgr.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * ContactDao
 */
@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    LiveData<List<ContactEntry>> getContactList();

    @Query("SELECT * FROM contact WHERE email = :email")
    LiveData<ContactEntry> getContact(String email);

    @Query("SELECT * FROM contact WHERE email = :email")
    ContactEntry getContactImmediately(String email);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insertContact(ContactEntry contactEntry);

    @Query("DELETE FROM contact WHERE email = :email")
    void deleteContact(String email);

    @Delete
    void deleteContacts(ContactEntry... contacts);

    @Update
    void updateContacts(ContactEntry... contacts);
}