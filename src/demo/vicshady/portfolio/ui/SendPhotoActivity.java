/* HISTORY
 * CATEGORY 		:- UIX | HOME SCREEN
 * DEVELOPER		:- VIKALP PATEL
 * AIM      		:- SENT MAIL & UPLOAD DATA TO SERVER
 * DESCRIPTION 		:- HOME SCREEN | MAIN
 * 
 * S - START E- END  C- COMMENTED  U -EDITED A -ADDED
 * --------------------------------------------------------------------------------------------------------------------
 * INDEX       DEVELOPER		DATE			FUNCTION		DESCRIPTION
 * --------------------------------------------------------------------------------------------------------------------
 * 10001       VIKALP PATEL    10/02/2014       BUG             FIXED FOR THEME ISSUE [MOVED TO MANIFEST]	RCA: THEME [2.3+]				
 * --------------------------------------------------------------------------------------------------------------------
 */
package demo.vicshady.portfolio.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;
import demo.vicshady.portfolio.app.Portfolio;
import demo.vicshady.portfolio.service.UploadData;
import demo.vicshady.portfolio.sql.DBConstant;
import demo.vicshady.portfolio.ui.utils.CustomToast;
import demo.vicshady.portfolio.utils.ImageCompression;
import demo.vicshady.portfolio.utils.Mail;
import demo.vicshady.portfolio.utils.MailBody;

public class SendPhotoActivity extends SherlockFragmentActivity{

	public static final int IMPORT_PICTURE = 1;
	public static final int PIC = 117;
	final int REQUEST_PORTFOLIO_CAMERA = 1002;
	
	Handler mHandler;
	ActionBar actionBar;
	
	Uri outputFileUri,currentFileUri;
	Cursor dataCursor;
	
	String compressedPath,_name,_contact,_address;
	String _id=null;
	String username,password,sentto;
	ArrayList<String> imagePaths;
	
	EditText address,name,contact;
	ImageView img;
	TextView delete;
	Button submit;
	FrameLayout fm;//TESTDRIOD
	
	SharedPreferences pref;
	
