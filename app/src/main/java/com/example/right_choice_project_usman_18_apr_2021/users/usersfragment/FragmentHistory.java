package com.example.right_choice_project_usman_18_apr_2021.users.usersfragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.ServiceHistoryActivity;
import com.example.right_choice_project_usman_18_apr_2021.adapters.ProviderUsersAdapter;
import com.example.right_choice_project_usman_18_apr_2021.adapters.ServiceHistoryAdapter;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentHistoryBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentLogoutBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSOrderHistory;
import com.example.right_choice_project_usman_18_apr_2021.model.GSProviderUser;
import com.example.right_choice_project_usman_18_apr_2021.users.userui.ProviderListActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHistory extends Fragment {


    TextView txt_user_username;
    Button btn_show;
    View view;
    String str_user_username;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    ArrayList<GSOrderHistory> arrayList;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);


       // btn_show = view.findViewById(R.id.btn_OK);
        recyclerView = view.findViewById(R.id.rv_serviceHistory);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait..");
        progressDialog.setCanceledOnTouchOutside(false);

        requestQueue = Volley.newRequestQueue(getContext());

        //SELECT `ID`, `PUserName`, `UUserName`, `Category`, `SubCategory`, `Date`,
        // `ServiceAddress`, `BookingDetial`, `CurrentDateTime`, `Status`
        // FROM `tbl_orders` WHERE 1
//        txt_user_username = view.findViewById(R.id.txt_user_name);
        arrayList = new ArrayList<>();
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
//        txt_ID.setText(sharedPreferences.getString("ID",""));
//        txt_user_username.setText(sharedPreferences.getString("UUserName",""));
//        txt_PuserName.setText(sharedPreferences.getString("PUserName",""));
//        txt_Category.setText(sharedPreferences.getString("Category",""));
//        txt_subCategory.setText(sharedPreferences.getString("SubCategory",""));
//        txt_bookAddress.setText(sharedPreferences.getString("ServiceAddress",""));
//        txt_servicedate.setText(sharedPreferences.getString("Date",""));
//        txt_servicestatus.setText(sharedPreferences.getString("Status",""));



        BackgroundServiceHistoryTask();




//work ker raha ha?
//        bhi php file invalid show kr rahi hy



        return view;
    }

    private void BackgroundServiceHistoryTask() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.UsersOrderHistory_LINK), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                if (!response.equals("invalid")) {
                    ArrayList<GSOrderHistory> arrayList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            GSOrderHistory gsOrderHistory = new GSOrderHistory();
                            gsOrderHistory.setID(jsonObject.getString("ID"));
                            gsOrderHistory.setProviderName(jsonObject.getString("ProviderName"));
                            gsOrderHistory.setUserName(jsonObject.getString("UserName"));
                            gsOrderHistory.setCategory(jsonObject.getString("Category"));
//                            gsOrderHistory.setSubCategory(jsonObject.getString("SubCategory"));
                            gsOrderHistory.setServiceAddress(jsonObject.getString("ServiceAddress"));
                            gsOrderHistory.setDate(jsonObject.getString("Date"));
                            gsOrderHistory.setStatus(jsonObject.getString("Status"));
                            arrayList.add(gsOrderHistory);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        ServiceHistoryAdapter          adapter = new ServiceHistoryAdapter(getActivity(), arrayList);
                        recyclerView.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Record not found", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("UUserName", "b");
//                params.put("City", str_City);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}