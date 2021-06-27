package com.example.right_choice_project_usman_18_apr_2021.users.usersfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.right_choice_project_usman_18_apr_2021.ProfileEditActivity;
import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentHomeBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentProfileBinding;

import static android.content.Context.MODE_PRIVATE;

public class FragmentProfile extends Fragment {

    FragmentProfileBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);

        binding.txtFullName.setText(sharedPreferences.getString("Name",""));
        binding.txtEmail.setText(sharedPreferences.getString("Email",""));
        binding.txtCnic.setText(sharedPreferences.getString("CNIC",""));
        binding.txtCell.setText(sharedPreferences.getString("Cell",""));
        binding.txtCity.setText(sharedPreferences.getString("City",""));
        binding.txtAddress.setText(sharedPreferences.getString("Address",""));
        binding.txtType.setText(sharedPreferences.getString("Type",""));
       // binding.ivImage.setImageResource(Integer.parseInt(sharedPreferences.getString("Image","")));
        Glide.with(this)  //2
                .load("https://rightchoice042021.000webhostapp.com/" +sharedPreferences.getString("Image","")) //3
                //.centerCrop() //4
                // .placeholder(R.drawable.ic_image_place_holder) //5
                //.error(R.drawable.ic_broken_image) //6
                // .fallback(R.drawable.ic_no_image) //7
                .into(binding.ivImage); //8

        binding.btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileEditActivity.class));
            }
        });

        return binding.getRoot();
    }
}
