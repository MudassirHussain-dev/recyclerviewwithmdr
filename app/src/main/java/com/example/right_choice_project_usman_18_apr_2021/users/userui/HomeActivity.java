package com.example.right_choice_project_usman_18_apr_2021.users.userui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.right_choice_project_usman_18_apr_2021.LoginActivity;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityHomeBinding;
import com.example.right_choice_project_usman_18_apr_2021.users.usersfragment.AboutFragment;
import com.example.right_choice_project_usman_18_apr_2021.users.usersfragment.FragmentFeedback;
import com.example.right_choice_project_usman_18_apr_2021.users.usersfragment.FragmentHistory;
import com.example.right_choice_project_usman_18_apr_2021.users.usersfragment.FragmentHome;
import com.example.right_choice_project_usman_18_apr_2021.users.usersfragment.FragmentProfile;
import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.users.usersfragment.FragmentShare;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.right_choice_project_usman_18_apr_2021.R.*;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private DrawerLayout drawer;
    String str_name, str_username, str_email, str_cnic, str_cell, str_experience, str_city, str_address, str_password, str_fashion;
    BottomNavigationView bottomNavigation;
    ActivityHomeBinding binding;
    TextView txt_name, txt_email;
    EditText et_email, et_cell, et_address;
    CircleImageView iv_headerimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);


        toolbar = findViewById(id.toolbar_app_bar_home);
        setSupportActionBar(toolbar);


        bottomNavigation = findViewById(id.bottom_navigation_app_bar_home);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout_homeActivity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, string.app_name, string.app_name);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(id.nav_view_homeActivity);

        View headerView = navigationView.getHeaderView(0);
        txt_name = (TextView) headerView.findViewById(R.id.txt_FullName);
        txt_email = (TextView) headerView.findViewById((R.id.txt_Email));
        iv_headerimage = (CircleImageView) headerView.findViewById(id.iv_headerimage);
        txt_name.setText(sharedPreferences.getString("Name", ""));
        txt_email.setText(sharedPreferences.getString("Email", ""));
        Glide.with(this)  //2
                .load("https://rightchoice042021.000webhostapp.com/" + sharedPreferences.getString("Image", "")) //3
                //.centerCrop() //4
                // .placeholder(R.drawable.ic_image_place_holder) //5
                //.error(R.drawable.ic_broken_image) //6
                // .fallback(R.drawable.ic_no_image) //7
                .into(iv_headerimage); //8

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(HomeActivity.this);
        // getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame, new Fragment_Leave_Application()).commit();
        getSupportFragmentManager().beginTransaction().replace(id.frame_content_home, new FragmentHome()).commit();


    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case id.nav_home:
                            Fragment fragment = new FragmentHome();
                            Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(id.frame_content_home, fragment).commit();
                            return true;
                        case id.nav_profile:
                            Fragment fragment2 = new FragmentProfile();
                            Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(id.frame_content_home, fragment2).commit();
                            return true;
                        case id.nav_service_history:
                            Fragment fragment3 = new FragmentHistory();
                            Toast.makeText(HomeActivity.this, "History", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(id.frame_content_home, fragment3).commit();
                            return true;
                        case id.nav_logout:
                          /*  Fragment fragment4 = new FragmentLogout();
                            Toast.makeText(HomeActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(id.frame_content_home, fragment4).commit();*/

                            return true;
                        case id.nav_share:
                            Fragment fragment5 = new FragmentShare();
                            Toast.makeText(HomeActivity.this, "Share", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(id.frame_content_home, fragment5).commit();
                            return true;
                        case id.nav_about:
                            Fragment fragment6 = new Fragment();
                            Toast.makeText(HomeActivity.this, "About", Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(id.frame_content_home, fragment6).commit();
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (id == R.id.nav_home) {

        /*    Fragment fragment = new Fragment_Home();
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null).replace(R.id.frame, fragment).commit();*/
            getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_content_home, new FragmentHome()).commit();
        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_content_home, new FragmentProfile()).commit();
        } else if (id == R.id.nav_service_history) {
            getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_content_home, new FragmentHistory()).commit();
        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            // builder.setMessage("Choice") .setTitle("Important");

            //Setting message manually and performing action on button click
            builder.setMessage("Do you want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences sharedPreferences = getSharedPreferences(getString(string.PREF_FILE), MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            finish();
                            Toast.makeText(HomeActivity.this, "LOGOUT", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            // finish();
                            dialog.cancel();

                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            //alert.setTitle("AlertDialogExample");
            alert.show();
        } else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_content_home, new AboutFragment()).commit();
        } else if (id == R.id.nav_share) {
            //getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_content_home, new FragmentShare()).commit();
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String body = "RIGHT CHOICE";
            String sub = "Your Subject";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
            myIntent.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(myIntent, "Share Using"));
        }else if (id == R.id.nav_Feedback) {
            getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frame_content_home, new FragmentFeedback()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homeActivity);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}