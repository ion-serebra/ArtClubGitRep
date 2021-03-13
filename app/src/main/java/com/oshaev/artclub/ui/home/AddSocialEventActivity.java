
package com.oshaev.artclub.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintInfo;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oshaev.artclub.R;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class AddSocialEventActivity extends AppCompatActivity {

    AddSocialEventViewModel addSocialEventViewModel;

    private EditText titleEditText;
    private EditText summaryEditText;
    private EditText paperEditText;
    private ImageButton backButton;
    private ImageButton doneButton;
    private ImageButton addImageButton;
    private ImageView addEventImage1;
    private ImageView addEventImage2;
    private ImageView addEventImage3;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private TextView progressText1;
    private TextView progressText2;
    private TextView progressText3;
    private RadioButton registrationEnableButton;

    private  static final int REQUEST_CODE_IMAGE = 111;

    private NewsItem item;

    private int imagesCount = 0;

    private String postTitle;
    private String titleImgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_social_event);
        titleEditText = findViewById(R.id.titleEditText);
        summaryEditText = findViewById(R.id.summaryEditText);
        paperEditText = findViewById(R.id.paperEditText);
        backButton = findViewById(R.id.backButton);
        doneButton = findViewById(R.id.doneButton);
        addImageButton = findViewById(R.id.addImageButton);
        addEventImage1 = findViewById(R.id.addEventImage1);
        progressBar1 = findViewById(R.id.progressBar1);
        progressText1 = findViewById(R.id.progressText1);
        addEventImage2 = findViewById(R.id.addEventImage2);
        progressBar2 = findViewById(R.id.progressBar2);
        progressText2 = findViewById(R.id.progressText2);
        addEventImage3 = findViewById(R.id.addEventImage3);
        progressBar3 = findViewById(R.id.progressBar3);
        progressText3 = findViewById(R.id.progressText3);
        registrationEnableButton = findViewById(R.id.registrationEnableButton);

        item = new NewsItem();

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.setTitle(titleEditText.getText().toString());
                item.setSummary(summaryEditText.getText().toString());
                item.setPaper(paperEditText.getText().toString());
                item.setDatePost(new Date());

                if(registrationEnableButton.isActivated())
                {
                    Toast.makeText(AddSocialEventActivity.this, "activated", Toast.LENGTH_SHORT).show();
                }

                Log.e("NewsItemItem", item.getTitle()+"  " + item.getPaper().toString());
                addSocialEventViewModel.setEvent(item);


                Intent intent = new Intent(AddSocialEventActivity.this, PostPriviewActivity.class);
                //intent.putExtra("eventKey", item.getKey());
                intent.putExtra("postTitle", postTitle);
                intent.putExtra("titleImgUrl", titleImgUrl);
                startActivity(intent);

                //finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent, "Выберите картинку"),
                        REQUEST_CODE_IMAGE);
            }
        });



        addSocialEventViewModel =
                ViewModelProviders.of(this).get(AddSocialEventViewModel.class);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Uri filePath;

        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK  // устанавливаем ImageView
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                if(imagesCount == 0)
                {
                    addEventImage1 = findViewById(R.id.addEventImage1);
                    progressBar1 = findViewById(R.id.progressBar1);
                    progressText1 = findViewById(R.id.progressText1);
                }

                if(imagesCount == 1)
                {
                    addEventImage1 = findViewById(R.id.addEventImage2);
                    progressBar1 = findViewById(R.id.progressBar2);
                    progressText1 = findViewById(R.id.progressText2);
                }

                if(imagesCount == 2)
                {
                    addEventImage1 = findViewById(R.id.addEventImage3);
                    progressBar1 = findViewById(R.id.progressBar3);
                    progressText1 = findViewById(R.id.progressText3);
                }

                    addEventImage1.setVisibility(View.VISIBLE);
                    addEventImage1.setImageBitmap(bitmap);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) { // загружаем картинки на сервер

            addSocialEventViewModel.selectedImageUri = data.getData();
            filePath=data.getData();
            //Uri filePath = data.getData();
            //addSocialEventViewModel.uploadImage(selectedImageUri);

            if(addSocialEventViewModel.selectedImageUri != null)
            {
                final ProgressDialog progressDialog = new ProgressDialog(getApplication());
                progressDialog.setTitle("Uploading...");
                //progressDialog.show();


                progressBar1.setVisibility(View.VISIBLE);
                progressText1.setVisibility(View.VISIBLE);
                FirebaseStorage storage = FirebaseStorage.getInstance();
                final StorageReference eventStorageReference = storage.getReference().child("culturalEvents").child(filePath.getLastPathSegment());
                final String currentUUid = UUID.randomUUID().toString();
                final StorageReference ref = eventStorageReference.child(currentUUid);

                Uri selectedImageUri = data.getData();
                //UploadTask uploadTask2 = eventStorageReference.putFile(selectedImageUri);




                UploadTask uploadTask = eventStorageReference.putFile(filePath);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }


                        return eventStorageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            if(imagesCount==0)
                            {
                                titleImgUrl = downloadUri.toString();
                            }
                            item.setImageUrlString(downloadUri.toString());
                            item.getImageUrlStrings().add(downloadUri.toString()); // получаем адрес фото в хранилище
                            Log.e("jopa", downloadUri.toString());
                        }
                        else{}
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(getApplication(), "Uploaded", Toast.LENGTH_SHORT).show();
                        progressBar1.setVisibility(View.INVISIBLE);
                        progressText1.setVisibility(View.INVISIBLE);
                        ++imagesCount;
                    }
                });

                System.out.println();
                int x = "1".compareTo("2");


                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
/*
                                progressDialog.dismiss();
                                progressBar1.setVisibility(View.INVISIBLE);
                                progressText1.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "Uploaded", Toast.LENGTH_SHORT).show();
 */
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplication(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded "+(int)progress+"%");
                                progressBar1.setProgress((int)progress);
                                progressText1.setText((int)progress+"%");
                                Log.d("progress","Uploaded "+(int)progress+"%");
                            }
                        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                       // item.setImageUrlString(task.getResult().toString());
                        //task.getResult();
                    }
                });
            }

        }

    }



}









