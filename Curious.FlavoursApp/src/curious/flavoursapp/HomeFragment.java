package curious.flavoursapp;

import java.util.ArrayList;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment
{

	ViewFlipper flipper;
	private View thisFragment;
	
	
	//Default constructor
	public HomeFragment() {}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
	{
		View rootView = inflater.inflate(R.layout.fragment_home, container,false);
		

		 flipper=(ViewFlipper)rootView.findViewById(R.id.viewFlipperHome);
		 flipper.setFlipInterval(2000);//setting the interval 500 milliseconds 
         flipper.startFlipping();    //views flipping starts.
         
         
/*         
         Button buttonPrev = (Button) rootView.findViewById(R.id.prev);
         Button buttonNext = (Button) rootView.findViewById(R.id.next);

         buttonPrev.setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View arg0) {
        	  flipper.showPrevious();
          }
         });

         buttonNext.setOnClickListener(new OnClickListener() {

          @Override
          public void onClick(View arg0) {
        	  flipper.showNext();
          }
         });*/

		
         return rootView;
 		
	}
	
	public void OnclickNext(View v)
	{
		
		flipper.showNext();
	}
	
	public void OnclickPrevious(View v)
	{
		flipper.showPrevious();
		
	}

}
