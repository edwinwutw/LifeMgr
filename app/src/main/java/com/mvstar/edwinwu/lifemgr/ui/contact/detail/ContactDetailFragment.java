package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import android.arch.lifecycle.MutableLiveData;
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
import com.mvstar.edwinwu.lifemgr.utilities.InjectorUtils;

/**
 * A fragment representing a single Contact detail screen.
 */
public class ContactDetailFragment extends Fragment {

    private ContactDetailBinding mDetailBinding;
    private ContactDetailViewModel mViewModel;

    private final MutableLiveData<ContactEntry> contact = new MutableLiveData<ContactEntry>();

    public static final String ARG_CONTACT_ID = "contact_id";

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

        String email = "";
        if (getArguments().containsKey(ARG_CONTACT_ID))
            email = getActivity().getIntent().getStringExtra(ARG_CONTACT_ID);

        ContactDetailViewModelFactory factory = InjectorUtils.provideDetailViewModelFactory(getActivity().getApplicationContext(), email);
        mViewModel = ViewModelProviders.of(this, factory).get(ContactDetailViewModel.class);

        mViewModel.getContact().observe(this, contactEntry -> {
            if (contactEntry != null) bindContactToUI(contactEntry);
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
        mViewModel.save(email, nickname, phone, info);
    }

    void requestEmailFocus(String error) {
        mDetailBinding.email.setError(error);
        mDetailBinding.email.requestFocus();
    }

}
