package demo.vicshady.portfolio.service;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import demo.vicshady.portfolio.R;
import demo.vicshady.portfolio.app.AppConstants;
import demo.vicshady.portfolio.dto.DataDTO;
import demo.vicshady.portfolio.dto.DataDetailsDTO;
import demo.vicshady.portfolio.dto.MediaUploadResponse;
import demo.vicshady.portfolio.dto.UploadDataResponseDTO;
import demo.vicshady.portfolio.sql.DBConstant;
import demo.vicshady.portfolio.ui.HomeActivity;

public class UploadData extends Service {

	public static final String BROADCAST_ACTION = "demo.vicshady.portfolio.displayevent";
    Intent intent;
    NotificationManager mNotifyManager;
    NotificationCompat.Builder mBuilder;
    private static final String TAG = "BroadcastService";
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		intent = new Intent(BROADCAST_ACTION);	
		onStartService();
		startNotification();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*handler.removeCallbacks(sendUpdatesToUI);
		sendUpdatesToUI = null;
		handler = null;*/
	}
	
    
    private void DisplayLoggingInfo(String message) {
    	Log.d(TAG, "entered DisplayLoggingInfo");
    	intent.putExtra("text", message);
    	sendBroadcast(intent);
    }
    
    ArrayList<DataDTO> dataDTOs;
	ArrayList<DataDetailsDTO> dataDetailsDTOs;
	
	
	public void startNotification()
	{

		mNotifyManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(this);
		Intent notificationIntent = new Intent(this, HomeActivity.class);
	    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		mBuilder.setContentTitle("Portfolio")
				.setContentText("Sync in Progress")
				.setSmallIcon(R.drawable.ic_launcher);
		mBuilder.setProgress(0, 0, true);
		mBuilder.setContentIntent(contentIntent);
		// Issues the notification
		mNotifyManager.notify(0, mBuilder.build());
	}
	
	public void onStartService()
	{
		loadData();
//		loadRecording();
		uploadServiceData();
	}
	
	public void loadData()
	{
		String _id;
		String name;
		String contact;
		String address;
		String attachment;
		String status;

		Cursor c = getContentResolver().query(DBConstant.Data_Columns.CONTENT_URI, null, DBConstant.Data_Columns.COLUMN_SYNC_STATUS +"=?", new String[]{"0"}, null);
		if( c != null && c.getCount() > 0)
		{
			dataDTOs = new ArrayList<DataDTO>();
			while(c.moveToNext())
			{
				_id = c.getString(c.getColumnIndex(DBConstant.Data_Columns.COLUMN_ID));
				name = c.getString(c.getColumnIndex(DBConstant.Data_Columns.COLUMN_NAME));
				contact = c.getString(c.getColumnIndex(DBConstant.Data_Columns.COLUMN_CONTACT));
				address = c.getString(c.getColumnIndex(DBConstant.Data_Columns.COLUMN_ADDRESS));
				attachment = c.getString(c.getColumnIndex(DBConstant.Data_Columns.COLUMN_ATTACHMENT));
				status = c.getString(c.getColumnIndex(DBConstant.Data_Columns.COLUMN_SYNC_STATUS));
				dataDTOs.add(new DataDTO(_id, name, contact, address, attachment,status));
			}
			c.close();

			for(int i = 0; i < dataDTOs.size(); i++)
			{
				c = getContentResolver().query(DBConstant.Data_Details_Columns.CONTENT_URI, null, DBConstant.Data_Details_Columns.COLUMN_DATA_ID +"=?", new String []{dataDTOs.get(i).getId()}, null);
				if( c != null && c.getCount() > 0)
				{
					ArrayList<DataDetailsDTO> dataDetailsDTOs = new ArrayList<DataDetailsDTO>();

					while(c.moveToNext())
					{
						String _exid = c.getString(c.getColumnIndex(DBConstant.Data_Details_Columns.COLUMN_ID));
						String _name = c.getString(c.getColumnIndex(DBConstant.Data_Details_Columns.COLUMN_NAME));
						String data_id = c.getString(c.getColumnIndex(DBConstant.Data_Details_Columns.COLUMN_DATA_ID));
						String url = c.getString(c.getColumnIndex(DBConstant.Data_Details_Columns.COLUMN_URL));
						String _status = c.getString(c.getColumnIndex(DBConstant.Data_Details_Columns.COLUMN_SYNC_STATUS));
						DataDetailsDTO dto = new DataDetailsDTO(_exid, data_id, url, _name, _status);
						dataDetailsDTOs.add(dto);
					}
					dataDTOs.get(i).setPaths(dataDetailsDTOs);
				}

			}
		}
	}
	
	public void uploadServiceData()
	{
		JSONObject finalJSON = new JSONObject();
		JSONObject tables = new JSONObject();
		try
		{
			if(dataDTOs != null && dataDTOs.size() > 0)
			{
				JSONArray exp = RequestBuilder.getData(dataDTOs);
				tables.put("data", exp);
				if(dataDTOs.get(0).getPaths()!=null)
				{
					JSONArray imagesDetails = new JSONArray();
					Log.i("Json Creating", "Image Found");
				for(int i = 0; i < dataDTOs.size(); i++)
				{
					ArrayList<DataDetailsDTO> images = dataDTOs.get(i).getPaths();

					for(int j = 0; j < images.size(); j++)
					{
						JSONObject t = RequestBuilder.getDataDetails(images.get(j));
						imagesDetails.put(t);
					}
				}
				tables.put("data_image", imagesDetails);
				}
				else
				{
					Log.i("Json Creating", "No Image Found");
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			Log.e("JSONCREATING-uploaddataservice()", e.toString());
		}		
		TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String currentSIMImsi = mTelephonyMgr.getDeviceId();
		
		JSONObject jsonObject = RequestBuilder.getServicesData(currentSIMImsi, tables);
		Log.e("VIKALP--------------->>>>>>>>>>", jsonObject.toString());
		Log.e("VIKALP--------------->>>>>>>>>>", tables.toString());
		UploadTask uploadTask = new UploadTask();
		uploadTask.execute(new JSONObject[]{jsonObject});
	}
	
	
	private class UploadTask extends AsyncTask<JSONObject, Void, Void>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(JSONObject... params) {
			// TODO Auto-generated method stub
			JSONObject dataToSend = params[0];
			
			String jsonString = dataToSend.toString();
			/*String s = dataToSend.toString();
			
			if(s != null)
			{
				s = s.replaceAll("\\\\", "");
			}
			try {
				dataToSend = new JSONObject(new String(s));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			try {
				DisplayLoggingInfo("Uploading data to server");
				String str = ServiceDelegate.postData(AppConstants.URLS.BASE_URL, dataToSend);
				
				//str = "{\"tables\":{\"service\":[]},\"lov\":{\"location\":[\"L 1\"],\"expense_category\":[],\"patient_type\":[\"OPD\",\"IPD\",\"SX\"],\"payment_mode\":[\"M2\",\"M1\"],\"diagnose_procedure\":[],\"referred_by\":[\"R2\",\"R1\"],\"start_time\":[],\"surgery_level\":[\"Level : 7\",\"Level : 6\",\"Level : 5\",\"Level : 4\",\"Level : 3\",\"Level : 2\",\"Level : 1\"],\"team_member\":[],\"ward\":[]}}";
				UploadDataResponseDTO responseDTO = ResponseParser.getUploadDataResponse(str);
				
				if(responseDTO != null)
				{
					// update services
					
					String _data = responseDTO.getData();
					if(_data!= null && !_data.equals("[]") && !_data.equals(""))
					{
						_data = _data.substring(1, _data.length() - 1);
						{
							ContentValues contentValues = new ContentValues();
							contentValues.put(DBConstant.Data_Columns.COLUMN_SYNC_STATUS, 1);
							String[] data =  _data.split(",");
							if(data.length > 0)
							{
								for(int i = 0; i < data.length; i++)
								{
									String s = data[i];
									if(data[i].startsWith("\"") && data[i].endsWith("\""))
									{
										s = data[i].substring(1, data[i].length() - 1);
									}
									try
									{
										int col = getContentResolver().update(DBConstant.Data_Columns.CONTENT_URI, contentValues, DBConstant.Data_Columns.COLUMN_ID +"=?", new String[]{s});
										Log.e("ROWS UPDATED : data : ", col +"");
									}
									catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}
								}
							}
							
						}
					}	
				}
				Log.e("UploadTask", str);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			DisplayLoggingInfo("Data submitted successfully.");
			uploadMediaFiles();
		}
	}
	
	public void uploadMediaFiles()
	{
		boolean stopService = false;
		if(dataDTOs != null && dataDTOs.size() > 0)
		{
			uploadImages();
			stopService=true;
		}
		if(stopService)
		{
			stopService();
		}
	}
	
	public void stopService()
	{
		DisplayLoggingInfo("Upload finish.");
		try
		{
			removeStickyBroadcast(intent);
			mNotifyManager.cancelAll();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		stopSelf();
	}
	
	public void uploadImages()
	{
		if(dataDTOs != null && dataDTOs.size() > 0)
		{
			UploadImageTask uploadMediaTask = new UploadImageTask();
			uploadMediaTask.execute();
		}
		else
		{
			stopService();
		}
	}
	
	
	private class UploadImageTask extends AsyncTask<Void, Void, String>
	{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String n = null;
			for(int i = 0; i < dataDTOs.size(); i++)
			{
				ArrayList<DataDetailsDTO> details = dataDTOs.get(i).getPaths();
				if(details != null && details.size() > 0)
				{
					for(int j = 0; j < details.size(); j++)
					{
						try
						{
							String typ = "image";
							String path = details.get(j).getUrl();
							File f = new File(path);
							n  = path.substring(path.lastIndexOf("/") + 1);
							DisplayLoggingInfo("Uploading " + n + "...");
							String s = ServiceDelegate.postRecordedFile1(typ, f, RequestBuilder.getUploadData(), AppConstants.URLS.MEDIA_BASE_URL);
							MediaUploadResponse uploadResponse = ResponseParser.getMediaUploadResponse(s);
							Log.e("UploadMediaTask : " + path + " : ", s);
						}catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}				
			}	
			return n;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			DisplayLoggingInfo("All expenses images uploaded successfully.");
			stopService();
		}
	}

}
