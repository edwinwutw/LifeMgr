package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

public class ContactListActivity extends AppCompatActivity implements
        ContactListAdapter.ContactListAdapterOnItemClickHandler{

    private ContactListViewModel mViewModel;
    private int mPosition = RecyclerView.NO_POSITION;

    private ContactListAdapter mContactAdapter;
    private RecyclerView mContactRecyclerview;

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

        mContactRecyclerview = findViewById(R.id.contact_list);
        assert mContactRecyclerview != null;
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mContactAdapter = new ContactListAdapter(this, this);

        mContactRecyclerview.setLayoutManager(layoutManager);
        mContactRecyclerview.setAdapter(mContactAdapter);
        mContactRecyclerview.setHasFixedSize(true);

        ContactListViewModelFactory factory = InjectorUtils.provideContactListViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this, factory).get(ContactListViewModel.class);

        mViewModel.getContactList().observe(this, contactList -> {
            mContactAdapter.swapForecast(contactList);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            mContactRecyclerview.smoothScrollToPosition(mPosition);

        });
    }

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
    public void onItemClick(int id) {
        if (id <= 0) return;

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(ContactDetailFragment.ARG_CONTACT_ID, id);
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contact_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(ContactListActivity.this, ContactDetailActivity.class);
            intent.putExtra(ContactDetailFragment.ARG_CONTACT_ID, id);

            startActivity(intent);
        }
    }
}
