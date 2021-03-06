package com.example.treven.inclass10;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.File;

/**
 * Created by Treven on 4/9/18.
 */

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailField;
    EditText passField;
    EditText firstname;
    EditText lastname;
    EditText otherPass;
    ImageView imageView;
    static final int CAM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        emailField = findViewById(R.id.editPhoneNumberField);
        passField = findViewById(R.id.passwordText);
        otherPass =  findViewById(R.id.repeatpasswordText);
        firstname = findViewById(R.id.editFirstNameField);
        lastname = findViewById(R.id.editLastNameField);
        mAuth = FirebaseAuth.getInstance();


        imageView = (ImageView)findViewById(R.id.PictureView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);


            }
        });
        findViewById(R.id.Cancelbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.SignUpbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if fields are empty
                if(TextUtils.isEmpty(firstname.getText().toString()) || TextUtils.isEmpty(lastname.getText().toString()) || TextUtils.isEmpty(emailField.getText().toString()) || TextUtils.isEmpty(passField.getText().toString())){
                    Toast.makeText(SignupActivity.this, "Please fill in all fields.",
                            Toast.LENGTH_SHORT).show();
                }else if (!passField.getText().toString().equals(otherPass.getText().toString())){
                    Toast.makeText(SignupActivity.this, "Passwords aren't identical.",
                            Toast.LENGTH_SHORT).show();

                }else {
                    mAuth.createUserWithEmailAndPassword(emailField.getText().toString(), passField.getText().toString())
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("test", "createUserWithEmail:success");

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(firstname.getText().toString() + " " + lastname.getText().toString())
                                                .build();

                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("test", "User profile updated.");
                                                            Intent contactListIntent = new Intent(SignupActivity.this, ContactsActivity.class);
                                                            startActivity(contactListIntent);
                                                            finish();
                                                        }
                                                    }
                                                });

                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("test", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });
                }
            }
        });

    }
    private File getFile(){
        File folder = new File("Sdcard/camera_app");

        if (!folder.exists()){
            folder.mkdir();
        }
        File image_file = new File(folder,"cam_image.jpg");
        return image_file;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String path = "sdcard/camera_app/cam_imagejpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}
