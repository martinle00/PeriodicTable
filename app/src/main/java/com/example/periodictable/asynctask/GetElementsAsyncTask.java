package com.example.periodictable.asynctask;

import android.os.AsyncTask;

import com.example.periodictable.Element;
import com.example.periodictable.database.AppDatabase;

import java.util.List;
// Async task to get elements from databaase
public class GetElementsAsyncTask extends AsyncTask<Void, Integer, List<Element>> {
    private AsyncTaskGetDelegate delegate;
    private AppDatabase database;

    public void setDelegate(AsyncTaskGetDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected List<Element> doInBackground(Void... voids) {
        return database.elementDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Element> result) {
        delegate.handleTaskGetResult(result);
    }
}

