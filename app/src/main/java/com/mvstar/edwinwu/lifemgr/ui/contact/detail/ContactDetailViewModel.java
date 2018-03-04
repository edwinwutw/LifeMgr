package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ContactDetailViewModel extends ViewModel {

    private final LiveData<ContactEntry> mContact;

    private final String mEmail;
    private final ContactRepository mRepository;

    public ContactDetailViewModel(ContactRepository repository, String email) {
        mRepository = repository;
        mEmail = email;
        mContact = mRepository.getContact(mEmail);
    }

    public LiveData<ContactEntry> getContact() {
        return mContact;
    }

    public void save(String email, String nickname, String phone, String info) {
        final ContactEntry contactEntry = new ContactEntry(email, nickname, phone, info);

        Single.create(emitter -> {
            try {
                insertContact(contactEntry);
                emitter.onSuccess("Success");
            } catch (Exception e) {
                emitter.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.d("Edwin", "success");
                        },
                        e -> {
                    Log.d("Edwin", "error");
                        });
    }

    public void insertContact(ContactEntry contactEntry) {
        mRepository.insertContact(contactEntry);
    }
}
