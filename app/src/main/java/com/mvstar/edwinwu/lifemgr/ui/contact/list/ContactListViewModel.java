package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


class ContactListViewModel extends ViewModel {
    private final ContactRepository mRepository;
    private final LiveData<List<ContactEntry>> mContactList;
    private final MutableLiveData<DeleteContactResult> mDeleteContactResultLiveData = new MutableLiveData<>();

    public ContactListViewModel(ContactRepository repository) {
        mRepository = repository;
        mContactList = mRepository.getContactList();
    }

    public LiveData<List<ContactEntry>> getContactList() {
        return mContactList;
    }

    public LiveData<DeleteContactResult> getDeleteContactResult() {
        return mDeleteContactResultLiveData;
    }

    public void delete(String email) {
        Single.create(emitter -> {
            try {
                deleteContact(email);
                emitter.onSuccess("Success");
            } catch(Exception e) {
                emitter.onError(e.getCause());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            mDeleteContactResultLiveData.setValue(DeleteContactResult.create(
                                    true, "", ""));
                        },
                        e -> {
                            mDeleteContactResultLiveData.setValue(DeleteContactResult.create(
                                    false, "", e.getMessage()));
                        });
    }

    public void deleteContact(String email) throws Exception {
        ContactEntry contact = mRepository.getContactImmediately(email);
        if (contact == null)
            throw new Exception(new Throwable("No such contact!"));

        mRepository.deleteContact(email);
    }

}
