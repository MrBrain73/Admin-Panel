package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.hotel.Fragments.RoomFragment;
import com.example.hotel.Fragments.StaffFragment;
import com.example.hotel.Fragments.VisitorsFragment;
import com.example.hotel.databinding.ActivityMainBinding;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        invisibleView();

        navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        NavigationUI.setupWithNavController(binding.navBottomView, navController);

        Objects.requireNonNull(getSupportActionBar())
                .setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 50)
                .build();

        ViewCompat.setBackground(binding.navBottomView,
                new MaterialShapeDrawable(shapeAppearanceModel));

        binding.buttonAdd.setImageDrawable(getDrawable(R.drawable.icon_add));

        binding.buttonAdd.setOnClickListener(v -> {
            Fragment currentFragment = getCurrentFragment();

            if (currentFragment instanceof RoomFragment) {
                invisibleView();
                navController.navigate(R.id.action_roomFragment_to_editRoomFragment);
            } else if (currentFragment instanceof StaffFragment) {
                invisibleView();
                navController.navigate(R.id.action_staffFragment_to_editStaffFragment);
            } else if (currentFragment instanceof VisitorsFragment) {
                invisibleView();
                navController.navigate(R.id.action_visitorsFragment_to_editVisitorFragment);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getCurrentFragment();
        if (!(currentFragment instanceof RoomFragment) &&
                !(currentFragment instanceof StaffFragment) &&
                !(currentFragment instanceof VisitorsFragment)) {
            super.onBackPressed();
        }
    }

    private Fragment getCurrentFragment() {
        return Objects.requireNonNull(getSupportFragmentManager()
                        .findFragmentById(R.id.fragmentContainerView))
                .getChildFragmentManager()
                .getFragments()
                .get(0);
    }

    private void invisibleView() {
        binding.navBottomView.setVisibility(View.INVISIBLE);
        binding.buttonAdd.setVisibility(View.INVISIBLE);
    }
}