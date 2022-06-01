package com.example.hotel.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Database.Staff.Staff;
import com.example.hotel.R;
import com.example.hotel.databinding.ItemListBinding;

import java.util.Collections;
import java.util.List;

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.ViewHolder> {

    private List<Staff> staffList = Collections.emptyList();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = ItemListBinding.bind(view);
        }

        void bind(Staff staff) {
            binding.itemTitle.setText(String.valueOf(staff.getSurname()));
            binding.itemSubTitle.setText(String.valueOf(staff.getName()));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(staffList.get(position));
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Staff> staff) {
        this.staffList = staff;
        notifyDataSetChanged();
    }
}
