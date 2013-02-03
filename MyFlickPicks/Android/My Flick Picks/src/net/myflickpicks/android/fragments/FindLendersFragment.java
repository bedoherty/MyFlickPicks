package net.myflickpicks.android.fragments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.myflickpicks.android.loaders.FindLendersLoader;
import net.myflickpicks.android.loaders.FindWatchersLoader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.facebook.model.GraphUser;

public class FindLendersFragment extends SherlockListFragment implements LoaderManager.LoaderCallbacks<List<String>> {
private String[] optionsArray = new String[]{"Airplane", "Dark Knight Rises", "Spaceballs"};
	
	//	This is the Adapter being used to displayed the loaded data
	ArrayAdapter mAdapter;
	
	private GraphUser currentUser;
	private String movieTitle;
	
	public FindLendersFragment(String title, GraphUser user)
	{
		currentUser = user;
		movieTitle = title;
	}

	@Override 
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		getActivity().setTitle("Friends Who Have " + movieTitle);
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);

		//		Set a default message if no data is received
		setEmptyText("No Data Received");

		// Create an empty adapter we will use to display the loaded data.
		mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());//SimpleCursorAdapter(getActivity(),
		setListAdapter(mAdapter);

		// Start out with a progress indicator.
		setListShown(false);

//			Setup the loadermanager
		getLoaderManager().initLoader(0, null, this);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
	}
	
	public Loader<List<String>> onCreateLoader(int id, Bundle args) {
//		Create a new laundryloader
	//return new LaundryLoader(getActivity(), residenceHall);
		return new FindLendersLoader(getActivity(), currentUser.getId(), currentUser, movieTitle);
	}

	public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
//		Clear the data currently in the list adapter
	mAdapter.clear();


	for (int i = 0; i < data.size(); i++)
	{
		
		mAdapter.add(data.get(i));
	}

	/*
//		Add the data to our list adapter
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
					String website = "http://graph.facebook.com/" + currentUser.getId().replace(" ", "%20");
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
