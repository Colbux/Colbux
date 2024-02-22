package com.eqozqq.colbux;

import android.Manifest;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.*;
import android.os.Vibrator;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class UploadContentActivity extends AppCompatActivity {
	
	public final int REQ_CD_UPLOAD_SERVER_BANNER = 101;
	public final int REQ_CD_UPLOAD_TEXTURE_BANNER = 102;
	public final int REQ_CD_UPLOAD_TEXTURES_FILE = 103;
	public final int REQ_CD_UPLOAD_MOD_FILE = 104;
	public final int REQ_CD_UPLOAD_MAP_FILE = 105;
	public final int REQ_CD_UPLOAD_MAP_BANNER = 106;
	public final int REQ_CD_UPLOAD_PLUGIN_FILE = 107;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String textureName = "";
	private String textureInfoUrl = "";
	private String textureDownloadUrl = "";
	private String modName = "";
	private String modInfoUrl = "";
	private String modDownloadUrl = "";
	private String mapName = "";
	private String mapDownloadUrl = "";
	private String serverName = "";
	private String serverVersion = "";
	private String serverDiscordUrl = "";
	private String textureVersion = "";
	private String textureDescription = "";
	private String modVersion = "";
	private String modDescription = "";
	private String mapVersion = "";
	private String trumbnailTextureUrl = "";
	private String trumbnailServerUrl = "";
	private String serverDescription = "";
	private HashMap<String, Object> TexturesMap = new HashMap<>();
	private HashMap<String, Object> ModsMap = new HashMap<>();
	private HashMap<String, Object> MapsMap = new HashMap<>();
	private HashMap<String, Object> ServersMap = new HashMap<>();
	private HashMap<String, Object> PluginsMap = new HashMap<>();
	private String pluginName = "";
	private String pluginDescription = "";
	private String pluginVersion = "";
	private String pluginAPI = "";
	private String pluginDownloadUrl = "";
	private String pluginInfoUrl = "";
	private String serverIp = "";
	private String serverPort = "";
	private double texturesUploadProgress = 0;
	private String texturesUploadFileName = "";
	private String texturesUploadFilePath = "";
	private String serversUploadBannerPath = "";
	private String serversUploadBannerName = "";
	private String texturesUploadBannerPath = "";
	private String texturesUploadBannerName = "";
	private double texturesUploadBannerProgress = 0;
	private String modsUploadFilePath = "";
	private String modsUploadFileName = "";
	private double modsUploadProgress = 0;
	private double mapsUploadProgress = 0;
	private String mapsUploadFilePath = "";
	private String mapsUploadFileName = "";
	private double mapsUploadBannerProgress = 0;
	private String mapsUploadBannerPath = "";
	private String mapsUploadBannerName = "";
	private double serversUploadBannerProgress = 0;
	private String pluginsUploadFilePath = "";
	private String pluginsUploadFileName = "";
	private double pluginsUploadProgress = 0;
	private String serverMonitoringUrl = "";
	private String pluginFullDescription = "";
	
	private ArrayList<HashMap<String, Object>> serversmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> texturesmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> modsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> ListMapMaps = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> mapsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> pluginsmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> seedsmap = new ArrayList<>();
	private ArrayList<String> contentList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> mcpeVersions = new ArrayList<>();
	private ArrayList<String> versionsList = new ArrayList<>();
	private ArrayList<String> pmApiVersionsList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> usernamesmap = new ArrayList<>();
	
	private LinearLayout nav_s1;
	private LinearLayout navigation_more;
	private LinearLayout bg;
	private ScrollView vscroll_upload;
	private LinearLayout linear_upload;
	private TextView webhookContentTypeText;
	private TextView textview2;
	private LinearLayout linear_choice_upload;
	private TextView text_upload_texture;
	private TextView text_upload_mod;
	private TextView text_upload_map;
	private TextView text_upload_server;
	private TextView text_upload_plugin;
	private LinearLayout linear1;
	private LinearLayout linear_upload_texture;
	private LinearLayout linear_upload_mod;
	private LinearLayout linear_upload_map;
	private LinearLayout linear_upload_server;
	private LinearLayout linear_upload_plugin;
	private TextView textview8;
	private EditText edittext_texture_name;
	private TextView textview9;
	private EditText edittext_texture_description;
	private LinearLayout linear21;
	private TextView textureVersionText;
	private TextView textview11;
	private TextView textureLinkToBannerText;
	private LinearLayout linear32;
	private TextView textview_textures_screenshots;
	private LinearLayout linear_texture_screenshots;
	private TextView textview12;
	private TextView textureDownloadUrlText;
	private TextView btn_upload_texture_file;
	private LinearLayout linear6;
	private TextView textview10;
	private Spinner spinner_select_texture_version;
	private ImageView texture_banner;
	private ImageView texture_screenshot1;
	private ImageView texture_screenshot2;
	private TextView btn_texture_upload;
	private TextView textview28;
	private EditText edittext_mod_name;
	private TextView textview29;
	private EditText edittext_mod_description;
	private LinearLayout linear22;
	private TextView modVersionText;
	private TextView textview31;
	private EditText edittext_mod_moreinfo;
	private TextView textview32;
	private TextView btn_upload_mod_file;
	private LinearLayout linear12;
	private TextView modDownloadUrlText;
	private TextView textview30;
	private Spinner spinner_select_mod_version;
	private TextView btn_mod_upload;
	private TextView textview35;
	private EditText edittext_map_name;
	private LinearLayout linear23;
	private TextView textview38;
	private LinearLayout linear33;
	private TextView textview_map_screenshots;
	private LinearLayout linear_map_screenshots1;
	private LinearLayout linear_map_screenshots2;
	private TextView textview39;
	private TextView btn_upload_map_file;
	private LinearLayout linear14;
	private TextView mapVersionText;
	private TextView mapDownloadUrlText;
	private TextView mapLinkToBannerText;
	private TextView textview37;
	private Spinner spinner_select_map_version;
	private ImageView map_banner;
	private ImageView map_screenshot1;
	private ImageView map_screenshot2;
	private ImageView map_screenshot3;
	private ImageView map_screenshot4;
	private TextView btn_map_upload;
	private TextView textview50;
	private EditText edittext_server_name;
	private TextView textview51;
	private EditText edittext_server_description;
	private TextView textview52;
	private LinearLayout linear20;
	private LinearLayout linear27;
	private TextView textview54;
	private LinearLayout linear34;
	private TextView textview_server_screenshots;
	private LinearLayout linear_server_screenshots1;
	private LinearLayout linear_server_screenshots2;
	private TextView textview57;
	private EditText edittext_server_linktomonitoring;
	private TextView textview55;
	private TextView serverLinkToBannerText;
	private EditText edittext_server_linktodiscord;
	private LinearLayout linear18;
	private TextView serverVersionText;
	private EditText edittext_server_ip;
	private TextView textview56;
	private EditText edittext_server_port;
	private TextView textview53;
	private Spinner spinner_select_server_version;
	private ImageView server_banner;
	private ImageView server_screenshot1;
	private ImageView server_screenshot2;
	private ImageView server_screenshot3;
	private ImageView server_screenshot4;
	private TextView btn_server_upload;
	private TextView textview42;
	private EditText edittext_plugin_name;
	private TextView textview43;
	private EditText edittext_plugin_description;
	private TextView textview47;
	private EditText edittext_plugin_full_description;
	private TextView textview45;
	private EditText edittext_plugin_linktomoreinfo;
	private LinearLayout linear30;
	private LinearLayout linear31;
	private TextView textview46;
	private TextView btn_upload_plugin_file;
	private LinearLayout linear16;
	private TextView pluginVersionText;
	private TextView pluginPmApiVersionText;
	private TextView pluginDownloadUrlText;
	private TextView textview49;
	private Spinner spinner_select_plugin_version;
	private TextView textview44;
	private Spinner spinner_select_plugin_pm_api_version;
	private TextView pluginUploaderText;
	private TextView btn_plugin_upload;
	
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
	private Intent upload_server_banner = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent upload_texture_banner = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference textureBanners = _firebase_storage.getReference("textureBanners");
	private OnCompleteListener<Uri> _textureBanners_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _textureBanners_download_success_listener;
	private OnSuccessListener _textureBanners_delete_success_listener;
	private OnProgressListener _textureBanners_upload_progress_listener;
	private OnProgressListener _textureBanners_download_progress_listener;
	private OnFailureListener _textureBanners_failure_listener;
	
	private TimerTask t2;
	private StorageReference textures = _firebase_storage.getReference("textures");
	private OnCompleteListener<Uri> _textures_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _textures_download_success_listener;
	private OnSuccessListener _textures_delete_success_listener;
	private OnProgressListener _textures_upload_progress_listener;
	private OnProgressListener _textures_download_progress_listener;
	private OnFailureListener _textures_failure_listener;
	
	private Intent upload_textures_file = new Intent(Intent.ACTION_GET_CONTENT);
	private Calendar c = Calendar.getInstance();
	private SharedPreferences user;
	private Intent upload_mod_file = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent upload_map_file = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent upload_map_banner = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent upload_plugin_file = new Intent(Intent.ACTION_GET_CONTENT);
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
	
	private StorageReference mapBanners = _firebase_storage.getReference("mapBanners");
	private OnCompleteListener<Uri> _mapBanners_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _mapBanners_download_success_listener;
	private OnSuccessListener _mapBanners_delete_success_listener;
	private OnProgressListener _mapBanners_upload_progress_listener;
	private OnProgressListener _mapBanners_download_progress_listener;
	private OnFailureListener _mapBanners_failure_listener;
	
	private StorageReference serverBanners = _firebase_storage.getReference("serverBanners");
	private OnCompleteListener<Uri> _serverBanners_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _serverBanners_download_success_listener;
	private OnSuccessListener _serverBanners_delete_success_listener;
	private OnProgressListener _serverBanners_upload_progress_listener;
	private OnProgressListener _serverBanners_download_progress_listener;
	private OnFailureListener _serverBanners_failure_listener;
	
	private StorageReference plugins = _firebase_storage.getReference("plugins");
	private OnCompleteListener<Uri> _plugins_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _plugins_download_success_listener;
	private OnSuccessListener _plugins_delete_success_listener;
	private OnProgressListener _plugins_upload_progress_listener;
	private OnProgressListener _plugins_download_progress_listener;
	private OnFailureListener _plugins_failure_listener;
	
	private SharedPreferences getLinks;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.upload_content);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);

		//TOOLBAR
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w = UploadContentActivity.this.getWindow();
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
		nav_s1 = findViewById(R.id.nav_s1);
		navigation_more = findViewById(R.id.navigation_more);
		bg = findViewById(R.id.bg);
		vscroll_upload = findViewById(R.id.vscroll_upload);
		linear_upload = findViewById(R.id.linear_upload);
		webhookContentTypeText = findViewById(R.id.webhookContentTypeText);
		textview2 = findViewById(R.id.textview2);
		linear_choice_upload = findViewById(R.id.linear_choice_upload);
		text_upload_texture = findViewById(R.id.text_upload_texture);
		text_upload_mod = findViewById(R.id.text_upload_mod);
		text_upload_map = findViewById(R.id.text_upload_map);
		text_upload_server = findViewById(R.id.text_upload_server);
		text_upload_plugin = findViewById(R.id.text_upload_plugin);
		linear1 = findViewById(R.id.linear1);
		linear_upload_texture = findViewById(R.id.linear_upload_texture);
		linear_upload_mod = findViewById(R.id.linear_upload_mod);
		linear_upload_map = findViewById(R.id.linear_upload_map);
		linear_upload_server = findViewById(R.id.linear_upload_server);
		linear_upload_plugin = findViewById(R.id.linear_upload_plugin);
		textview8 = findViewById(R.id.textview8);
		edittext_texture_name = findViewById(R.id.edittext_texture_name);
		textview9 = findViewById(R.id.textview9);
		edittext_texture_description = findViewById(R.id.edittext_texture_description);
		linear21 = findViewById(R.id.linear21);
		textureVersionText = findViewById(R.id.textureVersionText);
		textview11 = findViewById(R.id.textview11);
		textureLinkToBannerText = findViewById(R.id.textureLinkToBannerText);
		linear32 = findViewById(R.id.linear32);
		textview_textures_screenshots = findViewById(R.id.textview_textures_screenshots);
		linear_texture_screenshots = findViewById(R.id.linear_texture_screenshots);
		textview12 = findViewById(R.id.textview12);
		textureDownloadUrlText = findViewById(R.id.textureDownloadUrlText);
		btn_upload_texture_file = findViewById(R.id.btn_upload_texture_file);
		linear6 = findViewById(R.id.linear6);
		textview10 = findViewById(R.id.textview10);
		spinner_select_texture_version = findViewById(R.id.spinner_select_texture_version);
		texture_banner = findViewById(R.id.texture_banner);
		texture_screenshot1 = findViewById(R.id.texture_screenshot1);
		texture_screenshot2 = findViewById(R.id.texture_screenshot2);
		btn_texture_upload = findViewById(R.id.btn_texture_upload);
		textview28 = findViewById(R.id.textview28);
		edittext_mod_name = findViewById(R.id.edittext_mod_name);
		textview29 = findViewById(R.id.textview29);
		edittext_mod_description = findViewById(R.id.edittext_mod_description);
		linear22 = findViewById(R.id.linear22);
		modVersionText = findViewById(R.id.modVersionText);
		textview31 = findViewById(R.id.textview31);
		edittext_mod_moreinfo = findViewById(R.id.edittext_mod_moreinfo);
		textview32 = findViewById(R.id.textview32);
		btn_upload_mod_file = findViewById(R.id.btn_upload_mod_file);
		linear12 = findViewById(R.id.linear12);
		modDownloadUrlText = findViewById(R.id.modDownloadUrlText);
		textview30 = findViewById(R.id.textview30);
		spinner_select_mod_version = findViewById(R.id.spinner_select_mod_version);
		btn_mod_upload = findViewById(R.id.btn_mod_upload);
		textview35 = findViewById(R.id.textview35);
		edittext_map_name = findViewById(R.id.edittext_map_name);
		linear23 = findViewById(R.id.linear23);
		textview38 = findViewById(R.id.textview38);
		linear33 = findViewById(R.id.linear33);
		textview_map_screenshots = findViewById(R.id.textview_map_screenshots);
		linear_map_screenshots1 = findViewById(R.id.linear_map_screenshots1);
		linear_map_screenshots2 = findViewById(R.id.linear_map_screenshots2);
		textview39 = findViewById(R.id.textview39);
		btn_upload_map_file = findViewById(R.id.btn_upload_map_file);
		linear14 = findViewById(R.id.linear14);
		mapVersionText = findViewById(R.id.mapVersionText);
		mapDownloadUrlText = findViewById(R.id.mapDownloadUrlText);
		mapLinkToBannerText = findViewById(R.id.mapLinkToBannerText);
		textview37 = findViewById(R.id.textview37);
		spinner_select_map_version = findViewById(R.id.spinner_select_map_version);
		map_banner = findViewById(R.id.map_banner);
		map_screenshot1 = findViewById(R.id.map_screenshot1);
		map_screenshot2 = findViewById(R.id.map_screenshot2);
		map_screenshot3 = findViewById(R.id.map_screenshot3);
		map_screenshot4 = findViewById(R.id.map_screenshot4);
		btn_map_upload = findViewById(R.id.btn_map_upload);
		textview50 = findViewById(R.id.textview50);
		edittext_server_name = findViewById(R.id.edittext_server_name);
		textview51 = findViewById(R.id.textview51);
		edittext_server_description = findViewById(R.id.edittext_server_description);
		textview52 = findViewById(R.id.textview52);
		linear20 = findViewById(R.id.linear20);
		linear27 = findViewById(R.id.linear27);
		textview54 = findViewById(R.id.textview54);
		linear34 = findViewById(R.id.linear34);
		textview_server_screenshots = findViewById(R.id.textview_server_screenshots);
		linear_server_screenshots1 = findViewById(R.id.linear_server_screenshots1);
		linear_server_screenshots2 = findViewById(R.id.linear_server_screenshots2);
		textview57 = findViewById(R.id.textview57);
		edittext_server_linktomonitoring = findViewById(R.id.edittext_server_linktomonitoring);
		textview55 = findViewById(R.id.textview55);
		serverLinkToBannerText = findViewById(R.id.serverLinkToBannerText);
		edittext_server_linktodiscord = findViewById(R.id.edittext_server_linktodiscord);
		linear18 = findViewById(R.id.linear18);
		serverVersionText = findViewById(R.id.serverVersionText);
		edittext_server_ip = findViewById(R.id.edittext_server_ip);
		textview56 = findViewById(R.id.textview56);
		edittext_server_port = findViewById(R.id.edittext_server_port);
		textview53 = findViewById(R.id.textview53);
		spinner_select_server_version = findViewById(R.id.spinner_select_server_version);
		server_banner = findViewById(R.id.server_banner);
		server_screenshot1 = findViewById(R.id.server_screenshot1);
		server_screenshot2 = findViewById(R.id.server_screenshot2);
		server_screenshot3 = findViewById(R.id.server_screenshot3);
		server_screenshot4 = findViewById(R.id.server_screenshot4);
		btn_server_upload = findViewById(R.id.btn_server_upload);
		textview42 = findViewById(R.id.textview42);
		edittext_plugin_name = findViewById(R.id.edittext_plugin_name);
		textview43 = findViewById(R.id.textview43);
		edittext_plugin_description = findViewById(R.id.edittext_plugin_description);
		textview47 = findViewById(R.id.textview47);
		edittext_plugin_full_description = findViewById(R.id.edittext_plugin_full_description);
		textview45 = findViewById(R.id.textview45);
		edittext_plugin_linktomoreinfo = findViewById(R.id.edittext_plugin_linktomoreinfo);
		linear30 = findViewById(R.id.linear30);
		linear31 = findViewById(R.id.linear31);
		textview46 = findViewById(R.id.textview46);
		btn_upload_plugin_file = findViewById(R.id.btn_upload_plugin_file);
		linear16 = findViewById(R.id.linear16);
		pluginVersionText = findViewById(R.id.pluginVersionText);
		pluginPmApiVersionText = findViewById(R.id.pluginPmApiVersionText);
		pluginDownloadUrlText = findViewById(R.id.pluginDownloadUrlText);
		textview49 = findViewById(R.id.textview49);
		spinner_select_plugin_version = findViewById(R.id.spinner_select_plugin_version);
		textview44 = findViewById(R.id.textview44);
		spinner_select_plugin_pm_api_version = findViewById(R.id.spinner_select_plugin_pm_api_version);
		pluginUploaderText = findViewById(R.id.pluginUploaderText);
		btn_plugin_upload = findViewById(R.id.btn_plugin_upload);
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
		upload_server_banner.setType("image/*");
		upload_server_banner.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		upload_texture_banner.setType("image/*");
		upload_texture_banner.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		upload_textures_file.setType("*/*");
		upload_textures_file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		upload_mod_file.setType("*/*");
		upload_mod_file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		upload_map_file.setType("*/*");
		upload_map_file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		upload_map_banner.setType("image/*");
		upload_map_banner.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		upload_plugin_file.setType("*/*");
		upload_plugin_file.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		getLinks = getSharedPreferences("getLinks", Activity.MODE_PRIVATE);
		
		text_upload_texture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webhookContentTypeText.setText("Texture");
				linear_upload_texture.setVisibility(View.VISIBLE);
				linear_upload_mod.setVisibility(View.GONE);
				linear_upload_map.setVisibility(View.GONE);
				linear_upload_server.setVisibility(View.GONE);
				linear_upload_plugin.setVisibility(View.GONE);
				text_upload_texture.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF4CAF50, 0xFF121212));
				text_upload_mod.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_map.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_server.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_plugin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
			}
		});
		
		text_upload_mod.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webhookContentTypeText.setText("Mod");
				linear_upload_texture.setVisibility(View.GONE);
				linear_upload_mod.setVisibility(View.VISIBLE);
				linear_upload_map.setVisibility(View.GONE);
				linear_upload_server.setVisibility(View.GONE);
				linear_upload_plugin.setVisibility(View.GONE);
				text_upload_texture.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_mod.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF4CAF50, 0xFF121212));
				text_upload_map.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_server.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_plugin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
			}
		});
		
		text_upload_map.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webhookContentTypeText.setText("Map");
				linear_upload_texture.setVisibility(View.GONE);
				linear_upload_mod.setVisibility(View.GONE);
				linear_upload_map.setVisibility(View.VISIBLE);
				linear_upload_server.setVisibility(View.GONE);
				linear_upload_plugin.setVisibility(View.GONE);
				text_upload_texture.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_mod.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_map.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF4CAF50, 0xFF121212));
				text_upload_server.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_plugin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
			}
		});
		
		text_upload_server.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webhookContentTypeText.setText("Server");
				linear_upload_texture.setVisibility(View.GONE);
				linear_upload_mod.setVisibility(View.GONE);
				linear_upload_map.setVisibility(View.GONE);
				linear_upload_server.setVisibility(View.VISIBLE);
				linear_upload_plugin.setVisibility(View.GONE);
				text_upload_texture.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_mod.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_map.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_server.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF4CAF50, 0xFF121212));
				text_upload_plugin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
			}
		});
		
		text_upload_plugin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webhookContentTypeText.setText("Plugin");
				linear_upload_texture.setVisibility(View.GONE);
				linear_upload_mod.setVisibility(View.GONE);
				linear_upload_map.setVisibility(View.GONE);
				linear_upload_server.setVisibility(View.GONE);
				linear_upload_plugin.setVisibility(View.VISIBLE);
				text_upload_texture.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_mod.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_map.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_server.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF121212, 0xFF121212));
				text_upload_plugin.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF4CAF50, 0xFF121212));
			}
		});
		
		btn_upload_texture_file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_textures_file, REQ_CD_UPLOAD_TEXTURES_FILE);
			}
		});
		
		spinner_select_texture_version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					textureVersionText.setText("0.16.2");
				}
				if (_position == 1) {
					textureVersionText.setText("0.16.1");
				}
				if (_position == 2) {
					textureVersionText.setText("0.16.0");
				}
				if (_position == 3) {
					textureVersionText.setText("0.15.10");
				}
				if (_position == 4) {
					textureVersionText.setText("0.15.9");
				}
				if (_position == 5) {
					textureVersionText.setText("0.15.8");
				}
				if (_position == 6) {
					textureVersionText.setText("0.15.7");
				}
				if (_position == 7) {
					textureVersionText.setText("0.15.6");
				}
				if (_position == 8) {
					textureVersionText.setText("0.15.4");
				}
				if (_position == 9) {
					textureVersionText.setText("0.15.3");
				}
				if (_position == 10) {
					textureVersionText.setText("0.15.2");
				}
				if (_position == 11) {
					textureVersionText.setText("0.15.1");
				}
				if (_position == 12) {
					textureVersionText.setText("0.15.0");
				}
				if (_position == 13) {
					textureVersionText.setText("0.14.3");
				}
				if (_position == 14) {
					textureVersionText.setText("0.14.2");
				}
				if (_position == 15) {
					textureVersionText.setText("0.14.1");
				}
				if (_position == 16) {
					textureVersionText.setText("0.14.0");
				}
				if (_position == 17) {
					textureVersionText.setText("0.13.2");
				}
				if (_position == 18) {
					textureVersionText.setText("0.13.1");
				}
				if (_position == 19) {
					textureVersionText.setText("0.13.0");
				}
				if (_position == 20) {
					textureVersionText.setText("0.12.3");
				}
				if (_position == 21) {
					textureVersionText.setText("0.12.2");
				}
				if (_position == 22) {
					textureVersionText.setText("0.12.1");
				}
				if (_position == 23) {
					textureVersionText.setText("0.12.0");
				}
				if (_position == 24) {
					textureVersionText.setText("0.11.0");
				}
				if (_position == 25) {
					textureVersionText.setText("0.10.5");
				}
				if (_position == 26) {
					textureVersionText.setText("0.10.4");
				}
				if (_position == 27) {
					textureVersionText.setText("0.10.3");
				}
				if (_position == 28) {
					textureVersionText.setText("0.10.2");
				}
				if (_position == 29) {
					textureVersionText.setText("0.10.1");
				}
				if (_position == 30) {
					textureVersionText.setText("0.10.0");
				}
				if (_position == 31) {
					textureVersionText.setText("0.9.5");
				}
				if (_position == 32) {
					textureVersionText.setText("0.9.4");
				}
				if (_position == 33) {
					textureVersionText.setText("0.9.3");
				}
				if (_position == 34) {
					textureVersionText.setText("0.9.2");
				}
				if (_position == 35) {
					textureVersionText.setText("0.9.1");
				}
				if (_position == 36) {
					textureVersionText.setText("0.9.0");
				}
				if (_position == 37) {
					textureVersionText.setText("0.8.1");
				}
				if (_position == 38) {
					textureVersionText.setText("0.8.0");
				}
				if (_position == 39) {
					textureVersionText.setText("0.7.6");
				}
				if (_position == 40) {
					textureVersionText.setText("0.7.5");
				}
				if (_position == 41) {
					textureVersionText.setText("0.7.4");
				}
				if (_position == 42) {
					textureVersionText.setText("0.7.3");
				}
				if (_position == 43) {
					textureVersionText.setText("0.7.2");
				}
				if (_position == 44) {
					textureVersionText.setText("0.7.1");
				}
				if (_position == 45) {
					textureVersionText.setText("0.7.0");
				}
				if (_position == 46) {
					textureVersionText.setText("0.6.1");
				}
				if (_position == 47) {
					textureVersionText.setText("0.6.0");
				}
				if (_position == 48) {
					textureVersionText.setText("0.5.0");
				}
				if (_position == 49) {
					textureVersionText.setText("0.4.0");
				}
				if (_position == 50) {
					textureVersionText.setText("0.3.3");
				}
				if (_position == 51) {
					textureVersionText.setText("0.3.2");
				}
				if (_position == 52) {
					textureVersionText.setText("0.3.0");
				}
				if (_position == 53) {
					textureVersionText.setText("0.2.2");
				}
				if (_position == 54) {
					textureVersionText.setText("0.2.1");
				}
				if (_position == 55) {
					textureVersionText.setText("0.2.0");
				}
				if (_position == 56) {
					textureVersionText.setText("0.1.3");
				}
				if (_position == 57) {
					textureVersionText.setText("0.1.2");
				}
				if (_position == 58) {
					textureVersionText.setText("0.1.0");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		texture_banner.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_texture_banner, REQ_CD_UPLOAD_TEXTURE_BANNER);
			}
		});
		
		btn_texture_upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_texture_name.getText().toString().equals("")) {
					_Toast(getString(R.string.fill_in_all_the_fields_toast));
				}
				else {
					if (edittext_texture_description.getText().toString().equals("")) {
						_Toast(getString(R.string.fill_in_all_the_fields_toast));
					}
					else {
						if (textureVersionText.getText().toString().equals("")) {
							_Toast(getString(R.string.fill_in_all_the_fields_toast));
						}
						else {
							if (textureLinkToBannerText.getText().toString().equals("")) {
								_Toast(getString(R.string.choose_banner_toast));
							}
							else {
								if (textureDownloadUrlText.getText().toString().equals("")) {
									_Toast(getString(R.string.choose_file_toast));
								}
								else {
									textureName = edittext_texture_name.getText().toString();

									textureDescription = edittext_texture_description.getText().toString();
									textureVersion = textureVersionText.getText().toString();
									c = Calendar.getInstance();
									TexturesMap = new HashMap<>();
									TexturesMap.put("textureName", textureName);
									TexturesMap.put("textureDescription", textureDescription);
									TexturesMap.put("textureVersion", textureVersion);
									TexturesMap.put("textureDownloadUrl", textureDownloadUrl);
									TexturesMap.put("thumbnailTextureUrl", getLinks.getString("textureLinkToBanner", ""));
									TexturesMap.put("textureUploader", user.getString("username", ""));
									TexturesMap.put("date", new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(c.getTime()));
									TexturesMap.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
									TexturesMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
									textures_data.push().updateChildren(TexturesMap);
									TexturesMap.clear();
									_Toast(getString(R.string.new_texture_uploaded_toast));
									textureVersionText.setText("");
									textureLinkToBannerText.setText("");
									textureDownloadUrlText.setText("");
									texture_banner.setImageResource(R.drawable.ic_plus_circle_outline);
									edittext_texture_name.setText("");
									edittext_texture_description.setText("");
								}
							}
						}
					}
				}
			}
		});
		
		btn_upload_mod_file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_mod_file, REQ_CD_UPLOAD_MOD_FILE);
			}
		});
		
		spinner_select_mod_version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					modVersionText.setText("0.16.2");
				}
				if (_position == 1) {
					modVersionText.setText("0.16.1");
				}
				if (_position == 2) {
					modVersionText.setText("0.16.0");
				}
				if (_position == 3) {
					modVersionText.setText("0.15.10");
				}
				if (_position == 4) {
					modVersionText.setText("0.15.9");
				}
				if (_position == 5) {
					modVersionText.setText("0.15.8");
				}
				if (_position == 6) {
					modVersionText.setText("0.15.7");
				}
				if (_position == 7) {
					modVersionText.setText("0.15.6");
				}
				if (_position == 8) {
					modVersionText.setText("0.15.4");
				}
				if (_position == 9) {
					modVersionText.setText("0.15.3");
				}
				if (_position == 10) {
					modVersionText.setText("0.15.2");
				}
				if (_position == 11) {
					modVersionText.setText("0.15.1");
				}
				if (_position == 12) {
					modVersionText.setText("0.15.0");
				}
				if (_position == 13) {
					modVersionText.setText("0.14.3");
				}
				if (_position == 14) {
					modVersionText.setText("0.14.2");
				}
				if (_position == 15) {
					modVersionText.setText("0.14.1");
				}
				if (_position == 16) {
					modVersionText.setText("0.14.0");
				}
				if (_position == 17) {
					modVersionText.setText("0.13.2");
				}
				if (_position == 18) {
					modVersionText.setText("0.13.1");
				}
				if (_position == 19) {
					modVersionText.setText("0.13.0");
				}
				if (_position == 20) {
					modVersionText.setText("0.12.3");
				}
				if (_position == 21) {
					modVersionText.setText("0.12.2");
				}
				if (_position == 22) {
					modVersionText.setText("0.12.1");
				}
				if (_position == 23) {
					modVersionText.setText("0.12.0");
				}
				if (_position == 24) {
					modVersionText.setText("0.11.0");
				}
				if (_position == 25) {
					modVersionText.setText("0.10.5");
				}
				if (_position == 26) {
					modVersionText.setText("0.10.4");
				}
				if (_position == 27) {
					modVersionText.setText("0.10.3");
				}
				if (_position == 28) {
					modVersionText.setText("0.10.2");
				}
				if (_position == 29) {
					modVersionText.setText("0.10.1");
				}
				if (_position == 30) {
					modVersionText.setText("0.10.0");
				}
				if (_position == 31) {
					modVersionText.setText("0.9.5");
				}
				if (_position == 32) {
					modVersionText.setText("0.9.4");
				}
				if (_position == 33) {
					modVersionText.setText("0.9.3");
				}
				if (_position == 34) {
					modVersionText.setText("0.9.2");
				}
				if (_position == 35) {
					modVersionText.setText("0.9.1");
				}
				if (_position == 36) {
					modVersionText.setText("0.9.0");
				}
				if (_position == 37) {
					modVersionText.setText("0.8.1");
				}
				if (_position == 38) {
					modVersionText.setText("0.8.0");
				}
				if (_position == 39) {
					modVersionText.setText("0.7.6");
				}
				if (_position == 40) {
					modVersionText.setText("0.7.5");
				}
				if (_position == 41) {
					modVersionText.setText("0.7.4");
				}
				if (_position == 42) {
					modVersionText.setText("0.7.3");
				}
				if (_position == 43) {
					modVersionText.setText("0.7.2");
				}
				if (_position == 44) {
					modVersionText.setText("0.7.1");
				}
				if (_position == 45) {
					modVersionText.setText("0.7.0");
				}
				if (_position == 46) {
					modVersionText.setText("0.6.1");
				}
				if (_position == 47) {
					modVersionText.setText("0.6.0");
				}
				if (_position == 48) {
					modVersionText.setText("0.5.0");
				}
				if (_position == 49) {
					modVersionText.setText("0.4.0");
				}
				if (_position == 50) {
					modVersionText.setText("0.3.3");
				}
				if (_position == 51) {
					modVersionText.setText("0.3.2");
				}
				if (_position == 52) {
					modVersionText.setText("0.3.0");
				}
				if (_position == 53) {
					modVersionText.setText("0.2.2");
				}
				if (_position == 54) {
					modVersionText.setText("0.2.1");
				}
				if (_position == 55) {
					modVersionText.setText("0.2.0");
				}
				if (_position == 56) {
					modVersionText.setText("0.1.3");
				}
				if (_position == 57) {
					modVersionText.setText("0.1.2");
				}
				if (_position == 58) {
					modVersionText.setText("0.1.0");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		btn_mod_upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_mod_name.getText().toString().equals("")) {
					_Toast(getString(R.string.fill_in_all_the_fields_toast));
				}
				else {
					if (edittext_mod_description.getText().toString().equals("")) {
						_Toast(getString(R.string.fill_in_all_the_fields_toast));
					}
					else {
						if (modVersionText.getText().toString().equals("")) {
							_Toast(getString(R.string.fill_in_all_the_fields_toast));
						}
						else {
							if (edittext_mod_moreinfo.getText().toString().equals("")) {
								_Toast(getString(R.string.fill_in_all_the_fields_toast));
							}
							else {
								if (modDownloadUrlText.getText().toString().equals("")) {
									_Toast(getString(R.string.choose_file_toast));
								}
								else {
									modName = edittext_mod_name.getText().toString();

									modDescription = edittext_mod_description.getText().toString();
									modVersion = modVersionText.getText().toString();
									modInfoUrl = edittext_mod_moreinfo.getText().toString();
									ModsMap = new HashMap<>();
									ModsMap.put("modName", modName);
									ModsMap.put("modDescription", modDescription);
									ModsMap.put("modVersion", modVersion);
									ModsMap.put("modInfoUrl", modInfoUrl);
									ModsMap.put("modDownloadUrl", modDownloadUrl);
									ModsMap.put("modUploader", user.getString("username", ""));
									ModsMap.put("date", new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(c.getTime()));
									ModsMap.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
									ModsMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
									mods_data.push().updateChildren(ModsMap);
									ModsMap.clear();
									_Toast(getString(R.string.new_mod_uploaded_toast));
									modVersionText.setText("");
									modDownloadUrlText.setText("");
									edittext_mod_name.setText("");
									edittext_mod_description.setText("");
									edittext_mod_moreinfo.setText("");
								}
							}
						}
					}
				}
			}
		});
		
		btn_upload_map_file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_map_file, REQ_CD_UPLOAD_MAP_FILE);
			}
		});
		
		spinner_select_map_version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					mapVersionText.setText("0.16.2");
				}
				if (_position == 1) {
					mapVersionText.setText("0.16.1");
				}
				if (_position == 2) {
					mapVersionText.setText("0.16.0");
				}
				if (_position == 3) {
					mapVersionText.setText("0.15.10");
				}
				if (_position == 4) {
					mapVersionText.setText("0.15.9");
				}
				if (_position == 5) {
					mapVersionText.setText("0.15.8");
				}
				if (_position == 6) {
					mapVersionText.setText("0.15.7");
				}
				if (_position == 7) {
					mapVersionText.setText("0.15.6");
				}
				if (_position == 8) {
					mapVersionText.setText("0.15.4");
				}
				if (_position == 9) {
					mapVersionText.setText("0.15.3");
				}
				if (_position == 10) {
					mapVersionText.setText("0.15.2");
				}
				if (_position == 11) {
					mapVersionText.setText("0.15.1");
				}
				if (_position == 12) {
					mapVersionText.setText("0.15.0");
				}
				if (_position == 13) {
					mapVersionText.setText("0.14.3");
				}
				if (_position == 14) {
					mapVersionText.setText("0.14.2");
				}
				if (_position == 15) {
					mapVersionText.setText("0.14.1");
				}
				if (_position == 16) {
					mapVersionText.setText("0.14.0");
				}
				if (_position == 17) {
					mapVersionText.setText("0.13.2");
				}
				if (_position == 18) {
					mapVersionText.setText("0.13.1");
				}
				if (_position == 19) {
					mapVersionText.setText("0.13.0");
				}
				if (_position == 20) {
					mapVersionText.setText("0.12.3");
				}
				if (_position == 21) {
					mapVersionText.setText("0.12.2");
				}
				if (_position == 22) {
					mapVersionText.setText("0.12.1");
				}
				if (_position == 23) {
					mapVersionText.setText("0.12.0");
				}
				if (_position == 24) {
					mapVersionText.setText("0.11.0");
				}
				if (_position == 25) {
					mapVersionText.setText("0.10.5");
				}
				if (_position == 26) {
					mapVersionText.setText("0.10.4");
				}
				if (_position == 27) {
					mapVersionText.setText("0.10.3");
				}
				if (_position == 28) {
					mapVersionText.setText("0.10.2");
				}
				if (_position == 29) {
					mapVersionText.setText("0.10.1");
				}
				if (_position == 30) {
					mapVersionText.setText("0.10.0");
				}
				if (_position == 31) {
					mapVersionText.setText("0.9.5");
				}
				if (_position == 32) {
					mapVersionText.setText("0.9.4");
				}
				if (_position == 33) {
					mapVersionText.setText("0.9.3");
				}
				if (_position == 34) {
					mapVersionText.setText("0.9.2");
				}
				if (_position == 35) {
					mapVersionText.setText("0.9.1");
				}
				if (_position == 36) {
					mapVersionText.setText("0.9.0");
				}
				if (_position == 37) {
					mapVersionText.setText("0.8.1");
				}
				if (_position == 38) {
					mapVersionText.setText("0.8.0");
				}
				if (_position == 39) {
					mapVersionText.setText("0.7.6");
				}
				if (_position == 40) {
					mapVersionText.setText("0.7.5");
				}
				if (_position == 41) {
					mapVersionText.setText("0.7.4");
				}
				if (_position == 42) {
					mapVersionText.setText("0.7.3");
				}
				if (_position == 43) {
					mapVersionText.setText("0.7.2");
				}
				if (_position == 44) {
					mapVersionText.setText("0.7.1");
				}
				if (_position == 45) {
					mapVersionText.setText("0.7.0");
				}
				if (_position == 46) {
					mapVersionText.setText("0.6.1");
				}
				if (_position == 47) {
					mapVersionText.setText("0.6.0");
				}
				if (_position == 48) {
					mapVersionText.setText("0.5.0");
				}
				if (_position == 49) {
					mapVersionText.setText("0.4.0");
				}
				if (_position == 50) {
					mapVersionText.setText("0.3.3");
				}
				if (_position == 51) {
					mapVersionText.setText("0.3.2");
				}
				if (_position == 52) {
					mapVersionText.setText("0.3.0");
				}
				if (_position == 53) {
					mapVersionText.setText("0.2.2");
				}
				if (_position == 54) {
					mapVersionText.setText("0.2.1");
				}
				if (_position == 55) {
					mapVersionText.setText("0.2.0");
				}
				if (_position == 56) {
					mapVersionText.setText("0.1.3");
				}
				if (_position == 57) {
					mapVersionText.setText("0.1.2");
				}
				if (_position == 58) {
					mapVersionText.setText("0.1.0");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		map_banner.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_map_banner, REQ_CD_UPLOAD_MAP_BANNER);
			}
		});
		
		btn_map_upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_map_name.getText().toString().equals("")) {
					_Toast(getString(R.string.fill_in_all_the_fields_toast));
				}
				else {
					if (mapVersionText.getText().toString().equals("")) {
						_Toast(getString(R.string.fill_in_all_the_fields_toast));
					}
					else {
						if (mapLinkToBannerText.getText().toString().equals("")) {
							_Toast(getString(R.string.choose_banner_toast));
						}
						else {
							if (mapDownloadUrlText.getText().toString().equals("")) {
								_Toast(getString(R.string.choose_file_toast));
							}
							else {
								mapName = edittext_map_name.getText().toString();
								mapVersion = mapVersionText.getText().toString();
								MapsMap = new HashMap<>();
								MapsMap.put("mapName", mapName);
								MapsMap.put("mapVersion", mapVersion);
								MapsMap.put("thumbnailMapUrl", mapLinkToBannerText.getText().toString());
								MapsMap.put("mapDownloadUrl", mapDownloadUrl);
								MapsMap.put("mapUploader", user.getString("username", ""));
								MapsMap.put("date", new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(c.getTime()));
								MapsMap.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
								MapsMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
								maps_data.push().updateChildren(MapsMap);
								MapsMap.clear();
								_Toast(getString(R.string.new_map_uploaded_toast));
								mapVersionText.setText("");
								mapLinkToBannerText.setText("");
								mapDownloadUrlText.setText("");
								map_banner.setImageResource(R.drawable.ic_plus_circle_outline);
								edittext_map_name.setText("");
							}
						}
					}
				}
			}
		});
		
		spinner_select_server_version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					serverVersionText.setText("0.16.2");
				}
				if (_position == 1) {
					serverVersionText.setText("0.16.1");
				}
				if (_position == 2) {
					serverVersionText.setText("0.16.0");
				}
				if (_position == 3) {
					serverVersionText.setText("0.15.10");
				}
				if (_position == 4) {
					serverVersionText.setText("0.15.9");
				}
				if (_position == 5) {
					serverVersionText.setText("0.15.8");
				}
				if (_position == 6) {
					serverVersionText.setText("0.15.7");
				}
				if (_position == 7) {
					serverVersionText.setText("0.15.6");
				}
				if (_position == 8) {
					serverVersionText.setText("0.15.4");
				}
				if (_position == 9) {
					serverVersionText.setText("0.15.3");
				}
				if (_position == 10) {
					serverVersionText.setText("0.15.2");
				}
				if (_position == 11) {
					serverVersionText.setText("0.15.1");
				}
				if (_position == 12) {
					serverVersionText.setText("0.15.0");
				}
				if (_position == 13) {
					serverVersionText.setText("0.14.3");
				}
				if (_position == 14) {
					serverVersionText.setText("0.14.2");
				}
				if (_position == 15) {
					serverVersionText.setText("0.14.1");
				}
				if (_position == 16) {
					serverVersionText.setText("0.14.0");
				}
				if (_position == 17) {
					serverVersionText.setText("0.13.2");
				}
				if (_position == 18) {
					serverVersionText.setText("0.13.1");
				}
				if (_position == 19) {
					serverVersionText.setText("0.13.0");
				}
				if (_position == 20) {
					serverVersionText.setText("0.12.3");
				}
				if (_position == 21) {
					serverVersionText.setText("0.12.2");
				}
				if (_position == 22) {
					serverVersionText.setText("0.12.1");
				}
				if (_position == 23) {
					serverVersionText.setText("0.12.0");
				}
				if (_position == 24) {
					serverVersionText.setText("0.11.0");
				}
				if (_position == 25) {
					serverVersionText.setText("0.10.5");
				}
				if (_position == 26) {
					serverVersionText.setText("0.10.4");
				}
				if (_position == 27) {
					serverVersionText.setText("0.10.3");
				}
				if (_position == 28) {
					serverVersionText.setText("0.10.2");
				}
				if (_position == 29) {
					serverVersionText.setText("0.10.1");
				}
				if (_position == 30) {
					serverVersionText.setText("0.10.0");
				}
				if (_position == 31) {
					serverVersionText.setText("0.9.5");
				}
				if (_position == 32) {
					serverVersionText.setText("0.9.4");
				}
				if (_position == 33) {
					serverVersionText.setText("0.9.3");
				}
				if (_position == 34) {
					serverVersionText.setText("0.9.2");
				}
				if (_position == 35) {
					serverVersionText.setText("0.9.1");
				}
				if (_position == 36) {
					serverVersionText.setText("0.9.0");
				}
				if (_position == 37) {
					serverVersionText.setText("0.8.1");
				}
				if (_position == 38) {
					serverVersionText.setText("0.8.0");
				}
				if (_position == 39) {
					serverVersionText.setText("0.7.6");
				}
				if (_position == 40) {
					serverVersionText.setText("0.7.5");
				}
				if (_position == 41) {
					serverVersionText.setText("0.7.4");
				}
				if (_position == 42) {
					serverVersionText.setText("0.7.3");
				}
				if (_position == 43) {
					serverVersionText.setText("0.7.2");
				}
				if (_position == 44) {
					serverVersionText.setText("0.7.1");
				}
				if (_position == 45) {
					serverVersionText.setText("0.7.0");
				}
				if (_position == 46) {
					serverVersionText.setText("0.6.1");
				}
				if (_position == 47) {
					serverVersionText.setText("0.6.0");
				}
				if (_position == 48) {
					serverVersionText.setText("0.5.0");
				}
				if (_position == 49) {
					serverVersionText.setText("0.4.0");
				}
				if (_position == 50) {
					serverVersionText.setText("0.3.3");
				}
				if (_position == 51) {
					serverVersionText.setText("0.3.2");
				}
				if (_position == 52) {
					serverVersionText.setText("0.3.0");
				}
				if (_position == 53) {
					serverVersionText.setText("0.2.2");
				}
				if (_position == 54) {
					serverVersionText.setText("0.2.1");
				}
				if (_position == 55) {
					serverVersionText.setText("0.2.0");
				}
				if (_position == 56) {
					serverVersionText.setText("0.1.3");
				}
				if (_position == 57) {
					serverVersionText.setText("0.1.2");
				}
				if (_position == 58) {
					serverVersionText.setText("0.1.0");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		server_banner.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_server_banner, REQ_CD_UPLOAD_SERVER_BANNER);
			}
		});
		
		btn_server_upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_server_name.getText().toString().equals("")) {
					_Toast(getString(R.string.fill_in_all_the_fields_toast));
				}
				else {
					if (edittext_server_description.getText().toString().equals("")) {
						_Toast(getString(R.string.fill_in_all_the_fields_toast));
					}
					else {
						if (edittext_server_ip.getText().toString().equals("")) {
							_Toast(getString(R.string.fill_in_all_the_fields_toast));
						}
						else {
							if (edittext_server_port.getText().toString().equals("")) {
								_Toast(getString(R.string.fill_in_all_the_fields_toast));
							}
							else {
								if (serverVersionText.getText().toString().equals("")) {
									_Toast(getString(R.string.fill_in_all_the_fields_toast));
								}
								else {
									if (serverLinkToBannerText.getText().toString().equals("")) {
										_Toast(getString(R.string.choose_banner_toast));
									}
									else {
										if (edittext_server_linktodiscord.getText().toString().equals("")) {
											_Toast(getString(R.string.fill_in_all_the_fields_toast));
										}
										else {
											serverName = edittext_server_name.getText().toString();
											serverDescription = edittext_server_description.getText().toString();
											serverIp = edittext_server_ip.getText().toString();
											serverPort = edittext_server_port.getText().toString();
											serverVersion = serverVersionText.getText().toString();
											serverMonitoringUrl =
											edittext_server_linktomonitoring.getText().toString();
											serverDiscordUrl = edittext_server_linktodiscord.getText().toString();
											ServersMap = new HashMap<>();
											ServersMap.put("serverName", serverName);
											ServersMap.put("serverDescription", serverDescription);
											ServersMap.put("serverIp", serverIp);
											ServersMap.put("serverPort", serverPort);
											ServersMap.put("serverVersion", serverVersion);
											ServersMap.put("serverMonitoringUrl", serverMonitoringUrl);
											ServersMap.put("serverDiscordUrl", serverDiscordUrl);
											ServersMap.put("thumbnailServerUrl", serverLinkToBannerText.getText().toString());
											ServersMap.put("serverUploader", user.getString("username", ""));
											ServersMap.put("date", new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(c.getTime()));
											ServersMap.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
											ServersMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
											servers_data.push().updateChildren(ServersMap);
											ServersMap.clear();
											_Toast(getString(R.string.new_server_uploaded_toast));
											serverVersionText.setText("");
											serverLinkToBannerText.setText("");
											server_banner.setImageResource(R.drawable.ic_plus_circle_outline);
											edittext_server_name.setText("");
											edittext_server_description.setText("");
											edittext_server_linktomonitoring.setText("");
											edittext_server_linktodiscord.setText("");
											edittext_server_ip.setText("");
											edittext_server_port.setText("");
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		btn_upload_plugin_file.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(upload_plugin_file, REQ_CD_UPLOAD_PLUGIN_FILE);
			}
		});
		
		spinner_select_plugin_version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 0) {
					pluginVersionText.setText("0.16.2");
				}
				if (_position == 1) {
					pluginVersionText.setText("0.16.1");
				}
				if (_position == 2) {
					pluginVersionText.setText("0.16.0");
				}
				if (_position == 3) {
					pluginVersionText.setText("0.15.10");
				}
				if (_position == 4) {
					pluginVersionText.setText("0.15.9");
				}
				if (_position == 5) {
					pluginVersionText.setText("0.15.8");
				}
				if (_position == 6) {
					pluginVersionText.setText("0.15.7");
				}
				if (_position == 7) {
					pluginVersionText.setText("0.15.6");
				}
				if (_position == 8) {
					pluginVersionText.setText("0.15.4");
				}
				if (_position == 9) {
					pluginVersionText.setText("0.15.3");
				}
				if (_position == 10) {
					pluginVersionText.setText("0.15.2");
				}
				if (_position == 11) {
					pluginVersionText.setText("0.15.1");
				}
				if (_position == 12) {
					pluginVersionText.setText("0.15.0");
				}
				if (_position == 13) {
					pluginVersionText.setText("0.14.3");
				}
				if (_position == 14) {
					pluginVersionText.setText("0.14.2");
				}
				if (_position == 15) {
					pluginVersionText.setText("0.14.1");
				}
				if (_position == 16) {
					pluginVersionText.setText("0.14.0");
				}
				if (_position == 17) {
					pluginVersionText.setText("0.13.2");
				}
				if (_position == 18) {
					pluginVersionText.setText("0.13.1");
				}
				if (_position == 19) {
					pluginVersionText.setText("0.13.0");
				}
				if (_position == 20) {
					pluginVersionText.setText("0.12.3");
				}
				if (_position == 21) {
					pluginVersionText.setText("0.12.2");
				}
				if (_position == 22) {
					pluginVersionText.setText("0.12.1");
				}
				if (_position == 23) {
					pluginVersionText.setText("0.12.0");
				}
				if (_position == 24) {
					pluginVersionText.setText("0.11.0");
				}
				if (_position == 25) {
					pluginVersionText.setText("0.10.5");
				}
				if (_position == 26) {
					pluginVersionText.setText("0.10.4");
				}
				if (_position == 27) {
					pluginVersionText.setText("0.10.3");
				}
				if (_position == 28) {
					pluginVersionText.setText("0.10.2");
				}
				if (_position == 29) {
					pluginVersionText.setText("0.10.1");
				}
				if (_position == 30) {
					pluginVersionText.setText("0.10.0");
				}
				if (_position == 31) {
					pluginVersionText.setText("0.9.5");
				}
				if (_position == 32) {
					pluginVersionText.setText("0.9.4");
				}
				if (_position == 33) {
					pluginVersionText.setText("0.9.3");
				}
				if (_position == 34) {
					pluginVersionText.setText("0.9.2");
				}
				if (_position == 35) {
					pluginVersionText.setText("0.9.1");
				}
				if (_position == 36) {
					pluginVersionText.setText("0.9.0");
				}
				if (_position == 37) {
					pluginVersionText.setText("0.8.1");
				}
				if (_position == 38) {
					pluginVersionText.setText("0.8.0");
				}
				if (_position == 39) {
					pluginVersionText.setText("0.7.6");
				}
				if (_position == 40) {
					pluginVersionText.setText("0.7.5");
				}
				if (_position == 41) {
					pluginVersionText.setText("0.7.4");
				}
				if (_position == 42) {
					pluginVersionText.setText("0.7.3");
				}
				if (_position == 43) {
					pluginVersionText.setText("0.7.2");
				}
				if (_position == 44) {
					pluginVersionText.setText("0.7.1");
				}
				if (_position == 45) {
					pluginVersionText.setText("0.7.0");
				}
				if (_position == 46) {
					pluginVersionText.setText("0.6.1");
				}
				if (_position == 47) {
					pluginVersionText.setText("0.6.0");
				}
				if (_position == 48) {
					pluginVersionText.setText("0.5.0");
				}
				if (_position == 49) {
					pluginVersionText.setText("0.4.0");
				}
				if (_position == 50) {
					pluginVersionText.setText("0.3.3");
				}
				if (_position == 51) {
					pluginVersionText.setText("0.3.2");
				}
				if (_position == 52) {
					pluginVersionText.setText("0.3.0");
				}
				if (_position == 53) {
					pluginVersionText.setText("0.2.2");
				}
				if (_position == 54) {
					pluginVersionText.setText("0.2.1");
				}
				if (_position == 55) {
					pluginVersionText.setText("0.2.0");
				}
				if (_position == 56) {
					pluginVersionText.setText("0.1.3");
				}
				if (_position == 57) {
					pluginVersionText.setText("0.1.2");
				}
				if (_position == 58) {
					pluginVersionText.setText("0.1.0");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		spinner_select_plugin_pm_api_version.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (_position == 1) {
					pluginPmApiVersionText.setText("NC 1.0.0");
				}
				if (_position == 2) {
					pluginPmApiVersionText.setText("NC 1.0.1");
				}
				if (_position == 3) {
					pluginPmApiVersionText.setText("NC 1.0.2");
				}
				if (_position == 4) {
					pluginPmApiVersionText.setText("NC 1.1.0");
				}
				if (_position == 5) {
					pluginPmApiVersionText.setText("MCPE.AlphaServer");
				}
				if (_position == 6) {
					pluginPmApiVersionText.setText("PMMP 1.4.1");
				}
				if (_position == 7) {
					pluginPmApiVersionText.setText("PMMP 1.4");
				}
				if (_position == 8) {
					pluginPmApiVersionText.setText("PMMP 1.3.12");
				}
				if (_position == 9) {
					pluginPmApiVersionText.setText("PMMP 1.3.11");
				}
				if (_position == 10) {
					pluginPmApiVersionText.setText("PMMP 1.3.10");
				}
				if (_position == 11) {
					pluginPmApiVersionText.setText("PMMP 1.3.9");
				}
				if (_position == 12) {
					pluginPmApiVersionText.setText("PMMP 1.3.8");
				}
				if (_position == 13) {
					pluginPmApiVersionText.setText("PMMP 1.3.7");
				}
				if (_position == 14) {
					pluginPmApiVersionText.setText("PMMP 1.3.5");
				}
				if (_position == 15) {
					pluginPmApiVersionText.setText("PMMP 1.3.4");
				}
				if (_position == 16) {
					pluginPmApiVersionText.setText("PMMP 1.3.3");
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> _param1) {
				
			}
		});
		
		btn_plugin_upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_plugin_name.getText().toString().equals("")) {
					_Toast(getString(R.string.fill_in_all_the_fields_toast));
				}
				else {
					if (edittext_plugin_full_description.getText().toString().equals("")) {
						_Toast(getString(R.string.fill_in_all_the_fields_toast));
					}
					else {
						if (pluginVersionText.getText().toString().equals("")) {
							_Toast(getString(R.string.fill_in_all_the_fields_toast));
						}
						else {
							if (pluginPmApiVersionText.getText().toString().equals("")) {
								_Toast(getString(R.string.fill_in_all_the_fields_toast));
							}
							else {
								if (edittext_plugin_linktomoreinfo.getText().toString().equals("")) {
									_Toast(getString(R.string.fill_in_all_the_fields_toast));
								}
								else {
									if (pluginDownloadUrlText.getText().toString().equals("")) {
										_Toast(getString(R.string.choose_file_toast));
									}
									else {
										pluginName = edittext_plugin_name.getText().toString();
										pluginDescription = edittext_plugin_description.getText().toString();
										pluginFullDescription = edittext_plugin_full_description.getText().toString();
										pluginVersion = pluginVersionText.getText().toString();
										pluginAPI = pluginPmApiVersionText.getText().toString();
										pluginInfoUrl = edittext_plugin_linktomoreinfo.getText().toString();
										c = Calendar.getInstance();
										PluginsMap = new HashMap<>();
										PluginsMap.put("pluginName", pluginName);
										PluginsMap.put("pluginDescription", pluginDescription);
										PluginsMap.put("pluginFullDescription", pluginFullDescription);
										PluginsMap.put("pluginVersion", pluginVersion);
										PluginsMap.put("pluginAPI", pluginAPI);
										PluginsMap.put("pluginInfoUrl", pluginInfoUrl);
										PluginsMap.put("pluginDownloadUrl", pluginDownloadUrl);
										PluginsMap.put("pluginUploader", user.getString("username", ""));
										PluginsMap.put("date", new SimpleDateFormat("dd/MM/yyyy  HH:mm").format(c.getTime()));
										PluginsMap.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
										PluginsMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
										plugins_data.push().updateChildren(PluginsMap);
										PluginsMap.clear();
										_Toast(getString(R.string.new_plugin_uploaded_toast));
										pluginVersionText.setText("");
										pluginPmApiVersionText.setText("");
										pluginDownloadUrlText.setText("https://firebasestorage.googleapis.com/v0/b/colbux-e6d12.appspot.com/o/plugins%2FAdminSettings.php?alt=media&token=409dbad7-dc84-4ea9-9352-c970b2e513ef");
										edittext_plugin_name.setText("");
										edittext_plugin_description.setText("");
										edittext_plugin_full_description.setText("");
										edittext_plugin_linktomoreinfo.setText("");
									}
								}
							}
						}
					}
				}
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
						if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							
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
		
		_textureBanners_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				texturesUploadBannerProgress = _progressValue;
			}
		};
		
		_textureBanners_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_textureBanners_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				trumbnailTextureUrl = _downloadUrl;
				getLinks.edit().putString("textureLinkToBanner", _downloadUrl).commit();
				Glide.with(getApplicationContext()).load(Uri.parse(_downloadUrl)).into(texture_banner);
				_Toast(getString(R.string.uploading_banner_successfully));
			}
		};
		
		_textureBanners_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_textureBanners_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_textureBanners_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_textures_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				texturesUploadProgress = _progressValue;
			}
		};
		
		_textures_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_textures_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				textureDownloadUrl = _downloadUrl;
				textureDownloadUrlText.setText(_downloadUrl);
				_Toast(getString(R.string.uploading_file_successful));
			}
		};
		
		_textures_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
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
				modsUploadProgress = _progressValue;
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
				modDownloadUrl = _downloadUrl;
				modDownloadUrlText.setText(_downloadUrl);
				_Toast(getString(R.string.uploading_file_successful));
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
				mapsUploadProgress = _progressValue;
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
				mapDownloadUrl = _downloadUrl;
				mapDownloadUrlText.setText(_downloadUrl);
				_Toast(getString(R.string.uploading_file_successful));
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
		
		_mapBanners_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				mapsUploadBannerProgress = _progressValue;
			}
		};
		
		_mapBanners_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_mapBanners_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				String trumbnailMapUrl = _downloadUrl;
				mapLinkToBannerText.setText(_downloadUrl);
				Glide.with(getApplicationContext()).load(Uri.parse(_downloadUrl)).into(map_banner);
				_Toast(getString(R.string.uploading_banner_successfully));
			}
		};
		
		_mapBanners_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_mapBanners_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_mapBanners_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_serverBanners_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				serversUploadBannerProgress = _progressValue;
			}
		};
		
		_serverBanners_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_serverBanners_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				trumbnailServerUrl = _downloadUrl;
				serverLinkToBannerText.setText(_downloadUrl);
				Glide.with(getApplicationContext()).load(Uri.parse(_downloadUrl)).into(server_banner);
				_Toast(getString(R.string.uploading_banner_successfully));
			}
		};
		
		_serverBanners_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_serverBanners_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_serverBanners_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_plugins_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				pluginsUploadProgress = _progressValue;
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
				pluginDownloadUrl = _downloadUrl;
				pluginDownloadUrlText.setText(_downloadUrl);
				_Toast(getString(R.string.uploading_file_successful));
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
		FileUtil.writeFile("f", "x");
		if (!(FirebaseAuth.getInstance().getCurrentUser() != null)) {
			i.setClass(getApplicationContext(), AuthActivity.class);
			startActivity(i);
			finish();
		}
		getSupportActionBar().setTitle(getString(R.string.upload_content_title));
		_ui();
		/*
android.graphics.drawable.GradientDrawable edittext_plugin_full_description_gradientDrawable = new android.graphics.drawable.GradientDrawable();
edittext_plugin_full_description_gradientDrawable.setColor(0xFFFFFFFF);
edittext_plugin_full_description_gradientDrawable.setCornerRadii(new float[] {20, 20, 20, 20, 20, 20, 20, 20});
edittext_plugin_full_description_gradientDrawable.setStroke(4, 0xFF2196F3);
edittext_plugin_full_description.setBackground(edittext_plugin_full_description_gradientDrawable);
*/
		edittext_plugin_full_description.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
				
				public static final String TAG = "ALUKO";
			
			    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				        Log.d(TAG, "onCreateActionMode");
				        MenuInflater inflater = mode.getMenuInflater();
				        inflater.inflate(R.menu.style, menu);
				        menu.removeItem(android.R.id.selectAll);
				        return true;
				    }
			
			    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				        return false;
				    }
			
			    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				        Log.d(TAG, String.format("onActionItemClicked item=%s/%d", item.toString(), item.getItemId()));
				        CharacterStyle cs;
				        int start = edittext_plugin_full_description.getSelectionStart();
				        int end = edittext_plugin_full_description.getSelectionEnd();
				        SpannableStringBuilder ssb = new SpannableStringBuilder(edittext_plugin_full_description.getText());
				
				        switch(LayoutElement.from(item)) {
							//To add new item ID , Go for [ Resource > menu > style.xml ] and edit it.
							//and to import the ID go to More Blocks and edit it.
					        case BOLD_BUTTON:
					            cs = new StyleSpan(Typeface.BOLD);
					            ssb.setSpan(cs, start, end, 1);
					            edittext_plugin_full_description.setText(ssb);
								edittext_plugin_full_description.setSelection((int)end, (int)end);
					            return true;
					
					        case ITALIC_BUTTON:
					            cs = new StyleSpan(Typeface.ITALIC);
					            ssb.setSpan(cs, start, end, 1);
					            edittext_plugin_full_description.setText(ssb);
								edittext_plugin_full_description.setSelection((int)end, (int)end);
					            return true;
					
					        case UNDERLINE_BUTTON:
					            cs = new UnderlineSpan();
					            ssb.setSpan(cs, start, end, 1);
					            edittext_plugin_full_description.setText(ssb);
								edittext_plugin_full_description.setSelection((int)end, (int)end);
					            return true;
								
							case STRIKETHROUGH_BUTTON:
								cs = new StrikethroughSpan();
								ssb.setSpan(cs, start, end, 1);
								edittext_plugin_full_description.setText(ssb);
								edittext_plugin_full_description.setSelection((int)end, (int)end);
					            return true;
								
							case TEXTCOLOR_BUTTON:
								cs = new ForegroundColorSpan(Color.RED);
								ssb.setSpan(cs, start, end, 1);
								edittext_plugin_full_description.setText(ssb);
								edittext_plugin_full_description.setSelection((int)end, (int)end);
					            return true;
								
							case BACKGROUNDCOLOR_BUTTON:
								ssb.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, 1);
					            edittext_plugin_full_description.setText(ssb);
								edittext_plugin_full_description.setSelection((int)end, (int)end);
					            return true;
							}
				        return false;
				    }
			
			    public void onDestroyActionMode(ActionMode mode) {
						
				    }
		});
		
		linear_upload_mod.setVisibility(View.GONE);
		linear_upload_map.setVisibility(View.GONE);
		linear_upload_server.setVisibility(View.GONE);
		linear_upload_plugin.setVisibility(View.GONE);
		versionsList.add("0.16.2");
		versionsList.add("0.16.1");
		versionsList.add("0.16.0");
		versionsList.add("0.15.10");
		versionsList.add("0.15.9");
		versionsList.add("0.15.8");
		versionsList.add("0.15.7");
		versionsList.add("0.15.6");
		versionsList.add("0.15.4");
		versionsList.add("0.15.3");
		versionsList.add("0.15.2");
		versionsList.add("0.15.1");
		versionsList.add("0.15.0");
		versionsList.add("0.14.3");
		versionsList.add("0.14.2");
		versionsList.add("0.14.1");
		versionsList.add("0.14.0");
		versionsList.add("0.13.2");
		versionsList.add("0.13.1");
		versionsList.add("0.13.0");
		versionsList.add("0.12.3");
		versionsList.add("0.12.2");
		versionsList.add("0.12.1");
		versionsList.add("0.12.0");
		versionsList.add("0.11.0");
		versionsList.add("0.10.5");
		versionsList.add("0.10.4");
		versionsList.add("0.10.3");
		versionsList.add("0.10.2");
		versionsList.add("0.10.1");
		versionsList.add("0.10.0");
		versionsList.add("0.9.5");
		versionsList.add("0.9.4");
		versionsList.add("0.9.3");
		versionsList.add("0.9.2");
		versionsList.add("0.9.1");
		versionsList.add("0.9.0");
		versionsList.add("0.8.1");
		versionsList.add("0.8.0");
		versionsList.add("0.7.6");
		versionsList.add("0.7.5");
		versionsList.add("0.7.4");
		versionsList.add("0.7.3");
		versionsList.add("0.7.2");
		versionsList.add("0.7.1");
		versionsList.add("0.7.0");
		versionsList.add("0.6.1");
		versionsList.add("0.6.0");
		versionsList.add("0.5.0");
		versionsList.add("0.4.0");
		versionsList.add("0.3.3");
		versionsList.add("0.3.2");
		versionsList.add("0.3.0");
		versionsList.add("0.2.2");
		versionsList.add("0.2.1");
		versionsList.add("0.2.0");
		versionsList.add("0.1.3");
		versionsList.add("0.1.2");
		versionsList.add("0.1.1");
		pmApiVersionsList.add("NC 1.0.0");
		pmApiVersionsList.add("NC 1.0.1");
		pmApiVersionsList.add("NC 1.0.2");
		pmApiVersionsList.add("NC 1.1.0");
		pmApiVersionsList.add("MCPE.AlphaServer");
		pmApiVersionsList.add("PMMP 1.4.1");
		pmApiVersionsList.add("PMMP 1.4");
		pmApiVersionsList.add("PMMP 1.3.12");
		pmApiVersionsList.add("PMMP 1.3.11");
		pmApiVersionsList.add("PMMP 1.3.10");
		pmApiVersionsList.add("PMMP 1.3.9");
		pmApiVersionsList.add("PMMP 1.3.8");
		pmApiVersionsList.add("PMMP 1.3.7");
		pmApiVersionsList.add("PMMP 1.3.5");
		pmApiVersionsList.add("PMMP 1.3.4");
		pmApiVersionsList.add("PMMP 1.3.3");
		spinner_select_texture_version.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, versionsList));
		((ArrayAdapter)spinner_select_texture_version.getAdapter()).notifyDataSetChanged();
		spinner_select_mod_version.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, versionsList));
		((ArrayAdapter)spinner_select_mod_version.getAdapter()).notifyDataSetChanged();
		spinner_select_map_version.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, versionsList));
		((ArrayAdapter)spinner_select_map_version.getAdapter()).notifyDataSetChanged();
		spinner_select_server_version.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, versionsList));
		((ArrayAdapter)spinner_select_server_version.getAdapter()).notifyDataSetChanged();
		spinner_select_plugin_version.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, versionsList));
		((ArrayAdapter)spinner_select_plugin_version.getAdapter()).notifyDataSetChanged();
		spinner_select_plugin_pm_api_version.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, pmApiVersionsList));
		((ArrayAdapter)spinner_select_plugin_pm_api_version.getAdapter()).notifyDataSetChanged();
		_spinner(spinner_select_texture_version, versionsList, "#FFFFFF", "#333333");
		_spinner(spinner_select_mod_version, versionsList, "#FFFFFF", "#333333");
		_spinner(spinner_select_map_version, versionsList, "#FFFFFF", "#333333");
		_spinner(spinner_select_server_version, versionsList, "#FFFFFF", "#333333");
		_spinner(spinner_select_plugin_version, versionsList, "#FFFFFF", "#333333");
		_spinner(spinner_select_plugin_pm_api_version, pmApiVersionsList, "#FFFFFF", "#333333");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		if (_resultCode == 0) {
				
		}
		switch (_requestCode) {
			case REQ_CD_UPLOAD_SERVER_BANNER:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				serversUploadBannerPath = _filePath.get((int)(0));
				serversUploadBannerName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				if (FileUtil.isExistFile(serversUploadBannerPath)) {
					if (FileUtil.getFileLength(serversUploadBannerPath) > 0) {
						if ((FileUtil.getFileLength(serversUploadBannerPath) - 1) < (10 * (1024 * 1024))) {
							serverBanners.child(serversUploadBannerName).putFile(Uri.fromFile(new File(serversUploadBannerPath))).addOnFailureListener(_serverBanners_failure_listener).addOnProgressListener(_serverBanners_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return serverBanners.child(serversUploadBannerName).getDownloadUrl();
								}}).addOnCompleteListener(_serverBanners_upload_success_listener);
							        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
							        progressDialog.setMax(100);
							        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
							        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
							
							progressDialog.setCancelable(false);
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.show();
							progressDialog.setIndeterminate(true);
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											progressDialog.setProgress((int)serversUploadBannerProgress);
											if (serversUploadBannerProgress > 1) {
												progressDialog.setIndeterminate(false);
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
							t2 = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (progressDialog.getMax() == progressDialog.getProgress()) {
												t2 = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progressDialog.dismiss();
															}
														});
													}
												};
												_timer.schedule(t2, (int)(600));
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t2, (int)(10), (int)(100));
						}
						else {
							_Toast(getString(R.string.file_size_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.empty_file_error_toast));
					}
				}
				else {
					_Toast(getString(R.string.file_not_found_toast));
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_UPLOAD_TEXTURE_BANNER:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				texturesUploadBannerPath = _filePath.get((int)(0));
				if (texturesUploadBannerPath.equals("")) {
					texturesUploadBannerName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
					if (FileUtil.isExistFile(texturesUploadBannerPath)) {
						if (FileUtil.getFileLength(texturesUploadBannerPath) > 0) {
							if ((FileUtil.getFileLength(texturesUploadBannerPath) - 1) < (10 * (1024 * 1024))) {
								textureBanners.child(texturesUploadBannerName).putFile(Uri.fromFile(new File(texturesUploadBannerPath))).addOnFailureListener(_textureBanners_failure_listener).addOnProgressListener(_textureBanners_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
									@Override
									public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
										return textureBanners.child(texturesUploadBannerName).getDownloadUrl();
									}}).addOnCompleteListener(_textureBanners_upload_success_listener);
								        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
								        progressDialog.setMax(100);
								        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
								        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
								
								progressDialog.setCancelable(false);
								        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
								        progressDialog.show();
								progressDialog.setIndeterminate(true);
								t = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												progressDialog.setProgress((int)texturesUploadBannerProgress);
												if (texturesUploadBannerProgress > 1) {
													progressDialog.setIndeterminate(false);
												}
											}
										});
									}
								};
								_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
								t = new TimerTask() {
									@Override
									public void run() {
										runOnUiThread(new Runnable() {
											@Override
											public void run() {
												if (progressDialog.getMax() == progressDialog.getProgress()) {
													t = new TimerTask() {
														@Override
														public void run() {
															runOnUiThread(new Runnable() {
																@Override
																public void run() {
																	progressDialog.dismiss();
																}
															});
														}
													};
													_timer.schedule(t, (int)(600));
												}
											}
										});
									}
								};
								_timer.scheduleAtFixedRate(t, (int)(10), (int)(100));
							}
							else {
								_Toast(getString(R.string.file_size_error_toast));
							}
						}
						else {
							_Toast(getString(R.string.empty_file_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.file_not_found_toast));
					}
				}
				else {
					_Toast("texturesUploadBannerPath is null");
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_UPLOAD_TEXTURES_FILE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				texturesUploadFilePath = _filePath.get((int)(0));
				texturesUploadFileName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				if (FileUtil.isExistFile(texturesUploadFilePath)) {
					if (FileUtil.getFileLength(texturesUploadFilePath) > 0) {
						if ((FileUtil.getFileLength(texturesUploadFilePath) - 1) < (50 * (1024 * 1024))) {
							textures.child(texturesUploadFileName).putFile(Uri.fromFile(new File(texturesUploadFilePath))).addOnFailureListener(_textures_failure_listener).addOnProgressListener(_textures_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return textures.child(texturesUploadFileName).getDownloadUrl();
								}}).addOnCompleteListener(_textures_upload_success_listener);
							        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
							        progressDialog.setMax(100);
							        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
							        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
							
							progressDialog.setCancelable(false);
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.show();
							progressDialog.setIndeterminate(true);
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											progressDialog.setProgress((int)texturesUploadProgress);
											if (texturesUploadProgress > 1) {
												progressDialog.setIndeterminate(false);
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (progressDialog.getMax() == progressDialog.getProgress()) {
												t = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progressDialog.dismiss();
															}
														});
													}
												};
												_timer.schedule(t, (int)(600));
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(10), (int)(100));
							btn_upload_texture_file.setText(texturesUploadFileName);
						}
						else {
							_Toast(getString(R.string.file_size_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.empty_file_error_toast));
					}
				}
				else {
					_Toast(getString(R.string.file_not_found_toast));
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_UPLOAD_MOD_FILE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				modsUploadFilePath = _filePath.get((int)(0));
				modsUploadFileName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				if (FileUtil.isExistFile(modsUploadFilePath)) {
					if (FileUtil.getFileLength(modsUploadFilePath) > 0) {
						if ((FileUtil.getFileLength(modsUploadFilePath) - 1) < (50 * (1024 * 1024))) {
							mods.child(modsUploadFileName).putFile(Uri.fromFile(new File(modsUploadFilePath))).addOnFailureListener(_mods_failure_listener).addOnProgressListener(_mods_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return mods.child(modsUploadFileName).getDownloadUrl();
								}}).addOnCompleteListener(_mods_upload_success_listener);
							        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
							        progressDialog.setMax(100);
							        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
							        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
							
							progressDialog.setCancelable(false);
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.show();
							progressDialog.setIndeterminate(true);
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											progressDialog.setProgress((int)modsUploadProgress);
											if (modsUploadProgress > 1) {
												progressDialog.setIndeterminate(false);
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (progressDialog.getMax() == progressDialog.getProgress()) {
												t = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progressDialog.dismiss();
															}
														});
													}
												};
												_timer.schedule(t, (int)(600));
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(10), (int)(100));
							btn_upload_mod_file.setText(modsUploadFileName);
						}
						else {
							_Toast(getString(R.string.file_size_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.empty_file_error_toast));
					}
				}
				else {
					_Toast(getString(R.string.file_not_found_toast));
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_UPLOAD_MAP_FILE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				mapsUploadFilePath = _filePath.get((int)(0));
				mapsUploadFileName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				if (FileUtil.isExistFile(mapsUploadFilePath)) {
					if (FileUtil.getFileLength(mapsUploadFilePath) > 0) {
						if ((FileUtil.getFileLength(mapsUploadFilePath) - 1) < (50 * (1024 * 1024))) {
							maps.child(mapsUploadFileName).putFile(Uri.fromFile(new File(mapsUploadFilePath))).addOnFailureListener(_maps_failure_listener).addOnProgressListener(_maps_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return maps.child(mapsUploadFileName).getDownloadUrl();
								}}).addOnCompleteListener(_maps_upload_success_listener);
							        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
							        progressDialog.setMax(100);
							        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
							        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
							
							progressDialog.setCancelable(false);
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.show();
							progressDialog.setIndeterminate(true);
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											progressDialog.setProgress((int)mapsUploadProgress);
											if (mapsUploadProgress > 1) {
												progressDialog.setIndeterminate(false);
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (progressDialog.getMax() == progressDialog.getProgress()) {
												t = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progressDialog.dismiss();
															}
														});
													}
												};
												_timer.schedule(t, (int)(600));
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(10), (int)(100));
							btn_upload_map_file.setText(mapsUploadFileName);
						}
						else {
							_Toast(getString(R.string.file_size_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.empty_file_error_toast));
					}
				}
				else {
					_Toast(getString(R.string.file_not_found_toast));
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_UPLOAD_MAP_BANNER:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				mapsUploadBannerPath = _filePath.get((int)(0));
				mapsUploadBannerName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				if (FileUtil.isExistFile(mapsUploadBannerPath)) {
					if (FileUtil.getFileLength(mapsUploadBannerPath) > 0) {
						if ((FileUtil.getFileLength(mapsUploadBannerPath) - 1) < (10 * (1024 * 1024))) {
							mapBanners.child(mapsUploadBannerName).putFile(Uri.fromFile(new File(mapsUploadBannerPath))).addOnFailureListener(_mapBanners_failure_listener).addOnProgressListener(_mapBanners_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return mapBanners.child(mapsUploadBannerName).getDownloadUrl();
								}}).addOnCompleteListener(_mapBanners_upload_success_listener);
							        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
							        progressDialog.setMax(100);
							        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
							        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
							
							progressDialog.setCancelable(false);
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.show();
							progressDialog.setIndeterminate(true);
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											progressDialog.setProgress((int)mapsUploadBannerProgress);
											if (mapsUploadBannerProgress > 1) {
												progressDialog.setIndeterminate(false);
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (progressDialog.getMax() == progressDialog.getProgress()) {
												t = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progressDialog.dismiss();
															}
														});
													}
												};
												_timer.schedule(t, (int)(600));
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(10), (int)(100));
						}
						else {
							_Toast(getString(R.string.file_size_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.empty_file_error_toast));
					}
				}
				else {
					_Toast(getString(R.string.file_not_found_toast));
				}
			}
			else {
				
			}
			break;
			
			case REQ_CD_UPLOAD_PLUGIN_FILE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				pluginsUploadFilePath = _filePath.get((int)(0));
				pluginsUploadFileName = Uri.parse(_filePath.get((int)(0))).getLastPathSegment();
				if (FileUtil.isExistFile(pluginsUploadFilePath)) {
					if (FileUtil.getFileLength(pluginsUploadFilePath) > 0) {
						if ((FileUtil.getFileLength(pluginsUploadFilePath) - 1) < (50 * (1024 * 1024))) {
							plugins.child(pluginsUploadFileName).putFile(Uri.fromFile(new File(pluginsUploadFilePath))).addOnFailureListener(_plugins_failure_listener).addOnProgressListener(_plugins_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return plugins.child(pluginsUploadFileName).getDownloadUrl();
								}}).addOnCompleteListener(_plugins_upload_success_listener);
							        final ProgressDialog progressDialog = new ProgressDialog(UploadContentActivity.this, ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
							        progressDialog.setMax(100);
							        progressDialog.setTitle(getString(R.string.uploading_file_dialog_title));
							        progressDialog.setMessage(getString(R.string.uploading_file_dialog_message));
							
							progressDialog.setCancelable(false);
							        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
							        progressDialog.show();
							progressDialog.setIndeterminate(true);
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											progressDialog.setProgress((int)pluginsUploadProgress);
											if (pluginsUploadProgress > 1) {
												progressDialog.setIndeterminate(false);
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(500), (int)(200));
							t = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											if (progressDialog.getMax() == progressDialog.getProgress()) {
												t = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progressDialog.dismiss();
															}
														});
													}
												};
												_timer.schedule(t, (int)(600));
											}
										}
									});
								}
							};
							_timer.scheduleAtFixedRate(t, (int)(10), (int)(100));
							btn_upload_plugin_file.setText(pluginsUploadFileName);
						}
						else {
							_Toast(getString(R.string.file_size_error_toast));
						}
					}
					else {
						_Toast(getString(R.string.empty_file_error_toast));
					}
				}
				else {
					_Toast(getString(R.string.file_not_found_toast));
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
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
		textview2.setText(getString(R.string.upload_content_description));
		
		text_upload_texture.setText(getString(R.string.texture_text));
		
		text_upload_mod.setText(getString(R.string.mod_text));
		
		text_upload_map.setText(getString(R.string.map_text));
		
		text_upload_server.setText(getString(R.string.server_text));
		
		text_upload_plugin.setText(getString(R.string.plugin_text));
		edittext_texture_name.setHint(getString(R.string.upload_texture_edittext_name));
		
		edittext_texture_description.setHint(getString(R.string.upload_texture_edittext_description));
		
		btn_upload_texture_file.setText(getString(R.string.choose_file_toast));
		
		btn_texture_upload.setText(getString(R.string.upload_texture_ok_button));
		
		textview8.setText(getString(R.string.upload_content_name_text));
		
		textview9.setText(getString(R.string.upload_content_description_text));
		
		textview10.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview11.setText(getString(R.string.upload_content_link_to_banner_text));
		
		textview12.setText(getString(R.string.upload_content_link_to_download_text));
		edittext_mod_name.setHint(getString(R.string.upload_mod_edittext_name));
		
		edittext_mod_description.setHint(getString(R.string.upload_mod_edittext_description));
		
		edittext_mod_moreinfo.setHint(getString(R.string.upload_content_more_info_text));
		
		btn_upload_mod_file.setText(getString(R.string.choose_file_toast));
		
		btn_mod_upload.setText(getString(R.string.upload_mod_ok_button));
		
		textview28.setText(getString(R.string.upload_content_name_text));
		
		textview29.setText(getString(R.string.upload_content_description_text));
		
		textview30.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview31.setText(getString(R.string.upload_content_more_info_text));
		
		textview32.setText(getString(R.string.upload_content_link_to_download_text));
		edittext_map_name.setHint(getString(R.string.upload_map_edittext_name));
		
		btn_upload_map_file.setText(getString(R.string.choose_file_toast));
		
		btn_map_upload.setText(getString(R.string.upload_map_ok_button));
		
		textview35.setText(getString(R.string.upload_content_name_text));
		
		textview37.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview38.setText(getString(R.string.upload_content_link_to_banner_text));
		
		textview39.setText(getString(R.string.upload_content_link_to_download_text));
		edittext_server_name.setHint(getString(R.string.upload_server_edittext_name));
		
		edittext_server_description.setHint(getString(R.string.upload_server_edittext_description));
		
		edittext_server_ip.setHint(getString(R.string.upload_server_edittext_ip));
		
		edittext_server_port.setHint(getString(R.string.upload_server_edittext_port));
		
		edittext_server_linktodiscord.setHint(getString(R.string.upload_server_edittext_link_to_discord));
		
		edittext_server_linktomonitoring.setHint(getString(R.string.upload_content_link_to_monitoring_text));
		
		btn_server_upload.setText(getString(R.string.upload_server_ok_button));
		
		textview50.setText(getString(R.string.upload_content_name_text));
		
		textview51.setText(getString(R.string.upload_content_description_text));
		
		textview52.setText(getString(R.string.upload_content_data_text));
		
		textview53.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview54.setText(getString(R.string.upload_content_link_to_banner_text));
		
		textview55.setText(getString(R.string.upload_content_link_to_discord_text));
		
		textview57.setText(getString(R.string.upload_content_link_to_monitoring_text));
		
		edittext_plugin_name.setHint(getString(R.string.upload_plugin_edittext_name));
		
		edittext_plugin_description.setHint(getString(R.string.upload_plugin_edittext_description));
		
		edittext_plugin_full_description.setHint(getString(R.string.upload_plugin_edittext_full_description));
		
		edittext_plugin_linktomoreinfo.setHint(getString(R.string.upload_plugin_edittext_more_info));
		
		btn_upload_plugin_file.setText(getString(R.string.choose_file_toast));
		
		btn_plugin_upload.setText(getString(R.string.upload_plugin_ok_button));
		
		textview42.setText(getString(R.string.upload_content_name_text));
		
		textview43.setText(getString(R.string.upload_content_description_text));
		
		textview47.setText(getString(R.string.upload_content_full_description_text));
		
		textview49.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview44.setText(getString(R.string.upload_content_apiversion_text));
		
		textview45.setText(getString(R.string.upload_content_link_to_more_info_text));
		
		textview46.setText(getString(R.string.upload_content_link_to_download_text));
	}
	
	
	public void _ru() {
		textview2.setText(getString(R.string.upload_content_description));
		
		text_upload_texture.setText(getString(R.string.texture_text));
		
		text_upload_mod.setText(getString(R.string.mod_text));
		
		text_upload_map.setText(getString(R.string.map_text));
		
		text_upload_server.setText(getString(R.string.server_text));
		
		text_upload_plugin.setText(getString(R.string.plugin_text));
		edittext_texture_name.setHint(getString(R.string.upload_texture_edittext_name));
		
		edittext_texture_description.setHint(getString(R.string.upload_texture_edittext_description));
		
		btn_upload_texture_file.setText(getString(R.string.choose_file_toast));
		
		btn_texture_upload.setText(getString(R.string.upload_texture_ok_button));
		
		textview8.setText(getString(R.string.upload_content_name_text));
		
		textview9.setText(getString(R.string.upload_content_description_text));
		
		textview10.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview11.setText(getString(R.string.upload_content_link_to_banner_text));
		
		textview12.setText(getString(R.string.upload_content_link_to_download_text));
		edittext_mod_name.setHint(getString(R.string.upload_mod_edittext_name));
		
		edittext_mod_description.setHint(getString(R.string.upload_mod_edittext_description));
		
		edittext_mod_moreinfo.setHint(getString(R.string.upload_content_more_info_text));
		
		btn_upload_mod_file.setText(getString(R.string.choose_file_toast));
		
		btn_mod_upload.setText(getString(R.string.upload_mod_ok_button));
		
		textview28.setText(getString(R.string.upload_content_name_text));
		
		textview29.setText(getString(R.string.upload_content_description_text));
		
		textview30.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview31.setText(getString(R.string.upload_content_more_info_text));
		
		textview32.setText(getString(R.string.upload_content_link_to_download_text));
		edittext_map_name.setHint(getString(R.string.upload_map_edittext_name));
		
		btn_upload_map_file.setText(getString(R.string.choose_file_toast));
		
		btn_map_upload.setText(getString(R.string.upload_map_ok_button));
		
		textview35.setText(getString(R.string.upload_content_name_text));
		
		textview37.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview38.setText(getString(R.string.upload_content_link_to_banner_text));
		
		textview39.setText(getString(R.string.upload_content_link_to_download_text));
		edittext_server_name.setHint(getString(R.string.upload_server_edittext_name));
		
		edittext_server_description.setHint(getString(R.string.upload_server_edittext_description));
		
		edittext_server_ip.setHint(getString(R.string.upload_server_edittext_ip));
		
		edittext_server_port.setHint(getString(R.string.upload_server_edittext_port));
		
		edittext_server_linktodiscord.setHint(getString(R.string.upload_server_edittext_link_to_discord));
		
		edittext_server_linktomonitoring.setHint(getString(R.string.upload_content_link_to_monitoring_text));
		
		btn_server_upload.setText(getString(R.string.upload_server_ok_button));
		
		textview50.setText(getString(R.string.upload_content_name_text));
		
		textview51.setText(getString(R.string.upload_content_description_text));
		
		textview52.setText(getString(R.string.upload_content_data_text));
		
		textview53.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview54.setText(getString(R.string.upload_content_link_to_banner_text));
		
		textview55.setText(getString(R.string.upload_content_link_to_discord_text));
		
		textview57.setText(getString(R.string.upload_content_link_to_monitoring_text));
		
		edittext_plugin_name.setHint(getString(R.string.upload_plugin_edittext_name));
		
		edittext_plugin_description.setHint(getString(R.string.upload_plugin_edittext_description));
		
		edittext_plugin_full_description.setHint(getString(R.string.upload_plugin_edittext_full_description));
		
		edittext_plugin_linktomoreinfo.setHint(getString(R.string.upload_plugin_edittext_more_info));
		
		btn_upload_plugin_file.setText(getString(R.string.choose_file_toast));
		
		btn_plugin_upload.setText(getString(R.string.upload_plugin_ok_button));
		
		textview42.setText(getString(R.string.upload_content_name_text));
		
		textview43.setText(getString(R.string.upload_content_description_text));
		
		textview47.setText(getString(R.string.upload_content_full_description_text));
		
		textview49.setText(getString(R.string.upload_content_mcpe_version_text));
		
		textview44.setText(getString(R.string.upload_content_apiversion_text));
		
		textview45.setText(getString(R.string.upload_content_link_to_more_info_text));
		
		textview46.setText(getString(R.string.upload_content_link_to_download_text));
	}
	
	
	public void _ui() {
		text_upload_texture.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)4, 0xFF4CAF50, 0xFF121212));
		edittext_texture_name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_texture_description.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_mod_name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_mod_description.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_mod_moreinfo.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_map_name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_server_name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_server_description.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_server_linktomonitoring.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_server_linktodiscord.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_server_ip.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_server_port.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_plugin_name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_plugin_description.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_plugin_full_description.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		edittext_plugin_linktomoreinfo.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xFF212121));
		_rippleRoundStroke(btn_texture_upload, "#4CAF50", "#A5D6A7", 40, 0, "#4CAF50");
		_rippleRoundStroke(btn_upload_texture_file, "#2962FF", "#2196F3", 40, 0, "#2962FF");
		_rippleRoundStroke(btn_mod_upload, "#4CAF50", "#A5D6A7", 40, 0, "#4CAF50");
		_rippleRoundStroke(btn_upload_mod_file, "#2962FF", "#2196F3", 40, 0, "#2962FF");
		_rippleRoundStroke(btn_map_upload, "#4CAF50", "#A5D6A7", 40, 0, "#4CAF50");
		_rippleRoundStroke(btn_upload_map_file, "#2962FF", "#2196F3", 40, 0, "#2962FF");
		_rippleRoundStroke(btn_server_upload, "#4CAF50", "#A5D6A7", 40, 0, "#4CAF50");
		_rippleRoundStroke(btn_plugin_upload, "#4CAF50", "#A5D6A7", 40, 0, "#4CAF50");
		_rippleRoundStroke(btn_upload_plugin_file, "#2962FF", "#2196F3", 40, 0, "#2962FF");
		linear32.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)0, (int)4, 0xFF424242, 0xFF121212));
		linear33.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)0, (int)4, 0xFF424242, 0xFF121212));
		linear34.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)0, (int)4, 0xFF424242, 0xFF121212));
	}
	
	
	public void _spinner(final Spinner _spinner, final ArrayList<String> _list, final String _txtColor, final String _backColor) {
		_spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, _list) {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView1 = (TextView) super.getView(position, convertView, parent);
				textView1.setTextColor(Color.parseColor(_txtColor));
				return textView1; }
			
			@Override
			public View getDropDownView(int position, View convertView, ViewGroup parent) {
				TextView textView1 = (TextView) super.getDropDownView(position, convertView, parent); textView1.setTextColor(Color.parseColor(_txtColor));
				
				textView1.setBackgroundColor(Color.parseColor(_backColor));
				
				return textView1; }
		});
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
	
	
	public void _class_StyleCallBack() {
	}
	private enum LayoutElement {
			//ID form [ resource > menu > style.xml ]
		    NONE(-1),
		    BOLD_BUTTON(R.id.bold),
		    ITALIC_BUTTON(R.id.italic),
		    UNDERLINE_BUTTON(R.id.underline),
			STRIKETHROUGH_BUTTON(R.id.strikethrough),
			TEXTCOLOR_BUTTON(R.id.textcolor),
			BACKGROUNDCOLOR_BUTTON(R.id.backgroundcolor),
			REGULAR_BUTTON(R.id.regular);
		
		    private static class _ {
			        static SparseArray<LayoutElement> elements = new SparseArray<LayoutElement>();
			    }
		
		    LayoutElement(int id) {
			        _.elements.put(id, this);
			    }
		
		    public static LayoutElement from(MenuItem view) {
			        return _.elements.get(view.getItemId(), NONE);
			    }
		
	}
	
}
