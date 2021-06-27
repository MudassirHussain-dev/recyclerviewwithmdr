package com.example.right_choice_project_usman_18_apr_2021.users.userui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
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
import com.example.right_choice_project_usman_18_apr_2021.LoginActivity;
import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.adapters.ProviderUsersAdapter;
import com.example.right_choice_project_usman_18_apr_2021.adapters.SubCategoryAdapter;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivityProviderListBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.ActivitySubCategoryBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSProviderUser;
import com.example.right_choice_project_usman_18_apr_2021.model.GSSubCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SubCategoryActivity extends AppCompatActivity {

    String str_getSubCategory;
    String str_providerName;
    String str_getCategory;
    RequestQueue requestQueue;
    SubCategoryAdapter adapter;
    ArrayList<GSSubCategory> arrayList;
    ActivitySubCategoryBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        Intent intent = getIntent();
        str_getCategory = intent.getStringExtra("Fashion");
        Toast.makeText(this, "" + str_getCategory, Toast.LENGTH_SHORT).show();
        requestQueue = Volley.newRequestQueue(this);

        arrayList = new ArrayList<>();

        if (str_getCategory.equals("Carpenter")) {
            GSSubCategory gsSubCategory = new GSSubCategory("Door Installation",R.drawable.door_installation);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Door Repairing",R.drawable.door_repair);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("Table Repairing",R.drawable.table_repair);
            arrayList.add(gsSubCategory2);
            GSSubCategory gsSubCategory3 = new GSSubCategory("Chair Repairing",R.drawable.chair_repairing);
            arrayList.add(gsSubCategory3);
            GSSubCategory gsSubCategory4 = new GSSubCategory("Furniture Polish",R.drawable.furniture_polish);
            arrayList.add(gsSubCategory4);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
             adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Plumber"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Pipeline Water Leakage",R.drawable.pipeline_water_leakage);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Water Tank Leakage",R.drawable.water_tank_leakage);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("Sink spindle Change",R.drawable.sink_spindle_change);
            arrayList.add(gsSubCategory2);
            GSSubCategory gsSubCategory3 = new GSSubCategory("Sink Installation",R.drawable.sink_installation);
            arrayList.add(gsSubCategory3);
            GSSubCategory gsSubCategory4 = new GSSubCategory("Water Motor Installation",R.drawable.water_motor_installation);
            arrayList.add(gsSubCategory4);
            GSSubCategory gsSubCategory5 = new GSSubCategory("Water Tank Installation",R.drawable.water_tank_installation);
            arrayList.add(gsSubCategory5);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
             adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Electrician"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Ceiling Fan Installation",R.drawable.ceiling_fan_installation);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Fan Dimmer Switch Installation",R.drawable.fan_dimme_switch_installation);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("UPS Wiring",R.drawable.ups_wiring);
            arrayList.add(gsSubCategory2);
            GSSubCategory gsSubCategory3 = new GSSubCategory("Switchboard Button and Socket Replacement",R.drawable.switchboard_button_and_socket_replacement);
            arrayList.add(gsSubCategory3);
            GSSubCategory gsSubCategory4 = new GSSubCategory("Water Tank Automatic Switch Installation",R.drawable.water_tank_automatic_switch_installation);
            arrayList.add(gsSubCategory4);
            GSSubCategory gsSubCategory5 = new GSSubCategory("Exhaust Fan Installation",R.drawable.exhaust_fan_installation);
            arrayList.add(gsSubCategory5);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Appliances"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Washing Machine Repairing",R.drawable.washing_machine_repairing);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Dryer Repairing",R.drawable.dryer_repairing);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("Juicer and Grander Repairing",R.drawable.juicer_and_grander_repairing);
            arrayList.add(gsSubCategory2);
            GSSubCategory gsSubCategory3 = new GSSubCategory("Iron Repairing",R.drawable.iron_repairing);
            arrayList.add(gsSubCategory3);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Laundary"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Curtains Wash",R.drawable.curtains_wash);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Blanket (Single) Wash",R.drawable.blanket_single_wash);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("Blanket (Double) Wash",R.drawable.blanket_double_wash);
            arrayList.add(gsSubCategory2);
            GSSubCategory gsSubCategory3 = new GSSubCategory("Bedsheet Simple (Single) Wash",R.drawable.bedsheet_simple_single_wash);
            arrayList.add(gsSubCategory3);
            GSSubCategory gsSubCategory4 = new GSSubCategory("Bedsheet Simple (Double) Wash",R.drawable.bedsheet_simple_double_wash);
            arrayList.add(gsSubCategory4);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Cleaning"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Water Tank Cleaning",R.drawable.water_tank_cleaning);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Carpet Cleaning",R.drawable.carpet_cleaning);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("Blind Cleaning",R.drawable.blind_cleaning);
            arrayList.add(gsSubCategory2);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Painting"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Interior Painting",R.drawable.interior_painting);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Exterior Painting",R.drawable.exterior_painting);
            arrayList.add(gsSubCategory1);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Shifting"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Home Moving",R.drawable.home_moving);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Office Shifting",R.drawable.home_moving);
            arrayList.add(gsSubCategory1);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(str_getCategory.equals("Welder"))
        {
            GSSubCategory gsSubCategory = new GSSubCategory("Gate Repairing",R.drawable.gate_repairing);
            arrayList.add(gsSubCategory);
            GSSubCategory gsSubCategory1 = new GSSubCategory("Window Repairing",R.drawable.window_repairing);
            arrayList.add(gsSubCategory1);
            GSSubCategory gsSubCategory2 = new GSSubCategory("Roller Shutter Repairing",R.drawable.roller_shutter_repairing);
            arrayList.add(gsSubCategory2);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            binding.rvSubCategory.setLayoutManager(linearLayoutManager);
            adapter = new SubCategoryAdapter(getApplicationContext(), arrayList);
            binding.rvSubCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelected() != null) {

                    Intent intent = new Intent(SubCategoryActivity.this, ProviderListActivity.class);
                    intent.putExtra("Fashion",str_getCategory);
                    intent.putExtra("SubFashion",adapter.getSelected().getSubCategory());
                    intent.putExtra("ProviderName",str_providerName);
                    startActivity(intent);
                    Toast.makeText(SubCategoryActivity.this, ""+adapter.getSelected().getSubCategory(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SubCategoryActivity.this, "No Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}