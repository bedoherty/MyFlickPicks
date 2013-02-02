package net.myflickpicks.android.fragments;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import net.myflickpicks.android.loaders.MovieSearchLoader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

public class MovieSearchResultsFragment extends SherlockListFragment
implements LoaderManager.LoaderCallbacks<List<String>> {

	
//	This is the Adapter being used to displayed the loaded data
ArrayAdapter mAdapter;

//	Name of the Residence Hall for this fragment
private String searchQuery;

public MovieSearchResultsFragment(String query)
{
	searchQuery = query;
}

@Override public void onActivityCreated(Bundle savedInstanceState) {
super.onActivityCreated(savedInstanceState);

//	Set a default message if no data is received
setEmptyText("No Data Received");

// Create an empty adapter we will use to display the loaded data.
mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());//SimpleCursorAdapter(getActivity(),
setListAdapter(mAdapter);

// Start out with a progress indicator.
setListShown(false);

//	Setup the loadermanager
getLoaderManager().initLoader(0, null, this);
}

@Override
public void onListItemClick(final ListView l, View v, final int position, long id)
{
	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle("Add a Flick").setMessage("Would you like to add this flick to your watch list?")
    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
        	//newAlarm.SetAlarm(getActivity().getApplicationContext());
        	new DownloadWebpageText().execute(((TextView)(l.getChildAt(position))).getText().toString());
            
        }
    })
    .setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            //	Do nothing
        	Toast.makeText(getActivity().getApplicationContext(), "Not Adding", Toast.LENGTH_LONG).show(); // For example
        }
    });
    Dialog newDialog = builder.create();
    newDialog.show();
	
}


	
public Loader<List<String>> onCreateLoader(int id, Bundle args) {
//	Create a new laundryloader
//return new LaundryLoader(getActivity(), residenceHall);
	return new MovieSearchLoader(getActivity(), searchQuery);
}

public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
//	Clear the data currently in the list adapter
mAdapter.clear();


for (int i = 0; i < data.size(); i++)
{
	mAdapter.add(data.get(i));
}

/*
//	Add the data to our list adapter
for (int i = 0; i < data.size(); i++) {
	mAdapter.add("Machine " + data.keySet().toArray(new String[0])[i] + ": " + data.values().toArray(new String[0])[i]);
}

	*/


    setListShown(true);

}

@Override
public void onLoaderReset(Loader<List<String>> arg0) {
	// TODO Auto-generated method stub
	//arg0 = null;
}


private class DownloadWebpageText extends AsyncTask {
    // onPostExecute displays the results of the AsyncTask.
    protected void onPostExecute(String result) {
    	Toast.makeText(getActivity().getApplicationContext(), "Adding", Toast.LENGTH_LONG).show(); // For example

   }
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		
		
			
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpContext localContext = new BasicHttpContext();
				String website = "http://myflickpicks.net/php/db/addmovietolist.php?UserID=007&MovieTitle=" + ((String)params[0]).replace(" ", "%20");
				HttpGet httpGet = new HttpGet(website);
				httpGet.setHeader("accept", "application/json");
				HttpResponse response = httpClient.execute(httpGet, localContext);
	        	//Toast.makeText(getActivity().getApplicationContext(), "Adding", Toast.LENGTH_LONG).show(); // For example

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
}


}


