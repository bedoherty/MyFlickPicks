package net.myflickpicks.android.fragments;

import java.util.ArrayList;
import java.util.List;

import net.myflickpicks.android.R;
import net.myflickpicks.android.loaders.FlickPicksLoader;
import net.myflickpicks.android.loaders.MovieSearchLoader;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;

public class MyFlicksListFragment  extends SherlockListFragment implements LoaderManager.LoaderCallbacks<List<String>> {
private String[] optionsArray = new String[]{"Airplane", "Dark Knight Rises", "Spaceballs"};
	
	MenuItem fav;
	
	//	This is the Adapter being used to displayed the loaded data
	ArrayAdapter mAdapter;

	//	Name of the Residence Hall for this fragment
	private String userID = "007";

	@Override 
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		getActivity().setTitle("My Flick Picks");
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
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        fav = menu.add("add");
        fav.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        fav.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				MovieSearchFragment searchFrag = new MovieSearchFragment();
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, searchFrag, "movie_search").addToBackStack(getTag()).commit();
				return true;
			}
        	
        });
        //fav.sho;
        //fav.setIcon(R.);
    }
	
	public Loader<List<String>> onCreateLoader(int id, Bundle args) {
//		Create a new laundryloader
	//return new LaundryLoader(getActivity(), residenceHall);
		return new FlickPicksLoader(getActivity(), userID);
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
}
