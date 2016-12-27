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
import java.util.Collections;
import java.util.List;

public class MultiplesViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_CONTACT_LIST = 1;
    private List<Object> contactList = new ArrayList<>();

    public MultiplesViewAdapter(List<Contact> contacts) {
        buildList(contacts);
    }

    private void buildList(List<Contact> contacts) {

        //Sort list with ascendent letters
        Collections.sort(contacts, new Contact.CompareContacts());

        for(Contact contact : contacts) {
            String header = getFirstLetter(contact);

            if(!contactList.contains(header)) {
                contactList.add(header); //add header
            }

            contactList.add(contact); //add contact
        }
    }

    private String getIndexLetter(int position) {
        return (String) contactList.get(position);
    }

    private String getFirstLetter(Contact contact) {
        return String.valueOf(contact.getName().toUpperCase().charAt(0));
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

        if(viewType == VIEW_TYPE_HEADER) {
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
        if(getItemViewType(position) == VIEW_TYPE_HEADER) {

            ViewHolderCategory viewHolderCategory = (ViewHolderCategory) holder;
            String indexLetter = getIndexLetter(position);
            viewHolderCategory.categoryName.setText(indexLetter);

        } else {
            ViewHolderContactList viewHolderContactList = (ViewHolderContactList) holder;

            Contact contact = (Contact) contactList.get(position);
            viewHolderContactList.contactName.setText(contact.getName());
            viewHolderContactList.contactImage.setImageResource(contact.getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = contactList.get(position);

        if(object instanceof Contact) {
            return VIEW_TYPE_CONTACT_LIST;
        } else {
            return VIEW_TYPE_HEADER;
        }
    }
}
