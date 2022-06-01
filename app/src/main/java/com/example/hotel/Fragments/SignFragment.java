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
import com.example.hotel.databinding.FragmentSignBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class SignFragment extends Fragment {

    private FragmentSignBinding binding;
    private String emailUser;
    private String passwordUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.logInBtn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_signFragment_to_authFragment));

        binding.signUpBtn.setOnClickListener(v -> {
            boolean check = true;

            emailUser = Objects.requireNonNull(binding.emailSignUp.getEditText())
                    .getText().toString();
            passwordUser = Objects.requireNonNull(binding.passwordSignUp
                    .getEditText()).getText().toString();

            System.out.println("PASSWORD: " + passwordUser);

            if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches() || emailUser.equals("")) {
                binding.emailSignUp.setError(getString(R.string.email_incorrect));
                check = false;
            }
            if (passwordUser.equals("")) {
                binding.passwordSignUp.setError(getString(R.string.field_empty));
                check = false;
            }
            if (!passwordUser.equals(Objects.requireNonNull(binding.passwordConfSignUp
                    .getEditText()).getText().toString())) {
                binding.passwordConfSignUp.setError(getString(R.string.pass_do_not_match));
                check = false;
            }
            if (check) {
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(emailUser, passwordUser)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Navigation.findNavController(v)
                                        .navigate(R.id.action_signFragment_to_authFragment);
                                Toast.makeText(getActivity(),
                                        R.string.regis_success, Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(getActivity(),
                                        Objects.requireNonNull(task.getException())
                                                .toString(),
                                        Toast.LENGTH_LONG).show();
                        });
            }
        });
    }
}