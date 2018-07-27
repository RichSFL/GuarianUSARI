package com.example.android.guarianusari;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Stories>> {
    private static int LOADER_ID = 0;
    private StoriesAdapter adapter;
    private TextView EmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // Find a reference to the {@link ListView} in the layout
        ListView listView = (ListView) findViewById( R.id.list );

        EmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(EmptyStateTextView);
        
        
        adapter = new StoriesAdapter( this );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Stories stories = adapter.getItem( i );
                //Build the Intent
                String url = stories.url;
                Intent intent = new Intent( Intent.ACTION_VIEW );
                intent.setData( Uri.parse( url ) );
                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;
                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity( intent );
                }
            }
        } );
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService( Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            getSupportLoaderManager().initLoader( LOADER_ID, null, this );

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            EmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<Stories>> onCreateLoader(int id, Bundle args) {
        return new StoriesLoader( this );
    }

    @Override
    public void onLoadFinished(Loader<List<Stories>> loader, List<Stories> data) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        // Set empty state text to display "No earthquakes found."
        EmptyStateTextView.setText(R.string.no_stories);

        if (data != null) {
            adapter.setNotifyOnChange( false );
            adapter.clear();
            adapter.setNotifyOnChange( true );
            adapter.addAll( data );
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Stories>> loader) {

    }
}
