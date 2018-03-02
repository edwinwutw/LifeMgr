package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvstar.edwinwu.lifemgr.R;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;

import java.util.Date;
import java.util.List;

class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {


    private final Context mContext;

    private final ContactListAdapterOnItemClickHandler mClickHandler;

    private List<ContactEntry> mContactList;

    public ContactListAdapter(@NonNull Context context,
                              ContactListAdapterOnItemClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contact_list_content, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ContactEntry contact = mContactList.get(position);
        //viewHolder.listContactAvatar.setImageDrawable(null);
        viewHolder.listContactTitle.setText(mContactList.get(position).getEmail());
        viewHolder.listContactInfo.setText(mContactList.get(position).getInfo());

        viewHolder.itemView.setTag(mContactList.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == mContactList) return 0;
        return mContactList.size();
    }

    void swapForecast(final List<ContactEntry> newContactList) {
        // If there was no contact data, then recreate all of the list
        if (mContactList == null) {
            mContactList = newContactList;
            notifyDataSetChanged();
        } else {
        }
    }

    /**
     * The interface that receives onItemClick messages.
     */
    public interface ContactListAdapterOnItemClickHandler {
        void onItemClick(int id);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView listContactAvatar;
        final TextView listContactTitle;
        final TextView listContactInfo;

        ViewHolder(View view) {
            super(view);
            listContactAvatar = view.findViewById(R.id.contact_list_avatar);
            listContactTitle = view.findViewById(R.id.contact_list_title);
            listContactInfo = view.findViewById(R.id.contact_list_info);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            int id = mContactList.get(adapterPosition).getId();
            mClickHandler.onItemClick(id);
        }
    }
}