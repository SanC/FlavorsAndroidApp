package curious.flavoursapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class DrinksFragment extends Fragment
{
	ExpandableListAdapter listAdapter;
	ExpandableListView listViewMenu;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	
	
	NumberPicker np;
	
	//Default constructor
	public DrinksFragment() {}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
	{
		
		View rootView = inflater.inflate(R.layout.fragment_drinks, container,false);
		
		// get the listview
		listViewMenu = (ExpandableListView) rootView.findViewById(R.id.lvMenu);
		
		// preparing list data
		prepareListData();
		
		listAdapter = new ExpandableListAdapter(rootView.getContext(), listDataHeader, listDataChild);

		// setting list adapter
		listViewMenu.setAdapter(listAdapter);
		

        
		return rootView;
	}
	
	


	private void prepareListData() 
	{
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Kingfisher Beer");
		listDataHeader.add("Merlot Wine");
		listDataHeader.add("Pinot Noir Wine");

		// Adding child data
		List<String> top250 = new ArrayList<String>();
		top250.add("Kingfisher is an Indian beer brewed by United Breweries Group, Bangalore. The brand was launched in 1978. With a market share of over 50%, It is currently available in 52 countries outside India.[1] The Heineken Group holds 37.5% equity shares in United Breweries Ltd.[2]");


		List<String> nowShowing = new ArrayList<String>();
		nowShowing.add("Merlot is a dark blue-coloured wine grape variety, that is used as both a blending grape and for varietal wines. The name Merlot is thought to be a diminutive of merle, the French name for the blackbird, probably a reference to the color of the grape");


		List<String> comingSoon = new ArrayList<String>();
		comingSoon.add("Pinot noir (French: is a red wine grape variety of the species Vitis vinifera. The name may also refer to wines created predominantly from Pinot noir grapes. ");


		listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
		listDataChild.put(listDataHeader.get(1), nowShowing);
		listDataChild.put(listDataHeader.get(2), comingSoon);
		
	}

}
