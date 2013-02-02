package net.myflickpicks.android.loaders;

import java.util.List;

import net.myflickpicks.android.json.FlickPicksParser;
import net.myflickpicks.android.json.MovieSearchParser;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class FlickPicksLoader  extends AsyncTaskLoader<List<String>>
{

	private String userID;
	
	public FlickPicksLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FlickPicksLoader(Context context, String id)
	{
		super(context);
		userID = id;
	}

	public List<String> loadInBackground(){
		//List<String> results = new ArrayList<String>();//LaundryReader.getLaundryStrings(residenceHall);
		//results.add("Testing loaders and lists.");
		List<String> results = FlickPicksParser.getSearchResults(userID);
		return results;
	}


	@Override
	protected void onStartLoading(){
		forceLoad();
	}



}
