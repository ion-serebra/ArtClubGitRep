package com.oshaev.artclub.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oshaev.artclub.AllUsersListActivity;
import com.oshaev.artclub.LoginActivity;
import com.oshaev.artclub.R;
import com.oshaev.artclub.User;
import com.oshaev.artclub.databinding.ActivityUserProfilePublicBinding;
import com.oshaev.artclub.databinding.FragmentUserInfoBinding;
import com.oshaev.artclub.ui.MainActivity.MainActivity;
import com.oshaev.artclub.ui.notifications.fragments.PerformanceFragment;
import com.oshaev.artclub.usertasks.Performance;
import com.oshaev.artclub.usertasks.TasksRecyclerViewAdapter;
import com.oshaev.artclub.usertasks.UserTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment {


    private static final int REQUEST_CODE_IMAGE = 123;


    public static String usrNm;
    private static int userAccessLevel;
    public static  User thisUser;



    private TextView userSurname;
    private TextView userGroup;
    private ImageButton addPhotoButton;
    private Button userListButton;
    private Button generalMailingButton;
    TextView accessLevelTextView;
    RecyclerView tasksRecyclerView;
    TasksRecyclerViewAdapter adapter;
    LinearLayoutManager manager;
    ArrayList<UserTask> tasks;

    public static void setUserAccessLevel(int userAccessLevel) {
        ProfileFragment.userAccessLevel = userAccessLevel;
    }
    public static int getUserAccessLevel() {
        return userAccessLevel;
    }

    static private ProfileViewModel profileViewModel;
    private TextView userName;



    private FragmentUserInfoBinding fragmentUserInfoBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Toast.makeText(getContext(), "start", Toast.LENGTH_SHORT).show();

        //thisUser = new User();


        //эту строчку нужно убрать, чтобы Data Binding заработала
        //View root = inflater.inflate(R.layout.fragment_user_info, container, false);

        fragmentUserInfoBinding =
                DataBindingUtil.inflate
                        (inflater, R.layout.fragment_user_info, container, false);
         View root= fragmentUserInfoBinding.getRoot();

        if(profileViewModel==null) {
            profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
            Log.d("profileViewModel", "null");
        }

        userName = root.findViewById(R.id.userName);
        userSurname = root.findViewById(R.id.userSurname);
        userGroup= root.findViewById(R.id.userGroup);
        accessLevelTextView = root.findViewById(R.id.accessLevelTextView);
        userListButton = root.findViewById(R.id.superUsersList);
        addPhotoButton = root.findViewById(R.id.userAvatar);

        userListButton = root.findViewById(R.id.superUsersList);
        generalMailingButton = root.findViewById(R.id.generalMailingButton);

        tasksRecyclerView = root.findViewById(R.id.tasksRecyclerView);
        tasks = new ArrayList<UserTask>();

        ProfileFragmentButtonsHandler profileFragmentButtonsHandler =
                new ProfileFragmentButtonsHandler(getContext());
        fragmentUserInfoBinding.setButtonsHandler(profileFragmentButtonsHandler);

        manager = new LinearLayoutManager(getContext());
        adapter = new TasksRecyclerViewAdapter(profileViewModel.tasks);
        tasksRecyclerView.setLayoutManager(manager);
        tasksRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent, "Выберите картинку"),
                        REQUEST_CODE_IMAGE);
            }
        });

        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                userName.setText(s);
            }
        });
