package com.mvstar.edwinwu.lifemgr.data;

import com.mvstar.edwinwu.lifemgr.data.database.ContactDao;

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
    private boolean mInitialized = false;

    private ContactRepository(ContactDao weatherDao) {
        mContactDao = weatherDao;
    }
}