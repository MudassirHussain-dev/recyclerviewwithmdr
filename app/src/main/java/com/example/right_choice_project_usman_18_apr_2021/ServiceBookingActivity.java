package com.example.right_choice_project_usman_18_apr_2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityServiceBookingBinding;
import com.example.right_choice_project_usman_18_apr_2021.users.userui.HomeActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ServiceBookingActivity extends AppCompatActivity {

    String str_getCategory, str_City,str_getSubCategory, str_selectedProividers, str_Date, str_SrvicesAddress, str_bookingDetail, str_CurrentDateTime;
    String str_loginUserName, str_providerName, str_login_name;


    RequestQueue requestQueue;


    Calendar calendar = Calendar.getInstance();
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int month = calendar.get(Calendar.MONTH);
    final int year = calendar.get(Calendar.YEAR);

    @NonNull
    ActivityServiceBookingBinding binding;
    ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        str_loginUserName = sharedPreferences.getString("UserName", "");
        str_login_name = sharedPreferences.getString("Name","");

        Intent intent = getIntent();
        str_getCategory = intent.getStringExtra("Fashion");
        str_getSubCategory = intent.getStringExtra("SubFashion");
//        str_providerName = intent.getStringExtra("Name");
        str_selectedProividers = intent.getStringExtra("Provider");
        str_City = intent.getStringExtra("City");
        Toast.makeText(this, "" + str_getCategory+"\n"+str_getSubCategory+"\n"+str_selectedProividers, Toast.LENGTH_SHORT).show();


        requestQueue = Volley.newRequestQueue(this);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        str_CurrentDateTime = dtf.format(now);


        binding.etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ServiceBookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                        calendar.set(year, month, dayOfMonth);

                        //  String date = dayOfMonth + "-" + month + "-" + year;
                        String dateString = sdf.format(calendar.getTime()).toUpperCase();
                        binding.etDate.setText(dateString);
                    }
                }, year, month, day);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

                datePickerDialog.show();
            }
        });


        binding.btnOrderSAve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Date = binding.etDate.getText().toString();
                str_SrvicesAddress = binding.etSrvicesAddress.getText().toString();
                str_bookingDetail = binding.etBookingDetail.getText().toString();

                BackgroundOrderSubmitTask();


            }
        });


    }

    private void BackgroundOrderSubmitTask() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.OderSave_LINK), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(ServiceBookingActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ServiceBookingActivity.this, "Something went wrongs...please try again later", Toast.LENGTH_SHORT).show();
            }
        }) {
            //SELECT `ID`, `PUserName`, `UUserName`, `Category`, `SubCategory`, `Date`, `ServiceAddress`, `BookingDetial`,
            // `City`, `CurrentDateTime`, `Status`, `Feedback` FROM `tbl_orders` WHERE 1
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UUserName", str_loginUserName);
//                params.put("ProviderName", str_providerName);
                params.put("Category", str_getCategory);
//                params.put("UserName", str_login_name);
                params.put("SubCategory", str_getSubCategory);
                params.put("PUserName", str_selectedProividers);
                params.put("Date", str_Date);
                params.put("ServiceAddress", str_SrvicesAddress);
                params.put("BookingDetial", str_bookingDetail);
                params.put("CurrentDateTime", str_CurrentDateTime);
                params.put("Status", "Pending");
                return params;
            }
        };
    requestQueue.add(stringRequest);
    }


}