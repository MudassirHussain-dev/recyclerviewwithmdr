package com.example.right_choice_project_usman_18_apr_2021.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.right_choice_project_usman_18_apr_2021.ServiceBookingActivity;
import com.example.right_choice_project_usman_18_apr_2021.databinding.RowProvierListLayoutBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSProviderUser;


import java.util.ArrayList;

public class ProviderUsersAdapter extends RecyclerView.Adapter<ProviderUsersAdapter.ProviderUsersViewHolder> {
    ArrayList<GSProviderUser> arrayList;
    Context context;
    //no
    private int checkedPosition = 0;

    public ProviderUsersAdapter(Context context, ArrayList<GSProviderUser> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProviderUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProviderUsersViewHolder(RowProvierListLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderUsersViewHolder holder, int position) {
        holder.bindItems(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ProviderUsersViewHolder extends RecyclerView.ViewHolder {

        RowProvierListLayoutBinding binding;

        public ProviderUsersViewHolder(RowProvierListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindItems(GSProviderUser gsProviderUser) {
            binding.txtCity.setText(gsProviderUser.getCity());
            binding.txtJob.setText(gsProviderUser.getFashion());
            binding.txtName.setText(gsProviderUser.getName());
            binding.txtID.setText(gsProviderUser.getID());
            binding.txtExperience.setText(gsProviderUser.getExperience());
            // Picasso.with(getContext()).load(gsProviderUser.getImage()).into(binding.ivImage);
            //Picasso.get().load(gsProviderUser.getImage()).into(binding.ivImage);

            Glide.with(context)  //2
                    .load("https://rightchoice042021.000webhostapp.com/" + gsProviderUser.getImage()) //3
                    //.centerCrop() //4
                    // .placeholder(R.drawable.ic_image_place_holder) //5
                    //.error(R.drawable.ic_broken_image) //6
                    // .fallback(R.drawable.ic_no_image) //7
                    .into(binding.ivImage); //8

            if (checkedPosition == -1)
            {
                binding.cvSamplingDetailLayout.setCardBackgroundColor(Color.GRAY);
            }
            else
            {
                if (checkedPosition == getAdapterPosition())
                {
                    binding.cvSamplingDetailLayout.setCardBackgroundColor(Color.DKGRAY);
                }
                else
                {
                    binding.cvSamplingDetailLayout.setCardBackgroundColor(Color.GRAY);
                }
            }

            binding.cvSamplingDetailLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   /* Intent intent = new Intent(context, ServiceBookingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/

                    binding.cvSamplingDetailLayout.setCardBackgroundColor(Color.DKGRAY);
                    if (checkedPosition != getAdapterPosition())
                    {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }

                }
            });

        }

    }
    public GSProviderUser getSelected(){
        if (checkedPosition != -1) {
            return arrayList.get(checkedPosition);
        }
        return null;
    }
}
