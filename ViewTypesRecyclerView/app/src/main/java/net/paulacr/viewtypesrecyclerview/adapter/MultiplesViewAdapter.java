package net.paulacr.viewtypesrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.paulacr.viewtypesrecyclerview.R;
import net.paulacr.viewtypesrecyclerview.model.Contact;
import net.paulacr.viewtypesrecyclerview.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MultiplesViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_CATEGORY = 0;
    private static final int VIEW_TYPE_LETTER_INDEX = 1;
    private static final int VIEW_TYPE_CONTACT_LIST = 2;

    private List<Contact> familyList = new ArrayList<>();
    private List<Contact> friendsList = new ArrayList<>();
    private List<Contact> workList = new ArrayList<>();
    private List<Contact> contactList;
    private List<Contact> recyclerData = new ArrayList<>();

    public MultiplesViewAdapter(List<Contact> contactList) {
        this.contactList = contactList;
        splitListsByCategory();

        if(!familyList.isEmpty()) {
            recyclerData.add(null);
            recyclerData.addAll(familyList);
        }

        if(!friendsList.isEmpty()) {
            recyclerData.add(null);
            recyclerData.addAll(friendsList);
        }

        if(!workList.isEmpty()) {
            recyclerData.add(null);
            recyclerData.addAll(workList);
        }
    }

    static class ViewHolderCategory extends BaseViewHolder {

        TextView categoryName;

        public ViewHolderCategory(View view) {
            super(view);

            categoryName = (TextView) view.findViewById(R.id.category_name);
        }
    }

    static class ViewHolderLetterIndex extends BaseViewHolder {

        TextView index;

        public ViewHolderLetterIndex(View view) {
            super(view);
            index = (TextView) view.findViewById(R.id.letter_index);
        }
    }

    static class ViewHolderContactList extends BaseViewHolder {

        TextView contactName;
        ImageView contactImage;

        public ViewHolderContactList(View view) {
            super(view);
            contactName = (TextView) view.findViewById(R.id.name);
            contactImage = (ImageView) view.findViewById(R.id.profile_image);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        BaseViewHolder viewHolder;

        if(viewType == VIEW_TYPE_CATEGORY) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_header_category, parent, false);

            viewHolder = new ViewHolderCategory(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item_contact, parent, false);

            viewHolder = new ViewHolderContactList(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_CATEGORY) {
            ViewHolderCategory viewHolderCategory = (ViewHolderCategory) holder;

            //bind views
            String categoria;
            if(position == 0) {
                categoria = "Family";
            } else if(position == familyList.size() + 1) {
                categoria = "Friends";
            } else {
                categoria = "Work";
            }
            viewHolderCategory.categoryName.setText(categoria);

        } else {
            ViewHolderContactList viewHolderContactList = (ViewHolderContactList) holder;
            viewHolderContactList.contactName.setText(recyclerData.get(position).getName());
            viewHolderContactList.contactImage.setImageResource(recyclerData.get(position).getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0) {
            return VIEW_TYPE_CATEGORY;
        } else if(position == familyList.size() +1) {
            return VIEW_TYPE_CATEGORY;
        } else if(position == friendsList.size() + familyList.size() + 2) {
            return VIEW_TYPE_CATEGORY;
        } else {
            return VIEW_TYPE_CONTACT_LIST;
        }
    }

    private void splitListsByCategory() {

        for(int i = 0; i < contactList.size(); i++) {

            if(contactList.get(i).getCategory() == Contact.Category.FAMILY) {
                familyList.add(contactList.get(i));

            } else if(contactList.get(i).getCategory() == Contact.Category.FRIENDS) {
                friendsList.add(contactList.get(i));

            } else if(contactList.get(i).getCategory() == Contact.Category.WORK) {
                workList.add(contactList.get(i));
            }
        }
    }
}
