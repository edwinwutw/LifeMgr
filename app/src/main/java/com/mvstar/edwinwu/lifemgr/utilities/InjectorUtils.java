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

package com.mvstar.edwinwu.lifemgr.utilities;

import android.content.Context;

import com.mvstar.edwinwu.lifemgr.AppExecutors;
import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactDatabase;
import com.mvstar.edwinwu.lifemgr.data.network.ContactNetworkDataSource;
import com.mvstar.edwinwu.lifemgr.ui.contact.detail.ContactDetailViewModelFactory;
import com.mvstar.edwinwu.lifemgr.ui.contact.list.ContactListViewModelFactory;

/**
 * Provides static methods to inject the various classes
 */
public class InjectorUtils {

    public static ContactRepository provideRepository(Context context) {
        ContactDatabase database = ContactDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        ContactNetworkDataSource networkDataSource =
                ContactNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return ContactRepository.getInstance(database.contactDao(), networkDataSource, executors);
    }

    public static ContactNetworkDataSource provideNetworkDataSource(Context context) {
        // This call to provide repository is necessary if the app starts from a service - in this
        // case the repository will not exist unless it is specifically created.
        provideRepository(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return ContactNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static ContactDetailViewModelFactory provideDetailViewModelFactory(Context context, int contactID) {
        ContactRepository repository = provideRepository(context.getApplicationContext());
        return new ContactDetailViewModelFactory(repository, contactID);
    }

    public static ContactListViewModelFactory provideContactListViewModelFactory(Context context) {
        ContactRepository repository = provideRepository(context.getApplicationContext());
        return new ContactListViewModelFactory(repository);
    }

}