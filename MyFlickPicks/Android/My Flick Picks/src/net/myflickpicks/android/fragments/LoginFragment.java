package net.myflickpicks.android.fragments;

import net.myflickpicks.android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class LoginFragment extends SherlockFragment {
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  View myFragmentView = inflater.inflate(R.layout.login_layout, container, false);
	  
	  Button loginButton = (Button) myFragmentView.findViewById(R.id.loginbutton);
	    loginButton.setOnClickListener(new OnClickListener() {
	        @Override
	        public void onClick(final View v) {
	        	ListSelectFragment selectFrag = new ListSelectFragment();
	    		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, selectFrag, "list_select").addToBackStack(getTag()).commit();
	        }
	    });
	  
	  return myFragmentView;
	 }
}
