package demo.vicshady.portfolio.sql;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class PortfolioDB extends ContentProvider {

	public static final String AUTHORITY = "demo.vicshady.portfolio.sql.PortfolioDB";
	
	private static final UriMatcher sUriMatcher;
	private static final int DATA = 1;
	private static final int DATA_DETAILS = 2;
	
	private static HashMap<String, String> dataProjectionMap;
	private static HashMap<String, String> dataDetailProjectionMap;
	
	private static final int DATABASE_VERSION = 1;
	
	OpenHelper openHelper;
	
	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DBConstant.DB_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			//location table
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("CREATE TABLE ");
			strBuilder.append(DBConstant.TABLE_DATA);
			strBuilder.append('(');
			strBuilder.append(DBConstant.Data_Columns.COLUMN_ID +" INTEGER(20) PRIMARY KEY NOT NULL DEFAULT (STRFTIME('%s',CURRENT_TIMESTAMP))," );//EU10001
			strBuilder.append(DBConstant.Data_Columns.COLUMN_NAME +" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Columns.COLUMN_CONTACT +" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Columns.COLUMN_ADDRESS +" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Columns.COLUMN_ATTACHMENT +" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Columns.COLUMN_SYNC_STATUS +" NUMBER" );
			strBuilder.append(')');
			db.execSQL(strBuilder.toString());
			Log.e("CREATE TABLE->",strBuilder.toString());
			
			strBuilder = new StringBuilder();
			strBuilder.append("CREATE TABLE ");
			strBuilder.append(DBConstant.TABLE_DATA_DETAILS);
			strBuilder.append('(');
			strBuilder.append(DBConstant.Data_Details_Columns.COLUMN_ID +" INTEGER(20) PRIMARY KEY NOT NULL DEFAULT (STRFTIME('%s',CURRENT_TIMESTAMP))," );
			strBuilder.append(DBConstant.Data_Details_Columns.COLUMN_NAME+" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Details_Columns.COLUMN_DATA_ID+" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Details_Columns.COLUMN_URL +" TEXT UNIQUE, " );
			strBuilder.append(DBConstant.Data_Details_Columns.COLUMN_SYNC_STATUS +" NUMBER" );
			strBuilder.append(')');
			db.execSQL(strBuilder.toString());
			Log.e("CREATE TABLE->",strBuilder.toString());
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DBConstant.TABLE_DATA);//SA 10006
			db.execSQL("DROP TABLE IF EXISTS " + DBConstant.TABLE_DATA_DETAILS);//EA 10006
			onCreate(db);

		}
	}
	
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri)) {
		case DATA:
			count = db.delete(DBConstant.TABLE_DATA, where, whereArgs);
			break;
		case DATA_DETAILS:
			count = db.delete(DBConstant.TABLE_DATA_DETAILS, where, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (sUriMatcher.match(uri)) {
		case DATA:
			return DBConstant.Data_Columns.CONTENT_TYPE;
		case DATA_DETAILS:
			return DBConstant.Data_Details_Columns.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		if (sUriMatcher.match(uri) != DATA && sUriMatcher.match(uri) != DATA_DETAILS) 
		{ 
			throw new IllegalArgumentException("Unknown URI " + uri); 
		}
		
		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} 
		else {
			values = new ContentValues();
		}
		
		SQLiteDatabase db = openHelper.getWritableDatabase();
		long rowId = 0;
		
		switch (sUriMatcher.match(uri)) 
		{
			case DATA:
				 rowId = db.insertWithOnConflict(DBConstant.TABLE_DATA, null, values, SQLiteDatabase.CONFLICT_REPLACE);
				if (rowId > 0) 
				{
					Uri noteUri = ContentUris.withAppendedId(DBConstant.Data_Columns.CONTENT_URI, rowId);
					getContext().getContentResolver().notifyChange(noteUri, null);
					return noteUri;
				}
				break;
			case DATA_DETAILS:
				 rowId = db.insertWithOnConflict(DBConstant.TABLE_DATA_DETAILS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
				if (rowId > 0) 
				{
					Uri noteUri = ContentUris.withAppendedId(DBConstant.Data_Details_Columns.CONTENT_URI, rowId);
					getContext().getContentResolver().notifyChange(noteUri, null);
					return noteUri;
				}
				break;
			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
				
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		openHelper 		= new OpenHelper(getContext());
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (sUriMatcher.match(uri)) {
		case DATA:
			qb.setTables(DBConstant.TABLE_DATA);
			qb.setProjectionMap(dataProjectionMap);
			break;
		case DATA_DETAILS:
			qb.setTables(DBConstant.TABLE_DATA_DETAILS);
			qb.setProjectionMap(dataDetailProjectionMap);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		SQLiteDatabase db = openHelper.getReadableDatabase();
		//SQLiteDatabase db = openHelper.getWritableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int count = -1;
		switch (sUriMatcher.match(uri)) {
		case DATA:
			Log.e("VIKALP.............", "UPDATING............");
			count = db.update(DBConstant.TABLE_DATA, values, where, whereArgs);
			Log.e("VIKALP.............", "UPDATING............");
			break;
		case DATA_DETAILS:
			Log.e("VIKALP.............", "UPDATING............");
			count = db.update(DBConstant.TABLE_DATA_DETAILS, values, where, whereArgs);
			Log.e("VIKALP.............", "UPDATING............");
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

static {
		
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, DBConstant.TABLE_DATA, DATA);
		sUriMatcher.addURI(AUTHORITY, DBConstant.TABLE_DATA_DETAILS, DATA_DETAILS);

		dataProjectionMap = new HashMap<String, String>();
		dataProjectionMap.put(DBConstant.Data_Columns.COLUMN_ID, DBConstant.Data_Columns.COLUMN_ID);
		dataProjectionMap.put(DBConstant.Data_Columns.COLUMN_NAME, DBConstant.Data_Columns.COLUMN_NAME);
		dataProjectionMap.put(DBConstant.Data_Columns.COLUMN_CONTACT, DBConstant.Data_Columns.COLUMN_CONTACT);
		dataProjectionMap.put(DBConstant.Data_Columns.COLUMN_ADDRESS, DBConstant.Data_Columns.COLUMN_ADDRESS);
		dataProjectionMap.put(DBConstant.Data_Columns.COLUMN_ATTACHMENT, DBConstant.Data_Columns.COLUMN_ATTACHMENT);
		dataProjectionMap.put(DBConstant.Data_Columns.COLUMN_SYNC_STATUS, DBConstant.Data_Columns.COLUMN_SYNC_STATUS);
		
		dataDetailProjectionMap = new HashMap<String, String>();
		dataDetailProjectionMap.put(DBConstant.Data_Details_Columns.COLUMN_ID, DBConstant.Data_Details_Columns.COLUMN_ID);
		dataDetailProjectionMap.put(DBConstant.Data_Details_Columns.COLUMN_NAME, DBConstant.Data_Details_Columns.COLUMN_NAME);
		dataDetailProjectionMap.put(DBConstant.Data_Details_Columns.COLUMN_DATA_ID, DBConstant.Data_Details_Columns.COLUMN_DATA_ID);
		dataDetailProjectionMap.put(DBConstant.Data_Details_Columns.COLUMN_URL, DBConstant.Data_Details_Columns.COLUMN_URL);
		dataDetailProjectionMap.put(DBConstant.Data_Details_Columns.COLUMN_SYNC_STATUS, DBConstant.Data_Details_Columns.COLUMN_SYNC_STATUS);
	}
}
