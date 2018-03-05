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
        viewHolder.listContactTitle.setText(mContactList.get(position).getEmail());
        viewHolder.listContactNamePhone.setText(mContactList.get(position).getNickName() + " / " +
                mContactList.get(position).getMobileNumber());
        viewHolder.listContactInfo.setText(mContactList.get(position).getInfo());

        viewHolder.itemView.setTag(mContactList.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == mContactList) return 0;
        return mContactList.size();
    }

    void swapData(final List<ContactEntry> newContactList) {
        mContactList = newContactList;
        notifyDataSetChanged();
    }

    /**
     * The interface that receives onItemClick messages.
     */
    public interface ContactListAdapterOnItemClickHandler {
        void onItemClick(String email);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView listContactTitle;
        final TextView listContactNamePhone;
        final TextView listContactInfo;

        ViewHolder(View view) {
            super(view);
            listContactTitle = view.findViewById(R.id.contact_list_title);
            listContactNamePhone = view.findViewById(R.id.contact_list_nickname_phone);
            listContactInfo = view.findViewById(R.id.contact_list_info);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String email = mContactList.get(adapterPosition).getEmail();
            mClickHandler.onItemClick(email);
        }
    }
}