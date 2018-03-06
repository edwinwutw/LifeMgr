package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvstar.edwinwu.lifemgr.R;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;
import com.mvstar.edwinwu.lifemgr.databinding.ContactDetailBinding;
import com.mvstar.edwinwu.lifemgr.utilities.InformActionResult;
import com.mvstar.edwinwu.lifemgr.utilities.InjectorUtils;
import com.mvstar.edwinwu.lifemgr.utilities.ViewModelActionResult;

/**
 * A fragment representing a single Contact detail screen.
 */
public class ContactDetailFragment extends Fragment {

    private ContactDetailBinding mDetailBinding;
    private ContactDetailViewModel mViewModel;

    private final MutableLiveData<ContactEntry> contact = new MutableLiveData<ContactEntry>();

    public static final String ARG_CONTACT_ID = "contact_id";

    private boolean mExistingContact;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // check it is new contact or modify contacts
        mExistingContact = false;
        String email = "";
        if (getArguments().containsKey(ARG_CONTACT_ID)) {
            email = getArguments().getString(ARG_CONTACT_ID);
            // if email is valid email
            if (email != null) {
                mExistingContact = true;
                mDetailBinding.email.setEnabled(false);
            } else
                email = "";
        }

        ContactDetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(getActivity().getApplicationContext(), email);
        mViewModel = ViewModelProviders.of(this, factory).get(ContactDetailViewModel.class);


        // observe
        mViewModel.getContact().observe(this, contactEntry -> {
            if (contactEntry != null) bindContactToUI(contactEntry);
        });
        mViewModel.getSaveContactResult().observe(this, new Observer<ViewModelActionResult>() {
            @Override
            public void onChanged(@Nullable ViewModelActionResult result) {
                if (result != null) {
                    if (result.status() == true) {
                        getActivity().finish();
                    } else {
                        InformActionResult.BySnackBar(getActivity().findViewById(R.id.contact_detail_form),
                                getString(R.string.save_contact_fail), result.messageCode(),
                                result.message());
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDetailBinding = DataBindingUtil.inflate(inflater, R.layout.contact_detail, container, false);

        return mDetailBinding.getRoot();
    }

    private void bindContactToUI(ContactEntry contactEntry) {
        String email = contactEntry.getEmail();
        mDetailBinding.email.setText(email);
        String nickname = contactEntry.getNickName();
        mDetailBinding.nickname.setText(nickname);
        String phone = contactEntry.getMobileNumber();
        mDetailBinding.mobilenumber.setText(phone);
        String info = contactEntry.getInfo();
        mDetailBinding.contactinfo.setText(info);
    }

    public void save() {
        // Reset errors.
        mDetailBinding.email.setError(null);

        String email = mDetailBinding.email.getText().toString();
        String nickname = mDetailBinding.nickname.getText().toString();
        String phone = mDetailBinding.mobilenumber.getText().toString();
        String info = mDetailBinding.contactinfo.getText().toString();

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            requestEmailFocus(getString(R.string.error_field_required));
            return;
        }
        if (mExistingContact)
            mViewModel.update(email, nickname, phone, info);
        else
            mViewModel.insert(email, nickname, phone, info);
    }

    void requestEmailFocus(String error) {
        mDetailBinding.email.setError(error);
        mDetailBinding.email.requestFocus();
    }
}
