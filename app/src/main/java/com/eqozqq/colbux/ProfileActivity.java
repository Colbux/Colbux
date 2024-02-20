package com.eqozqq.colbux;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class ProfileActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> authMap = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> authmap = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private CircleImageView circleimageview_avatar;
	private TextView textview_username;
	private EditText textview_username_about_me;
	private TextView textview1;
	private LinearLayout linear_email;
	private LinearLayout linear_password;
	private ImageView imageview1;
	private TextView textview2;
	private TextView textview_email;
	private ImageView imageview2;
	private TextView textview9;
	private TextView textview_password;
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);

		//TOOLBAR
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w = ProfileActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF2A2831);
		}
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar_bg)));


		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		circleimageview_avatar = findViewById(R.id.circleimageview_avatar);
		textview_username = findViewById(R.id.textview_username);
		textview_username_about_me = findViewById(R.id.textview_username_about_me);
		textview1 = findViewById(R.id.textview1);
		linear_email = findViewById(R.id.linear_email);
		linear_password = findViewById(R.id.linear_password);
		imageview1 = findViewById(R.id.imageview1);
		textview2 = findViewById(R.id.textview2);
		textview_email = findViewById(R.id.textview_email);
		imageview2 = findViewById(R.id.imageview2);
		textview9 = findViewById(R.id.textview9);
		textview_password = findViewById(R.id.textview_password);
		
		linear_email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear_password.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				users.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						authmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								authmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_childKey.equals("")) {
							textview_username.setText(_childValue.get("username").toString());
							textview_email.setText(_childValue.get("email").toString());
						}
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
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
	}
	
	private void initializeLogic() {
		getSupportActionBar().setTitle("Мой профиль");
		Glide.with(getApplicationContext()).load(Uri.parse("https://cdn.discordapp.com/attachments/952639511794425917/1132202720846164028/image0.png")).into(circleimageview_avatar);
		users.push().updateChildren(authMap);
		users.addChildEventListener(_users_child_listener);
		_rippleRoundStroke(linear_email, "#121212", "#212121", 0, 0, "#FFFFFF");
		_rippleRoundStroke(linear_password, "#121212", "#212121", 0, 0, "#FFFFFF");
	}
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
}
