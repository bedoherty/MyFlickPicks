package net.myflickpicks.android.loaders;

import java.util.List;

import net.myflickpicks.android.json.FindWatchersParser;
import net.myflickpicks.android.json.FlickPicksParser;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.facebook.model.GraphUser;

public class FindWatchersLoader extends AsyncTaskLoader<List<String>>
{

	private String userID;
	private GraphUser currentUser;
	private String movieTitle;
	
	public FindWatchersLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FindWatchersLoader(Context context, String id, GraphUser user, String title)
	{
		super(context);
		userID = id;
		currentUser = user;
		movieTitle = title;
	}

	public List<String> loadInBackground(){
		//List<String> results = new ArrayList<String>();//LaundryReader.getLaundryStrings(residenceHall);
		//results.add("Testing loaders and lists.");
		List<String> results = FindWatchersParser.getSearchResults(currentUser, movieTitle);
		return results;
	}


	@Override
	protected void onStartLoading(){
		forceLoad();
	}


}
