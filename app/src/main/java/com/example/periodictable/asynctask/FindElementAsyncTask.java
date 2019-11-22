package com.example.periodictable.asynctask;

import android.os.AsyncTask;

import com.example.periodictable.Element;
import com.example.periodictable.database.AppDatabase;

// Asynctask to find elements from the database
public class FindElementAsyncTask extends AsyncTask<Integer, Void, Element> {
    private AsyncTaskFindDelegate delegate;
    private AppDatabase database;

    public void setDelegate(AsyncTaskFindDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected Element doInBackground(Integer... integers) {
        return database.elementDao().findElementByAtomicNumber(integers[0]);
    }

    @Override
    protected void onPostExecute(Element result) {
        delegate.handleTaskResult(result);
    }
}

