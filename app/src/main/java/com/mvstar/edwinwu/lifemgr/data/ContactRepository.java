package com.mvstar.edwinwu.lifemgr.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.mvstar.edwinwu.lifemgr.AppExecutors;
import com.mvstar.edwinwu.lifemgr.data.database.ContactDao;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;
import com.mvstar.edwinwu.lifemgr.data.network.ContactNetworkDataSource;

import java.util.Date;
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
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private ContactRepository(ContactDao contactDao,
                              ContactNetworkDataSource contactNetworkDataSource,
                              AppExecutors executors) {
        mContactDao = contactDao;
        mContactNetworkDataSource = contactNetworkDataSource;
        mExecutors = executors;

//Todo
//        // As long as the repository exists, observe the network LiveData.
//        // If that LiveData changes, update the database.
//        LiveData<ContactEntry[]> networkData = mContactNetworkDataSource.getCurrentWeatherForecasts();
//        networkData.observeForever(newForecastsFromNetwork -> {
//            mExecutors.diskIO().execute(() -> {
//                // Deletes old historical data
//                deleteOldData();
//                Log.d(LOG_TAG, "Old weather deleted");
//                // Insert our new weather data into Sunshine's database
//                mContactDao.bulkInsert(newForecastsFromNetwork);
//                Log.d(LOG_TAG, "New values inserted");
//            });
//        });
    }

    public synchronized static ContactRepository getInstance(
            ContactDao contactDao, ContactNetworkDataSource contactNetworkDataSource,
            AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ContactRepository(contactDao, contactNetworkDataSource,
                        executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    private synchronized void initializeData() {

        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;
//Todo
//        // This method call triggers Sunshine to create its task to synchronize weather data
//        // periodically.
//        mContactNetworkDataSource.scheduleRecurringFetchWeatherSync();
//
//        mExecutors.diskIO().execute(() -> {
//            if (isFetchNeeded()) {
//                startFetchContactService();
//            }
//        });
    }

    public LiveData<List<ContactEntry>> getContactList() {
        initializeData();
        return mContactDao.getContactList();
    }

    private void deleteOldData() {
        mContactDao.deleteContact("email");
    }

//Todo
//    private boolean isFetchNeeded() {
//        Date today = SunshineDateUtils.getNormalizedUtcDateForToday();
//        int count = mContactDao.countAllFutureWeather(today);
//        return (count < ContactNetworkDataSource.NUM_DAYS);
//    }

//Todo
//    private void startFetchContactService() {
//        mContactNetworkDataSource.startFetchWeatherService();
//    }
}