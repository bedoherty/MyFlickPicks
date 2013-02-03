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


public class LaunchActivity extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_layout);
        
        if (findViewById(R.id.fragmentframe) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            /*if (savedInstanceState != null) {
                return;
            }*/

            // Create an instance of ExampleFragment
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
