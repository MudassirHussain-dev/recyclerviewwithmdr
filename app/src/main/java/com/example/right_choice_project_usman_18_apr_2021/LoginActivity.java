package com.example.right_choice_project_usman_18_apr_2021;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.right_choice_project_usman_18_apr_2021.users.userui.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btn_login_to_register, btn_login_to_drawer;
    String str_SelectedUser = "";
    String str_UserName, str_Password;

    ProgressDialog progressDialog;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        SharedPreferences loginSharedPreference = getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        binding.etUserName.setText(loginSharedPreference.getString("UserName",""));
        binding.etPassword.setText(loginSharedPreference.getString("Password",""));

        btn_login_to_register = (Button) findViewById(R.id.btn_noaccount_register);
        btn_login_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                // builder.setMessage("Choice") .setTitle("Important");

                //Setting message manually and performing action on button click
                builder.setMessage("Register As ?")
                        .setCancelable(false)
                        .setPositiveButton("User", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                str_SelectedUser = "User";
                                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                                intent.putExtra("User", str_SelectedUser);
                                startActivity(intent);
                                // finish();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Provider", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                str_SelectedUser = "Provider";
                                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                                intent.putExtra("User", str_SelectedUser);
                                startActivity(intent);
                                // finish();
                                dialog.cancel();

                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                //alert.setTitle("AlertDialogExample");
                alert.show();

            }
        });

        btn_login_to_drawer = (Button) findViewById(R.id.btn_login_LoginActivity);
        btn_login_to_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_UserName = binding.etUserName.getText().toString();
                str_Password = binding.etPassword.getText().toString();

                BackgroundLoginTask();
            }
        });

    }

    private void BackgroundLoginTask() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.Login_LINK), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (response != null) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            // SELECT `ID`, `Name`, `UserName`, `Email`, `CNIC`, `Cell`,
                            // `Experience`, `City`, `Address`, `Image`, `Password`,
                            // `Type`, `Fashion`, `Status`, `DateTime` FROM `tbl_users` WHERE 1

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            arrayList.add(jsonObject.getString("Name"));//0
                            arrayList.add(jsonObject.getString("UserName"));//1
                            arrayList.add(jsonObject.getString("Email"));//2
                            arrayList.add(jsonObject.getString("CNIC"));//3
                            arrayList.add(jsonObject.getString("Cell"));//4
                            arrayList.add(jsonObject.getString("Experience"));//5
                            arrayList.add(jsonObject.getString("City"));//6
                            arrayList.add(jsonObject.getString("Address"));//7
                            arrayList.add(jsonObject.getString("Image"));//8
                            arrayList.add(jsonObject.getString("Password"));//9
                            arrayList.add(jsonObject.getString("Type"));//10
                            arrayList.add(jsonObject.getString("Fashion"));//11
                            arrayList.add(jsonObject.getString("Status"));//12
                            arrayList.add(jsonObject.getString("DateTime"));//13
                        }
                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name",arrayList.get(0));
                        editor.putString("UserName",arrayList.get(1));
                        editor.putString("Email",arrayList.get(2));
                        editor.putString("CNIC",arrayList.get(3));
                        editor.putString("Cell",arrayList.get(4));
                        editor.putString("Experience",arrayList.get(5));
                        editor.putString("City",arrayList.get(6));
                        editor.putString("Address",arrayList.get(7));
                        editor.putString("Image",arrayList.get(8));
                        editor.putString("Password",arrayList.get(9));
                        editor.putString("Type",arrayList.get(10));
                        editor.putString("Fashion",arrayList.get(11));
                        editor.putString("Status",arrayList.get(12));
                        editor.putString("DateTime",arrayList.get(13));
                        editor.commit();
                        editor.apply();
                        if(arrayList.get(10).equals("User")){
                            Toast.makeText(LoginActivity.this, "User", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }else  if (arrayList.get(10).equals("Provider")){
                          //  startActivity(new Intent(getApplicationContext(), ProviderListActivity.class));
                            Toast.makeText(LoginActivity.this, "Provider", Toast.LENGTH_SHORT).show();
                        }else  if(arrayList.get(10).equals("Admin")){
                            Toast.makeText(LoginActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Invalid UserName or Password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Something went wrong....", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UserName", str_UserName);
                params.put("Password", str_Password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}