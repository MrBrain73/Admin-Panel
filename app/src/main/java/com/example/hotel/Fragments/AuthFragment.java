package com.example.hotel.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel.R;
import com.example.hotel.databinding.FragmentAuthBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AuthFragment extends Fragment {

    private FragmentAuthBinding binding;
    private String emailUser;
    private String passwordUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.signUpBtn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_authFragment_to_signFragment));

        binding.logInBtn.setOnClickListener(v -> {

            emailUser = Objects.requireNonNull(binding.emailLogIn.getEditText())
                    .getText().toString();
            passwordUser = Objects.requireNonNull(binding.passwordLogIn.getEditText())
                    .getText().toString();

            if (Patterns.EMAIL_ADDRESS.matcher(emailUser).matches() &&
                    !passwordUser.equals("")) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailUser, passwordUser)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), R.string.success,
                                        Toast.LENGTH_LONG).show();
                                Navigation.findNavController(v)
                                        .navigate(R.id.action_authFragment_to_roomFragment);
                            } else
                                Toast.makeText(getActivity(),
                                        Objects.requireNonNull(task.getException())
                                                .toString(), Toast.LENGTH_LONG).show();
                        });
            } else if (passwordUser.equals("")) {
                binding.passwordLogIn.setError(getString(R.string.field_empty));
            }
        });
    }
}