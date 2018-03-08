package com.mvstar.edwinwu.lifemgr.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.mvstar.edwinwu.lifemgr.data.database.ContactDao;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;
import com.mvstar.edwinwu.lifemgr.data.network.ContactNetworkDataSource;

import java.util.List;

/**
 * Handles data operations for contacts. Acts as a mediator between {@link ContactNetworkDataSource}
 * and {@link ContactDao}
 */
public class ContactRepository {
    private static final String LOG_TAG = ContactRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static ContactRepository sInstance;
    private final ContactDao mContactDao;
    private final ContactNetworkDataSource mContactNetworkDataSource;

    private ContactRepository(ContactDao contactDao,
                              ContactNetworkDataSource contactNetworkDataSource) {
        mContactDao = contactDao;
        mContactNetworkDataSource = contactNetworkDataSource;
    }

    public synchronized static ContactRepository getInstance(
            ContactDao contactDao, ContactNetworkDataSource contactNetworkDataSource) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ContactRepository(contactDao, contactNetworkDataSource);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<ContactEntry>> getContactList() {
        return mContactDao.getContactList();
    }

    public LiveData<ContactEntry> getContact(String email) {
        return mContactDao.getContact(email);
    }

    public ContactEntry getContactImmediately(String email) throws Exception {
        return mContactDao.getContactImmediately(email);
    }

    public void insertContact(ContactEntry contactEntry) throws Exception {
        mContactDao.insertContact(contactEntry);
    }

    public void updateContact(ContactEntry contactEntry) throws Exception {
        mContactDao.updateContacts(contactEntry);
    }

    public void deleteContact(String email) throws Exception {
        mContactDao.deleteContact(email);
    }
}