package com.softeq.prepopdb.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.softeq.android.prepopdb.R;
import com.softeq.prepopdb.dbhelper.ExternalDbOpenHelper;

public class PrepopSqliteDbActivity extends ListActivity {
    private static final String DB_NAME = "yourdb.sqlite";
    //������� ��������� �������� ������� ���� ����� �� �����������
	private static final String TABLE_NAME = "friends";
	private static final String FRIEND_ID = "_id";
	private static final String FRIEND_NAME = "name";
	public static final String FRIEND_PLACE = "place";
    public static final String FRIEND_PHONE = "phone";
    public static final String FRIEND_LOC = "loc";
    public static final String FRIEND_CATEG = "categ";
   
   
	
	
	
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> friends;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //��� �������� ������
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
        //���, ���� �������!
        fillFreinds();
        setUpList();      
    }

    

	/*Cursor cursor = db.query(TABLE_NAME, new String[] {"_id", "title", "title_raw"}, 
            "title_raw like " + "'%Smith%'", null, null, null, null);
	
	
	   
@Override
public void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  
    // create or open database file
    db = openOrCreateDatabase(database_name , SQLiteDatabase.CREATE_IF_NECESSARY,  
                                                                           null);
    db.setVersion(1);
    db.setLocale(Locale.getDefault());
    db.setLockingEnabled(true);
   
    // creating table in database
    db.execSQL("CREATE TABLE IF NOT EXISTS "+table_name+" " +
                    "( sid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "  name TEXT," +
                    "  age INTEGER," +
                    "  course TEXT ); ");
   
    //Insert("Rohit","19","CA");
   
    Cursor cur=fetchAllTodos();
    startManagingCursor(cur);
   
    cur.moveToFirst();
    new ArrayList<String>();
    do {
          TextView txtId=(TextView)findViewById(R.id.txtID);
          txtId.append(cur.getString(0)+"     "+cur.getString(1)+"     
               "+cur.getString(2)+"     "+cur.getString(3)+ "\n");
    }while (cur.moveToNext());
}

// fetching records from database
public Cursor fetchAllTodos()
{
          return db.query(table_name, new String[] { KEY_ROWID, KEY_NAME,
                      KEY_AGE, KEY_COURSE }, null, null, null,null, null);                                                                        
}

// inserting record in the database
public void Insert(String name, String age, String course)
{
   ContentValues data=createContentValues(name, age, course);
   db.insert(table_name, null, data);
   Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show();
}

// return a content of the database
private ContentValues createContentValues(String name, String age, String course)
{
          ContentValues values = new ContentValues();
          values.put(KEY_NAME, name);
          values.put(KEY_AGE, age);
          values.put(KEY_COURSE, course);
          return values;
}
}
	
	
	
	*/
    
    
	private void setUpList() {
		//��������� ����������� ������� � layout �������� ��� ���������
		setListAdapter(new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, friends));
		listView = getListView();
		
		//������� ���� ����, ��� ����
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
								int position,long id) {
				Toast.makeText(getApplicationContext(),
							((TextView) view).getText().toString(),
							 Toast.LENGTH_SHORT).show();				
			}
		});
	}
	
	//���������� �������� �� ���� ������
	private void fillFreinds() {
		friends = new ArrayList<String>();
		Cursor friendCursor = database.query(TABLE_NAME,
				new String[] 
						 {FRIEND_ID, FRIEND_NAME,FRIEND_PLACE,FRIEND_PHONE,FRIEND_LOC,FRIEND_CATEG},
						 null, null, null, null, null);

		friendCursor.moveToFirst();
		if(!friendCursor.isAfterLast()) {
			do {
				String name = friendCursor.getString(1);
				friends.add(name);
			} while (friendCursor.moveToNext());
		}
		friendCursor.close();
	}
}