package com.example.right_choice_project_usman_18_apr_2021.users.usersfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentFeedbackBinding;

import static android.content.Context.MODE_PRIVATE;

public class FragmentFeedback extends Fragment {

    float Rating;
    RatingBar ratingBar;
    Button btn_SumbitFeedback;
    View view;
    String str_getCategory, str_City,str_getSubCategory, str_selectedProividers, str_Date, str_SrvicesAddress, str_bookingDetail, str_CurrentDateTime;
    String str_loginUserName;
    FragmentFeedbackBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_feedback, container, false);
        ratingBar = view.findViewById(R.id.rb_Rating);


        //SELECT `ID`, `PUserName`, `UUserName`, `Category`, `SubCategory`, `Date`,
        // `ServiceAddress`, `BookingDetial`, `CurrentDateTime`, `Status`
        // FROM `tbl_orders` WHERE 1
//        SharedPreferences sharedPreferences  = getActivity().getSharedPreferences(getString(R.string.OderSave_LINK), MODE_PRIVATE);
//        str_loginUserName = sharedPreferences.getString("UUserName","");



        btn_SumbitFeedback = view.findViewById(R.id.btn_submitFeedback);

        btn_SumbitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "FragementFeedback.java:  "+ ratingBar.getRating(), Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}