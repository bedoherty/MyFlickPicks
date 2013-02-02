package net.myflickpicks.android.loaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.myflickpicks.android.json.MovieSearchParser;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class MovieSearchLoader extends AsyncTaskLoader<List<String>>
{

	private String searchQuery;
	
	public MovieSearchLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MovieSearchLoader(Context context, String query)
	{
		super(context);
		searchQuery = query;
	}

	public List<String> loadInBackground(){
		//List<String> results = new ArrayList<String>();//LaundryReader.getLaundryStrings(residenceHall);
		//results.add("Testing loaders and lists.");
		List<String> results = MovieSearchParser.getSearchResults(searchQuery);
		return results;
	}


	@Override
	protected void onStartLoading(){
		forceLoad();
	}

}
