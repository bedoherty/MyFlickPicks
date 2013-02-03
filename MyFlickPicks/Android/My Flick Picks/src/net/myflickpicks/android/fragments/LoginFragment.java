package net.myflickpicks.android.fragments;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.myflickpicks.android.R;
import com.facebook.*;
import com.facebook.model.*;
import com.facebook.widget.LoginButton;
import com.mongodb.BasicDBList;
import com.mongodb.util.JSON;

import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class LoginFragment extends SherlockFragment {
	
	private GraphUser currentUser;
	private UiLifecycleHelper uiHelper;
	
	private Session.StatusCallback callback = new Session.StatusCallback() {
        // callback when session changes state
        @Override
        public void call(Session session, SessionState state, Exception exception) {
      		 Log.v("MyDebug", "Calling session");
      	  if (session.isOpened()) {
      		
      			 Log.v("MyDebug", "Session is opened.");
      			 
      			

            // make request to the /me API
      	
            Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

              // callback after Graph API response with user object
              @Override
              public void onCompleted(GraphUser user, Response response)  {
              	if (user != null) {
   
              	  currentUser = user;
              	  Log.v("MyDebug", "Non Null user");
              	ListSelectFragment selectFrag = new ListSelectFragment(currentUser);
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, selectFrag, "list_select").addToBackStack(getTag()).commit();
                }
                else
                {
              	 Log.v("MyDebug", "Null user");
            
                }
              }
            });
          }
        }
		
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  View myFragmentView = inflater.inflate(R.layout.login_layout, container, false);
	  
	  LoginButton authButton = (LoginButton) myFragmentView.findViewById(R.id.loginbutton);
	  authButton.setFragment(this);
//	  Button loginButton = (Button) myFragmentView.findViewById(R.id.loginbutton);

	  return myFragmentView;
	 }

	@Override
	public void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
}
