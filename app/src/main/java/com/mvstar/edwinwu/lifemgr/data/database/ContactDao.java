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
   /**
    * Gets the contact for a email
    *
    * @return {@link LiveData} with contact list
    */
   @Query("SELECT * FROM contact")
   LiveData<List<ContactEntry>> getContactList();

   /**
    * Inserts a list of {@link ContactEntry} into the contact table. If there is a conflicting id
    * uses the {@link OnConflictStrategy} of fail to inserting. The required uniqueness of these values is defined in the {@link ContactEntry}.
    *
    * @param contactEntries A list of contacts to insert
    */
   @Insert(onConflict = OnConflictStrategy.FAIL)
   void bulkInsert(ContactEntry... contactEntries);

   /**
    * Deletes any weather data older than the given day
    *
    * @param email The contact to delete by email
    */
   @Query("DELETE FROM contact WHERE email = :email")
   void deleteContact(String email);

}