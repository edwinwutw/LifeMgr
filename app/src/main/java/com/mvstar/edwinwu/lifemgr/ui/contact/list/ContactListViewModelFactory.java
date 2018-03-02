package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;

public class ContactListViewModelFactory implements ViewModelProvider.Factory {

    private final ContactRepository mRepository;

    public ContactListViewModelFactory(ContactRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ContactListViewModel(mRepository);
    }
}
