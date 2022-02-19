package com.example.weatherapp38.ui.first;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.weatherapp38.R;
import com.example.weatherapp38.base.BaseFragment;
import com.example.weatherapp38.data.local.WeatherDao;
import com.example.weatherapp38.data.model.Weather;
import com.example.weatherapp38.databinding.FragmentFirstBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FirstFragment extends BaseFragment<FragmentFirstBinding> {

    private FirstViewModel model;
    private NavController controller;
    private FirstFragmentArgs args;
    @Inject
    WeatherDao dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            args = FirstFragmentArgs.fromBundle(getArguments());
    }


    @Override
    protected FragmentFirstBinding bind() {
        return FragmentFirstBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {
        model.liveData.observe(getViewLifecycleOwner(), responce -> {
            switch (responce.status) {
                case SUCCESS:
                    setData(responce.data);
                    binding.progress.setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    setData(dao.getWeather());
                    Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                    break;
            }
        });
    }

    @Override
    protected void setupUI() {
        model = new ViewModelProvider(requireActivity()).get(FirstViewModel.class);
        model.setCity(args.getCity());
        model.getCity();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        binding.cityBtn.setOnClickListener(view1
                -> controller.navigate(R.id.action_firstFragment_to_secondFragment));
    }

    private void setData(Weather weather) {
        String location = weather.getName();
        String urlImg = "https://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + ".png";
        String maxTemp = Math.round(weather.getMain().getTempMax()) + "C";
        String wind = String.valueOf((int) Math.round(weather.getWind().getSpeed()));
        String minTemp = String.valueOf((int) Math.round(weather.getMain().getTempMin()));
        String humidity = String.valueOf(weather.getMain().getHumidity());
        String barometr = String.valueOf(weather.getMain().getPressure());
        String mainWeather = weather.getWeather().get(0).getMain();
        String tempNow = String.valueOf((int) Math.round(weather.getMain().getTemp()));

        binding.barometrTv.setText(barometr);
        binding.cityBtn.setText(location);
        binding.tempnowTv.setText(tempNow);
        Glide.with(requireActivity()).load(urlImg).into(binding.weatherIv);
        binding.tempmaxTv.setText(maxTemp);
        binding.windTv.setText(wind);
        binding.tempminTv.setText(minTemp);
        binding.tvHum.setText(humidity);
        binding.weatherNowTv.setText(mainWeather);
    }
}