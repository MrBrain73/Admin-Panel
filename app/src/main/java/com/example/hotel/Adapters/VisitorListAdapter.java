package com.example.hotel.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Database.Visitors.Visitor;
import com.example.hotel.R;
import com.example.hotel.databinding.ItemListBinding;

import java.util.Collections;
import java.util.List;

public class VisitorListAdapter extends RecyclerView.Adapter<VisitorListAdapter.ViewHolder> {

    private List<Visitor> visitorList = Collections.emptyList();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = ItemListBinding.bind(view);
        }

        void bind(Visitor visitor) {
            binding.itemTitle.setText(String.valueOf(visitor.getSurname()));
            binding.itemSubTitle.setText(String.valueOf(visitor.getName()));
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
        holder.bind(visitorList.get(position));
    }

    @Override
    public int getItemCount() {
        return visitorList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Visitor> visitor) {
        this.visitorList = visitor;
        notifyDataSetChanged();
    }
}
