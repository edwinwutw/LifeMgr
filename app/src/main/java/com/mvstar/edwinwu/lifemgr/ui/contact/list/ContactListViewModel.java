package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;
import com.mvstar.edwinwu.lifemgr.utilities.ViewModelActionResult;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


class ContactListViewModel extends ViewModel {
    private final ContactRepository mRepository;
    private final LiveData<List<ContactEntry>> mContactList;
    private final MutableLiveData<ViewModelActionResult> mDeleteContactResultLiveData = new MutableLiveData<>();

    public ContactListViewModel(ContactRepository repository) {
        mRepository = repository;
        mContactList = mRepository.getContactList();
    }

    public LiveData<List<ContactEntry>> getContactList() {
        return mContactList;
    }

    public LiveData<ViewModelActionResult> getDeleteContactResult() {
        return mDeleteContactResultLiveData;
    }

    public void deleteContact(String email) {
        Single.create(emitter -> {
            ContactEntry contact = mRepository.getContactImmediately(email);
            if (contact == null)
                throw new  IllegalArgumentException(new Throwable("No such contact!"));

            mRepository.deleteContact(email);
            emitter.onSuccess("Success");
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                result -> mDeleteContactResultLiveData.setValue(
                        ViewModelActionResult.create(true, "", "")),
                e -> mDeleteContactResultLiveData.setValue(
                        ViewModelActionResult.create(false, "", e.getMessage()))
            );
    }
}
