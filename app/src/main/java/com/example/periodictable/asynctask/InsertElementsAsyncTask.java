package com.example.periodictable.asynctask;

import android.os.AsyncTask;

import com.example.periodictable.Element;
import com.example.periodictable.database.AppDatabase;

import java.util.Arrays;
// AsyncTask to insert elements to database
public class InsertElementsAsyncTask extends AsyncTask<Element, Integer, String> {
    private AsyncTaskInsertDelegate delegate;
    private AppDatabase database;

    public void setDelegate(AsyncTaskInsertDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Element... elements) {
        database.elementDao().insertAll(Arrays.asList(elements));
        return "This value will be passed to onPostExecute as the result parameter.";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.handleTaskResult(result);
    }
}

