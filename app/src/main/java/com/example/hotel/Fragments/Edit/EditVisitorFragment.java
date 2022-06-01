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
import com.example.hotel.Database.Visitors.Visitor;
import com.example.hotel.R;
import com.example.hotel.databinding.FragmentEditVisitorBinding;

import java.util.Objects;

public class EditVisitorFragment extends Fragment {

    private FragmentEditVisitorBinding binding;
    private AppDbViewModel visitorViewModel;
    private String name, surname, countDay, phone;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditVisitorBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        visitorViewModel = new ViewModelProvider(this).get(AppDbViewModel.class);

        binding.buttonSave.setOnClickListener(v -> {
            initFields();

            if (name.equals("") && surname.equals("") && countDay.equals("") && phone.equals("")) {
                Visitor visitor = new Visitor(name, surname,
                        Integer.parseInt(countDay), Integer.parseInt(phone));

                visitorViewModel.insert(visitor);

                Navigation.findNavController(view)
                        .navigate(R.id.action_editVisitorFragment_to_visitorsFragment);
            } else Toast.makeText(requireContext(), R.string.field_empty, Toast.LENGTH_LONG).show();
        });
    }

    private void initFields() {
        name = Objects.requireNonNull(binding.textName.getEditText())
                .getText().toString();
        surname = Objects.requireNonNull(binding.textSurname.getEditText())
                .getText().toString();
        countDay = Objects.requireNonNull(binding.textCountDay.getEditText())
                .getText().toString();
        phone = Objects.requireNonNull(binding.textPhone.getEditText())
                .getText().toString();
    }
}