package com.example.right_choice_project_usman_18_apr_2021.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.right_choice_project_usman_18_apr_2021.databinding.RowProvierListLayoutBinding;
import com.example.right_choice_project_usman_18_apr_2021.databinding.RowServiceHistoryLayoutBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSOrderHistory;
import com.example.right_choice_project_usman_18_apr_2021.model.GSProviderUser;

import java.util.ArrayList;

public class ServiceHistoryAdapter extends RecyclerView.Adapter<ServiceHistoryAdapter.ServiceHistoryViewHolder> {

    ArrayList<GSOrderHistory> arrayList;
    Context context;


    public ServiceHistoryAdapter(Context context, ArrayList<GSOrderHistory> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceHistoryViewHolder(RowServiceHistoryLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHistoryViewHolder holder, int position) {
        holder.bindItems(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ServiceHistoryViewHolder extends RecyclerView.ViewHolder {

        RowServiceHistoryLayoutBinding binding;

        public ServiceHistoryViewHolder(RowServiceHistoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindItems(GSOrderHistory gsOrderHistory) {
            binding.txtServiceID.setText(gsOrderHistory.getID());
            binding.txtProviderName.setText(gsOrderHistory.getProviderName());
            binding.txtUserName.setText(gsOrderHistory.getUserName());
            binding.txtServiceCategory.setText(gsOrderHistory.getCategory());
            binding.txtServiceSubCategory.setText(gsOrderHistory.getSubCategory());
            binding.txtServiceAddress.setText(gsOrderHistory.getServiceAddress());
            binding.txtServiceBookingDate.setText(gsOrderHistory.getDate());
            binding.txtServiceStatus.setText(gsOrderHistory.getStatus());


        }
    }
}
