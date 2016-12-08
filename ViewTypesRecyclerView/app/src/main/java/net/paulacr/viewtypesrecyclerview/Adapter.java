package net.paulacr.viewtypesrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.paulacr.viewtypesrecyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CATEGORY = 0;
    private static final int VIEW_TYPE_LETTER_INDEX = 1;
    private static final int VIEW_TYPE_CONTACT_LIST = 2;

    private List<Contact> familyList;
    private List<Contact> friendsList;
    private List<Contact> workList;
    private List<Contact> contactList;
    private List<Contact> recyclerData = new ArrayList<>();

    public Adapter(List<Contact> contactList) {
        this.contactList = contactList;
        splitListsByCategory();

        //adiciona espaço vazio para primeira categoria


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

    static class ViewHolderCategory extends RecyclerView.ViewHolder {

        private TextView categoryName;
        private ImageView categoryIcon;

        public ViewHolderCategory(View view) {
            super(view);
        }
    }

    static class ViewHolderLetterIndex extends RecyclerView.ViewHolder {

        private TextView categoryName;

        public ViewHolderLetterIndex(View view) {
            super(view);
        }
    }

    static class ViewHolderContactList extends RecyclerView.ViewHolder {

        private TextView contactName;
        private ImageView contactImage;

        public ViewHolderContactList(View view) {
            super(view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;

        if(viewType == VIEW_TYPE_CATEGORY) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_header_category, parent);

            viewHolder = new ViewHolderCategory(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item_contact, parent);

            viewHolder = new ViewHolderCategory(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == VIEW_TYPE_CATEGORY) {
            ViewHolderCategory viewHolderCategory = (ViewHolderCategory) holder;

            //bind views
            viewHolderCategory.categoryName.setText(recyclerData.get(position).getCategory().toString());

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

        if(recyclerData == null) {
            return VIEW_TYPE_CATEGORY;
        } else {
            return VIEW_TYPE_CONTACT_LIST;
        }

    }

    private void splitListsByCategory() {
        familyList = new ArrayList<>();
        friendsList = new ArrayList<>();
        workList = new ArrayList<>();

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
