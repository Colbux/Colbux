package com.eqozqq.colbux;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SettingsActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private ScrollView vscroll1;
	private LinearLayout linear_webview;
	private LinearLayout linear1;
	private LinearLayout linear_language;
	private LinearLayout linear_profile;
	private LinearLayout linear_privacy_policy;
	private LinearLayout linear_terms_conditions;
	private LinearLayout linear_version;
	private ImageView imageview1;
	private TextView textview1;
	private TextView textview8;
	private ImageView imageview2;
	private TextView textview3;
	private TextView textview9;
	private ImageView imageview3;
	private TextView textview7;
	private ImageView imageview4;
	private TextView textview10;
	private TextView textview5;
	private TextView textview6;
	private LinearLayout linear_webview_toolbar;
	private ProgressBar progressbar1;
	private WebView webview1;
	private ImageView btn_close_webview;
	
	private Intent i = new Intent();
	private SharedPreferences language;
	private SharedPreferences user;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);

		//TOOLBAR
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w = SettingsActivity.this.getWindow();
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
		linear_webview = findViewById(R.id.linear_webview);
		linear1 = findViewById(R.id.linear1);
		linear_language = findViewById(R.id.linear_language);
		linear_profile = findViewById(R.id.linear_profile);
		linear_privacy_policy = findViewById(R.id.linear_privacy_policy);
		linear_terms_conditions = findViewById(R.id.linear_terms_conditions);
		linear_version = findViewById(R.id.linear_version);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		textview8 = findViewById(R.id.textview8);
		imageview2 = findViewById(R.id.imageview2);
		textview3 = findViewById(R.id.textview3);
		textview9 = findViewById(R.id.textview9);
		imageview3 = findViewById(R.id.imageview3);
		textview7 = findViewById(R.id.textview7);
		imageview4 = findViewById(R.id.imageview4);
		textview10 = findViewById(R.id.textview10);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		linear_webview_toolbar = findViewById(R.id.linear_webview_toolbar);
		progressbar1 = findViewById(R.id.progressbar1);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		btn_close_webview = findViewById(R.id.btn_close_webview);
		language = getSharedPreferences("language", Activity.MODE_PRIVATE);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		
		linear_language.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_changeLanguageDialog();
			}
		});
		
		linear_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), ProfileActivity.class);
				startActivity(i);
			}
		});
		
		linear_privacy_policy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.setVisibility(View.GONE);
				linear_webview.setVisibility(View.VISIBLE);
				webview1.loadUrl("file:///android_asset/privacy-policy.html");
			}
		});
		
		linear_terms_conditions.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.setVisibility(View.GONE);
				linear_webview.setVisibility(View.VISIBLE);
				webview1.loadUrl("file:///android_asset/terms-conditions.html");
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				progressbar1.setVisibility(View.GONE);
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
				vscroll1.setVisibility(View.VISIBLE);
				linear_webview.setVisibility(View.GONE);
				webview1.stopLoading();
			}
		});
	}
	
	private void initializeLogic() {
		getSupportActionBar().setTitle(getString(R.string.dialog_settings_settings_text));
		try {
					android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo( "com.eqozqq.colbux", 0 );
			textview6.setText(pinfo.versionName);}
		catch (Exception e){ _Toast(e.toString()); }
		textview8.setText(language.getString("language", ""));
		textview9.setText(user.getString("username", ""));
		webview1.getSettings().setBuiltInZoomControls(true);
		webview1.getSettings().setDisplayZoomControls(true);
		_webviewprogress(webview1, progressbar1);
		_rippleRoundStroke(linear_language, "#121212", "#212121", 0, 0, "#FFFFFF");
		_rippleRoundStroke(linear_profile, "#121212", "#212121", 0, 0, "#FFFFFF");
		_rippleRoundStroke(linear_version, "#121212", "#212121", 0, 0, "#FFFFFF");
		_rippleRoundStroke(linear_privacy_policy, "#121212", "#212121", 0, 0, "#FFFFFF");
		_rippleRoundStroke(linear_terms_conditions, "#121212", "#212121", 0, 0, "#FFFFFF");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (language.getString("language", "").equals("")) {
			setLocale("ru");
			_ru();
			textview8.setText("Русский");
		}
		if (language.getString("language", "").equals("en")) {
			setLocale("en");
			_en();
			textview8.setText("English");
		}
	}
	
	@Override
	public void onBackPressed() {
		if (webview1.canGoBack()) {
			webview1.goBack();
			super.onBackPressed();
		}
		else {
			if (linear_webview.getVisibility() == View.VISIBLE) {
				vscroll1.setVisibility(View.VISIBLE);
				linear_webview.setVisibility(View.GONE);
			}
			else {
				finish();
			}
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
		
		linear.setElevation(5);
		text.setTextColor(0xFFFFFFFF);

		Toast toast = new Toast(getApplicationContext()); 
		
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastL);
		toast.show();
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
	
	
	public void _changeLanguageDialog() {
		final AlertDialog change_language_dialog= new AlertDialog.Builder(SettingsActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.language,null); 
		change_language_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		change_language_dialog.setView(inflate);
		TextView textview_title = (TextView) inflate.findViewById(R.id.textview_title);
		
		TextView textview_language_russian = (TextView) inflate.findViewById(R.id.textview_language_russian);
		
		TextView textview_language_english = (TextView) inflate.findViewById(R.id.textview_language_english);
		
		//ScrollView vscroll1 = (ScrollView) inflate.findViewById(R.id.vscroll1); vscroll1 вообще нету в language.xml, что оно тут делает?
		
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
	
	
	public void _ru() {
		textview1.setText(getString(R.string.settings_activity_change_language_text));
		textview3.setText(getString(R.string.settings_activity_profile_text));
		textview5.setText(getString(R.string.settings_activity_version_text));
		textview7.setText(getString(R.string.settings_activity_privacy_policy_text));
		textview10.setText(getString(R.string.settings_activity_terms_conditions_text));
	}
	
	
	public void _en() {
		textview1.setText(getString(R.string.settings_activity_change_language_text));
		textview3.setText(getString(R.string.settings_activity_profile_text));
		textview5.setText(getString(R.string.settings_activity_version_text));
		textview7.setText(getString(R.string.settings_activity_privacy_policy_text));
		textview10.setText(getString(R.string.settings_activity_terms_conditions_text));
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
	
	
	public void _webviewprogress(final WebView _Webview, final ProgressBar _Progressbar) {
		_Progressbar.setFitsSystemWindows(true);
		
		_Progressbar.setScrollBarStyle(android.widget.ProgressBar.SCROLLBARS_OUTSIDE_INSET);
		
		_Webview.setWebChromeClient(new WebChromeClient() {
			
			@Override public void onProgressChanged(WebView view, int newProgress) {
				
				_Progressbar.setProgress(newProgress);
			}
		});
		
	}
	
}
