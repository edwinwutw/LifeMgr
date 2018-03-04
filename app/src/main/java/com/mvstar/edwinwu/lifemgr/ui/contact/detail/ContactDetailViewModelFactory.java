package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;

public class ContactDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ContactRepository mRepository;
    private final String mEmail;

    public ContactDetailViewModelFactory(ContactRepository repository, String email) {
        this.mRepository = repository;
        this.mEmail = email;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ContactDetailViewModel(mRepository, mEmail);
    }
}
