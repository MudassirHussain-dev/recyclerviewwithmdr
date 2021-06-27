package com.example.right_choice_project_usman_18_apr_2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityLoginBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityProfileEditBinding;
import com.example.right_choice_project_usman_18_apr_2021.users.userui.HomeActivity;

import java.util.HashMap;
import java.util.Map;

public class ProfileEditActivity extends AppCompatActivity {

    String str_name, str_username, str_email, str_cnic, str_cell, str_experience, str_city, str_address, str_password, str_fashion;
    ActivityProfileEditBinding binding;
    Button btn_save_profile_info;
    RequestQueue requestQueue;
    Uri filepath;
    ProgressDialog progressDialog;
    String str_LoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

//        String str_Selecteduser = getIntent().getStringExtra("User");
//
//
//        //ll_Category = (LinearLayout) findViewById(R.id.ll_category);
//        Toast.makeText(this, "" + str_Selecteduser, Toast.LENGTH_SHORT).show();
//        if (str_Selecteduser.equals("Provider")) {
//            binding.llCategory.setVisibility(View.VISIBLE);
//        }

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        str_LoginUser = sharedPreferences.getString("UserName", "");

        binding.btnSaveProfileProfileEditActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_email = binding.etEmailProfileEditActivity.getText().toString().trim();
                str_cell = binding.etCellProfileEditActivity.getText().toString().trim();
                str_address = binding.etAddressProfileEditActivity.getText().toString().trim();
                BackgroundAddUsersProviderTask();
            }
        });

    }

    private void BackgroundAddUsersProviderTask() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.usersDataEdit_LINK),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if (response.equals("True")) {
                            Toast.makeText(getApplicationContext(), "Profile Data Changes Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Please try again..", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something went wrongs....", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // SELECT `ID`, `Name`, `UserName`, `Email`, `CNIC`, `Cell`, `Address`, `Password`, `Type`, `Status`, `DateTime` FROM `tbl_users` WHERE 1

                params.put("Email", str_email);
                params.put("UserName",str_LoginUser );/// run appl
                params.put("Cell", str_cell);
                params.put("Address", str_address);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ProfileEditActivity.this);
        requestQueue.add(stringRequest);
    }
}