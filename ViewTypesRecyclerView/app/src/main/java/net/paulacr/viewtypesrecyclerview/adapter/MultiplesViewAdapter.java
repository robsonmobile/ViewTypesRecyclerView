package net.paulacr.viewtypesrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
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
    private static final int VIEW_TYPE_CONTACT_LIST = 1;

    private List<Contact> familyList = new ArrayList<>();
    private List<Contact> friendsList = new ArrayList<>();
    private List<Contact> workList = new ArrayList<>();
    private List<Contact> recyclerData = new ArrayList<>();

    public MultiplesViewAdapter(List<Contact> contactList) {
        splitListsByCategory(contactList);

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
            String categoryName;
            Contact.Category category = recyclerData.get(position + 1).getCategory();

            if(category == Contact.Category.FAMILY) {
                categoryName = "Family";
            } else if(category == Contact.Category.FRIENDS) {
                categoryName = "Friends";
            } else {
                categoryName = "Work";
            }
            viewHolderCategory.categoryName.setText(categoryName);

        } else {
            ViewHolderContactList viewHolderContactList = (ViewHolderContactList) holder;
            viewHolderContactList.contactName.setText(recyclerData.get(position).getName());
            viewHolderContactList.contactImage.setImageResource(recyclerData.get(position).getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return recyclerData.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(recyclerData.get(position) == null) {
            return VIEW_TYPE_CATEGORY;
        } else {
            return VIEW_TYPE_CONTACT_LIST;
        }
    }

    private void splitListsByCategory(List<Contact> contactList) {

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
