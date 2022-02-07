package com.example.weatherapp38.ui.first;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp38.common.Resource;
import com.example.weatherapp38.data.model.Weather;
import com.example.weatherapp38.data.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FirstViewModel extends ViewModel {

    private final Repository repository;
    public LiveData<Resource<Weather>> liveData;
    private String city;

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    public FirstViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getCity() {
        repository.setCity(city);
        liveData = repository.getTemp();
    }
}
