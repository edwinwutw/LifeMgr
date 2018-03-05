package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.auto.value.AutoValue;
import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class ContactDetailViewModel extends ViewModel {

    private final LiveData<ContactEntry> mContact;
    private final MutableLiveData<SaveContactResult> mSaveContactResultLiveData = new MutableLiveData<>();


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

    public LiveData<SaveContactResult> getSaveContactResult() {
        return mSaveContactResultLiveData;
    }

    public void insert(String email, String nickname, String phone, String info) {
        final ContactEntry contactEntry = new ContactEntry(email, nickname, phone, info);

        Single.create(emitter -> {
            try {
                insertContact(contactEntry);
                emitter.onSuccess("Success");
            } catch(Exception e) {
                emitter.onError(e.getCause());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            mSaveContactResultLiveData.setValue(SaveContactResult.create(true, "", ""));
                        },
                        e -> {
                            mSaveContactResultLiveData.setValue(SaveContactResult.create(false, e.getCause().toString(), e.getMessage()));
                        });
    }

    public void insertContact(ContactEntry contactEntry) throws Exception {
        mRepository.insertContact(contactEntry);
    }

    public void update(String email, String nickname, String phone, String info) {
        final ContactEntry contactEntry = new ContactEntry(email, nickname, phone, info);

        Single.create(emitter -> {
            try {
                updateContact(contactEntry);
                emitter.onSuccess("Success");
            } catch(Exception e) {
                emitter.onError(e.getCause());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            mSaveContactResultLiveData.setValue(SaveContactResult.create(
                                    true, "", ""));
                        },
                        e -> {
                            mSaveContactResultLiveData.setValue(SaveContactResult.create(
                                    false, "", e.getMessage()));
                        });
    }

    public void updateContact(ContactEntry contactEntry) throws Exception {
        mRepository.updateContact(contactEntry);
    }
}
