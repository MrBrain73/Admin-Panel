package com.example.hotel.Fragments.Edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel.Database.Rooms.Room;
import com.example.hotel.Database.AppDbViewModel;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentEditRoomBinding;

import java.util.Objects;

public class EditRoomFragment extends Fragment {

    private FragmentEditRoomBinding binding;
    private AppDbViewModel roomViewModel;
    private String number, classOfRoom, capacity, price;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditRoomBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        roomViewModel = new ViewModelProvider(this).get(AppDbViewModel.class);

        binding.buttonSave.setOnClickListener(v -> {
            initFields();

            if (!(number.equals("") && classOfRoom.equals("")
                    && capacity.equals("") && price.equals(""))) {
                Room room = new Room(Integer.parseInt(number), classOfRoom,
                        Integer.parseInt(capacity), Integer.parseInt(price), false);

                roomViewModel.insert(room);

                Navigation.findNavController(view)
                        .navigate(R.id.action_editRoomFragment_to_roomFragment);
            } else Toast.makeText(requireContext(), R.string.field_empty, Toast.LENGTH_LONG).show();
        });
    }

    private void initFields() {
        number = Objects.requireNonNull(binding.textNumber.getEditText())
                .getText().toString();
        classOfRoom = Objects.requireNonNull(binding.textClass.getEditText())
                .getText().toString();
        capacity = Objects.requireNonNull(binding.textCapacity.getEditText())
                .getText().toString();
        price = Objects.requireNonNull(binding.textPrice.getEditText())
                .getText().toString();
    }
}