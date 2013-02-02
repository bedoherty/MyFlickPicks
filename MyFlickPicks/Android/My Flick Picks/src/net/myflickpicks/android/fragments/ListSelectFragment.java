package net.myflickpicks.android.fragments;

import net.myflickpicks.android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockListFragment;

public class ListSelectFragment extends SherlockListFragment {
private String[] optionsArray = new String[]{"Movies to Watch", "Movies I Own", "Movies with Friends"};
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		getActivity().setTitle("My Lists");
		super.onActivityCreated(savedInstanceState);

		//	Set a default message if no data is received
		setEmptyText("No Data Received");

		// Create an empty adapter we will use to display the loaded data.
		ArrayAdapter<String> hallListAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, optionsArray);
		setListAdapter(hallListAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		
		MyFlicksListFragment flicksListFragment = new MyFlicksListFragment();
		this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, flicksListFragment, "flick_picks").addToBackStack(getTag()).commit();
		
	}
}
