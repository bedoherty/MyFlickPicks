package net.myflickpicks.android.fragments;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.myflickpicks.android.R;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

public class ListSelectFragment extends SherlockListFragment {
private String[] optionsArray = new String[]{"Movies to Watch", "My Movie Collection"};
	
	private GraphUser currentUser;
	private String userID;
	private List<String> friendIDs;


	public ListSelectFragment(GraphUser user, List<String> ids)
	{
		currentUser = user;
		friendIDs = ids;
		//userID = id;
	}
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		getActivity().setTitle("My Lists");
		super.onActivityCreated(savedInstanceState);
		if (currentUser != null && currentUser.getId() != null)
		{
			getActivity().setTitle(currentUser.getName());
			
			new DownloadImage().execute(currentUser.getUsername());
		}

		
		
		
		//	Set a default message if no data is received
		setEmptyText("No Data Received");

		// Create an empty adapter we will use to display the loaded data.
		ArrayAdapter<String> hallListAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, optionsArray);
		setListAdapter(hallListAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		if (position == 0)
		{
			MyFlicksListFragment flicksListFragment = new MyFlicksListFragment(currentUser);
			this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, flicksListFragment, "flick_picks").addToBackStack(getTag()).commit();
		}
		else if (position == 1)
		{
			MyFlicksCollectionFragment flicksCollectionFragment = new MyFlicksCollectionFragment(currentUser);
			this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, flicksCollectionFragment, "flick_collection").addToBackStack(getTag()).commit();
		}
	}
	
	private class DownloadImage extends AsyncTask {
	    // onPostExecute displays the results of the AsyncTask.
		@Override
	    protected void onPostExecute(Object result) {
	    	//Toast.makeText(getActivity().getApplicationContext(), "Adding", Toast.LENGTH_LONG).show(); // For example
	    	Log.v("MyDebug", "Adding new icon");
	    	getSherlockActivity().getSupportActionBar().setIcon((Drawable)result);
	    	
	   }
		@Override
		protected Object doInBackground(Object... params) {
			// TODO Auto-generated method stub
			URL url;
			try {
				url = new URL("http://graph.facebook.com/" + params[0].toString() + "/picture?type=large");
				InputStream content = (InputStream)url.getContent();
			    Drawable d = Drawable.createFromStream(content , "src"); 
			   
			    
			    Log.v("MyDebug", "Worked");
			    return d;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("MyDebug", "Malform Exception");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.v("MyDebug", "IO Exception");
			}
			return null;
		}
	}

}
