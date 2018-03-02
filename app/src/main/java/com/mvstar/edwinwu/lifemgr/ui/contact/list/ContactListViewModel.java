package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;

import java.util.List;


class ContactListViewModel extends ViewModel {
    private final ContactRepository mRepository;
    private final LiveData<List<ContactEntry>> mContactList;

    public ContactListViewModel(ContactRepository repository) {
        mRepository = repository;
        mContactList = mRepository.getContactList();
    }

    public LiveData<List<ContactEntry>> getContactList() {
        return mContactList;
    }
}
