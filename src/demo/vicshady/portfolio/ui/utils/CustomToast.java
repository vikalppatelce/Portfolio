/* HISTORY
 * CATEGORY 		:- ACTIVITY
 * DEVELOPER		:- VIKALP PATEL
 * AIM			    :- ADD IPD ACTIVITY
 * DESCRIPTION 		:- SAVE IPD
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------
 */

package demo.vicshady.portfolio.ui.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import demo.vicshady.rajvidesigning.R;

public class CustomToast {

	public static void showToastMessage(Context context,String message){

		View layout = LayoutInflater.from(context).inflate(R.layout.customtoast, null);
         TextView text = (TextView) layout.findViewById(R.id.text);
         text.setText(message);
         Toast toast = new Toast(context);
         toast.setGravity(Gravity.CENTER, 0, 0);
         toast.setDuration(Toast.LENGTH_SHORT);
         toast.setView(layout);
         toast.show();
    }
}