	//TextWatcher addressWatcher;
	OnEditorActionListener addressWatcher; 
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//SA 10001
		//setTheme(R.style.Sherlock___Theme_DarkActionBar);
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("Send Photograph");
		actionBar.setIcon(android.R.drawable.ic_menu_gallery);
		//EA 10001
//		setContentView(R.layout.new_home);
		setContentView(R.layout.send_photograph);
		img = (ImageView)findViewById(R.id.picviewer);
		address = (EditText)findViewById(R.id.address);
		name = (EditText)findViewById(R.id.name);
		contact  = (EditText)findViewById(R.id.contact_no);
		fm = (FrameLayout)findViewById(R.id.frame);//TESTDROID
		delete = (TextView)findViewById(R.id.delete);//TESTDRIOD
		pref = PreferenceManager.getDefaultSharedPreferences(Portfolio.getApplication());		

//		address.addTextChangedListener(textWatcher);
//		contact.addTextChangedListener(textWatcher);
//		name.addTextChangedListener(textWatcher);
		mHandler = new Handler(Portfolio.getApplication().getMainLooper());
		imagePaths =  new ArrayList<String>();
	}

	public void onAddPicture(View v)
	{
		showDialog(PIC);
	}
	
	public void onImportPicture(View v)
	{
		Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, IMPORT_PICTURE);
	}
	
	public void onDelete(View v)
	{
		CustomToast.showToastMessage(getApplicationContext(), "Deleted!");
		img.setImageDrawable(null);
		fm.setVisibility(View.GONE);
		imagePaths.clear();
	}
	public void onClear(View v)
	{
		name.setText("");
		address.setText("");
		contact.setText("");
		img.setImageDrawable(null);
		img.setVisibility(View.GONE);
		imagePaths.clear();
	}
	
	public void clear()
	{
		name.setText("");
		address.setText("");
		contact.setText("");
		img.setImageDrawable(null);
		img.setVisibility(View.GONE);
		fm.setVisibility(View.GONE);
		imagePaths.clear();

	}
	public void onSubmit(View v)
	{
		_name=name.getText().toString();
		_address=address.getText().toString();
		_contact=contact.getText().toString();
		
		username=pref.getString("prefUser", null);
		password=pref.getString("prefPass", null);
		sentto=pref.getString("prefSent", null);
		
		if(validate(_name,_contact,_address))
		{
			save(_name,_address,_contact);
		if (isNetworkAvailable()) {
			if (validateMail()) {
				new MailTask(SendPhotoActivity.this).execute();
			} else {
				CustomToast.showToastMessage(getApplicationContext(),"Please check email credentials");
			}
			Intent i = new Intent(this, UploadData.class);
			startService(i);
		}
		else
		{
			CustomToast.showToastMessage(getApplicationContext(), "Please check your internet connection");
		}
		}
	}
	
	public class MailTask extends AsyncTask<String,Void,String>{
		public Context context;
		public ProgressDialog pDialog;
		public MailTask(Context c)
		{
			context = c; 
		}
		@Override
		protected String doInBackground(String... params) {
			/*
			 * MAIL SENDING
			 */
			 Mail m = new Mail(username, password); 
		      String[] toArr = {"vikalppatelce@yahoo.com", "vikalppatel043@gmail.com",sentto}; 
		      m.setTo(toArr);
		      m.setFrom("VikalpPatel043@gmail.com"); 
		      m.setSubject("This is an email sent using Portfolio Application from an Android device.");
//		      m.setBody("<html><body><b><p>Dear Sir,"
//		      		+ "  Following are the details added on Portfolio Application."
//		      		+ "  Name:"+ _name +"  Contact No:"+_contact +"  Address:"+_address+"</p><p> These is autogenerated mail. </p></b></body></html>"); 
		      
		      m.setBody("<html><body>"+MailBody.getBody(_name,_contact,_address)+"</body></html>");
		      try { 
		    	  if(compressedPath!=null && compressedPath.length() > 0)
		        m.addAttachment(compressedPath); 
		        if(m.send()) { 
		        	Log.e("MailApp", "Mail sent successfully!"); 
		        } else { 
		        	Log.e("MailApp", "Could not send email"); 
		        } 
		      } catch(Exception e) { 
		        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
		        Log.e("MailApp", "Could not send email", e); 
		      } 
			return "MailSent";
		}
		@Override
		protected void onPostExecute(String result) {
//			Toast.makeText(HomeActivity.this, "Mail Sent", Toast.LENGTH_SHORT).show();
			CustomToast.showToastMessage(SendPhotoActivity.this, "Mail Sent");
//			submit.setEnabled(true);
//			submit.setText("Submit");
			pDialog.dismiss();
			clear();
        }

        @Override
		protected void onPreExecute() {
        	super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Sending...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
	}
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle b) {
		// TODO Auto-generated method stub
		switch (id) {
		 case PIC:
			 LayoutInflater inflater = LayoutInflater.from(this);
			 View dialogview = inflater.inflate(R.layout.add_pic_dialog, null);
			 AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
			 dialogbuilder.setView(dialogview);
			 return dialogbuilder.create();
		}
		return super.onCreateDialog(id);
	}

	
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case PIC:
			final AlertDialog alertDialog = (AlertDialog) dialog;
			Button import_picture = (Button) alertDialog.findViewById(R.id.import_picture);
			Button take_picture = (Button) alertDialog.findViewById(R.id.take_picture);
			import_picture.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onImportPicture(v);
					alertDialog.dismiss();
				}
			});
			take_picture.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onTakePicture(v);
					alertDialog.dismiss();
				}
			});
			}
	}
	
	public void onTakePicture(View v)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		getImagePath();
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		//Intent cameraIntent = new Intent(getApplicationContext(),CameraActivity.class);
//	        cameraIntent.putExtra("FILE_URI", outputFileUri.toString());
	        startActivityForResult(intent, REQUEST_PORTFOLIO_CAMERA);
	}
	
	public void save(String name,String contact,String address)
	{
		Bundle b = new Bundle();
		b.putString("message", "Saving");
		ContentValues dataValues = new ContentValues();
		dataValues.put(DBConstant.Data_Columns.COLUMN_NAME, name);
		dataValues.put(DBConstant.Data_Columns.COLUMN_CONTACT, contact);
		dataValues.put(DBConstant.Data_Columns.COLUMN_ADDRESS, address);
		dataValues.put(DBConstant.Data_Columns.COLUMN_ATTACHMENT, imagePaths.size()>0 ? "1" : "0");
		dataValues.put(DBConstant.Data_Columns.COLUMN_SYNC_STATUS, "0");
		getContentResolver().insert(DBConstant.Data_Columns.CONTENT_URI,dataValues);
		
		if(imagePaths.size() > 0)
		{
			dataCursor = getContentResolver().query(DBConstant.Data_Columns.CONTENT_URI, null, null, null, null);
			if (dataCursor != null && dataCursor.getCount() > 0) 
			{
				dataCursor.moveToLast();
				_id = dataCursor.getString(dataCursor.getColumnIndex(DBConstant.Data_Columns.COLUMN_ID));
				
				for (int i = 0; i < imagePaths.size(); i++) {
					String url = imagePaths.get(i);
					String file_name = url.substring(url.lastIndexOf("/") + 1);
					ContentValues dataDetail = new ContentValues();
					dataDetail.put(DBConstant.Data_Details_Columns.COLUMN_NAME, file_name);
					dataDetail.put(DBConstant.Data_Details_Columns.COLUMN_URL, url);
					dataDetail.put(DBConstant.Data_Details_Columns.COLUMN_DATA_ID, _id);
					dataDetail.put(DBConstant.Data_Details_Columns.COLUMN_SYNC_STATUS, 0);
					getContentResolver().insert(DBConstant.Data_Details_Columns.CONTENT_URI, dataDetail);
				}
			}
		}
		
	}
	
	public boolean validateMail()
	{
		if(username!=null && password!=null && sentto!=null)
			return true;
		
		if(username==null)
			return false;
		if(password==null)
			return false;
		if(sentto == null)
			return false;
		
		return false;
	}
	public boolean validate(String name, String contact, String address)
	{
		if(name.length() > 0 && contact.length() > 0 && address.length() >0)
			return true;
		
		if(name.length() <= 0)
		{
			this.name.setError("Please enter name");
			return false;
		}
		if(contact.length() <= 0)
		{
			this.contact.setError("Please enter contact");
			return false;
		}
		
		if(address.length() <= 0)
		{
			this.address.setError("Please enter address");
			return false;
		}
		
		return false;
	}
	
	TextWatcher textWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			greenSubmit(count);
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		@Override
		public void afterTextChanged(Editable s) {
		}
	};
	public void greenSubmit(int count)
	{
		if(count > 0)
		{
			if(address.getText().toString().length() > 0 && 
					name.getText().toString().length() > 0 &&
					contact.getText().toString().length() > 0
					)
			submit.setBackgroundResource(R.drawable.button_large_green);
		}
		else
			submit.setBackgroundResource(R.drawable.selectable_item_background);
	}
	public void getImagePath()
	{
		File imageDirectory =null;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) 
		{
			imageDirectory = new File(AppConstants.IMAGE_DIRECTORY_PATH);
		}
		else
		{
			imageDirectory = new File(AppConstants.IMAGE_DIRECTORY_PATH_DATA);
		}
		imageDirectory.mkdirs();
		File tempFile = new File(imageDirectory, getVideoName()+ AppConstants.EXTENSION);
		outputFileUri = Uri.fromFile( tempFile );
		currentFileUri = outputFileUri;
	}
	public static String getVideoName()
	{
		String name = "portfolio";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			name = sdf.format(new Date(System.currentTimeMillis()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	public void copy(File src, File dst) throws IOException {
	    InputStream in = new FileInputStream(src);
	    OutputStream out = new FileOutputStream(dst);

	    // Transfer bytes from in to out
	    byte[] buf = new byte[1024];
	    int len;
	    while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	    }
	    in.close();
	    out.close();
	}
	private void galleryAddPic() {
	    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
	    File f = new File(currentFileUri.getPath());
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    this.sendBroadcast(mediaScanIntent);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap bm = null;
		if (resultCode == RESULT_OK) 
		{
			img.setVisibility(View.VISIBLE);
			fm.setVisibility(View.VISIBLE);//TESTDROID
			if (requestCode == IMPORT_PICTURE) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaColumns.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				getImagePath();
				try {
					copy(new File(picturePath), new File(currentFileUri.getPath()));
					} 
				catch (IOException e) 
				{
					Log.e("IMPORT_PICTURE", e.toString());
				}
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				btmapOptions.inSampleSize = 2;
				bm = BitmapFactory.decodeFile(picturePath,btmapOptions);
				compressedPath = ImageCompression.compressImage(currentFileUri.getPath());
				galleryAddPic();
				imagePaths.add(compressedPath);
				img.setImageBitmap(bm);
			}
		}
		
		if (requestCode == REQUEST_PORTFOLIO_CAMERA) {

			if (resultCode == RESULT_OK) {
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				btmapOptions.inSampleSize = 2;
				bm = BitmapFactory.decodeFile(currentFileUri.getPath(),btmapOptions);
				compressedPath = ImageCompression.compressImage(currentFileUri.getPath());
				galleryAddPic();
				imagePaths.add(compressedPath);
				img.setImageBitmap(bm);
			} else {
				Toast.makeText(getApplicationContext(),"Error while taking picture!", Toast.LENGTH_SHORT).show();
			}
		}
		
		
	}
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
	       inflater.inflate(R.menu.main, menu);
		 return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case android.R.id.home:
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            return true;
        case R.id.more: 
        	return true;
        case R.id.action_settings:
        	Intent iSettings = new Intent(this,PrefsActivity.class);
        	startActivity(iSettings);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
