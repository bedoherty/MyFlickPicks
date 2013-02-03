package net.myflickpicks.android.loaders;

import java.util.List;

import com.facebook.model.GraphUser;

import net.myflickpicks.android.json.FlickPicksParser;
import net.myflickpicks.android.json.MovieSearchParser;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class FlickPicksLoader  extends AsyncTaskLoader<List<String>>
{

	private String userID;
	private GraphUser currentUser;
	
	public FlickPicksLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FlickPicksLoader(Context context, String id, GraphUser user)
	{
		super(context);
		userID = id;
		currentUser = user;
	}

	public List<String> loadInBackground(){
		//List<String> results = new ArrayList<String>();//LaundryReader.getLaundryStrings(residenceHall);
		//results.add("Testing loaders and lists.");
		List<String> results = FlickPicksParser.getSearchResults(currentUser);
		return results;
	}


	@Override
	protected void onStartLoading(){
		forceLoad();
	}



}
