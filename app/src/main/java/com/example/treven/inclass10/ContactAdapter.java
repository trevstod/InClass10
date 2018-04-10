package com.example.treven.inclass10;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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

public class ContactAdapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("expenses");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        private ArrayList<Contact> DataSet = new ArrayList<>();

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
<<<<<<< HEAD
            TextView C_Name, C_Phone, C_Email;
            ImageButton editButton, deleteButton;
=======
>>>>>>> 497123e11f70dfb4ab934b6fb8500d85a72c1aba

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
                        ArrayList<Expense> expenses = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            HashMap<String, String> expenseMap = (HashMap<String, String>) data.getValue();
                            Contact contact = new Contact(expenseMap.get("name"), expenseMap.get("category"), expenseMap.get("amount"), expenseMap.get("date"));
                            contacts.add(contact);
                        }
<<<<<<< HEAD
                        dataset = ;
=======
                        DataSet = expenses;
>>>>>>> 497123e11f70dfb4ab934b6fb8500d85a72c1aba
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) { }
            });
        }

        // Create new views (invoked by the layout manager)
        @Override
        public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
            // create a new view
            ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
<<<<<<< HEAD
                    .inflate(R.layout.contact_view, parent, false);
=======
                    .inflate(R.layout.activity_contacts, parent, false);
>>>>>>> 497123e11f70dfb4ab934b6fb8500d85a72c1aba

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

<<<<<<< HEAD
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.C_Name.setText(dataset.get(position).getName());
        holder.C_Phone.setText(dataset.get(position).getPhone());
        holder.C_Email.setText(dataset.get(position).getEmail());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expenseDetails = new Intent(view.getContext(), EditContactActivity.class);
                expenseDetails.putExtra("name", dataset.get(position).getName());
                expenseDetails.putExtra("amount", dataset.get(position).getAmount());
                expenseDetails.putExtra("category", dataset.get(position).getCategory());
                view.getContext().startActivity(expenseDetails);
            }
        });
    }
=======
        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.expenseName.setText(DataSet.get(position).getName());
            holder.expenseAmt.setText(DataSet.get(position).getAmount());
            holder.csl.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DataSet.remove(DataSet.get(position));
                    notifyDataSetChanged(); // Important for updating the recycler view elements once removed
                    Toast.makeText(view.getContext(), "Expense Deleted", Toast.LENGTH_LONG).show();
                    return true;
                }
            });
            holder.csl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent expenseDetails = new Intent(view.getContext(), DetailExpense.class);
                    expenseDetails.putExtra("First Name", DataSet.get(position).getfName());
                    expenseDetails.putExtra("Last Name", DataSet.get(position).getlName());
                    expenseDetails.putExtra("Email", DataSet.get(position).geteMail());
                    view.getContext().startActivity(expenseDetails);
                }
            });
        }
>>>>>>> 497123e11f70dfb4ab934b6fb8500d85a72c1aba

        @Override
        public int getItemCount() {
            return dataset.size();
            return DataSet.size();
        }
}
