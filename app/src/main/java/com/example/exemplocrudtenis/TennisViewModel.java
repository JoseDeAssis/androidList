package com.example.exemplocrudtenis;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TennisViewModel extends AndroidViewModel {

    private final TennisRepository tennisRepository;
    private final LiveData<List<Tennis>> tennisList;

    public TennisViewModel(@NonNull Application application) {
        super(application);
        tennisRepository = new TennisRepository(application);
        tennisList = tennisRepository.getAllTennis();
    }

    public void insert(Tennis tennis) {
        tennisRepository.insert(tennis);
    }

    public void update(Tennis tennis) {
        tennisRepository.update(tennis);
    }

    public void delete(Tennis tennis) {
        tennisRepository.delete(tennis);
    }

    public LiveData<List<Tennis>> getAllTennis() {
        return tennisList;
    }

}
