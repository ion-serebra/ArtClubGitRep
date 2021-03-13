package com.oshaev.artclub.ui.notifications;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oshaev.artclub.User;
import com.oshaev.artclub.ui.notifications.fragments.PerformanceFragment;
import com.oshaev.artclub.usertasks.Performance;
import com.oshaev.artclub.usertasks.UserTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class ProfileViewModel extends ViewModel {

    private static final int REQUEST_CODE_IMAGE = 123;


    private FirebaseDatabase database;
    private static User thisUser;
    public String userKey;
    private String userName;
    private String userSurname;
    private String userGroup;
    private Integer userAccessLevel;
    private MutableLiveData<String> userNameData = new MutableLiveData<>();
    private MutableLiveData<String> userSurnameData = new MutableLiveData<>();
    private MutableLiveData<String> userGroupData = new MutableLiveData<>();
    private MutableLiveData<Integer> userAccessLevelData = new MutableLiveData<>();
    private MutableLiveData<Integer> userExpData = new MutableLiveData<>();
    private String name;

    boolean isCompletedTasks;

    //tasks variables:

    UserTask userTask;
    static ArrayList<UserTask> tasks = new ArrayList<UserTask>();
    static ArrayList<UserTask> completedTasks = new ArrayList<UserTask>();
    //ArrayList<UserTask> tasks;
    public MutableLiveData<ArrayList<UserTask>> tasksData = new MutableLiveData<ArrayList<UserTask>>();
    public MutableLiveData<ArrayList<UserTask>> completedTasksData = new MutableLiveData<ArrayList<UserTask>>();
    MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    ArrayList<Performance> userPerformances;;

    FirebaseDatabase db;


    User user;

    public ProfileViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is notifications fragment");



        thisUser = new User();
        db = FirebaseDatabase.getInstance();

    }


    public MutableLiveData<ArrayList<UserTask>> getTasks() {
        getTasksOnline();
        if(tasks!=null) {
            tasksData.setValue(tasks);
        }
        return tasksData;
    }

    public MutableLiveData<String> getText() {
        getUser();
        userNameData.setValue(userName);
        return userNameData;
    }

    public MutableLiveData<String> getSurname() {
        getUser();
        userSurnameData.setValue(userSurname);
        return userSurnameData;
    }

    public MutableLiveData<String> getGroup() {
        getUser();
        userGroupData.setValue(userGroup);
        return userGroupData;
    }

    public MutableLiveData<Integer> getExp() {
        getUser();
        userExpData.postValue(thisUser.getExp());
        return userExpData;
    }

    public MutableLiveData<Integer> getAccessLevel() {
        getUser();
        userAccessLevelData.setValue(userAccessLevel);
        return userAccessLevelData;
    }

    public String getName() {
        return name;
    }

    public MutableLiveData<User> getCurrentUser()
    {
        getUser();
        userMutableLiveData.setValue(thisUser);
        return userMutableLiveData;
    }

    public MutableLiveData<String> getUserName()
    {
        getUser();
        MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();
        userMutableLiveData.setValue(userName);
        return userMutableLiveData;

    }

    public MutableLiveData<ArrayList<UserTask>> getTasksData()
    {
        //getTasksOnline();
        tasksData.setValue(tasks);
        return  tasksData;
    }


    public void getUser() // получаем user из Firebase Database
    {

        database = FirebaseDatabase.getInstance();

        database.getReference().child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                thisUser.getUserKey();
                user = snapshot.getValue(User.class);
                //userName = user.getSurname();
                if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    thisUser = snapshot.getValue(User.class);
                    userKey = snapshot.getKey();
                    thisUser.setUserKey(snapshot.getKey());
                    //Log.e("userKey", thisUser.getCompletedTasks().toString());
                    userName = user.getName();
                    userSurname = user.getSurname();
                    userGroup = user.getGroup();
                    userAccessLevel = user.getAccessLevel();
                    userPerformances = user.getPerformances();

                    userExpData.postValue(thisUser.getExp());
                    userNameData.postValue(user.getName());
                    userSurnameData.postValue(userSurname);
                    userGroupData.postValue(userGroup);
                    userAccessLevelData.postValue(userAccessLevel);
                    userMutableLiveData.postValue(user);
                    userAvatarPathData.postValue(thisUser.getUserAvatarPath());

                    getCompletedTasks();
/*
                    try {
                        if (snapshot.child("completedTasks").getChildrenCount()<0) {
                            ArrayList<UserTask> startCompletedTasks = new ArrayList<>();
                            startCompletedTasks.add(new UserTask());
                            database.getReference().child("users")
                                    .child(thisUser.getUserKey()).child("completedTasks").setValue(startCompletedTasks);
                        }
                    } catch (Error e){}
 */
                 //   getCompletedTasks();
                    //getTasksOnline();

                    //getTasksOnline();
                    //getCompletedTasks();
                    //Toast.makeText(context, userName+"  ", Toast.LENGTH_SHORT).show(); // works fine

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void getTasksOnline()
    {

        //getCompletedTasks();

        tasks.clear();
        database = FirebaseDatabase.getInstance();
        database.getReference().child("tasks").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                userTask = snapshot.getValue(UserTask.class);
                //Calendar tasksCalendar = Calendar.getInstance();
                //taksCalendar.setTime(snapshot.getValue(UserTask.class).getDate());
                //Date currentDate = new Date();

                boolean flag = false;
                Date currentDate = new Date();
                Log.e("incycle", currentDate.getTime()+"");


                for(int i = completedTasks.size()-1; i > 0; i--)
                {
                    Log.e("in cycle", completedTasks.get(i).getName());
                    if(completedTasks.get(i).getName()
                            .equals(snapshot.getValue(UserTask.class).getName()))
                    {
                        flag = true;
                        Log.e("incycle", "совпадение " +completedTasks.toString());
                        if(currentDate.getTime() - completedTasks.get(i).getDate().getTime()
                                < completedTasks.get(i).getTimeRecharge() * 3600000){
                            Log.e("incycle", "временное совпадение " +completedTasks.get(i).getName());
                            break;
                        }
                        tasks.add(snapshot.getValue(UserTask.class));
                        tasksData.setValue(tasks);
                        Log.e("in cycle", "yes");
                        break;

                    }
                }

                if(flag == false)
                {
                    tasks.add(snapshot.getValue(UserTask.class));
                    tasksData.setValue(tasks);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public ArrayList<Performance> getUserPerformances()
    {

        return null;
    }

    public void updatePerformances(int position)
    {
        //getUser();

        Log.d("совпадение","///////////////////////////////////////////////////////");
        int i = 0;
        for(Performance performance : thisUser.getPerformances())
        {
            i++;
            for(Performance performance1 : tasks.get(position).getPerformances())
            {
                if(performance.getName().equals(performance1.getName()))
                {
                    int curExp = performance.getExperience();
                    performance.setExperience(curExp+performance1.getExperience());
                    //thisUser.getPerformances().get(i).setExperience(performance.getExperience()+performance1.getExperience());
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    if(userKey!=null) {
                        ref.child("users").child(userKey).child("performances").setValue(thisUser.getPerformances());
                        Log.d("совпадение",performance.getName()+" Опыт: " + performance.getExperience());
                    }
                }
            }


        }
        //MainActivity.setCurrentUser(thisUser);
        PerformanceFragment.setPerformancesValues(thisUser.getPerformances());
        tasks.remove(position);
        //adapter.notifyDataSetChanged();

    }

    public void addCompletedTask(final UserTask completedUserTask)
    {
       // getCompletedTasks();
        // Текущее времяg
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        ArrayList<UserTask> ud = new ArrayList<>();
        ArrayList<Performance> strength1 = new ArrayList<>();
        strength1.add(new Performance("Сила", 1));
        ud.add(new UserTask("zeroTask", strength1,
                1,0,0,1));
        completedUserTask.setDate(currentDate);
        completedTasks.add(completedUserTask);


        thisUser.setExp(thisUser.getExp()+completedUserTask.getExp());
        thisUser.setCoins(thisUser.getCoins()+completedUserTask.getCoinsReward());
        userExpData.setValue(thisUser.getExp());
        userMutableLiveData.postValue(thisUser);
        db.getReference().child("users").child(userKey).child("exp")
                .setValue(thisUser.getExp());
        db.getReference().child("users").child(userKey).child("level")
                .setValue(thisUser.getCurrentLevel());

        db.getReference().child("users").child(userKey)
                .child("completedTasks").push().setValue(completedUserTask);

    }

    public void getCompletedTasks()
    {

        //isCompletedTasks = false;
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        //completedTasks.clear();

        //database.getReference().child("users").child(thisUser.getUserKey()).child("completedTasks")
        database.getReference().child("users").child(thisUser.getUserKey()).child("completedTasks").
                addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //isCompletedTasks = true;
                userTask = snapshot.getValue(UserTask.class);
                completedTasks.add(userTask);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("jopa", snapshot.getValue(UserTask.class).getName());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    MutableLiveData<String> userAvatarPathData = new MutableLiveData<>();

    public MutableLiveData<String > getAvatarPath(){
            userAvatarPathData.postValue(thisUser.getUserAvatarPath());
            return userAvatarPathData;
    }

    public void uploadImage(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK) {

            Uri filePath=data.getData();
            if(filePath != null)
            {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                final StorageReference avatarReference = storage.getReference().child("avatars").child(filePath.getLastPathSegment());
                UploadTask uploadTask = avatarReference.putFile(filePath);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }


                        return avatarReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            //item.setImageUrlString(downloadUri.toString());
                            //item.getImageUrlStrings().add(downloadUri.toString());

                            if(thisUser!=null)
                            {
                                database.getReference().child("users").child(thisUser.getUserKey())
                                        .child("userAvatarPath").setValue(downloadUri.toString()); // загружаем адрес картинки в базу данных
                            }
                            Log.e("jopa", downloadUri.toString());
                        }
                        else{}
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                    }
                });
            }
        }
    }
}