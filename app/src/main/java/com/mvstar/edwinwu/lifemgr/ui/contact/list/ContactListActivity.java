package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mvstar.edwinwu.lifemgr.R;
import com.mvstar.edwinwu.lifemgr.ui.contact.detail.ContactDetailActivity;
import com.mvstar.edwinwu.lifemgr.ui.contact.detail.ContactDetailFragment;
import com.mvstar.edwinwu.lifemgr.utilities.InjectorUtils;

import java.util.Date;

public class ContactListActivity extends AppCompatActivity implements
        ContactListAdapter.ContactListAdapterOnItemClickHandler{

    private ContactListViewModel mViewModel;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactDetailIntent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
                startActivity(contactDetailIntent);
            }
        });

        if (findViewById(R.id.contact_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.contact_list);
        assert recyclerView != null;
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ContactListAdapter adapter = new ContactListAdapter(this, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        setupRecyclerView((RecyclerView) recyclerView);

        ContactListViewModelFactory factory = InjectorUtils.provideContactListViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(ContactListViewModel.class);

        mViewModel.getContactList().observe(this, contactList -> {
//            adapter.swapForecast(contactList);
//            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
//            mRecyclerView.smoothScrollToPosition(mPosition);
//
//            // Show the weather list or the loading screen based on whether the forecast data exists
//            // and is loaded
//            if (weatherEntries != null && weatherEntries.size() != 0) showWeatherDataView();
//            else showLoading();
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ContactListAdapter adapter = new ContactListAdapter(this, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

//    public static class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final ContactListActivity mParentActivity;
//        private final boolean mTwoPane;
//        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mTwoPane) {
//                    Bundle arguments = new Bundle();
//                    arguments.putString(ContactDetailFragment.ARG_CONTACT_ID, "1");
//                    ContactDetailFragment fragment = new ContactDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.contact_detail_container, fragment)
//                            .commit();
//                } else {
//                    Context context = view.getContext();
//                    Intent intent = new Intent(context, ContactDetailActivity.class);
//                    intent.putExtra(ContactDetailFragment.ARG_CONTACT_ID, "1");
//
//                    context.startActivity(intent);
//                }
//            }
//        };
//
//        SimpleItemRecyclerViewAdapter(ContactListActivity parent,
//                                      boolean twoPane) {
//            mParentActivity = parent;
//            mTwoPane = twoPane;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.contact_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.itemView.setOnClickListener(mOnClickListener);
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder {
//            final TextView mIdView;
//            final TextView mContentView;
//
//            ViewHolder(View view) {
//                super(view);
//                mIdView = (TextView) view.findViewById(R.id.id_text);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Date date) {
        Intent contactDetailIntent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
        int id = 0;//date.getTime();
        contactDetailIntent.putExtra(ContactDetailFragment.ARG_CONTACT_ID, id);
        startActivity(contactDetailIntent);
    }
}
