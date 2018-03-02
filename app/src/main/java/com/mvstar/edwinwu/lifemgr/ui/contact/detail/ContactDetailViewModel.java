package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;

class ContactDetailViewModel extends ViewModel {

    private final LiveData<ContactEntry> mContact;

    // contact ID
    private final int mContactID;
    private final ContactRepository mRepository;

    public ContactDetailViewModel(ContactRepository repository, int contactID) {
        mRepository = repository;
        mContactID = contactID;
        mContact = mRepository.getContact(mContactID);
    }

    public LiveData<ContactEntry> getContact() {
        return mContact;
    }
}
