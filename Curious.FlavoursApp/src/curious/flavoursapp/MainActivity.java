package curious.flavoursapp;

import android.support.v4.app.ActionBarDrawerToggle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	
	private DrawerLayout mDrawerLayout; //To access the main layout 
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle; //Action Bar Toggle Button top of the page with Home, Text and Settings openener
	private CharSequence mTitle; // Title of the page
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.foodmenu_main_list);
		

		String[] values = new String[] { "Home", "Drinks & Soups", "Appetizers",
			        "Main Course", "Briyanis", "Favorites" };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, values);
	    mDrawerList.setAdapter(adapter);
		
		mTitle = getTitle();
	
		
		// enabling action bar app icon and behaving it as toggle button
		//to enable up,back button after reaching children tabs
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//Enable or disable the "home" button in the corner of the action bar
		getActionBar().setHomeButtonEnabled(true);
		
		//Click listeenr for list items
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		

		if (savedInstanceState == null) {
			// on first time display Home view
			displayView(0);
		}
		
	}
	
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
	//Display corresponding fragment whenever a list item is selected
	private void displayView(int position)
	{
		Fragment fragment = null;
		switch(position)
		{
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new DrinksFragment();
				break;
			default:
				break;
						
		}
		
		if(fragment !=null)
		{
			FragmentManager fragmentManager =  getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
			
			//update the selected item in drawer and close the list
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			//setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
		 else 
		 {
				// error in creating fragment
				Log.e("MainActivity", "Error in creating fragment");
		 }
		
	}
	
	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
