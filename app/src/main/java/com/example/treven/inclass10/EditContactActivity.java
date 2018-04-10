package com.example.treven.inclass10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Treven on 4/9/18.
 */

public class EditContactActivity extends AppCompatActivity {

    EditText nameField;
    EditText lastField;
    EditText emailField;
    EditText phoneField;
    private DatabaseReference mDatabase;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    ArrayList<Contact> cList;
    ImageView editImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        cList = new ArrayList<>();
        nameField = findViewById(R.id.editFirstNameField);
        lastField = findViewById(R.id.editLastNameField);
        emailField = findViewById(R.id.editLastNameField);
        phoneField = findViewById(R.id.editPhoneNumberField);
        editImageButton = findViewById(R.id.imageButton);




        findViewById(R.id.editContactButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contact newContact = new Contact(nameField.getText().toString(),
                        lastField.getText().toString(),
                        emailField.getText().toString(),
                        phoneField.getText().toString();

                Intent contactListIntent = new Intent(EditContactActivity.this, ContactsActivity.class);

                mDatabase.child(user.getUid()).child("contacts").push().setValue(newContact);
                startActivity(contactListIntent);
                finish();
            }
        });

        editImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}



