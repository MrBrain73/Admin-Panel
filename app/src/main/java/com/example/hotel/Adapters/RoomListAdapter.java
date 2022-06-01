package com.example.hotel.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Database.Rooms.Room;
import com.example.hotel.R;
import com.example.hotel.databinding.ItemListBinding;

import java.util.Collections;
import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

    private List<Room> roomList = Collections.emptyList();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = ItemListBinding.bind(view);
        }

        void bind(Room room) {
            binding.itemTitle.setText(String.valueOf(room.getNumberRoom()));
            binding.itemSubTitle.setText(String.valueOf(room.getClassRoom()));
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
        holder.bind(roomList.get(position));
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Room> rooms) {
        this.roomList = rooms;
        notifyDataSetChanged();
    }
}
