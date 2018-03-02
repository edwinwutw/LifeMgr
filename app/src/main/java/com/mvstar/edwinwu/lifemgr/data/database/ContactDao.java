package com.mvstar.edwinwu.lifemgr.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

/**
 * ContactDao
 */
@Dao
public interface ContactDao {
   @Query("SELECT * FROM contact")
   LiveData<List<ContactEntry>> getContactList();

   @Query("SELECT * FROM contact WHERE id = :id")
   LiveData<ContactEntry> getContact(int id);

   @Insert(onConflict = OnConflictStrategy.FAIL)
   void bulkInsert(ContactEntry... contactEntries);

   @Query("DELETE FROM contact WHERE email = :email")
   void deleteContact(String email);
}