/*
        profileViewModel.getExp().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer exp) {
                fragmentUserInfoBinding.setExp(exp.toString());
                Log.e("jopa", exp.toString())   ;
            }
        });
 */

        profileViewModel.getCurrentUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                fragmentUserInfoBinding.setExp(user.getThisLevelExp().toString());
                fragmentUserInfoBinding.setLevel(user.getCurrentLevelString());
                fragmentUserInfoBinding.setUser(user);
                fragmentUserInfoBinding.setProgress(user.getThisLevelExp());
                fragmentUserInfoBinding.setProgressMax(user.getExpSegment());
                if(user.getAccessLevel()>=3)
                {
                    userListButton.setVisibility(View.VISIBLE);
                }
                else{
                    userListButton.setVisibility(View.INVISIBLE);
                }
            }
        });


        //fragmentUserInfoBinding.setExp(20);
        profileViewModel.getSurname().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                userSurname.setText(s);
            }
        });

        profileViewModel.getGroup().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                userGroup.setText(s);
            }
        });

        profileViewModel.getUser();
        //profileViewModel.getTasksOnline();
        //Log.e("userKey", profileViewModel.userKey+" or null");

        profileViewModel.tasksData.observe(getViewLifecycleOwner(), new Observer<ArrayList<UserTask>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserTask> s) {
               // tasks = s;
               // runLayoutAnimation(tasksRecyclerView);
               // adapter.notifyDataSetChanged();
            }
        });

        profileViewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<ArrayList<UserTask>>() {
            @Override
            public void onChanged(ArrayList<UserTask> userTasks) {
                tasks = userTasks;
                runLayoutAnimation(tasksRecyclerView);
                adapter.notifyDataSetChanged();
            }
        });

        /*
        profileViewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<ArrayList<UserTask>>()
        {
            @Override
            public void onChanged(ArrayList<UserTask> userTasks) {
                tasks = userTasks;
                for(UserTask userTask : userTasks) {
                    Log.d("tasks", userTask.getPerformances().get(0).getName());
                }
            }
        });

         */

        //tasks = profileViewModel.tasks;


        profileViewModel.getAvatarPath().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //if(profileViewModel.getAvatarPath()!=null) {
                if(s!=null) {
                    Glide.with(addPhotoButton.getContext()).
                            load(s).into(addPhotoButton);
                    addPhotoButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
                //}
            }
        });






        adapter.setOnButtonClickListener(new TasksRecyclerViewAdapter.OnButtonClickListener() {
            @Override
            public void OnClick(int position) {
                // thisUser.getMap().put()
                //  tasks.get(position).getPerformanceIntegerHashMap().get(performance1);
                profileViewModel.addCompletedTask(profileViewModel.tasks.get(position));
                profileViewModel.updatePerformances(position);
                //tasks.remove(position);
                //profileViewModel.removeTask(position);
                Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                 // Log.d("eee", "performance " + tasks.get(position).getStringIntegerHashMap() + " name:");

            }
        });




        Button completedTasksButton  = root.findViewById(R.id.completedTasksButton);


        completedTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // слушатель нажатия на кнопку выполнения задания
                Intent intent = new Intent(getContext(), CompletedTasksActivity.class);
                intent.putExtra("userKey", profileViewModel.userKey);
                startActivity(intent);
            }
        });

        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllUsersListActivity.class);
                intent.putExtra("source", "adminProfile");
                startActivity(intent);
            }
        });





