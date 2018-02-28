package com.mvstar.edwinwu.lifemgr.ui.contact;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvstar.edwinwu.lifemgr.R;

/**
 * A fragment representing a single Contact detail screen.
 */
public class ContactDetailFragment extends Fragment {
    /**
     * The fragment argument representing the contact ID that this fragment
     * represents.
     */
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

        if (getArguments().containsKey(ARG_CONTACT_ID)) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_detail, container, false);

        return rootView;
    }
}
