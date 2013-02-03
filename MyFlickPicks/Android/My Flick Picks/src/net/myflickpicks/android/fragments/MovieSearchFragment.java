package net.myflickpicks.android.fragments;

import net.myflickpicks.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;
import com.facebook.model.GraphUser;

public class MovieSearchFragment extends SherlockFragment {
	
	private GraphUser currentUser;
	
	public MovieSearchFragment(GraphUser user)
	{
		currentUser = user;
	}
	
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  final View myFragmentView = inflater.inflate(R.layout.movie_search_layout, container, false);
	  
	  Button searchButton = (Button) myFragmentView.findViewById(R.id.searchmoviebutton);
	   searchButton.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(final View v) {
	        	EditText searchQueryBox = (EditText)myFragmentView.findViewById(R.id.searchmovietextbox);
	        	MovieSearchResultsFragment resultsFrag = new MovieSearchResultsFragment(searchQueryBox.getText().toString(), currentUser);
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, resultsFrag, "search_results").addToBackStack(getTag()).commit();
	        }
	    });
	    
	  
	  return myFragmentView;
	 }

}
