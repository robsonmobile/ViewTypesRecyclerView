package net.paulacr.viewtypesrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.paulacr.viewtypesrecyclerview.adapter.MultiplesViewAdapter;
import net.paulacr.viewtypesrecyclerview.decorator.MultipleViewsItemDecorator;
import net.paulacr.viewtypesrecyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        MultiplesViewAdapter adapter = new MultiplesViewAdapter(generateContactsList());
        MultipleViewsItemDecorator decorator = new MultipleViewsItemDecorator(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(decorator);
        recyclerView.setAdapter(adapter);
    }

    private List<Contact> generateContactsList() {
        List<Contact> contactList = new ArrayList<>();

        Contact contact1 = new Contact();
        contact1.setName("Mãe");
        contact1.setIcon(R.mipmap.ic_launcher);
        contact1.setCategory(Contact.Category.FAMILY);

        Contact contact2 = new Contact();
        contact2.setName("Tia legal");
        contact2.setIcon(R.mipmap.ic_launcher);
        contact2.setCategory(Contact.Category.FAMILY);

        Contact contact3 = new Contact();
        contact3.setName("Tia chata");
        contact3.setIcon(R.mipmap.ic_launcher);
        contact3.setCategory(Contact.Category.FAMILY);

        Contact contact4 = new Contact();
        contact4.setName("Amigaaa");
        contact4.setIcon(R.mipmap.ic_launcher);
        contact4.setCategory(Contact.Category.FRIENDS);

        Contact contact5 = new Contact();
        contact5.setName("O nerd");
        contact5.setIcon(R.mipmap.ic_launcher);
        contact5.setCategory(Contact.Category.WORK);

        Contact contact6 = new Contact();
        contact6.setName("A legal");
        contact6.setIcon(R.mipmap.ic_launcher);
        contact6.setCategory(Contact.Category.WORK);

        contactList.add(contact1);
        contactList.add(contact2);
        contactList.add(contact3);
        contactList.add(contact4);
        contactList.add(contact5);
        contactList.add(contact6);

        return contactList;
    }
}
