package com.example.treven.inclass10;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactAdapter extends ArrayAdapter<Contact>
{
private Context context;
private ArrayList<Contact> expenses;
private int resource;


public ContactAdapter(Context context, ArrayList<Contact> expenses, int resource)
        {
        super(context, resource, expenses);
        this.context = context;
        this.expenses = expenses;
        this.resource = resource;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent)
        {
        if(convertView == null)
        {
        LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(resource, parent, false);
        }

        Contact contact = expenses.get(position);

        TextView textName = (TextView) convertView.findViewById(R.id.textViewContactName);
        TextView textAmount = (TextView) convertView.findViewById(R.id.textViewPhone);

        textName.setText(contact.getname());
        textAmount.setText("$" + contact.getPhone());

        return convertView;
        }
}/* extends RecyclerView.Adapter<Adapter.ViewHolder> {

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        private ArrayList<Contact> dataset = new ArrayList<>();

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case

            TextView C_Name, C_Phone, C_Email;
            ImageButton editButton, deleteButton;

            public ViewHolder(ConstraintLayout view) {
                super(view);
                C_Name = view.findViewById(R.id.textViewContactName);
                C_Phone = view.findViewById(R.id.textViewPhone);
                C_Email = view.findViewById(R.id.textViewEmail);
                editButton = view.findViewById(R.id.imageButtonEdit);
                deleteButton = view.findViewById(R.id.imageButtonDelete);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ContactAdapter() {

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        ArrayList<Contact> contacts = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            HashMap<String, String> expenseMap = (HashMap<String, String>) data.getValue();
                            Contact contact = new Contact(expenseMap.get("name"), expenseMap.get("phone"), expenseMap.get("email"));
                            contacts.add(contact);
                        }

                        dataset = contacts;
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
            // create a new view
            ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_view, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.C_Name.setText(dataset.get(position).getname());
        holder.C_Phone.setText(dataset.get(position).getPhone());
        holder.C_Email.setText(dataset.get(position).geteMail());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expenseDetails = new Intent(view.getContext(), EditContactActivity.class);
                expenseDetails.putExtra("name", dataset.get(position).getname());
                expenseDetails.putExtra("phone", dataset.get(position).getPhone());
                expenseDetails.putExtra("email", dataset.get(position).geteMail());
                view.getContext().startActivity(expenseDetails);
            }
        });
    }
        @Override
        public int getItemCount() {
            return dataset.size();
        }
}