/*
        TextView logOutButton = root.findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });






        //final TextView textView = root.findViewById(R.id.text_notifications);

        TextView logOutButton = root.findViewById(R.id.logOutButton);



        accessLevelTextView = root.findViewById(R.id.accessLevelTextView);

        userListButton = root.findViewById(R.id.superUsersList);
        generalMailingButton = root.findViewById(R.id.generalMailingButton);




        addPhotoButton = root.findViewById(R.id.userAvatar);






        //database = FirebaseDatabase.getInstance();
        //usersDatabaseReference = database.getReference().child("users");



        usersChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    User user = snapshot.getValue(User.class);

                    if (user.getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        thisUser = user;
                        userName.setText("Имя: "+ user.getName());
                        Toast.makeText(getContext(), user.getName(), Toast.LENGTH_SHORT).show();

                        /*
                        accessLevel = user.getAccessLevel();
                        userAccessLevel = accessLevel;
                        accessLevelTextView.setText("Уровень доступа: " + accessLevel);
                        userName.setText("Имя: " + user.getName());
                        usrNm = user.getName();
                        //userSurname.setText("Фамилия: " + user.getSurname());
                        userGroup.setText("Группа: " + user.getGroup());
                        userKey = snapshot.getKey();

                        if (accessLevel == 4) {
                            userListButton.setVisibility(View.VISIBLE);
                            generalMailingButton.setVisibility(View.VISIBLE);
                        } else {
                            userListButton.setVisibility(View.GONE);
                            generalMailingButton.setVisibility(View.GONE);
                        }





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
        };
        usersDatabaseReference.addChildEventListener(usersChildEventListener);


        generalMailingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AllUsersListActivity.class);
                intent.putExtra("source", "adminProfile");
                startActivity(intent);
            }
        });





        userSurname.setText(notificationsViewModel.getUserName().getValue());



        //userName.setText("Имя: " +MainActivity.userName);
        //userSurname.setText("Фамилия: " +MainActivity.userSurname);
        //userGroup.setText("Группа: " +MainActivity.userGroup);


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });









        ArrayList<Performance> perf1 = new ArrayList<>();
        perf1.add(new Performance("Мудрость", 2));

        ArrayList<Performance> perf2 = new ArrayList<>();
        perf2.add(new Performance("Мудрость", 1));
        perf2.add(new Performance("Интеллект", 1));

        ArrayList<Performance> health3 = new ArrayList<>();
        health3.add(new Performance("Здоровье", 2));

        ArrayList<Performance> charisma1 = new ArrayList<>();
        charisma1.add(new Performance("Харизма", 2));

        ArrayList<Performance> perception1 = new ArrayList<>();
        perception1.add(new Performance("Восприятие", 1));

        ArrayList<Performance> memory1 = new ArrayList<>();
        memory1.add(new Performance("Память", 1));

        ArrayList<Performance> willpower1 = new ArrayList<>();
        willpower1.add(new Performance("Сила воли", 1));

        ArrayList<Performance> willpowerHealth = new ArrayList<>();
        willpowerHealth.add(new Performance("Сила воли", 1));
        willpowerHealth.add(new Performance("Здоровье", 1));

        ArrayList<Performance> agility1 = new ArrayList<>();
        agility1.add(new Performance("Ловкость", 1));

        ArrayList<Performance> personalGrowth1 = new ArrayList<>();
        personalGrowth1.add(new Performance("Личностный рост", 1));

        ArrayList<Performance> strength1 = new ArrayList<>();
        strength1.add(new Performance("Сила", 1));



        tasks = new ArrayList<UserTask>();

        tasks.add(new UserTask("Почистить зубы", perf1, 2, 0, 1, 0));
        tasks.add(new UserTask("Сделать зарядку", health3,2, 1, 3, 0));
        tasks.add(new UserTask("Позапоминать людей в метро", memory1,2, 1, 3, 0));
        tasks.add(new UserTask("Утренняя пробежка", health3,6, 3, 5, 2));
        tasks.add(new UserTask("10 минут медитации", willpowerHealth,4, 3, 4, 0));
        tasks.add(new UserTask("Дать покой глазам на 15 минут", health3,10, 4, 7, 5));
        tasks.add(new UserTask("Связаться с родственниками", personalGrowth1,10, 4, 7, 5));
        tasks.add(new UserTask("Изучать иностранный язык 30 минут", health3,10, 4, 7, 5));
        tasks.add(new UserTask("Прочитать 25 страниц новой книги", perf1,10, 4, 7, 5));
        tasks.add(new UserTask("Просмотреть свои задачи", perf1,10, 4, 7, 5));
        tasks.add(new UserTask("Силовая тренировка", strength1,6, 3, 5, 2));



        tasksRecyclerView = root.findViewById(R.id.tasksRecyclerView);

        manager = new LinearLayoutManager(getContext());
        adapter = new TasksRecyclerViewAdapter(tasks);
        adapter.setOnItemClickListener(new TasksRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int position) {

                Intent intent = new Intent(getContext(), UserTaskActivity.class);

                UserTask userTask = tasks.get(position);
                intent.putExtra("reward", userTask.getReward());
                intent.putExtra("difficulty", userTask.getDifficulty());
                intent.putExtra("priority", userTask.getPriority());
                intent.putExtra("fear", userTask.getFear());
                intent.putExtra("title", userTask.getName());
                startActivity(intent);



                usersChildEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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
                };
            }
        });





        adapter.setOnButtonClickListener(new TasksRecyclerViewAdapter.OnButtonClickListener() {
            @Override
            public void OnClick(int position) {
              // thisUser.getMap().put()
                //  tasks.get(position).getPerformanceIntegerHashMap().get(performance1);

                   // Log.d("eee", "performance " + tasks.get(position).getStringIntegerHashMap() + " name:");

                Log.d("совпадение","///////////////////////////////////////////////////////");
                for(Performance performance : thisUser.getPerformances())
                {

                    for(Performance performance1 : tasks.get(position).getPerformances())
                    {
                        if(performance.getName().equals(performance1.getName()))
                        {
                            performance.setExperience(performance.getExperience()+performance1.getExperience());
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            if(userKey!=null) {
                                ref.child("users").child(userKey).setValue(thisUser);
                            }
                        }
                    }
                    Log.d("совпадение",performance.getName()+" Опыт: " + performance.getExperience());
                }
                MainActivity.setCurrentUser(thisUser);
                PerformanceFragment.setPerformancesValues(thisUser.getPerformances());
                tasks.remove(position);
                adapter.notifyDataSetChanged();


            }
        });

        manager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }


        };


        //tasksRecyclerView.setNestedScrollingEnabled(false);
        tasksRecyclerView.setLayoutManager(manager);
        tasksRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        */
        return root;


    }

    public class ProfileFragmentButtonsHandler
    {
        Context context;

        public ProfileFragmentButtonsHandler(Context context) {
            this.context = context;
        }

        public void onSettingsClicked(View view)
        {
            Intent intent = new Intent(context, UserSettingsActivity.class);
            startActivity(intent);
        }

    }

    private void runLayoutAnimation(RecyclerView recyclerView)
    {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        profileViewModel.uploadImage(requestCode, resultCode, data);

        }



/*
    private void updatePerformances(int position)
    {

        Log.d("совпадение","///////////////////////////////////////////////////////");
        for(Performance performance : thisUser.getPerformances())
        {

            for(Performance performance1 : tasks.get(position).getPerformances())
            {
                if(performance.getName().equals(performance1.getName()))
                {
                    performance.setExperience(performance.getExperience()+performance1.getExperience());
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    if(userKey!=null) {
                        ref.child("users").child(userKey).setValue(thisUser);
                    }
                }
            }
            Log.d("совпадение",performance.getName()+" Опыт: " + performance.getExperience());
        }
        //MainActivity.setCurrentUser(thisUser);
        PerformanceFragment.setPerformancesValues(thisUser.getPerformances());
        tasks.remove(position);
        adapter.notifyDataSetChanged();

    }

 */


/*
    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getContext(), "pause", Toast.LENGTH_SHORT).show();
    }
 */
}
