package com.example.treven.inclass10;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactAdapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("expenses");
        private ArrayList<Contact> DataSet = new ArrayList<>();

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case


            public ViewHolder(ConstraintLayout view) {
                super(view);

            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ContactAdapter() {

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

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
                    .inflate(R.layout.contact_view, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.expenseName.setText(dataset.get(position).getName());
            holder.expenseAmt.setText(dataset.get(position).getAmount());
            holder.csl.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    dataset.remove(dataset.get(position));
                    notifyDataSetChanged(); // Important for updating the recycler view elements once removed
                    Toast.makeText(view.getContext(), "Expense Deleted", Toast.LENGTH_LONG).show();
                    return true;
                }
            });
            holder.csl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent expenseDetails = new Intent(view.getContext(), DetailExpense.class);
                    expenseDetails.putExtra("name", dataset.get(position).getName());
                    expenseDetails.putExtra("amount", dataset.get(position).getAmount());
                    expenseDetails.putExtra("category", dataset.get(position).getCategory());
                    view.getContext().startActivity(expenseDetails);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }
    }

}
