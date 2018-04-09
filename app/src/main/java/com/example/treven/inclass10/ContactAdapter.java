package com.example.treven.inclass10;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
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
            ConstraintLayout csl;
            TextView expenseName, expenseAmt;
            public ViewHolder(ConstraintLayout view) {
                super(view);
                csl = view.findViewById(R.id.csl);
                expenseName = view.findViewById(R.id.textViewExpense);
                expenseAmt = view.findViewById(R.id.textViewAmt);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public Adapter() {

            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        ArrayList<Expense> expenses = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            HashMap<String, String> expenseMap = (HashMap<String, String>) data.getValue();
                            Expense expense = new Expense(expenseMap.get("name"), expenseMap.get("category"), expenseMap.get("amount"), expenseMap.get("date"));
                            expenses.add(expense);
                        }
                        dataset = expenses;
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
                    .inflate(R.layout.expense_view, parent, false);

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
