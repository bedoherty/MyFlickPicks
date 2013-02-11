package net.myflickpicks.android;

import net.myflickpicks.android.fragments.LoginFragment;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*
 * 	Main launch activity for My Flick Picks.  Uses a frame layout to host fragments.
 */
public class LaunchActivity extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_layout);
        
        if (findViewById(R.id.fragmentframe) != null) {
            // Create an instance of the LoginFragment shown in the fragmentframe
            LoginFragment loginFrag = (LoginFragment) getSupportFragmentManager().findFragmentByTag("login_frag");
            
            if (loginFrag == null)
            {
            	loginFrag = new LoginFragment();
            }
           
            
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentframe, loginFrag, "login_frag").commit();
   
        }
    }
}
