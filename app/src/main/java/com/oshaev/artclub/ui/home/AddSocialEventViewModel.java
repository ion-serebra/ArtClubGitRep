package com.oshaev.artclub.ui.home;

import android.app.Application;
import android.app.ProgressDialog;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oshaev.artclub.ChatMessage;
import com.oshaev.artclub.ui.notifications.FirstFragment;

import java.util.UUID;

public class AddSocialEventViewModel extends AndroidViewModel {


    public final ObservableField<String> postTitle = new ObservableField<>("");
    public final ObservableField<String> postPaper = new ObservableField<>("");
    private FirebaseDatabase db;
    private DatabaseReference reference;
    private FirebaseStorage storage;
    private StorageReference eventStorageReference;
    Uri selectedImageUri;


    public AddSocialEventViewModel(@NonNull Application application) {
        super(application);

        db = FirebaseDatabase.getInstance();
        reference = db.getReference().child("cultural event");
        storage = FirebaseStorage.getInstance();
        eventStorageReference = storage.getReference().child("events");
    }

    public void setEvent(NewsItem event)
    {
        reference.push().setValue(event);
    }

    public void uploadImage(Uri filePath)
    {

    }

}
