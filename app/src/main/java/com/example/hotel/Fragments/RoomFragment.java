package com.example.hotel.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotel.Adapters.RoomListAdapter;
import com.example.hotel.Database.AppDbViewModel;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentRoomBinding;

public class RoomFragment extends Fragment {

    private FragmentRoomBinding binding;
    private RoomListAdapter adapter;
    private AppDbViewModel roomViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRoomBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().findViewById(R.id.navBottomView).setVisibility(View.VISIBLE);
        requireActivity().findViewById(R.id.buttonAdd).setVisibility(View.VISIBLE);


        adapter = new RoomListAdapter();
        binding.listRooms.setAdapter(adapter);
        binding.listRooms.setLayoutManager(new LinearLayoutManager(requireContext()));

        roomViewModel = new ViewModelProvider(this).get(AppDbViewModel.class);
        roomViewModel.getAllRooms()
                .observe(getViewLifecycleOwner(), rooms -> adapter.setData(rooms));

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            roomViewModel.deleteAllRoom();
        }
        return true;
    }
}