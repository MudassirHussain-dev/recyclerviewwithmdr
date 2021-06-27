package com.example.right_choice_project_usman_18_apr_2021.users.usersfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.right_choice_project_usman_18_apr_2021.R;
import com.example.right_choice_project_usman_18_apr_2021.databinding.FragmentHomeBinding;
import com.example.right_choice_project_usman_18_apr_2021.users.userui.ProviderListActivity;
import com.example.right_choice_project_usman_18_apr_2021.users.userui.SubCategoryActivity;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHome extends Fragment {
    FragmentHomeBinding binding;
    //ImageButton im_carprenter;
    String str_Category;
  //View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
      //  im_carprenter = view.findViewById(R.id.btn_Carpenter);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
       binding.txtFullName.setText(sharedPreferences.getString("Name",""));
       binding.txtMobilenumber.setText(sharedPreferences.getString("Cell",""));
       binding.txtCity.setText(sharedPreferences.getString("City",""));
        Toast.makeText(getActivity(), ""+sharedPreferences.getString("City",""), Toast.LENGTH_SHORT).show();



        binding.btnCarpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Carpenter";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnPlumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Plumber";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnElectrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Electrician";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Appliances";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnLaundary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Laundary";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Cleaning";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Painting";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnShifting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Shifting";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });
        binding.btnWelder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_Category="Welder";
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("Fashion",str_Category);
                startActivity(intent);
            }
        });


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //binding.notesRecyclerView.setVisibility(View.VISIBLE);
    }
}
