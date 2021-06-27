package com.example.right_choice_project_usman_18_apr_2021;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityRegisterBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    //declaration
    //LinearLayout ll_Category;
    String str_Selecteduser;
    String str_currentDateTimeString;
    String str_name, str_username, str_email, str_cnic, str_cell,str_experience, str_city, str_address, str_password, str_fashion;
    Button btn_register_to_login, btn_register;
    Spinner sp_city, sp_Category;
    String[] st_array_cities = {"Multan", "Khanewal", "Mian Channu"};
    String[] st_array_category = {"Carpenter", "Electrician", "Plumber", "Welder", "Appliances", "Laundry", "Cleaning", "Shifting", "Painting"};

    public static int RC_CAMERA_PERMISSIONS = 500;
    AlertDialog alertDialog_Camera;
    Uri filepath;

    Bitmap bitmaps;


    ActivityRegisterBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);


        str_Selecteduser = getIntent().getStringExtra("User");


        //ll_Category = (LinearLayout) findViewById(R.id.ll_category);
        Toast.makeText(this, "" + str_Selecteduser, Toast.LENGTH_SHORT).show();
        if (str_Selecteduser.equals("Provider")) {
            binding.llCategory.setVisibility(View.VISIBLE);
        }


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        str_currentDateTimeString = dtf.format(now);


        //sp_Category = (Spinner) findViewById(R.id.sp_select_category);

        ArrayAdapter arr_Category = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, st_array_category);
        binding.spSelectCategory.setAdapter(arr_Category);

        //Register Button Implementation
        btn_register_to_login = (Button) findViewById(R.id.btn_haveAccount_LOGIN);
        btn_register_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move from RegisterActicity to LoginActivity
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, st_array_cities);
        binding.spSelectCityRegisterActivity.setAdapter(adapter);

        binding.ibTakeSamplingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundPermissionCamera();
            }
        });

        btn_register = (Button) findViewById(R.id.btn_register_RegisterActivity);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move from RegisterActicity to LoginActivity
                str_name = binding.etNameRegisterActivity.getText().toString().trim();
                str_username = binding.etUsernameRegisterActivity.getText().toString().trim();
                str_email = binding.etEmailRegisterActivity.getText().toString().trim();
                str_cnic = binding.etCnicRegisterActivity.getText().toString().trim();
                str_cell = binding.etCellRegisterActivity.getText().toString().trim();
                str_experience = binding.etExperienceRegisterActivity.getText().toString().trim();
                str_address = binding.etAddressRegisterActivity.getText().toString().trim();
                str_password = binding.etPasswordRegisterActivity.getText().toString().trim();
                str_city = binding.spSelectCityRegisterActivity.getSelectedItem().toString();
                if (str_Selecteduser.equals("Provider")) {
                    str_fashion = binding.spSelectCategory.getSelectedItem().toString();
                } else {
                    str_fashion = "User";
                }

              /*  if (str_name.isEmpty() || str_username.isEmpty() || str_email.isEmpty() || str_cnic.isEmpty() || str_cell.isEmpty() || str_address.isEmpty() || str_password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();

                } else {

                }
                */
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
                 bitmaps = ((BitmapDrawable)binding.civTakeSamplingImage.getDrawable()).getBitmap();
               // signupTask();
               BackgroundAddUsersProviderTask();



            }
        });


    }

//    private void signupTask() {
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.signup_LINK), new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                progressDialog.dismiss();
//                if (response.equals("True")) {
//                    Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please try again..", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
////                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
////                finish();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), "Something went wrongs....", Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                // SELECT `ID`, `Name`, `UserName`, `Email`, `CNIC`, `Cell`, `Address`, `Password`, `Type`, `Status`, `DateTime` FROM `tbl_users` WHERE 1
//                params.put("Name", str_name);
//                params.put("Image", getStringImage(bitmaps));
//
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }

    private void BackgroundPermissionCamera() {
        int permissionCheck = ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.CAMERA);

        // do we have camera permission?
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //BackgroundSelectImage();
            selectImage();
        } else {
            //we don't have it, request camera permission from system
            ActivityCompat.requestPermissions(RegisterActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    RC_CAMERA_PERMISSIONS);
        }
    }

    private void selectImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(RegisterActivity.this).inflate(R.layout.row_camera_layout, viewGroup, false);

        Button btn_CustomCamera = dialogView.findViewById(R.id.btn_customcamera);
        Button btn_CustomGallery = dialogView.findViewById(R.id.btn_customgellery);
        Button btn_CustomCancel = dialogView.findViewById(R.id.btn_customcancel);

        btn_CustomCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
                alertDialog_Camera.dismiss();
            }
        });

        btn_CustomGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                alertDialog_Camera.dismiss();
            }
        });

        btn_CustomCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog_Camera.dismiss();
            }
        });

        builder.setView(dialogView);
        alertDialog_Camera = builder.create();
        alertDialog_Camera.setCanceledOnTouchOutside(false);
        alertDialog_Camera.show();
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imagebytes = baos.toByteArray();
        String encodeimage = Base64.encodeToString(imagebytes, Base64.DEFAULT);
        return encodeimage;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bitmap;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RegisterActivity.this.RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(RegisterActivity.this.getContentResolver(), filepath);
                //  bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, true);
                binding.civTakeSamplingImage.setImageURI(filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == 0 && resultCode == RegisterActivity.this.RESULT_OK && data != null) {
            bitmap = (Bitmap) data.getExtras().get("data");
            //bitmap = Bitmap.createScaledBitmap(bitmap, 250, 250, true);
            binding.civTakeSamplingImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == RC_CAMERA_PERMISSIONS && filepath != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // required permissions granted, start crop image activity
            BackgroundPermissionCamera();
            //startCropImageActivity(mCropImageUri);
            selectImage();


        } /*else {
            Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
        }*/

    }

    private void BackgroundAddUsersProviderTask() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.usersRegister_LINK), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (response.equals("True")) {
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please try again..", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
//               startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
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
                params.put("Name", str_name);
                params.put("UserName", str_username);
                params.put("Email", str_email);
                params.put("CNIC", str_cnic);
                params.put("Cell", str_cell);
                params.put("Experience", str_experience);
                params.put("City", str_city);
                params.put("Address", str_address);
                params.put("Password", str_password);
                params.put("Type", str_Selecteduser);
                params.put("Fashion", str_fashion);
                params.put("Status", "ACTIVE");
                params.put("Image", getStringImage(bitmaps));
                params.put("DateTime", str_currentDateTimeString);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}