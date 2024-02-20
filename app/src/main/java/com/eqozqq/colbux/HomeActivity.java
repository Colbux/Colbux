package com.eqozqq.colbux;

import android.Manifest;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.*;
import android.os.Vibrator;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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

import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String dlink = "";
	private String filename = "";
	private String ext = "";
	private double dl_progress = 0;
	private double positions = 0;
	private double num = 0;
	private String fileName = "";
	private String textureName = "";
	private String textureInfoUrl = "";
	private String textureDownloadUrl = "";
	private String modName = "";
	private String modInfoUrl = "";
	private String modDownloadUrl = "";
	private String mapName = "";
	private String mapInfoUrl = "";
	private String mapDownloadUrl = "";
	private String trumbnailMapUrl = "";
	private String serverName = "";
	private String serverData = "";
	private String serverVersion = "";
	private String serverDiscordUrl = "";
	private String serverIpPort = "";
	private String textureVersion = "";
	private String textureDescription = "";
	private String modVersion = "";
	private String modDescription = "";
	private String mapVersion = "";
	private String trumbnailTextureUrl = "";
	private String trumbnailServerUrl = "";
	private String trumbnailModUrl = "";
	private String serverDescription = "";
	private HashMap<String, Object> TexturesMap = new HashMap<>();
	private HashMap<String, Object> ModsMap = new HashMap<>();
	private HashMap<String, Object> MapsMap = new HashMap<>();
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> ServersMap = new HashMap<>();
	private String thumbnailTextureUrl = "";
	private String thumbnailModUrl = "";
	private String thumbnailMapUrl = "";
	private String thumbnailServerUrl = "";
	private double moreModifications = 0;
	private HashMap<String, Object> PluginsMap = new HashMap<>();
	private String pluginName = "";
	private String pluginDescription = "";
	private String pluginVersion = "";
	private String pluginAPI = "";
	private String pluginDownloadUrl = "";
	private String pluginInfoUrl = "";
	private double Position = 0;
	private HashMap<String, Object> authMap = new HashMap<>();
	private double progress = 0;
	private double progressdownload = 0;
	private String downloadurl = "";
	private String uploadfilename = "";
	private double serversProgress = 0;
	private double serversDownloadProgress = 0;
	private String texturesUploadFileName = "";
	private double texturesProgressDownload = 0;
	private String filePath = "";
	private double downloadProgress = 0;
	private String file_size = "";
	
	private ArrayList<HashMap<String, Object>> serversmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> texturesmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> modsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> ListMapMaps = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> mapsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> pluginsmap = new ArrayList<>();
	private ArrayList<String> contentList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> pocketminemap = new ArrayList<>();
	
	private LinearLayout contents;
	private LinearLayout nav_s1;
	private LinearLayout navigation_more;
	private LinearLayout navigation;
	private LinearLayout content_home;
	private LinearLayout content_modifications;
	private LinearLayout content_servers;
	private LinearLayout content_fordevs;
	private LinearLayout linear_webview;
	private LinearLayout linear_skins;
	private LinearLayout linear_blocklauncher;
	private LinearLayout linear4;
	private TextView textview_skins_mcpe_description;
	private TextView btn_learn_how_to_set_a_skin;
	private TextView textview_skins_mcpe_title;
	private TextView textview3;
	private LinearLayout linear6;
	private TextView textview_bl_description;
	private TextView btn_download_bl095;
	private TextView btn_download_bl081;
	private TextView btn_download_bl061;
	private TextView textview_bl_title;
	private LinearLayout linear7;
	private TextView textview13;
	private TextView textview11;
	private TextView textview10;
	private LinearLayout modifications_tablayout;
	private ListView listview_textures;
	private ListView listview_mods;
	private ListView listview_maps;
	private LinearLayout textures_modifications_tab;
	private LinearLayout mods_modifications_tab;
	private LinearLayout maps_modifications_tab;
	private LinearLayout textures_modifications_tab_item;
	private LinearLayout textures_modifications_tab_onclicked;
	private TextView textures_modifications_tab_text;
	private LinearLayout mods_modifications_tab_item;
	private LinearLayout mods_modifications_tab_onclicked;
	private TextView mods_modifications_tab_text;
	private LinearLayout maps_modifications_tab_item;
	private LinearLayout maps_modifications_tab_onclicked;
	private TextView maps_modifications_tab_text;
	private ListView listview_servers;
	private LinearLayout fordevs_tablayout;
	private ListView listview_plugins;
	private LinearLayout plugins_fordevs_tab;
	private LinearLayout plugins_fordevs_tab_item;
	private LinearLayout plugins_fordevs_tab_onclicked;
	private TextView plugins_fordevs_tab_text;
	private LinearLayout linear_webview_toolbar;
	private ProgressBar progressbar1;
	private WebView webview1;
	private ImageView btn_close_webview;
	private LinearLayout nav_home;
	private LinearLayout nav_modifications;
	private LinearLayout nav_servers;
	private LinearLayout nav_fordevs;
	private LinearLayout nav_home_item;
	private LinearLayout nav_home_onclicked;
	private ImageView nav_home_image;
	private TextView nav_home_text;
	private LinearLayout nav_modifications_item;
	private LinearLayout nav_modifications_onclicked;
	private ImageView nav_modifications_image;
	private TextView nav_modifications_text;
	private LinearLayout nav_servers_item;
	private LinearLayout nav_servers_onclicked;
	private ImageView nav_servers_image;
	private TextView nav_servers_text;
	private LinearLayout nav_fordevs_item;
	private LinearLayout nav_fordev_onclicked;
	private ImageView nav_fordevs_image;
	private TextView nav_fordevs_text;
	
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private ProgressDialog prog;
	private TimerTask t;
	private DatabaseReference textures_data = _firebase.getReference("textures");
	private ChildEventListener _textures_data_child_listener;
	private DatabaseReference mods_data = _firebase.getReference("mods");
	private ChildEventListener _mods_data_child_listener;
	private AlertDialog.Builder md;
	private DatabaseReference maps_data = _firebase.getReference("maps");
	private ChildEventListener _maps_data_child_listener;
	private AlertDialog.Builder mapsd;
	private DatabaseReference servers_data = _firebase.getReference("servers");
	private ChildEventListener _servers_data_child_listener;
	private AlertDialog.Builder serversd;
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private AlertDialog.Builder confirm;
	private AlertDialog.Builder errors;
	private AlertDialog.Builder ingfo;
	private DatabaseReference plugins_data = _firebase.getReference("plugins");
	private ChildEventListener _plugins_data_child_listener;
	private AlertDialog.Builder pd;
	private Vibrator vibrate;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private SharedPreferences language;
	private SharedPreferences ld;
	private StorageReference textures = _firebase_storage.getReference("textures");
	private OnCompleteListener<Uri> _textures_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _textures_download_success_listener;
	private OnSuccessListener _textures_delete_success_listener;
	private OnProgressListener _textures_upload_progress_listener;
	private OnProgressListener _textures_download_progress_listener;
	private OnFailureListener _textures_failure_listener;
	
	private StorageReference mods = _firebase_storage.getReference("mods");
	private OnCompleteListener<Uri> _mods_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _mods_download_success_listener;
	private OnSuccessListener _mods_delete_success_listener;
	private OnProgressListener _mods_upload_progress_listener;
	private OnProgressListener _mods_download_progress_listener;
	private OnFailureListener _mods_failure_listener;
	
	private StorageReference maps = _firebase_storage.getReference("maps");
	private OnCompleteListener<Uri> _maps_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _maps_download_success_listener;
	private OnSuccessListener _maps_delete_success_listener;
	private OnProgressListener _maps_upload_progress_listener;
	private OnProgressListener _maps_download_progress_listener;
	private OnFailureListener _maps_failure_listener;
	
	private StorageReference servers = _firebase_storage.getReference("servers");
	private OnCompleteListener<Uri> _servers_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _servers_download_success_listener;
	private OnSuccessListener _servers_delete_success_listener;
	private OnProgressListener _servers_upload_progress_listener;
	private OnProgressListener _servers_download_progress_listener;
	private OnFailureListener _servers_failure_listener;
	
	private StorageReference plugins = _firebase_storage.getReference("plugins");
	private OnCompleteListener<Uri> _plugins_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _plugins_download_success_listener;
	private OnSuccessListener _plugins_delete_success_listener;
	private OnProgressListener _plugins_upload_progress_listener;
	private OnProgressListener _plugins_download_progress_listener;
	private OnFailureListener _plugins_failure_listener;
	
	private TimerTask t2;
	private Calendar c = Calendar.getInstance();
	private SharedPreferences user;
	private DatabaseReference plugins_comments = _firebase.getReference("plugins/comments");
	private ChildEventListener _plugins_comments_child_listener;
	private SharedPreferences infodialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);

		//TOOLBAR
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w = HomeActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF2A2831);
		}
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
		contents = findViewById(R.id.contents);
		nav_s1 = findViewById(R.id.nav_s1);
		navigation_more = findViewById(R.id.navigation_more);
		navigation = findViewById(R.id.navigation);
		content_home = findViewById(R.id.content_home);
		content_modifications = findViewById(R.id.content_modifications);
		content_servers = findViewById(R.id.content_servers);
		content_fordevs = findViewById(R.id.content_fordevs);
		linear_webview = findViewById(R.id.linear_webview);
		linear_skins = findViewById(R.id.linear_skins);
		linear_blocklauncher = findViewById(R.id.linear_blocklauncher);
		linear4 = findViewById(R.id.linear4);
		textview_skins_mcpe_description = findViewById(R.id.textview_skins_mcpe_description);
		btn_learn_how_to_set_a_skin = findViewById(R.id.btn_learn_how_to_set_a_skin);
		textview_skins_mcpe_title = findViewById(R.id.textview_skins_mcpe_title);
		textview3 = findViewById(R.id.textview3);
		linear6 = findViewById(R.id.linear6);
		textview_bl_description = findViewById(R.id.textview_bl_description);
		btn_download_bl095 = findViewById(R.id.btn_download_bl095);
		btn_download_bl081 = findViewById(R.id.btn_download_bl081);
		btn_download_bl061 = findViewById(R.id.btn_download_bl061);
		textview_bl_title = findViewById(R.id.textview_bl_title);
		linear7 = findViewById(R.id.linear7);
		textview13 = findViewById(R.id.textview13);
		textview11 = findViewById(R.id.textview11);
		textview10 = findViewById(R.id.textview10);
		modifications_tablayout = findViewById(R.id.modifications_tablayout);
		listview_textures = findViewById(R.id.listview_textures);
		listview_mods = findViewById(R.id.listview_mods);
		listview_maps = findViewById(R.id.listview_maps);
		textures_modifications_tab = findViewById(R.id.textures_modifications_tab);
		mods_modifications_tab = findViewById(R.id.mods_modifications_tab);
		maps_modifications_tab = findViewById(R.id.maps_modifications_tab);
		textures_modifications_tab_item = findViewById(R.id.textures_modifications_tab_item);
		textures_modifications_tab_onclicked = findViewById(R.id.textures_modifications_tab_onclicked);
		textures_modifications_tab_text = findViewById(R.id.textures_modifications_tab_text);
		mods_modifications_tab_item = findViewById(R.id.mods_modifications_tab_item);
		mods_modifications_tab_onclicked = findViewById(R.id.mods_modifications_tab_onclicked);
		mods_modifications_tab_text = findViewById(R.id.mods_modifications_tab_text);
		maps_modifications_tab_item = findViewById(R.id.maps_modifications_tab_item);
		maps_modifications_tab_onclicked = findViewById(R.id.maps_modifications_tab_onclicked);
		maps_modifications_tab_text = findViewById(R.id.maps_modifications_tab_text);
		listview_servers = findViewById(R.id.listview_servers);
		fordevs_tablayout = findViewById(R.id.fordevs_tablayout);
		listview_plugins = findViewById(R.id.listview_plugins);
		plugins_fordevs_tab = findViewById(R.id.plugins_fordevs_tab);
		plugins_fordevs_tab_item = findViewById(R.id.plugins_fordevs_tab_item);
		plugins_fordevs_tab_onclicked = findViewById(R.id.plugins_fordevs_tab_onclicked);
		plugins_fordevs_tab_text = findViewById(R.id.plugins_fordevs_tab_text);
		linear_webview_toolbar = findViewById(R.id.linear_webview_toolbar);
		progressbar1 = findViewById(R.id.progressbar1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		btn_close_webview = findViewById(R.id.btn_close_webview);
		nav_home = findViewById(R.id.nav_home);
		nav_modifications = findViewById(R.id.nav_modifications);
		nav_servers = findViewById(R.id.nav_servers);
		nav_fordevs = findViewById(R.id.nav_fordevs);
		nav_home_item = findViewById(R.id.nav_home_item);
		nav_home_onclicked = findViewById(R.id.nav_home_onclicked);
		nav_home_image = findViewById(R.id.nav_home_image);
		nav_home_text = findViewById(R.id.nav_home_text);
		nav_modifications_item = findViewById(R.id.nav_modifications_item);
		nav_modifications_onclicked = findViewById(R.id.nav_modifications_onclicked);
		nav_modifications_image = findViewById(R.id.nav_modifications_image);
		nav_modifications_text = findViewById(R.id.nav_modifications_text);
		nav_servers_item = findViewById(R.id.nav_servers_item);
		nav_servers_onclicked = findViewById(R.id.nav_servers_onclicked);
		nav_servers_image = findViewById(R.id.nav_servers_image);
		nav_servers_text = findViewById(R.id.nav_servers_text);
		nav_fordevs_item = findViewById(R.id.nav_fordevs_item);
		nav_fordev_onclicked = findViewById(R.id.nav_fordev_onclicked);
		nav_fordevs_image = findViewById(R.id.nav_fordevs_image);
		nav_fordevs_text = findViewById(R.id.nav_fordevs_text);
		d = new AlertDialog.Builder(this);
		md = new AlertDialog.Builder(this);
		mapsd = new AlertDialog.Builder(this);
		serversd = new AlertDialog.Builder(this);
		auth = FirebaseAuth.getInstance();
		confirm = new AlertDialog.Builder(this);
		errors = new AlertDialog.Builder(this);
		ingfo = new AlertDialog.Builder(this);
		pd = new AlertDialog.Builder(this);
		vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		language = getSharedPreferences("language", Activity.MODE_PRIVATE);
		ld = getSharedPreferences("ld", Activity.MODE_PRIVATE);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		infodialog = getSharedPreferences("infodialog", Activity.MODE_PRIVATE);
		
		linear_skins.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Download("https://www.mediafire.com/file/xykj7ut0srpalh2/BlockLauncher-230121.apk/file");
			}
		});
		
		btn_learn_how_to_set_a_skin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				content_home.setVisibility(View.GONE);
				content_modifications.setVisibility(View.GONE);
				content_servers.setVisibility(View.GONE);
				content_fordevs.setVisibility(View.GONE);
				linear_webview.setVisibility(View.VISIBLE);
				webview1.loadUrl("file:///android_asset/skins.html");
				_webviewprogress(webview1, progressbar1);
				_WebView(true, true, true, true, true, webview1);
			}
		});
		
		btn_download_bl095.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_DownloadFile("https://firebasestorage.googleapis.com/v0/b/colbux-e6d12.appspot.com/o/mods%2FBlockLauncher095.apk?alt=media&token=db1d5a42-6b84-403b-b6f1-ddfa7c8da79f", "/Download/");
			}
		});
		
		btn_download_bl081.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_DownloadFile("https://firebasestorage.googleapis.com/v0/b/colbux-e6d12.appspot.com/o/mods%2FBlockLauncher-230121.apk?alt=media&token=46fe3182-d475-4d4d-96c2-45d4e022d3a7", "/Download/");
			}
		});
		
		btn_download_bl061.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_DownloadFile("https://firebasestorage.googleapis.com/v0/b/colbux-e6d12.appspot.com/o/mods%2Fbl_06_r1.apk?alt=media&token=4bfdd3b5-f926-4197-b154-b8abdefc4fe1", "/Download/");
			}
		});
		
		textures_modifications_tab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textures_modifications_tab.setBackgroundColor(0xFF212121);
				mods_modifications_tab.setBackgroundColor(Color.TRANSPARENT);
				maps_modifications_tab.setBackgroundColor(Color.TRANSPARENT);
				listview_textures.setVisibility(View.VISIBLE);
				listview_mods.setVisibility(View.GONE);
				listview_maps.setVisibility(View.GONE);
				textures_modifications_tab_onclicked.setBackgroundColor(0xFF4CAF50);
				mods_modifications_tab_onclicked.setBackgroundColor(Color.TRANSPARENT);
				maps_modifications_tab_onclicked.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
		mods_modifications_tab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textures_modifications_tab.setBackgroundColor(Color.TRANSPARENT);
				mods_modifications_tab.setBackgroundColor(0xFF212121);
				maps_modifications_tab.setBackgroundColor(Color.TRANSPARENT);
				listview_textures.setVisibility(View.GONE);
				listview_mods.setVisibility(View.VISIBLE);
				listview_maps.setVisibility(View.GONE);
				textures_modifications_tab_onclicked.setBackgroundColor(Color.TRANSPARENT);
				mods_modifications_tab_onclicked.setBackgroundColor(0xFF4CAF50);
				maps_modifications_tab_onclicked.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
		maps_modifications_tab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textures_modifications_tab.setBackgroundColor(Color.TRANSPARENT);
				mods_modifications_tab.setBackgroundColor(Color.TRANSPARENT);
				maps_modifications_tab.setBackgroundColor(0xFF212121);
				listview_textures.setVisibility(View.GONE);
				listview_mods.setVisibility(View.GONE);
				listview_maps.setVisibility(View.VISIBLE);
				textures_modifications_tab_onclicked.setBackgroundColor(Color.TRANSPARENT);
				mods_modifications_tab_onclicked.setBackgroundColor(Color.TRANSPARENT);
				maps_modifications_tab_onclicked.setBackgroundColor(0xFF4CAF50);
			}
		});
		
		plugins_fordevs_tab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				plugins_fordevs_tab.setBackgroundColor(0xFF212121);
				listview_plugins.setVisibility(View.VISIBLE);
				plugins_fordevs_tab_onclicked.setVisibility(View.VISIBLE);
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
		
		btn_close_webview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				content_home.setVisibility(View.VISIBLE);
				content_modifications.setVisibility(View.GONE);
				content_servers.setVisibility(View.GONE);
				content_fordevs.setVisibility(View.GONE);
				linear_webview.setVisibility(View.GONE);
			}
		});
		
		nav_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nav_home.setBackgroundColor(0xFF212121);
				nav_modifications.setBackgroundColor(Color.TRANSPARENT);
				nav_servers.setBackgroundColor(Color.TRANSPARENT);
				nav_fordevs.setBackgroundColor(Color.TRANSPARENT);
				content_home.setVisibility(View.VISIBLE);
				content_modifications.setVisibility(View.GONE);
				content_servers.setVisibility(View.GONE);
				content_fordevs.setVisibility(View.GONE);
				nav_home_onclicked.setBackgroundColor(0xFF4CAF50);
				nav_modifications_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_servers_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_fordev_onclicked.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
		nav_modifications.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nav_home.setBackgroundColor(Color.TRANSPARENT);
				nav_modifications.setBackgroundColor(0xFF212121);
				nav_servers.setBackgroundColor(Color.TRANSPARENT);
				nav_fordevs.setBackgroundColor(Color.TRANSPARENT);
				content_home.setVisibility(View.GONE);
				content_modifications.setVisibility(View.VISIBLE);
				content_servers.setVisibility(View.GONE);
				content_fordevs.setVisibility(View.GONE);
				nav_home_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_modifications_onclicked.setBackgroundColor(0xFF4CAF50);
				nav_servers_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_fordev_onclicked.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
		nav_servers.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nav_home.setBackgroundColor(Color.TRANSPARENT);
				nav_modifications.setBackgroundColor(Color.TRANSPARENT);
				nav_servers.setBackgroundColor(0xFF212121);
				nav_fordevs.setBackgroundColor(Color.TRANSPARENT);
				content_home.setVisibility(View.GONE);
				content_modifications.setVisibility(View.GONE);
				content_servers.setVisibility(View.VISIBLE);
				content_fordevs.setVisibility(View.GONE);
				nav_home_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_modifications_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_servers_onclicked.setBackgroundColor(0xFF4CAF50);
				nav_fordev_onclicked.setBackgroundColor(Color.TRANSPARENT);
			}
		});
		
		nav_fordevs.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nav_home.setBackgroundColor(Color.TRANSPARENT);
				nav_modifications.setBackgroundColor(Color.TRANSPARENT);
				nav_servers.setBackgroundColor(Color.TRANSPARENT);
				nav_fordevs.setBackgroundColor(0xFF212121);
				content_home.setVisibility(View.GONE);
				content_modifications.setVisibility(View.GONE);
				content_servers.setVisibility(View.GONE);
				content_fordevs.setVisibility(View.VISIBLE);
				nav_home_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_modifications_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_servers_onclicked.setBackgroundColor(Color.TRANSPARENT);
				nav_fordev_onclicked.setBackgroundColor(0xFF4CAF50);
			}
		});
		
		_textures_data_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				textures_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						texturesmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								texturesmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_textures.setAdapter(new Listview_texturesAdapter(texturesmap));
						((BaseAdapter)listview_textures.getAdapter()).notifyDataSetChanged();
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
				textures_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						texturesmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								texturesmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						
						
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
				textures_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						texturesmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								texturesmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						
						
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
				ColbuxUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		textures_data.addChildEventListener(_textures_data_child_listener);
		
		_mods_data_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				mods_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						modsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								modsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_mods.setAdapter(new Listview_modsAdapter(modsmap));
						((BaseAdapter)listview_mods.getAdapter()).notifyDataSetChanged();
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
				mods_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						modsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								modsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_mods.setAdapter(new Listview_modsAdapter(modsmap));
						((BaseAdapter)listview_mods.getAdapter()).notifyDataSetChanged();
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
				mods_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						modsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								modsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_mods.setAdapter(new Listview_modsAdapter(modsmap));
						((BaseAdapter)listview_mods.getAdapter()).notifyDataSetChanged();
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
				ColbuxUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		mods_data.addChildEventListener(_mods_data_child_listener);
		
		_maps_data_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				maps_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						mapsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								mapsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_maps.setAdapter(new Listview_mapsAdapter(mapsmap));
						((BaseAdapter)listview_maps.getAdapter()).notifyDataSetChanged();
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
				maps_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						mapsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								mapsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_maps.setAdapter(new Listview_mapsAdapter(mapsmap));
						((BaseAdapter)listview_maps.getAdapter()).notifyDataSetChanged();
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
				maps_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						mapsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								mapsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_maps.setAdapter(new Listview_mapsAdapter(mapsmap));
						((BaseAdapter)listview_maps.getAdapter()).notifyDataSetChanged();
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
				ColbuxUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		maps_data.addChildEventListener(_maps_data_child_listener);
		
		_servers_data_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				servers_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						serversmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								serversmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_servers.setAdapter(new Listview_serversAdapter(serversmap));
						((BaseAdapter)listview_servers.getAdapter()).notifyDataSetChanged();
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
				servers_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						serversmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								serversmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_servers.setAdapter(new Listview_serversAdapter(serversmap));
						((BaseAdapter)listview_servers.getAdapter()).notifyDataSetChanged();
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
				servers_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						serversmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								serversmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_servers.setAdapter(new Listview_serversAdapter(serversmap));
						((BaseAdapter)listview_servers.getAdapter()).notifyDataSetChanged();
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
				ColbuxUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		servers_data.addChildEventListener(_servers_data_child_listener);
		
		_plugins_data_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				plugins_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						pluginsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								pluginsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_plugins.setAdapter(new Listview_pluginsAdapter(pluginsmap));
						((BaseAdapter)listview_plugins.getAdapter()).notifyDataSetChanged();
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
				plugins_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						pluginsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								pluginsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_plugins.setAdapter(new Listview_pluginsAdapter(pluginsmap));
						((BaseAdapter)listview_plugins.getAdapter()).notifyDataSetChanged();
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
				plugins_data.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						pluginsmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								pluginsmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview_plugins.setAdapter(new Listview_pluginsAdapter(pluginsmap));
						((BaseAdapter)listview_plugins.getAdapter()).notifyDataSetChanged();
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
				ColbuxUtil.showMessage(getApplicationContext(), _errorMessage);
			}
		};
		plugins_data.addChildEventListener(_plugins_data_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		
		_textures_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_textures_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				texturesProgressDownload = _progressValue;
			}
		};
		
		_textures_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_textures_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				_Toast(getString(R.string.download_dialog_download_successful_toast));
			}
		};
		
		_textures_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_textures_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_mods_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_mods_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_mods_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_mods_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_mods_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_mods_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_maps_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_maps_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_maps_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_maps_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_maps_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_maps_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_servers_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_servers_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_servers_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_servers_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_servers_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_servers_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_plugins_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_plugins_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_plugins_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_plugins_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_plugins_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_plugins_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_plugins_comments_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		plugins_comments.addChildEventListener(_plugins_comments_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		if (!(FirebaseAuth.getInstance().getCurrentUser() != null)) {
			i.setClass(getApplicationContext(), AuthActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		}
		else {
			
		}
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);
		if (infodialog.getString("infodialog", "").equals("true")) {
			
		}
		else {
			infodialog.edit().putString("infodialog", "true").commit();
			_InformationDialog("Внимание", "Эта версия не доделана до конца, и в ней может быть большое количество ошибок", "Discord", "OK");
		}
		if (ld.getString("ld", "").equals("true")) {
			
		}
		else {
			ld.edit().putString("ld", "true").commit();
			_changeLanguageDialog();
		}
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		
		getSupportActionBar().setTitle("Colbux");
		listview_textures.setVerticalScrollBarEnabled(false);
		
		listview_textures.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		listview_mods.setVerticalScrollBarEnabled(false);
		
		listview_mods.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		listview_maps.setVerticalScrollBarEnabled(false);
		
		listview_maps.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		listview_servers.setVerticalScrollBarEnabled(false);
		
		listview_servers.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		listview_plugins.setVerticalScrollBarEnabled(false);
		
		listview_plugins.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		linear_skins.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF212121));
		linear_blocklauncher.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF212121));
		textview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFCC80));
		textview11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFCC80));
		textview10.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFCC80));
		textview13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFCC80));
		_rippleRoundStroke(btn_learn_how_to_set_a_skin, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		_rippleRoundStroke(btn_download_bl095, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		_rippleRoundStroke(btn_download_bl081, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		_rippleRoundStroke(btn_download_bl061, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, getString(R.string.setting_popup_upload_content_text));
		menu.add(0, 1, 0, getString(R.string.setting_popup_change_language_text));
		menu.add(0, 2, 0, getString(R.string.dialog_settings_settings_text));
		menu.add(0, 3, 0, getString(R.string.setting_popup_logout_text));
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int _id = item.getItemId();
		final String _title = (String) item.getTitle();
		if (_id == 0) {
			if (android.os.Build.VERSION.SDK_INT >=30) {
				_ErrorAndroidSDKDialog(getString(R.string.android_error_dialog_title), getString(R.string.android_error_dialog_message), getString(R.string.android_error_dialog_button1), "ОК");
			}
			else {
				
			}
		}
		if (_id == 1) {
			_changeLanguageDialog();
		}
		if (_id == 2) {
			i.setClass(getApplicationContext(), SettingsActivity.class);
			startActivity(i);
		}
		if (_id == 3) {
			FirebaseAuth.getInstance().signOut();
			i.setClass(getApplicationContext(), AuthActivity.class);
			startActivity(i);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (webview1.canGoBack()) {
			webview1.goBack();
		} else {
			if (linear_webview.getVisibility() == View.VISIBLE) {
				content_home.setVisibility(View.VISIBLE);
				content_modifications.setVisibility(View.GONE);
				content_servers.setVisibility(View.GONE);
				content_fordevs.setVisibility(View.GONE);
				linear_webview.setVisibility(View.GONE);
			} else {
				finishAffinity();
			}
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
	
	
	public void _MarqueTextView(final TextView _view, final String _text) {
		_view.setText(_text);
		_view.setSingleLine(true);
		_view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_view.setSelected(true);
	}
	
	
	public void _addCardView(final View _layoutView, final double _margins, final double _cornerRadius, final double _cardElevation, final double _cardMaxElevation, final boolean _preventCornerOverlap, final String _backgroundColor) {
		androidx.cardview.widget.CardView cv = new androidx.cardview.widget.CardView(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		int m = (int)_margins;
		lp.setMargins(m,m,m,m);
		cv.setLayoutParams(lp);
		int c = Color.parseColor(_backgroundColor);
		cv.setCardBackgroundColor(c);
		cv.setRadius((float)_cornerRadius);
		cv.setCardElevation((float)_cardElevation);
		cv.setMaxCardElevation((float)_cardMaxElevation);
		cv.setPreventCornerOverlap(_preventCornerOverlap);
		if(_layoutView.getParent() instanceof LinearLayout){
			ViewGroup vg = ((ViewGroup)_layoutView.getParent());
			vg.removeView(_layoutView);
			vg.removeAllViews();
			vg.addView(cv);
			cv.addView(_layoutView);
		}else{
		}
	}
	
	
	public void _Download(final String _url) {
		
	}
	
	
	public void _setBackground(final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9E9E9E")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	public void _onLongClick(final View _view, final String _toast, final boolean _vibrate) {
		
		if (_vibrate) {
			_view.setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override public boolean onLongClick(View v) {

					_view.setTooltipText(_toast);
					vibrate.vibrate((long)(25));
					return false;
				}
			});
		}
		else {
			_view.setOnLongClickListener(new View.OnLongClickListener() {
				
				@Override public boolean onLongClick(View v) {

					_view.setTooltipText(_toast);
					return false;
				}
			});
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
	
	
	public void _en() {
		nav_home_text.setText(getString(R.string.nav_home_text));
		nav_modifications_text.setText(getString(R.string.nav_modifications_text));
		nav_servers_text.setText(getString(R.string.nav_servers_text));
		nav_fordevs_text.setText(getString(R.string.nav_fordevs_text));
		textures_modifications_tab_text.setText(getString(R.string.textures_modifications_tab_text));
		mods_modifications_tab_text.setText(getString(R.string.mods_modifications_tab_text));
		maps_modifications_tab_text.setText(getString(R.string.maps_modifications_tab_text));
		plugins_fordevs_tab_text.setText(getString(R.string.plugins_fordevs_tab_text));
		textview_skins_mcpe_title.setText(getString(R.string.skins_for_mcpe_title_text));
		textview_skins_mcpe_description.setText(getString(R.string.skins_for_mcpe_description_text));
		btn_learn_how_to_set_a_skin.setText(getString(R.string.skins_for_mcpe_button_text));
		textview_bl_title.setText(getString(R.string.modified_bl_title_text));
		textview_bl_description.setText(getString(R.string.modified_bl_description_text));
		btn_download_bl095.setText(getString(R.string.modified_bl_download095_button_text));
		btn_download_bl081.setText(getString(R.string.modified_bl_download081_button_text));
		btn_download_bl061.setText(getString(R.string.modified_bl_download061_button_text));
	}
	
	
	public void _ru() {
		nav_home_text.setText(getString(R.string.nav_home_text));
		nav_modifications_text.setText(getString(R.string.nav_modifications_text));
		nav_servers_text.setText(getString(R.string.nav_servers_text));
		nav_fordevs_text.setText(getString(R.string.nav_fordevs_text));
		textures_modifications_tab_text.setText(getString(R.string.textures_modifications_tab_text));
		mods_modifications_tab_text.setText(getString(R.string.mods_modifications_tab_text));
		maps_modifications_tab_text.setText(getString(R.string.maps_modifications_tab_text));
		plugins_fordevs_tab_text.setText(getString(R.string.plugins_fordevs_tab_text));
		textview_skins_mcpe_title.setText(getString(R.string.skins_for_mcpe_title_text));
		textview_skins_mcpe_description.setText(getString(R.string.skins_for_mcpe_description_text));
		btn_learn_how_to_set_a_skin.setText(getString(R.string.skins_for_mcpe_button_text));
		textview_bl_title.setText(getString(R.string.modified_bl_title_text));
		textview_bl_description.setText(getString(R.string.modified_bl_description_text));
		btn_download_bl095.setText(getString(R.string.modified_bl_download095_button_text));
		btn_download_bl081.setText(getString(R.string.modified_bl_download081_button_text));
		btn_download_bl061.setText(getString(R.string.modified_bl_download061_button_text));
	}
	
	
	public void _DownloadFile(final String _url, final String _path) {
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()));
		android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			
			
			final String urlDownload = _url;
			
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlDownload));
			
			final String fileName = URLUtil.guessFileName(urlDownload, null, null);
			
			request.setDescription(getString(R.string.download_title));
			
			request.setTitle(fileName);
			
			request.allowScanningByMediaScanner();
			
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			
			request.setDestinationInExternalPublicDir(_path, fileName);
			
			final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
			
			final long downloadId = manager.enqueue(request);
			
			final ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
			progressDialog.setCancelable(true);
			progressDialog.setTitle(getString(R.string.download_title));
			progressDialog.setCanceledOnTouchOutside(true);

			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean downloading = true;

					while (downloading) {
						DownloadManager.Query q = new DownloadManager.Query();
						q.setFilterById(downloadId);
						android.database.Cursor cursor = manager.query(q);

						if (cursor != null && cursor.moveToFirst()) {
							int columnIndexBytesDownloaded = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
							int columnIndexBytesTotal = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
							int columnIndexStatus = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);

							if (columnIndexBytesDownloaded != -1 && columnIndexBytesTotal != -1 && columnIndexStatus != -1) {
								int bytes_downloaded = cursor.getInt(columnIndexBytesDownloaded);
								int bytes_total = cursor.getInt(columnIndexBytesTotal);

								if (cursor.getInt(columnIndexStatus) == DownloadManager.STATUS_SUCCESSFUL) {
									downloading = false;
								}

								final int downloadProgress = (bytes_total > 0) ? (int) ((bytes_downloaded * 100l) / bytes_total) : 0;

								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										progressDialog.setTitle(getString(R.string.download_title));
										progressDialog.setMessage(getString(R.string.download_title) + " " + fileName);
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
						} else {
							// Ошибка: нулевой курсор или пустой результат
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_Toast("Error: Cursor is null or empty result");
								}
							});

							// Выход из цикла, так как произошла ошибка
							downloading = false;
						}
					}
				}
			}).start();


		} else {
			_Toast(getString(R.string.no_internet_connection_dialog_title));
		}
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
		
		linear.setElevation(8);
		text.setTextColor(0xFFFFFFFF);

		Toast toast = new Toast(getApplicationContext()); 
		
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastL);
		toast.show();
	}
	
	
	public void _circleRipple(final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _changeLanguageDialog() {
		final AlertDialog change_language_dialog= new AlertDialog.Builder(HomeActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.language,null); 
		change_language_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		change_language_dialog.setView(inflate);
		TextView textview_title = (TextView) inflate.findViewById(R.id.textview_title);
		
		TextView textview_language_russian = (TextView) inflate.findViewById(R.id.textview_language_russian);
		
		TextView textview_language_english = (TextView) inflate.findViewById(R.id.textview_language_english);
		
		//ScrollView vscroll1 = (ScrollView) inflate.findViewById(R.id.vscroll1);
		
		LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
		
		LinearLayout bg2 = (LinearLayout) inflate.findViewById(R.id.bg2);
		
		LinearLayout linear_title = (LinearLayout) inflate.findViewById(R.id.linear_title);
		
		LinearLayout linear_language_russian = (LinearLayout) inflate.findViewById(R.id.linear_language_russian);
		
		LinearLayout linear_language_english = (LinearLayout) inflate.findViewById(R.id.linear_language_english);
		textview_title.setText(getString(R.string.dialog_change_language_title));
		bg.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		_rippleRoundStroke(linear_language_russian, "#212121", "#444444", 15, 0, "#FFFFFF");
		_rippleRoundStroke(linear_language_english, "#212121", "#444444", 15, 0, "#FFFFFF");
		linear_language_russian.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				change_language_dialog.dismiss();
				language.edit().remove("language").commit();
				setLocale("ru");
				_ru();
				recreate();
			}
		});
		linear_language_english.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				change_language_dialog.dismiss();
				language.edit().putString("language", "en").commit();
				setLocale("en");
				_en();
				recreate();
			}
		});
		change_language_dialog.setCancelable(true);
		change_language_dialog.show();
	}
	
	
	public void _webviewprogress(final WebView _Webview, final ProgressBar _Progressbar) {
		_Progressbar.setFitsSystemWindows(true);
		
		_Progressbar.setScrollBarStyle(android.widget.ProgressBar.SCROLLBARS_OUTSIDE_INSET);
		
		_Webview.setWebChromeClient(new WebChromeClient() {
			
			@Override public void onProgressChanged(WebView view, int newProgress) {
				
				_Progressbar.setProgress(newProgress);
			}
		});
		
	}
	
	
	public void _WebView(final boolean _js, final boolean _zoom, final boolean _download, final boolean _html, final boolean _cookies, final WebView _view) {
		_view.getSettings().setJavaScriptEnabled(_js);
		WebSettings webSettings = _view.getSettings(); 
		webSettings.setJavaScriptEnabled(_html); 
		webSettings.setJavaScriptCanOpenWindowsAutomatically(_html);
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
			webSettings.setAllowFileAccessFromFileURLs(_html); 
			webSettings.setAllowUniversalAccessFromFileURLs(_html);
		}
		if(_zoom == true){
			_view.getSettings().setBuiltInZoomControls(true);_view.getSettings().setDisplayZoomControls(false);
		}
		else if(_zoom == false){
			_view.getSettings().setBuiltInZoomControls(false);_view.getSettings().setDisplayZoomControls(true);
		}
		if(_download == true){
			
			_WebView_Download(webview1, "/Download/");
		}
		else if(_download == false){
		}
	}
	
	
	public void _WebView_Download(final WebView _wview, final String _path) {
		if (FileUtil.isExistFile(_path)) {
			
		}
		else {
			FileUtil.makeDir(_path);
		}
		_wview.setDownloadListener(new DownloadListener() { public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) { DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url)); request.addRequestHeader("User-Agent", userAgent); request.setDescription(getString(R.string.download_title)); request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype)); request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); java.io.File aatv = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/XenonDry/Download");if(!aatv.exists()){if (!aatv.mkdirs()){ Log.e("TravellerLog ::","Problem creating Image folder");}} request.setDestinationInExternalPublicDir(_path, URLUtil.guessFileName(url, contentDisposition, mimetype)); 
				
				DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE); manager.enqueue(request); _Toast(getString(R.string.download_title)); BroadcastReceiver onComplete = new BroadcastReceiver() { public void onReceive(Context ctxt, Intent intent) { _Toast(getString(R.string.download_dialog_download_successful_toast)); unregisterReceiver(this); }}; registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)); } }); 
	}
	
	
	public void _ErrorAndroidSDKDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
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
		_rippleRoundStroke(button_1, "#212121", "#333333", 10, 0, "#FFFFFF");
		_rippleRoundStroke(button_2, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		textview_title.setText(_title);
		textview_message.setText(_message);
		button_1.setText(_button1text);
		button_2.setText(_button2text);
		_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
		button_1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog.dismiss();
				i.setClass(getApplicationContext(), UploadContentActivity.class);
				startActivity(i);
			}
		});
		button_2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog.dismiss();
			}
		});
		dialog.setCancelable(true);
		dialog.show();
	}
	
	
	public void _InformationDialog(final String _title, final String _message, final String _button1text, final String _button2text) {
		final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this).create();
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
		_rippleRoundStroke(button_1, "#000000", "#333333", 10, 0, "#FFFFFF");
		_rippleRoundStroke(button_2, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
		textview_title.setText(_title);
		textview_message.setText(_message);
		button_1.setText(_button1text);
		button_2.setText(_button2text);
		_rippleRoundStroke(bg, "#000000", "#000000", 15, 0, "#000000");
		button_1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog.dismiss();
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://discord.gg/4fv4RrTav4"));
				startActivity(i);
			}
		});
		button_2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				dialog.dismiss();
			}
		});
		dialog.setCancelable(true);
		dialog.show();
	}
	
	public class Listview_texturesAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview_texturesAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.textures, null);
			}
			
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout container1 = _view.findViewById(R.id.container1);
			final LinearLayout container2 = _view.findViewById(R.id.container2);
			final ImageView img_content = _view.findViewById(R.id.img_content);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView texture_description = _view.findViewById(R.id.texture_description);
			final LinearLayout linear_actions = _view.findViewById(R.id.linear_actions);
			final TextView texture_name = _view.findViewById(R.id.texture_name);
			final TextView texture_version = _view.findViewById(R.id.texture_version);
			final TextView textview_uploaded_by = _view.findViewById(R.id.textview_uploaded_by);
			final TextView texture_uploader = _view.findViewById(R.id.texture_uploader);
			final TextView texture_download = _view.findViewById(R.id.texture_download);
			final TextView s1 = _view.findViewById(R.id.s1);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout texture_comment = _view.findViewById(R.id.texture_comment);
			final ImageView texture_icon_comment = _view.findViewById(R.id.texture_icon_comment);
			final TextView texture_comment_count = _view.findViewById(R.id.texture_comment_count);
			
			_addCardView(img_content, 0, 25, 0, 0, true, "#FFFFFF");
			_rippleRoundStroke(bg, "#212121", "#757575", 25, 0, "#FFFFFF");
			Glide.with(getApplicationContext()).load(Uri.parse(texturesmap.get((int)_position).get("thumbnailTextureUrl").toString())).into(img_content);
			_MarqueTextView(texture_name, texturesmap.get((int)_position).get("textureName").toString());
			texture_description.setText(texturesmap.get((int)_position).get("textureDescription").toString());
			texture_download.setText(getString(R.string.download_button_text));
			texture_version.setText(texturesmap.get((int)_position).get("textureVersion").toString());
			texture_uploader.setText(texturesmap.get((int)_position).get("textureUploader").toString());
			textview_uploaded_by.setText(getString(R.string.uploaded_by_text));
			texture_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFF4E5569));
			_rippleRoundStroke(texture_download, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
			bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setAction(Intent.ACTION_VIEW);
					i.setClass(getApplicationContext(), InfoActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					i.putExtra("ifTexture", "yes");
					i.putExtra("ifMod", "no");
					i.putExtra("ifMap", "no");
					i.putExtra("ifServer", "no");
					i.putExtra("ifPlugin", "no");
					i.putExtra("username", user.getString("username", ""));
					i.putExtra("thumbnailTextureUrl", texturesmap.get((int)_position).get("thumbnailTextureUrl").toString());
					i.putExtra("textureName", texturesmap.get((int)_position).get("textureName").toString());
					i.putExtra("textureDescription", texturesmap.get((int)_position).get("textureDescription").toString());
					i.putExtra("textureVersion", texturesmap.get((int)_position).get("textureVersion").toString());
					i.putExtra("textureUploader", texturesmap.get((int)_position).get("textureUploader").toString());
					i.putExtra("textureDate", texturesmap.get((int)_position).get("date").toString());
					i.putExtra("textureDownloadUrl", texturesmap.get((int)_position).get("textureDownloadUrl").toString());
					startActivity(i);
				}
			});
			texture_download.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (texturesmap.get((int)_position).get("textureDownloadUrl").toString().equals("")) {
						_Toast(getString(R.string.download_dialog_error_not_founded_url));
					}
					else {
						_DownloadFile(texturesmap.get((int)_position).get("textureDownloadUrl").toString(), "/Download/");
					}
				}
			});
			texture_name.setTextIsSelectable(true);
			texture_description.setTextIsSelectable(true);
			texture_uploader.setTextIsSelectable(true);
			
			return _view;
		}
	}
	
	public class Listview_modsAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview_modsAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.mods, null);
			}
			
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView mod_description = _view.findViewById(R.id.mod_description);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView mod_name = _view.findViewById(R.id.mod_name);
			final TextView mod_version = _view.findViewById(R.id.mod_version);
			final TextView textview_uploaded_by = _view.findViewById(R.id.textview_uploaded_by);
			final TextView mod_uploader = _view.findViewById(R.id.mod_uploader);
			final LinearLayout linear_actions = _view.findViewById(R.id.linear_actions);
			final TextView mod_download = _view.findViewById(R.id.mod_download);
			final TextView s1 = _view.findViewById(R.id.s1);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout mod_comment = _view.findViewById(R.id.mod_comment);
			final ImageView mod_icon_comment = _view.findViewById(R.id.mod_icon_comment);
			final TextView mod_comment_count = _view.findViewById(R.id.mod_comment_count);
			
			_rippleRoundStroke(bg, "#212121", "#757575", 15, 0, "#FFFFFF");
			_MarqueTextView(mod_name, modsmap.get((int)_position).get("modName").toString());
			mod_description.setText(modsmap.get((int)_position).get("modDescription").toString());
			mod_version.setText(modsmap.get((int)_position).get("modVersion").toString());
			mod_uploader.setText(modsmap.get((int)_position).get("modUploader").toString());
			mod_download.setText(getString(R.string.download_button_text));
			textview_uploaded_by.setText(getString(R.string.uploaded_by_text));
			mod_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFF4E5569));
			_rippleRoundStroke(mod_download, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
			bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setAction(Intent.ACTION_VIEW);
					i.setClass(getApplicationContext(), InfoActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					i.putExtra("ifTexture", "no");
					i.putExtra("ifMod", "yes");
					i.putExtra("ifMap", "no");
					i.putExtra("ifServer", "no");
					i.putExtra("ifPlugin", "no");
					i.putExtra("username", user.getString("username", ""));
					i.putExtra("modName", modsmap.get((int)_position).get("modName").toString());
					i.putExtra("modDescription", modsmap.get((int)_position).get("modDescription").toString());
					i.putExtra("modVersion", modsmap.get((int)_position).get("modVersion").toString());
					i.putExtra("modUploader", modsmap.get((int)_position).get("modUploader").toString());
					i.putExtra("modDate", texturesmap.get((int)_position).get("date").toString());
					i.putExtra("modDownloadUrl", modsmap.get((int)_position).get("modDownloadUrl").toString());
					startActivity(i);
				}
			});
			mod_download.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (modsmap.get((int)_position).get("modDownloadUrl").toString().equals("")) {
						_Toast(getString(R.string.download_dialog_error_not_founded_url));
					}
					else {
						_DownloadFile(modsmap.get((int)_position).get("modDownloadUrl").toString(), "/Download/");
					}
				}
			});
			mod_name.setTextIsSelectable(true);
			mod_description.setTextIsSelectable(true);
			mod_uploader.setTextIsSelectable(true);
			
			return _view;
		}
	}
	
	public class Listview_mapsAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview_mapsAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.maps, null);
			}
			
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout container1 = _view.findViewById(R.id.container1);
			final LinearLayout container2 = _view.findViewById(R.id.container2);
			final ImageView img_content = _view.findViewById(R.id.img_content);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final TextView map_name = _view.findViewById(R.id.map_name);
			final TextView map_version = _view.findViewById(R.id.map_version);
			final TextView textview_uploaded_by = _view.findViewById(R.id.textview_uploaded_by);
			final TextView map_uploader = _view.findViewById(R.id.map_uploader);
			final TextView map_download = _view.findViewById(R.id.map_download);
			final TextView s1 = _view.findViewById(R.id.s1);
			final LinearLayout linear13 = _view.findViewById(R.id.linear13);
			final LinearLayout map_comment = _view.findViewById(R.id.map_comment);
			final ImageView map_icon_comment = _view.findViewById(R.id.map_icon_comment);
			final TextView map_comment_count = _view.findViewById(R.id.map_comment_count);
			
			_addCardView(img_content, 0, 25, 0, 0, true, "#FFFFFF");
			_rippleRoundStroke(bg, "#212121", "#757575", 25, 0, "#FFFFFF");
			Glide.with(getApplicationContext()).load(Uri.parse(mapsmap.get((int)_position).get("thumbnailMapUrl").toString())).into(img_content);
			_MarqueTextView(map_name, mapsmap.get((int)_position).get("mapName").toString());
			map_version.setText(mapsmap.get((int)_position).get("mapVersion").toString());
			map_uploader.setText(mapsmap.get((int)_position).get("mapUploader").toString());
			map_download.setText(getString(R.string.download_button_text));
			textview_uploaded_by.setText(getString(R.string.uploaded_by_text));
			map_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFF4E5569));
			_rippleRoundStroke(map_download, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
			map_download.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (mapsmap.get((int)_position).get("mapDownloadUrl").toString().equals("")) {
						_Toast(getString(R.string.download_dialog_error_not_founded_url));
					}
					else {
						_DownloadFile(mapsmap.get((int)_position).get("mapDownloadUrl").toString(), "/Download/");
					}
				}
			});
			bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setAction(Intent.ACTION_VIEW);
					i.setClass(getApplicationContext(), InfoActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					i.putExtra("ifTexture", "no");
					i.putExtra("ifMod", "no");
					i.putExtra("ifMap", "yes");
					i.putExtra("ifServer", "no");
					i.putExtra("ifPlugin", "no");
					i.putExtra("username", user.getString("username", ""));
					i.putExtra("thumbnailMapUrl", mapsmap.get((int)_position).get("thumbnailMapUrl").toString());
					i.putExtra("mapName", mapsmap.get((int)_position).get("mapName").toString());
					i.putExtra("mapVersion", mapsmap.get((int)_position).get("mapVersion").toString());
					i.putExtra("mapUploader", mapsmap.get((int)_position).get("mapUploader").toString());
					i.putExtra("mapDate", texturesmap.get((int)_position).get("date").toString());
					i.putExtra("mapDownloadUrl", mapsmap.get((int)_position).get("mapDownloadUrl").toString());
					startActivity(i);
				}
			});
			map_name.setTextIsSelectable(true);
			map_uploader.setTextIsSelectable(true);
			
			return _view;
		}
	}
	
	public class Listview_serversAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview_serversAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.servers, null);
			}
			
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout container1 = _view.findViewById(R.id.container1);
			final LinearLayout container2 = _view.findViewById(R.id.container2);
			final ImageView img_content = _view.findViewById(R.id.img_content);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final TextView server_description = _view.findViewById(R.id.server_description);
			final LinearLayout linear_data = _view.findViewById(R.id.linear_data);
			final TextView server_name = _view.findViewById(R.id.server_name);
			final TextView server_version = _view.findViewById(R.id.server_version);
			final TextView s1 = _view.findViewById(R.id.s1);
			final ImageView server_discord = _view.findViewById(R.id.server_discord);
			final TextView textview_uploaded_by = _view.findViewById(R.id.textview_uploaded_by);
			final TextView server_uploader = _view.findViewById(R.id.server_uploader);
			final TextView s2 = _view.findViewById(R.id.s2);
			final LinearLayout linear_ip_and_port = _view.findViewById(R.id.linear_ip_and_port);
			final ImageView copyServerData = _view.findViewById(R.id.copyServerData);
			final TextView s3 = _view.findViewById(R.id.s3);
			final LinearLayout server_comment = _view.findViewById(R.id.server_comment);
			final TextView server_ip = _view.findViewById(R.id.server_ip);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView server_port = _view.findViewById(R.id.server_port);
			final ImageView server_icon_comment = _view.findViewById(R.id.server_icon_comment);
			final TextView server_comment_count = _view.findViewById(R.id.server_comment_count);
			
			_addCardView(img_content, 0, 25, 0, 0, true, "#FFFFFF");
			_rippleRoundStroke(bg, "#212121", "#757575", 25, 0, "#FFFFFF");
			Glide.with(getApplicationContext()).load(Uri.parse(serversmap.get((int)_position).get("thumbnailServerUrl").toString())).into(img_content);
			_MarqueTextView(server_name, serversmap.get((int)_position).get("serverName").toString());
			server_description.setText(serversmap.get((int)_position).get("serverDescription").toString());
			server_ip.setText(serversmap.get((int)_position).get("serverIp").toString());
			server_port.setText(serversmap.get((int)_position).get("serverPort").toString());
			server_version.setText(serversmap.get((int)_position).get("serverVersion").toString());
			server_uploader.setText(serversmap.get((int)_position).get("serverUploader").toString());
			textview_uploaded_by.setText(getString(R.string.uploaded_by_text));
			linear_ip_and_port.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFFFCC80));
			server_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFF4E5569));
			_onLongClick(server_discord, getString(R.string.serverDiscord), true);
			server_discord.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.putExtra("serverDiscordUrl", serversmap.get((int)_position).get("serverDiscordUrl").toString());
					startActivity(i);
				}
			});
			bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setAction(Intent.ACTION_VIEW);
					i.setClass(getApplicationContext(), InfoActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					i.putExtra("ifTexture", "no");
					i.putExtra("ifMod", "no");
					i.putExtra("ifMap", "no");
					i.putExtra("ifServer", "yes");
					i.putExtra("ifPlugin", "no");
					i.putExtra("username", user.getString("username", ""));
					i.putExtra("thumbnailServerUrl", serversmap.get((int)_position).get("thumbnailServerUrl").toString());
					i.putExtra("serverName", serversmap.get((int)_position).get("serverName").toString());
					i.putExtra("serverDescription", serversmap.get((int)_position).get("serverDescription").toString());
					i.putExtra("serverIp", serversmap.get((int)_position).get("serverIp").toString());
					i.putExtra("serverPort", serversmap.get((int)_position).get("serverPort").toString());
					i.putExtra("serverDiscordUrl", serversmap.get((int)_position).get("serverDiscordUrl").toString());
					i.putExtra("serverVersion", serversmap.get((int)_position).get("serverVersion").toString());
					i.putExtra("serverUploader", serversmap.get((int)_position).get("serverUploader").toString());
					i.putExtra("serverDate", texturesmap.get((int)_position).get("date").toString());
					startActivity(i);
				}
			});
			copyServerData.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", serversmap.get((int)_position).get("serverIp").toString().concat(":".concat(serversmap.get((int)_position).get("serverPort").toString()))));
					_Toast(getString(R.string.copyServerData_onlongclicked_toast));
				}
			});
			linear_ip_and_port.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", serversmap.get((int)_position).get("serverIp").toString().concat(":".concat(serversmap.get((int)_position).get("serverPort").toString()))));
					_Toast(getString(R.string.copyServerData_onlongclicked_toast));
				}
			});
			linear_ip_and_port.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", serversmap.get((int)_position).get("serverIp").toString().concat(":".concat(serversmap.get((int)_position).get("serverPort").toString()))));
					_Toast(getString(R.string.copyServerData_onlongclicked_toast));
					return true;
				}
			});
			server_name.setTextIsSelectable(true);
			server_description.setTextIsSelectable(true);
			server_ip.setTextIsSelectable(true);
			server_port.setTextIsSelectable(true);
			server_uploader.setTextIsSelectable(true);
			
			return _view;
		}
	}
	
	public class Listview_pluginsAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview_pluginsAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.plugins, null);
			}
			
			final LinearLayout bg = _view.findViewById(R.id.bg);
			final LinearLayout linear_top = _view.findViewById(R.id.linear_top);
			final TextView plugin_description = _view.findViewById(R.id.plugin_description);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			final TextView plugin_name = _view.findViewById(R.id.plugin_name);
			final TextView plugin_version = _view.findViewById(R.id.plugin_version);
			final TextView plugin_api = _view.findViewById(R.id.plugin_api);
			final TextView textview_uploaded_by = _view.findViewById(R.id.textview_uploaded_by);
			final TextView plugin_uploader = _view.findViewById(R.id.plugin_uploader);
			final LinearLayout linear_actions = _view.findViewById(R.id.linear_actions);
			final TextView plugin_download = _view.findViewById(R.id.plugin_download);
			final TextView s1 = _view.findViewById(R.id.s1);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout plugin_comment = _view.findViewById(R.id.plugin_comment);
			final ImageView plugin_icon_comment = _view.findViewById(R.id.plugin_icon_comment);
			final TextView plugin_comment_count = _view.findViewById(R.id.plugin_comment_count);
			
			_rippleRoundStroke(bg, "#212121", "#757575", 15, 0, "#FFFFFF");
			_MarqueTextView(plugin_name, pluginsmap.get((int)_position).get("pluginName").toString());
			plugin_description.setText(pluginsmap.get((int)_position).get("pluginDescription").toString());
			plugin_version.setText(pluginsmap.get((int)_position).get("pluginVersion").toString());
			plugin_api.setText(pluginsmap.get((int)_position).get("pluginAPI").toString());
			plugin_uploader.setText(pluginsmap.get((int)_position).get("pluginUploader").toString());
			plugin_download.setText(getString(R.string.download_button_text));
			textview_uploaded_by.setText(getString(R.string.uploaded_by_text));
			plugin_version.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFF4E5569));
			plugin_api.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)0, Color.TRANSPARENT, 0xFFFF9800));
			_rippleRoundStroke(plugin_download, "#2196F3", "#64B5F6", 10, 0, "#FFFFFF");
			bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setAction(Intent.ACTION_VIEW);
					i.setClass(getApplicationContext(), InfoActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					i.putExtra("ifTexture", "no");
					i.putExtra("ifMod", "no");
					i.putExtra("ifMap", "no");
					i.putExtra("ifServer", "no");
					i.putExtra("ifPlugin", "yes");
					i.putExtra("username", user.getString("username", ""));
					i.putExtra("pluginName", pluginsmap.get((int)_position).get("pluginName").toString());
					i.putExtra("pluginDescription", pluginsmap.get((int)_position).get("pluginDescription").toString());
					i.putExtra("pluginFullDescription", pluginsmap.get((int)_position).get("pluginFullDescription").toString());
					i.putExtra("pluginVersion", pluginsmap.get((int)_position).get("pluginVersion").toString());
					i.putExtra("pluginPMVersion", pluginsmap.get((int)_position).get("pluginAPI").toString());
					i.putExtra("pluginInfoUrl", pluginsmap.get((int)_position).get("pluginInfoUrl").toString());
					i.putExtra("pluginUploader", pluginsmap.get((int)_position).get("pluginUploader").toString());
					i.putExtra("pluginDate", pluginsmap.get((int)_position).get("date").toString());
					i.putExtra("pluginInfoUrl", pluginsmap.get((int)_position).get("pluginInfoUrl").toString());
					i.putExtra("pluginDownloadUrl", pluginsmap.get((int)_position).get("pluginDownloadUrl").toString());
					startActivity(i);
				}
			});
			plugin_download.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (pluginsmap.get((int)_position).get("pluginDownloadUrl").toString().equals("")) {
						_Toast(getString(R.string.download_dialog_error_not_founded_url));
					}
					else {
						_DownloadFile(pluginsmap.get((int)_position).get("pluginDownloadUrl").toString(), "/Download/");
					}
				}
			});
			plugin_name.setTextIsSelectable(true);
			plugin_description.setTextIsSelectable(true);
			plugin_uploader.setTextIsSelectable(true);
			
			return _view;
		}
	}
}
