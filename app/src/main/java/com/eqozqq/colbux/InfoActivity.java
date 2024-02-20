package com.eqozqq.colbux;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class InfoActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String key = "";
	private double comment_num = 0;
	private String fulldescription = "";
	private double texturesCommentNum = 0;
	private double modsCommentNum = 0;
	private String filePath = "";
	private String fileName = "";
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> textureCommentsMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> modCommentsMap = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear_comments;
	private LinearLayout bg;
	private LinearLayout linear_banner;
	private LinearLayout linear_top;
	private LinearLayout linear_actions;
	private LinearLayout s2;
	private ImageView imageview_banner;
	private LinearLayout linear_content_version;
	private LinearLayout linear_server_data;
	private TextView textview_description;
	private LinearLayout linear_uploader;
	private TextView textview_full_description;
	private TextView textview_title;
	private TextView textview_version;
	private TextView textview_pm_version;
	private LinearLayout linear_ip_and_port;
	private ImageView copyServerData;
	private ImageView btn_server_discord;
	private TextView server_ip;
	private TextView textview1;
	private TextView server_port;
	private ImageView imageview1;
	private TextView textview_uploader;
	private TextView s1;
	private TextView textview_upload_date;
	private TextView btn_download;
	private TextView btn_more_info;
	private WebView webview1;
	private LinearLayout linear_comments_top;
	private ListView listview1;
	private ImageView imageview_comments;
	private TextView textview_comments_count;
	private TextView textview_comments;
	private TextView btn_write_comment;
	
	private Intent i = new Intent();
	private DatabaseReference comments = _firebase.getReference("comments");
	private ChildEventListener _comments_child_listener;
	private SharedPreferences user;
	private StorageReference storage = _firebase_storage.getReference("storage");
	private OnCompleteListener<Uri> _storage_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _storage_download_success_listener;
	private OnSuccessListener _storage_delete_success_listener;
	private OnProgressListener _storage_upload_progress_listener;
	private OnProgressListener _storage_download_progress_listener;
	private OnFailureListener _storage_failure_listener;
	
	private DatabaseReference textureComments = _firebase.getReference("comments/textures/");
	private ChildEventListener _textureComments_child_listener;
	private DatabaseReference modComments = _firebase.getReference("comments/mods/");
	private ChildEventListener _modComments_child_listener;
	private SharedPreferences language;
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.info);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);

		//TOOLBAR
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w = InfoActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF2A2831);
		}
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar_bg)));
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
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
		linear_comments = findViewById(R.id.linear_comments);
		bg = findViewById(R.id.bg);
		linear_banner = findViewById(R.id.linear_banner);
		linear_top = findViewById(R.id.linear_top);
		linear_actions = findViewById(R.id.linear_actions);
		s2 = findViewById(R.id.s2);
		imageview_banner = findViewById(R.id.imageview_banner);
		linear_content_version = findViewById(R.id.linear_content_version);
		linear_server_data = findViewById(R.id.linear_server_data);
		textview_description = findViewById(R.id.textview_description);
		linear_uploader = findViewById(R.id.linear_uploader);
		textview_full_description = findViewById(R.id.textview_full_description);
		textview_title = findViewById(R.id.textview_title);
		textview_version = findViewById(R.id.textview_version);
		textview_pm_version = findViewById(R.id.textview_pm_version);
		linear_ip_and_port = findViewById(R.id.linear_ip_and_port);
		copyServerData = findViewById(R.id.copyServerData);
		btn_server_discord = findViewById(R.id.btn_server_discord);
		server_ip = findViewById(R.id.server_ip);
		textview1 = findViewById(R.id.textview1);
		server_port = findViewById(R.id.server_port);
		imageview1 = findViewById(R.id.imageview1);
		textview_uploader = findViewById(R.id.textview_uploader);
		s1 = findViewById(R.id.s1);
		textview_upload_date = findViewById(R.id.textview_upload_date);
		btn_download = findViewById(R.id.btn_download);
		btn_more_info = findViewById(R.id.btn_more_info);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear_comments_top = findViewById(R.id.linear_comments_top);
		listview1 = findViewById(R.id.listview1);
		imageview_comments = findViewById(R.id.imageview_comments);
		textview_comments_count = findViewById(R.id.textview_comments_count);
		textview_comments = findViewById(R.id.textview_comments);
		btn_write_comment = findViewById(R.id.btn_write_comment);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		language = getSharedPreferences("language", Activity.MODE_PRIVATE);
		
		linear_ip_and_port.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", getIntent().getStringExtra("serverIp").concat(":".concat(getIntent().getStringExtra("serverPort")))));
				_Toast(getString(R.string.copyServerData_onlongclicked_toast));
				return true;
			}
		});
		
		linear_ip_and_port.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", getIntent().getStringExtra("serverIp").concat(":".concat(getIntent().getStringExtra("serverPort")))));
				_Toast(getString(R.string.copyServerData_onlongclicked_toast));
			}
		});
		
		copyServerData.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", getIntent().getStringExtra("serverIp").concat(":".concat(getIntent().getStringExtra("serverPort")))));
				_Toast(getString(R.string.copyServerData_onlongclicked_toast));
			}
		});
		
		btn_server_discord.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", getIntent().getStringExtra("serverDiscordUrl")));
				_Toast("Ссылка на Discord сервер скопирована");
				return true;
			}
		});
		
		btn_server_discord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse(getIntent().getStringExtra("serverDiscordUrl")));
				startActivity(i);
			}
		});
		
		btn_download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("ifTexture").equals("yes")) {
					_DownloadFile(getIntent().getStringExtra("textureDownloadUrl"), "/Download/");
				}
				else {
					if (getIntent().getStringExtra("ifMod").equals("yes")) {
						_DownloadFile(getIntent().getStringExtra("modDownloadUrl"), "/Download/");
					}
					else {
						if (getIntent().getStringExtra("ifMap").equals("yes")) {
							_DownloadFile(getIntent().getStringExtra("mapDownloadUrl"), "/Download/");
						}
						else {
							if (getIntent().getStringExtra("ifPlugin").equals("yes")) {
								_DownloadFile(getIntent().getStringExtra("pluginDownloadUrl"), "/Download/");
							}
							else {
								_Toast(getString(R.string.download_dialog_error_not_founded_url));
							}
						}
					}
				}
			}
		});
		
		btn_more_info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (getIntent().getStringExtra("ifTexture").equals("yes")) {
					
				}
				else {
					if (getIntent().getStringExtra("ifMod").equals("yes")) {
						
					}
					else {
						if (getIntent().getStringExtra("ifMap").equals("yes")) {
							
						}
						else {
							if (getIntent().getStringExtra("ifPlugin").equals("yes")) {
								final AlertDialog webview_dialog= new AlertDialog.Builder(InfoActivity.this).create();
								View inflate = getLayoutInflater().inflate(R.layout.webview,null); 
								webview_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
								webview_dialog.setView(inflate);
								TextView textview1 = (TextView) inflate.findViewById(R.id.textview1);
								
								TextView button_copy_url_dialog = (TextView) inflate.findViewById(R.id.button_copy_url_dialog);
								
								LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
								WebSettings webSettings = webview1.getSettings(); 
								webSettings.setJavaScriptEnabled(true); 
								bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
								_rippleRoundStroke(button_copy_url_dialog, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
								textview1.setText(getIntent().getStringExtra("pluginInfoUrl"));
								button_copy_url_dialog.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View _view) {
										((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", getIntent().getStringExtra("pluginInfoUrl")));
									}
								});
								webview_dialog.setCancelable(true);
								webview_dialog.show();
							}
							else {
								_Toast("Error");
							}
						}
					}
				}
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		btn_write_comment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), CommentsActivity.class);
				i.putExtra("title", getIntent().getStringExtra("pluginName"));
				i.putExtra("username", getIntent().getStringExtra("username"));
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
				startActivity(i);
			}
		});
		
		_comments_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				comment_num++;
				textview_comments_count.setText(String.valueOf((long)(comment_num)));
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
				comment_num++;
				textview_comments_count.setText(String.valueOf((long)(comment_num)));
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
				comment_num++;
				textview_comments_count.setText(String.valueOf((long)(comment_num)));
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
		
		_storage_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_storage_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				_Toast(getString(R.string.download_dialog_download_started_toast));
			}
		};
		
		_storage_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_storage_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				_Toast(getString(R.string.download_dialog_download_successful_toast));
			}
		};
		
		_storage_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_storage_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_textureComments_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				texturesCommentNum++;
				textview_comments_count.setText(String.valueOf((long)(texturesCommentNum)));
				textureComments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						textureCommentsMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								textureCommentsMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(textureCommentsMap));
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
				texturesCommentNum++;
				textview_comments_count.setText(String.valueOf((long)(texturesCommentNum)));
				textureComments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						textureCommentsMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								textureCommentsMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(textureCommentsMap));
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
				texturesCommentNum++;
				textview_comments_count.setText(String.valueOf((long)(texturesCommentNum)));
				textureComments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						textureCommentsMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								textureCommentsMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(textureCommentsMap));
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
		textureComments.addChildEventListener(_textureComments_child_listener);
		
		_modComments_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				modsCommentNum++;
				textview_comments_count.setText(String.valueOf((long)(modsCommentNum)));
				modComments.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						modCommentsMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								modCommentsMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(modCommentsMap));
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
		modComments.addChildEventListener(_modComments_child_listener);
	}
	
	private void initializeLogic() {
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		
		if (getIntent().getStringExtra("ifTexture").equals("yes")) {
			texturesCommentNum = 0;
			linear_banner.setVisibility(View.VISIBLE);
			textview_pm_version.setVisibility(View.GONE);
			textview_full_description.setVisibility(View.GONE);
			linear_server_data.setVisibility(View.GONE);
			linear_actions.setVisibility(View.VISIBLE);
			btn_more_info.setVisibility(View.GONE);
			btn_more_info.setText(getString(R.string.btn_more_info_text));
			Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("thumbnailTextureUrl"))).into(imageview_banner);
			getSupportActionBar().setTitle(getIntent().getStringExtra("textureName"));
			textview_title.setText(getIntent().getStringExtra("textureName"));
			textview_description.setText(getIntent().getStringExtra("textureDescription"));
			textview_version.setText(getIntent().getStringExtra("textureVersion"));
			textview_uploader.setText(getIntent().getStringExtra("textureUploader"));
			textview_upload_date.setText(getIntent().getStringExtra("textureDate"));
		}
		else {
			if (getIntent().getStringExtra("ifMod").equals("yes")) {
				modsCommentNum = 0;
				linear_banner.setVisibility(View.GONE);
				textview_pm_version.setVisibility(View.GONE);
				linear_server_data.setVisibility(View.GONE);
				linear_actions.setVisibility(View.VISIBLE);
				btn_more_info.setVisibility(View.GONE);
				btn_more_info.setText(getString(R.string.btn_more_info_text));
				getSupportActionBar().setTitle(getIntent().getStringExtra("modName"));
				textview_title.setText(getIntent().getStringExtra("modName"));
				textview_description.setText(getIntent().getStringExtra("modDescription"));
				textview_full_description.setText(getIntent().getStringExtra("modFullDescription"));
				textview_version.setText(getIntent().getStringExtra("modVersion"));
				textview_uploader.setText(getIntent().getStringExtra("modUploader"));
				textview_upload_date.setText(getIntent().getStringExtra("modDate"));
			}
			else {
				if (getIntent().getStringExtra("ifMap").equals("yes")) {
					linear_banner.setVisibility(View.VISIBLE);
					textview_pm_version.setVisibility(View.GONE);
					textview_description.setVisibility(View.GONE);
					textview_full_description.setVisibility(View.GONE);
					linear_server_data.setVisibility(View.GONE);
					linear_actions.setVisibility(View.VISIBLE);
					btn_more_info.setVisibility(View.GONE);
					btn_more_info.setText(getString(R.string.btn_more_info_text));
					Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("thumbnailMapUrl"))).into(imageview_banner);
					getSupportActionBar().setTitle(getIntent().getStringExtra("mapName"));
					textview_title.setText(getIntent().getStringExtra("mapName"));
					textview_description.setText(getIntent().getStringExtra("mapDescription"));
					textview_version.setText(getIntent().getStringExtra("mapVersion"));
					textview_uploader.setText(getIntent().getStringExtra("mapUploader"));
					textview_upload_date.setText(getIntent().getStringExtra("mapDate"));
				}
				else {
					if (getIntent().getStringExtra("ifServer").equals("yes")) {
						linear_banner.setVisibility(View.VISIBLE);
						textview_pm_version.setVisibility(View.GONE);
						textview_full_description.setVisibility(View.GONE);
						linear_server_data.setVisibility(View.VISIBLE);
						linear_actions.setVisibility(View.GONE);
						btn_more_info.setVisibility(View.VISIBLE);
						btn_more_info.setText(getString(R.string.server_monitoring_text));
						Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("thumbnailServerUrl"))).into(imageview_banner);
						getSupportActionBar().setTitle(getIntent().getStringExtra("serverName"));
						textview_title.setText(getIntent().getStringExtra("serverName"));
						textview_description.setText(getIntent().getStringExtra("serverDescription"));
						textview_version.setText(getIntent().getStringExtra("serverVersion"));
						textview_uploader.setText(getIntent().getStringExtra("serverUploader"));
						textview_upload_date.setText(getIntent().getStringExtra("serverDate"));
						server_ip.setText(getIntent().getStringExtra("serverIp"));
						server_port.setText(getIntent().getStringExtra("serverPort"));
					}
					else {
						if (getIntent().getStringExtra("ifPlugin").equals("yes")) {
							linear_banner.setVisibility(View.GONE);
							textview_pm_version.setVisibility(View.VISIBLE);
							linear_server_data.setVisibility(View.GONE);
							linear_actions.setVisibility(View.VISIBLE);
							btn_more_info.setVisibility(View.VISIBLE);
							btn_more_info.setText(getString(R.string.btn_more_info_text));
							getSupportActionBar().setTitle(getIntent().getStringExtra("pluginName"));
							textview_title.setText(getIntent().getStringExtra("pluginName"));
							textview_description.setText(getIntent().getStringExtra("pluginDescription"));
							textview_full_description.setText(getIntent().getStringExtra("pluginFullDescription"));
							textview_version.setText(getIntent().getStringExtra("pluginVersion"));
							textview_pm_version.setText(getIntent().getStringExtra("pluginPMVersion"));
							textview_uploader.setText(getIntent().getStringExtra("pluginUploader"));
							textview_upload_date.setText(getIntent().getStringExtra("pluginDate"));
						}
						else {
							_Toast("Error");
						}
					}
				}
			}
		}
		_rippleRoundStroke(btn_download, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		_rippleRoundStroke(btn_more_info, "#009688", "#B2DFDB", 10, 0, "#FFFFFF");
		_rippleRoundStroke(btn_write_comment, "#616161", "#BDBDBD", 10, 0, "#FFFFFF");
		textview_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFF4E5569));
		textview_pm_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFFFF9800));
		linear_ip_and_port.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFCC80));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (language.getString("language", "").equals("")) {
			setLocale("ru");
			_ru();
		}
		if (language.getString("language", "").equals("en")) {
			setLocale("en");
			_en();
		}
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
	
	
	public void _DownloadFile(final String _url, final String _path) {
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()));
		android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			
			
			final String urlDownload = _url;
			
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlDownload));
			
			final String fileName = URLUtil.guessFileName(urlDownload, null, null);
			
			request.setDescription("Downloading - " + fileName);
			
			request.setTitle(fileName);
			
			request.allowScanningByMediaScanner();
			
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			
			request.setDestinationInExternalPublicDir(_path, fileName);
			
			final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
			
			final long downloadId = manager.enqueue(request);
			
			final ProgressDialog progressDialog = new ProgressDialog(InfoActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
			progressDialog.setCancelable(true);
			progressDialog.setTitle("Downloading");
			progressDialog.setCanceledOnTouchOutside(true);

			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean downloading = true;

					while (downloading) {
						DownloadManager.Query q = new DownloadManager.Query();
						q.setFilterById(downloadId);
						android.database.Cursor cursor = manager.query(q);
						cursor.moveToFirst();

						int columnIndexBytesDownloaded = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
						int columnIndexBytesTotal = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
						int columnIndexStatus = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);

						if (columnIndexBytesDownloaded != -1 && columnIndexBytesTotal != -1 && columnIndexStatus != -1) {
							int bytes_downloaded = cursor.getInt(columnIndexBytesDownloaded);
							int bytes_total = cursor.getInt(columnIndexBytesTotal);

							if (cursor.getInt(columnIndexStatus) == DownloadManager.STATUS_SUCCESSFUL) {
								downloading = false;
							}

							final int downloadProgress = (int) ((bytes_downloaded * 100l) / bytes_total);

							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									progressDialog.setTitle("Downloading");
									progressDialog.setMessage("Downloading " + fileName);
									progressDialog.show();

									if (downloadProgress == 100) {
										filePath = _path.concat(fileName);
										_Toast(getString(R.string.download_dialog_download_successful_toast));
										progressDialog.dismiss();
									}
								}
							});
						} else {
							// Ошибка: один из столбцов не найден
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_Toast("Error: One or more columns not found");
								}
							});

							// Выход из цикла, так как произошла ошибка
							downloading = false;
						}
						cursor.close();
					}
				}
			}).start();

		} else {
			_Toast(getString(R.string.no_internet_connection_dialog_title));
		}
	}


	public void _langSet() {
		
		    }public void setLocale(String lang) {
		
		java.util.Locale myLocale = new Locale(lang);
		        android.content.res.Resources res = getResources();
		        android.util.DisplayMetrics dm = res.getDisplayMetrics();
		        android.content.res.Configuration conf = res.getConfiguration();
		        conf.locale = myLocale;
		        res.updateConfiguration(conf, dm);
		        
		    }
	    {
	}
	
	
	public void _ru() {
		btn_download.setText(getString(R.string.download_button_text));
		
		btn_more_info.setText(getString(R.string.btn_more_info_text));
		
		btn_write_comment.setText(getString(R.string.btn_write_comment_text));
		
		textview_comments.setText(getString(R.string.comments_text));
	}
	
	
	public void _en() {
		btn_download.setText(getString(R.string.download_button_text));
		
		btn_more_info.setText(getString(R.string.btn_more_info_text));
		
		btn_write_comment.setText(getString(R.string.btn_write_comment_text));
		
		textview_comments.setText(getString(R.string.comments_text));
	}
	
	
	public void _ColbuxDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog = new AlertDialog.Builder(InfoActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.dialog,null); 
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog.setView(inflate);
		TextView textview_title = (TextView) inflate.findViewById(R.id.textview_title);
		
		TextView textview_message = (TextView) inflate.findViewById(R.id.textview_message);
		
		TextView button_1 = (TextView) inflate.findViewById(R.id.button_1);
		
		TextView button_2 = (TextView) inflate.findViewById(R.id.button_2);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout linear3 = (LinearLayout) inflate.findViewById(R.id.linear3);
		textview_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_bold.ttf"), Typeface.BOLD);
		textview_message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_medium.ttf"), Typeface.NORMAL);
		button_1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_medium.ttf"), Typeface.NORMAL);
		button_2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensans_medium.ttf"), Typeface.NORMAL);
		_rippleRoundStroke(button_1, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		_rippleRoundStroke(button_2, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		textview_title.setText(_title);
		textview_message.setText(_message);
		button_1.setText(_button1text);
		button_2.setText(_button2text);
		_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
		button_1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog.dismiss();
			}
		});
		button_2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog.dismiss();
			}
		});
		dialog.setCancelable(true);
		dialog.show();
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
