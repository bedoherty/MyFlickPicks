package net.myflickpicks.android.loaders;

import java.util.List;

import net.myflickpicks.android.json.FlickPicksParser;
import net.myflickpicks.android.json.FlicksCollectionParser;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.facebook.model.GraphUser;

public class FlicksCollectionLoader extends AsyncTaskLoader<List<String>>
{

	private String userID;
	private GraphUser currentUser;
	
	public FlicksCollectionLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FlicksCollectionLoader(Context context, String id, GraphUser user)
	{
		super(context);
		userID = id;
		currentUser = user;
	}

	public List<String> loadInBackground(){
		//List<String> results = new ArrayList<String>();//LaundryReader.getLaundryStrings(residenceHall);
		//results.add("Testing loaders and lists.");
		List<String> results = FlicksCollectionParser.getSearchResults(currentUser);
		return results;
	}


	@Override
	protected void onStartLoading(){
		forceLoad();
	}



}
