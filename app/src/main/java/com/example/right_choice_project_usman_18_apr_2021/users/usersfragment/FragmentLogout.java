package com.example.right_choice_project_usman_18_apr_2021.users.usersfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.right_choice_project_usman_18_apr_2021.LoginActivity;
import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentLogoutBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentProfileBinding;

public class FragmentLogout extends Fragment {


    FragmentLogoutBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater,container,false);



        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return binding.getRoot();
    }
}
