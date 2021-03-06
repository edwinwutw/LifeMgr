/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mvstar.edwinwu.lifemgr.data.network;

import android.content.Context;
import android.util.Log;

/**
 * Provides an API for doing all operations with the server data
 */
public class ContactNetworkDataSource {
    private static final String LOG_TAG = ContactNetworkDataSource.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static ContactNetworkDataSource sInstance;
    private final Context mContext;

    private ContactNetworkDataSource(Context context) {
        mContext = context;
    }

    /**
     * Get the singleton for this class
     */
    public static ContactNetworkDataSource getInstance(Context context) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ContactNetworkDataSource(context.getApplicationContext());
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }
}