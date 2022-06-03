package com.example.hotel.Fragments.Edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel.Database.AppDbViewModel;
import com.example.hotel.Database.Rooms.Room;
import com.example.hotel.Database.Visitors.Visitor;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentEditVisitorBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Objects;

public class EditVisitorFragment extends Fragment {

    private FragmentEditVisitorBinding binding;
    private MaterialDatePicker<Pair<Long, Long>> datePicker;
    private AppDbViewModel visitorViewModel;
    private String name, surname, countDay, phone, room;
    private Room currentRoom;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditVisitorBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        visitorViewModel = new ViewModelProvider(this).get(AppDbViewModel.class);

        datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(R.string.title_picker)
                .build();

        datePicker.addOnPositiveButtonClickListener(selection ->
                countDay = datePicker.getHeaderText());

        binding.datePickerBtn.setOnClickListener(v -> {
            datePicker.show(getParentFragmentManager(), "DATE_PICKER");
        });

        binding.buttonSave.setOnClickListener(v -> {
            initFields();

            if (!room.equals("")) {
                visitorViewModel.getRoomByNum(Integer.parseInt(room))
                        .observe(getViewLifecycleOwner(), room1 -> currentRoom = room1);
                if (!(name.equals("") && surname.equals("")
                        && countDay == null && phone.equals(""))) {
                    if (!currentRoom.isTaken()) {
                        try {
                            currentRoom.setTaken(true);

                            Visitor visitor = new Visitor(name, surname,
                                    countDay, Integer.parseInt(phone));

                            visitorViewModel.insert(visitor);
                            visitorViewModel.updateItem(currentRoom);

                            Navigation.findNavController(view)
                                    .navigate(R.id.action_editVisitorFragment_to_visitorsFragment);
                        } catch (NumberFormatException ex) {
                            binding.textPhone.setError(getString(R.string.error_parse_int));
                        }
                    } else binding.textRoom.setError(getString(R.string.error_taken));
                } else
                    Toast.makeText(requireContext(), R.string.field_empty, Toast.LENGTH_LONG).show();
            } else binding.textRoom.setError(getString(R.string.field_empty));
        });
    }

    private void initFields() {
        name = Objects.requireNonNull(binding.textName.getEditText())
                .getText().toString();
        surname = Objects.requireNonNull(binding.textSurname.getEditText())
                .getText().toString();
        phone = Objects.requireNonNull(binding.textPhone.getEditText())
                .getText().toString();
        room = Objects.requireNonNull(binding.textRoom.getEditText())
                .getText().toString();
    }
}