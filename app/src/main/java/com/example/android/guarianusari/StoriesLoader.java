package com.example.android.guarianusari;

import android.content.Context;
import android.util.Log;
import android.support.v4.content.AsyncTaskLoader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class StoriesLoader extends AsyncTaskLoader<List<Stories>> {

    public StoriesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Stories> loadInBackground() {
        List<Stories> StoriesList = null;
        try {
            URL url = QueryUtils.createUrl();
            String jsonResponse = QueryUtils.makeHttpRequest(url);
            StoriesList = QueryUtils.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("Queryutils", "Error Loader LoadInBackground: ", e);
        }
        return StoriesList;
    }
}
