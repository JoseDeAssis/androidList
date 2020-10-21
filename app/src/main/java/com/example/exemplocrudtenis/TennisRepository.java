package com.example.exemplocrudtenis;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TennisRepository {
    private TennisDao tennisDao;
    private LiveData<List<Tennis>> allTennis;

    public TennisRepository(Application application) {
        AppTennisRoomDatabase database = AppTennisRoomDatabase.getInstance(application);
        tennisDao = database.tennisDao();
        allTennis = tennisDao.getAllTennis();
    }

    public void insert(Tennis tennis) {
        new InsertTennisAsyncTask(tennisDao).execute(tennis);
    }

    public void update(Tennis tennis) {
        new UpdateTennisAsyncTask(tennisDao).execute(tennis);
    }

    public void delete(Tennis tennis) {
        new DeleteTennisAsyncTask(tennisDao).execute(tennis);
    }

    public LiveData<List<Tennis>> getAllTennis() {
        return allTennis;
    }

    private static class InsertTennisAsyncTask extends AsyncTask<Tennis, Void, Void> {
        private final TennisDao tennisDao;

        private InsertTennisAsyncTask(TennisDao tennisDao) {
            this.tennisDao = tennisDao;
        }

        @Override
        protected Void doInBackground(Tennis... tennis) {
            tennisDao.insert(tennis[0]);
            return null;
        }
    }

    private static class UpdateTennisAsyncTask extends AsyncTask<Tennis, Void, Void> {
        private final TennisDao tennisDao;

        private UpdateTennisAsyncTask(TennisDao tennisDao) {
            this.tennisDao = tennisDao;
        }

        @Override
        protected Void doInBackground(Tennis... tennis) {
            tennisDao.update(tennis[0]);
            return null;
        }
    }

    private static class DeleteTennisAsyncTask extends AsyncTask<Tennis, Void, Void> {
        private final TennisDao tennisDao;

        private DeleteTennisAsyncTask(TennisDao tennisDao) {
            this.tennisDao = tennisDao;
        }

        @Override
        protected Void doInBackground(Tennis... tennis) {
            tennisDao.delete(tennis[0]);
            return null;
        }
    }
}
