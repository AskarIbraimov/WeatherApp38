package com.example.weatherapp38.ui.second;

import com.example.weatherapp38.base.BaseFragment;
import com.example.weatherapp38.databinding.FragmentSecondBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SecondFragment extends BaseFragment<FragmentSecondBinding> {

    @Override
    protected void setupUI() {
        binding.btnSearch.setOnClickListener(view -> navController.
                navigate(SecondFragmentDirections
                        .actionSecondFragmentToFirstFragment(Objects.requireNonNull
                                (binding.etSearch.getText()).toString())));
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected FragmentSecondBinding bind() {
        return FragmentSecondBinding.inflate(getLayoutInflater());
    }
}