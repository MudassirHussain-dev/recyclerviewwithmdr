package com.example.right_choice_project_usman_18_apr_2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.right_choice_project_usman_18_apr_2021.adapters.ProviderUsersAdapter;
import com.example.right_choice_project_usman_18_apr_2021.adapters.ServiceHistoryAdapter;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityProviderListBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityServiceBookingBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityServiceHistoryBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSOrderHistory;
import com.example.right_choice_project_usman_18_apr_2021.model.GSProviderUser;
import com.example.right_choice_project_usman_18_apr_2021.users.userui.ProviderListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceHistoryActivity extends AppCompatActivity {

    String str_getCategory, str_getSubCategory, str_City, str_provider_userName, str_user_userName;
    RequestQueue requestQueue;
    ServiceHistoryAdapter adapter;
    ArrayList<GSOrderHistory> arrayList;
//    String[] st_array_cities = {"Multan", "Khanewal", "Mian Channu"};

    ProgressDialog progressDialog;
    ActivityServiceHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        //SELECT `ID`, `PUserName`, `UUserName`, `Category`, `SubCategory`, `Date`,
        // `ServiceAddress`, `BookingDetial`, `CurrentDateTime`, `Status`
        // FROM `tbl_orders` WHERE 1

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
//        str_user_userName = sharedPreferences.getString("UserName", "");
        Intent intent = getIntent();
        str_user_userName  = intent.getStringExtra("UUserName");
        str_provider_userName = intent.getStringExtra("PUserName");

        requestQueue = Volley.newRequestQueue(this);

        arrayList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.UsersOrderHistory_LINK), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressDialog.dismiss();
                if (!response.equals("")) {
                    ArrayList<GSOrderHistory> arrayList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
//                            SELECT `ID`, `PUserName`, `UUserName`, `Category`, `SubCategory`,
//                            `Date`, `ServiceAddress`, `BookingDetial`, `CurrentDateTime`, `Status` FROM `tbl_orders` WHERE 1
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                           /* GSOrderHistory gsOrderHistory = new GSOrderHistory();
                            GSOrderHistory.setID(jsonObject.getString("ID"));
                            GSOrderHistory.setPUserName(jsonObject.getString("PUserName"));
                            GSOrderHistory.setUUserName(jsonObject.getString("UUserName"));
                            GSOrderHistory.setCategory(jsonObject.getString("Category"));
                            GSOrderHistory.setSubCategory(jsonObject.getString("SubCategory"));
                            GSOrderHistory.setServiceAddress(jsonObject.getString("ServiceAddress"));
                            GSOrderHistory.setDate(jsonObject.getString("Date"));
                            GSOrderHistory.setStatus(jsonObject.getString("Status"));
*/

                           // arrayList.add(gsOrderHistory);
                            //ap sahi tarah data set kro....
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                        binding.rvServiceHistory.setLayoutManager(linearLayoutManager);
                        adapter = new ServiceHistoryAdapter(getApplicationContext(), arrayList);
                        binding.rvServiceHistory.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ServiceHistoryActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ServiceHistoryActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
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