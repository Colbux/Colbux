package com.eqozqq.colbux;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class CommentsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> commentsMap = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout toolbar;
	private ListView listview1;
	private LinearLayout linear_post_comment;
	private TextView toolbar_text;
	private EditText edittext_post_comment;
	private ImageView btn_post_comment;
	
	private Intent i = new Intent();
	private DatabaseReference comments = _firebase.getReference("comments");
	private ChildEventListener _comments_child_listener;
	private Calendar c = Calendar.getInstance();
	private AlertDialog.Builder d;
	private SharedPreferences file;
	private SharedPreferences user;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.comments);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		toolbar = findViewById(R.id.toolbar);
		listview1 = findViewById(R.id.listview1);
		linear_post_comment = findViewById(R.id.linear_post_comment);
		toolbar_text = findViewById(R.id.toolbar_text);
		edittext_post_comment = findViewById(R.id.edittext_post_comment);
		btn_post_comment = findViewById(R.id.btn_post_comment);
		d = new AlertDialog.Builder(this);
		file = getSharedPreferences("file", Activity.MODE_PRIVATE);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		btn_post_comment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!edittext_post_comment.getText().toString().equals("")) {
					c = Calendar.getInstance();
					commentsMap = new HashMap<>();
					commentsMap.put("message", edittext_post_comment.getText().toString());
					commentsMap.put("username", user.getString("username", ""));
					commentsMap.put("date", new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(c.getTime()));
					comments.push().updateChildren(commentsMap);
					commentsMap.clear();
					edittext_post_comment.setText("");
				}
			}
		});
		
		_comments_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				comments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				comments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				comments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		comments.addChildEventListener(_comments_child_listener);
	}
	
	private void initializeLogic() {
		toolbar_text.setText("Комментарии • ".concat(getIntent().getStringExtra("title")));
	}
	
	public void _Toast(final String _text) {
		LayoutInflater inflater = getLayoutInflater(); View toastL = inflater.inflate(R.layout.toast, null);
		LinearLayout linear = (LinearLayout) toastL.findViewById(R.id.linear);
		
		TextView text = (TextView) toastL.findViewById(R.id.text);
		
		text.setText(_text);
		android.graphics.drawable.GradientDrawable ln = new android.graphics.drawable.GradientDrawable ();
		ln.setColor (Color.parseColor("#424242"));
		
		ln.setCornerRadius (10);
		
		linear.setBackground (ln);
		
		linear.setElevation(5);
		text.setTextColor(0xFFFFFFFF);

		Toast toast = new Toast(getApplicationContext()); 
		
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastL);
		toast.show();
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.comment, null);
			}
			
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView message = _view.findViewById(R.id.message);
			final TextView username = _view.findViewById(R.id.username);
			final TextView s1 = _view.findViewById(R.id.s1);
			final TextView time = _view.findViewById(R.id.time);
			
			username.setText(_data.get((int)_position).get("username").toString());
			time.setText(_data.get((int)_position).get("date").toString());
			message.setText(_data.get((int)_position).get("message").toString());
			
			return _view;
		}
	}
}
