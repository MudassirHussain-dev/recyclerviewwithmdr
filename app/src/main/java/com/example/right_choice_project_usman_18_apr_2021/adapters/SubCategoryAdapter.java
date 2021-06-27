package com.example.right_choice_project_usman_18_apr_2021.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.right_choice_project_usman_18_apr_2021.databinding.RowSubitemsLayoutBinding;
import com.example.right_choice_project_usman_18_apr_2021.model.GSSubCategory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    ArrayList<GSSubCategory> arrayList;
    Context context;
    private int checkedPosition = 0;

    public SubCategoryAdapter(Context context,ArrayList<GSSubCategory> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NotNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubCategoryViewHolder(RowSubitemsLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        holder.bindItems(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder{
        RowSubitemsLayoutBinding binding;

        public SubCategoryViewHolder(RowSubitemsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bindItems(GSSubCategory gsSubCategory) {
            binding.txtSubCategoryName.setText(gsSubCategory.getSubCategory());
            binding.ivImage.setImageResource(gsSubCategory.getImage());


            if (checkedPosition == -1) {
                binding.cvSubCategoryDetailLayout.setCardBackgroundColor(Color.GRAY);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    binding.cvSubCategoryDetailLayout.setCardBackgroundColor(Color.DKGRAY);
                } else {
                    binding.cvSubCategoryDetailLayout.setCardBackgroundColor(Color.GRAY);
                }
            }


            binding.cvSubCategoryDetailLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.cvSubCategoryDetailLayout.setCardBackgroundColor(Color.DKGRAY);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }

                   /* Intent intent = new Intent(context, FeedbackActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);*/
                }
            });
        }

    }

    public GSSubCategory getSelected(){
        if (checkedPosition != -1) {
            return arrayList.get(checkedPosition);
        }
        return null;
    }
}
