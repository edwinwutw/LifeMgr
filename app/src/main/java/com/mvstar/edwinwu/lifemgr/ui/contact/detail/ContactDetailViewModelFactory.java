package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;

public class ContactDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ContactRepository mRepository;
    private final int mContactID;

    public ContactDetailViewModelFactory(ContactRepository repository, int contactID) {
        this.mRepository = repository;
        this.mContactID = contactID;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ContactDetailViewModel(mRepository, mContactID);
    }
}
