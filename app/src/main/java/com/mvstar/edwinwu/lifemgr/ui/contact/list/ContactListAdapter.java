package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvstar.edwinwu.lifemgr.R;
import com.mvstar.edwinwu.lifemgr.data.database.ContactEntry;
import com.mvstar.edwinwu.lifemgr.databinding.ActivityContactListBinding;
import com.mvstar.edwinwu.lifemgr.databinding.ContactListContentBinding;

import java.util.Date;
import java.util.List;

class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {


    private final Context mContext;

    private final ContactListAdapterOnItemClickHandler mClickHandler;

    private List<ContactEntry> mContactList;

    private ContactListContentBinding mListContentBinding;

    public ContactListAdapter(@NonNull Context context,
                              ContactListAdapterOnItemClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListContentBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.contact_list_content, parent, false);
        View view = mListContentBinding.getRoot();
        //View view = LayoutInflater.from(mContext).inflate(R.layout.contact_list_content, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ContactEntry contact = mContactList.get(position);
        viewHolder.listContactTitle.setText(contact.getEmail());
        viewHolder.listContactNamePhone.setText(contact.getNickName() + " / " +
                                                contact.getMobileNumber());
        viewHolder.listContactInfo.setText(contact.getInfo());

        viewHolder.itemView.setTag(contact);
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
            listContactTitle = mListContentBinding.contactListTitle;//view.findViewById(R.id.contact_list_title);
            listContactNamePhone = mListContentBinding.contactListNicknamePhone;//view.findViewById(R.id.contact_list_nickname_phone);
            listContactInfo = mListContentBinding.contactListInfo;//view.findViewById(R.id.contact_list_info);

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