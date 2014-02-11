package demo.vicshady.portfolio.sql;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class DBConstant {
	
	public static final String DB_NAME = "PortfolioDB";
	
	public static final String TABLE_DATA 							= "data";
	public static final String TABLE_DATA_DETAILS 					= "dataDetails";
	
	public static class Data_Columns implements BaseColumns
	{
		public static final Uri CONTENT_URI = Uri.parse("content://"+ PortfolioDB.AUTHORITY + "/data");
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/data";

		public static final String COLUMN_ID 				= "_id";
		public static final String COLUMN_NAME 				= "name";
		public static final String COLUMN_CONTACT 			= "contact";
		public static final String COLUMN_ADDRESS 			= "address";
		public static final String COLUMN_ATTACHMENT 		= "attachment";
		public static final String COLUMN_SYNC_STATUS 		= "status";
	}
	
	public static class Data_Details_Columns implements BaseColumns
	{
		public static final Uri CONTENT_URI = Uri.parse("content://"+ PortfolioDB.AUTHORITY + "/dataDetails");
		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/dataDetails";

		public static final String COLUMN_ID 				= "_id";
		public static final String COLUMN_NAME 				= "name";
		public static final String COLUMN_DATA_ID 			= "data_id";
		public static final String COLUMN_URL 				= "url";
		public static final String COLUMN_SYNC_STATUS 		= "status";
	}

}
