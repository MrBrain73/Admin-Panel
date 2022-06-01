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

import com.example.hotel.Database.AppDbViewModel;
import com.example.hotel.Database.Staff.Staff;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentEditStaffBinding;

import java.util.Objects;

public class EditStaffFragment extends Fragment {

    private FragmentEditStaffBinding binding;
    private AppDbViewModel staffViewModel;
    private String name, surname;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditStaffBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        staffViewModel = new ViewModelProvider(this).get(AppDbViewModel.class);

        binding.buttonSave.setOnClickListener(v -> {
            initFields();

            if (name.equals("") && surname.equals("")) {
                Staff staff = new Staff(name, surname);

                staffViewModel.insert(staff);

                Navigation.findNavController(view)
                        .navigate(R.id.action_editStaffFragment_to_staffFragment);

            } else Toast.makeText(requireContext(), R.string.field_empty, Toast.LENGTH_LONG).show();
        });
    }

    private void initFields() {
        name = Objects.requireNonNull(binding.textName.getEditText())
                .getText().toString();
        surname = Objects.requireNonNull(binding.textSurname.getEditText())
                .getText().toString();
    }
}