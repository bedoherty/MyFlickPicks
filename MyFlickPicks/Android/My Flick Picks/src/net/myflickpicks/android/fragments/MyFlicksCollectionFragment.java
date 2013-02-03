package net.myflickpicks.android.fragments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.myflickpicks.android.R;
import net.myflickpicks.android.loaders.FlickPicksLoader;
import net.myflickpicks.android.loaders.FlicksCollectionLoader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.facebook.model.GraphUser;

public class MyFlicksCollectionFragment extends SherlockListFragment implements LoaderManager.LoaderCallbacks<List<String>> {
private String[] optionsArray = new String[]{"Airplane", "Dark Knight Rises", "Spaceballs"};
	
	MenuItem fav;
	
	//	This is the Adapter being used to displayed the loaded data
	ArrayAdapter mAdapter;

	//	Name of the Residence Hall for this fragment
	private String userID = "007";
	
	private GraphUser currentUser;
	
	public MyFlicksCollectionFragment(GraphUser user)
	{
		currentUser = user;
	}

	@Override 
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		getActivity().setTitle("My Movie Collection");
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
		final String movieTitle = ((TextView)(l.getChildAt(position))).getText().toString();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle(movieTitle);
	    builder.setItems(R.array.options_array2, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               if (which == 0)
	               {
	            	 new DeleteCardTask().execute(movieTitle);
	               }
	           }
	    });
	    builder.create();	
	    builder.show();
	    Log.v("MyDebug", "Attempting to open choice window.");
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        fav = menu.add("add");
        fav.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        fav.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				CollectionSearchFragment searchFrag = new CollectionSearchFragment(currentUser);
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, searchFrag, "collection_search").addToBackStack(getTag()).commit();
				return true;
			}
        	
        });
        //fav.sho;
        //fav.setIcon(R.);
    }
	
	public Loader<List<String>> onCreateLoader(int id, Bundle args) {
//		Create a new laundryloader
	//return new LaundryLoader(getActivity(), residenceHall);
		return new FlicksCollectionLoader(getActivity(), userID, currentUser);
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
	
	private class DeleteCardTask extends AsyncTask {
	    // onPostExecute displays the results of the AsyncTask.
	    protected void onPostExecute(String result) {
	    	Toast.makeText(getActivity().getApplicationContext(), "Deleting", Toast.LENGTH_LONG).show(); // For example

	   }
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			
			
				
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpContext localContext = new BasicHttpContext();
					String website = "http://myflickpicks.net/php/db/deletecollectionmovie.php?UserID=" + currentUser.getId() + "&MovieTitle=" + params[0].toString().replace(" ", "%20");
					HttpGet httpGet = new HttpGet(website);
					httpGet.setHeader("accept", "application/json");
					HttpResponse response = httpClient.execute(httpGet, localContext);
		        	//Toast.makeText(getActivity().getApplicationContext(), "Adding", Toast.LENGTH_LONG).show(); // For example
					Log.v("MyDebug", "Door 1");
					Log.v("MyDebug", currentUser.getId());
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.v("MyDebug", "Door 2");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.v("MyDebug", "Door 3");
				}
			return null;
		}
	}
}