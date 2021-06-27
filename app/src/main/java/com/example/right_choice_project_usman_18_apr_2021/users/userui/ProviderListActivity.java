package com.example.right_choice_project_usman_18_apr_2021.users.userui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.ServiceBookingActivity;
import com.example.right_choice_project_usman_18_apr_2021.adapters.ProviderUsersAdapter;
import com.example.right_choice_project_usman_18_apr_2021.adapters.SubCategoryAdapter;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityProviderListBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSProviderUser;
import com.example.right_choice_project_usman_18_apr_2021.model.GSSubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProviderListActivity extends AppCompatActivity {

    String str_getCategory, str_getSubCategory, str_City;
    RequestQueue requestQueue;
    ProviderUsersAdapter adapter;
    ArrayList<GSProviderUser> arrayList;
    String[] st_array_cities = {"Multan", "Khanewal", "Mian Channu"};

    ProgressDialog progressDialog;

    ActivityProviderListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProviderListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        Intent intent = getIntent();
        str_getCategory = intent.getStringExtra("Fashion");
        str_getSubCategory = intent.getStringExtra("SubFashion");

        requestQueue = Volley.newRequestQueue(this);


        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, st_array_cities);
        binding.spCity.setAdapter(cityAdapter);

       /* binding.spCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                str_City = binding.spCity.getSelectedItem().toString();
                Toast.makeText(ProviderListActivity.this, ""+str_City, Toast.LENGTH_SHORT).show();
            }
        });*/


        binding.spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                str_City = adapter.getItemAtPosition(i).toString();
                //or this can be also right: selecteditem = level[i];
                BackgroundGetProviderListTask();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected() != null) {

                    Intent intent = new Intent(ProviderListActivity.this, ServiceBookingActivity.class);
                    intent.putExtra("Fashion", str_getCategory);
                    intent.putExtra("SubFashion", str_getSubCategory);
                    intent.putExtra("Provider", adapter.getSelected().getUserName());
                    intent.putExtra("ProviderName",adapter.getSelected().getName());
                    intent.putExtra("City", adapter.getSelected().getCity());
                    startActivity(intent);
                    Toast.makeText(ProviderListActivity.this, "" + adapter.getSelected().getName(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ProviderListActivity.this, "No Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void BackgroundGetProviderListTask() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.GetProvers_LINK), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (!response.equals("")) {
                    ArrayList<GSProviderUser> arrayList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            GSProviderUser gsProviderUser = new GSProviderUser();
                            gsProviderUser.setID(jsonObject.getString("ID"));
                            gsProviderUser.setName(jsonObject.getString("Name"));
                            gsProviderUser.setUserName(jsonObject.getString("UserName"));
                            gsProviderUser.setImage(jsonObject.getString("Image"));
                            gsProviderUser.setFashion(jsonObject.getString("Fashion"));
                            gsProviderUser.setCity(jsonObject.getString("City"));
                            gsProviderUser.setAddress(jsonObject.getString("Address"));
                            gsProviderUser.setExperience(jsonObject.getString("Experience"));
                            arrayList.add(gsProviderUser);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                        binding.rvProviderList.setLayoutManager(linearLayoutManager);
                        adapter = new ProviderUsersAdapter(getApplicationContext(), arrayList);
                        binding.rvProviderList.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ProviderListActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ProviderListActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Fashion", str_getCategory);
                params.put("City", str_City);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}

