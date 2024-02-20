package com.eqozqq.colbux;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.Uri;
import android.os.*;
import android.view.*;
import android.view.View;
import android.widget.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.google.firebase.auth.GetTokenResult;
import com.google.android.gms.tasks.*;


public class AuthActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> authMap = new HashMap<>();
	private double num = 0;
	private String imagePath = "";
	private String imageName = "";
	private HashMap<String, Object> usernamesMap = new HashMap<>();
	private String c1 = "";
	private String c2 = "";
	private String c3 = "";
	private String c4 = "";
	private String c5 = "";
	private String c6 = "";
	private String captcha_answer = "";
	
	private ArrayList<HashMap<String, Object>> authmap = new ArrayList<>();
	
	private LinearLayout welcome_bg;
	private LinearLayout register_bg;
	private LinearLayout login_bg;
	private LinearLayout reset_password_bg;
	private LinearLayout captcha_bg;
	private LinearLayout welcome_top;
	private LinearLayout linear_snackbar;
	private TextView textview_welcome;
	private TextView btn_getstarted;
	private TextView btn_ihaveanaccount;
	private LinearLayout welcome_bottom;
	private LinearLayout btn_discord;
	private LinearLayout linear4;
	private TextView textview_join_our_discord;
	private ImageView image_discord;
	private LinearLayout registration_top;
	private TextView textview_registration;
	private TextView textview12;
	private TextView avatarLinkText;
	private LinearLayout linear5;
	private TextView textview13;
	private EditText edittext_register_email;
	private TextView textview14;
	private EditText edittext_register_password;
	private TextView btn_register;
	private TextView textview11;
	private ImageView btn_registration_goback;
	private EditText edittext_register_username;
	private LinearLayout login_top;
	private TextView textview_login;
	private TextView textview15;
	private EditText edittext_login_email;
	private TextView textview16;
	private EditText edittext_login_password;
	private TextView btn_login;
	private TextView btn_login_forgotpassword;
	private ImageView btn_login_goback;
	private LinearLayout reset_password_top;
	private TextView textview_reset_password;
	private TextView textview17;
	private EditText edittext_reset_password;
	private TextView btn_reset_password;
	private ImageView btn_reset_password_goback;
	private LinearLayout captcha_top;
	private TextView textview18;
	private LinearLayout linear6;
	private EditText edittext_captcha;
	private TextView btn_verify_captcha;
	private ImageView btn_captcha_goback;
	private TextView textview19;
	private TextView captcha_num1;
	private TextView captcha_num2;
	private TextView captcha_num3;
	private TextView captcha_num4;
	private TextView captcha_num5;
	private TextView captcha_num6;
	private TextView s1;
	private ImageView btn_refresh_captcha;
	
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
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private Intent i = new Intent();
	private AlertDialog.Builder d;
	private Calendar c = Calendar.getInstance();
	private TimerTask t;
	private SharedPreferences files;
	private SharedPreferences user;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.auth);
		initialize(_savedInstanceState);

		//TOOLBAR
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w = AuthActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF2A2831);
		}


		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		welcome_bg = findViewById(R.id.welcome_bg);
		register_bg = findViewById(R.id.register_bg);
		login_bg = findViewById(R.id.login_bg);
		reset_password_bg = findViewById(R.id.reset_password_bg);
		captcha_bg = findViewById(R.id.captcha_bg);
		welcome_top = findViewById(R.id.welcome_top);
		linear_snackbar = findViewById(R.id.linear_snackbar);
		textview_welcome = findViewById(R.id.textview_welcome);
		btn_getstarted = findViewById(R.id.btn_getstarted);
		btn_ihaveanaccount = findViewById(R.id.btn_ihaveanaccount);
		welcome_bottom = findViewById(R.id.welcome_bottom);
		btn_discord = findViewById(R.id.btn_discord);
		linear4 = findViewById(R.id.linear4);
		textview_join_our_discord = findViewById(R.id.textview_join_our_discord);
		image_discord = findViewById(R.id.image_discord);
		registration_top = findViewById(R.id.registration_top);
		textview_registration = findViewById(R.id.textview_registration);
		textview12 = findViewById(R.id.textview12);
		avatarLinkText = findViewById(R.id.avatarLinkText);
		linear5 = findViewById(R.id.linear5);
		textview13 = findViewById(R.id.textview13);
		edittext_register_email = findViewById(R.id.edittext_register_email);
		textview14 = findViewById(R.id.textview14);
		edittext_register_password = findViewById(R.id.edittext_register_password);
		btn_register = findViewById(R.id.btn_register);
		textview11 = findViewById(R.id.textview11);
		btn_registration_goback = findViewById(R.id.btn_registration_goback);
		edittext_register_username = findViewById(R.id.edittext_register_username);
		login_top = findViewById(R.id.login_top);
		textview_login = findViewById(R.id.textview_login);
		textview15 = findViewById(R.id.textview15);
		edittext_login_email = findViewById(R.id.edittext_login_email);
		textview16 = findViewById(R.id.textview16);
		edittext_login_password = findViewById(R.id.edittext_login_password);
		btn_login = findViewById(R.id.btn_login);
		btn_login_forgotpassword = findViewById(R.id.btn_login_forgotpassword);
		btn_login_goback = findViewById(R.id.btn_login_goback);
		reset_password_top = findViewById(R.id.reset_password_top);
		textview_reset_password = findViewById(R.id.textview_reset_password);
		textview17 = findViewById(R.id.textview17);
		edittext_reset_password = findViewById(R.id.edittext_reset_password);
		btn_reset_password = findViewById(R.id.btn_reset_password);
		btn_reset_password_goback = findViewById(R.id.btn_reset_password_goback);
		captcha_top = findViewById(R.id.captcha_top);
		textview18 = findViewById(R.id.textview18);
		linear6 = findViewById(R.id.linear6);
		edittext_captcha = findViewById(R.id.edittext_captcha);
		btn_verify_captcha = findViewById(R.id.btn_verify_captcha);
		btn_captcha_goback = findViewById(R.id.btn_captcha_goback);
		textview19 = findViewById(R.id.textview19);
		captcha_num1 = findViewById(R.id.captcha_num1);
		captcha_num2 = findViewById(R.id.captcha_num2);
		captcha_num3 = findViewById(R.id.captcha_num3);
		captcha_num4 = findViewById(R.id.captcha_num4);
		captcha_num5 = findViewById(R.id.captcha_num5);
		captcha_num6 = findViewById(R.id.captcha_num6);
		s1 = findViewById(R.id.s1);
		btn_refresh_captcha = findViewById(R.id.btn_refresh_captcha);
		auth = FirebaseAuth.getInstance();
		d = new AlertDialog.Builder(this);
		files = getSharedPreferences("files", Activity.MODE_PRIVATE);
		user = getSharedPreferences("user", Activity.MODE_PRIVATE);
		
		btn_getstarted.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.GONE);
				register_bg.setVisibility(View.VISIBLE);
				login_bg.setVisibility(View.GONE);
			}
		});
		
		btn_ihaveanaccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.GONE);
				register_bg.setVisibility(View.GONE);
				login_bg.setVisibility(View.VISIBLE);
			}
		});
		
		btn_discord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setData(Uri.parse("https://discord.gg/4fv4RrTav4"));
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		
		btn_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (_valid_inputs()) {
					welcome_bg.setVisibility(View.GONE);
					register_bg.setVisibility(View.GONE);
					login_bg.setVisibility(View.GONE);
					reset_password_bg.setVisibility(View.GONE);
					captcha_bg.setVisibility(View.VISIBLE);
					_Captcha();
				}
			}
		});
		
		btn_registration_goback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.VISIBLE);
				register_bg.setVisibility(View.GONE);
				login_bg.setVisibility(View.GONE);
				reset_password_bg.setVisibility(View.GONE);
				captcha_bg.setVisibility(View.GONE);
			}
		});
		
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (_login_valid_inputs()) {
					auth.signInWithEmailAndPassword(edittext_login_email.getText().toString().trim(), edittext_login_password.getText().toString().trim()).addOnCompleteListener(AuthActivity.this, _auth_sign_in_listener);
				}
			}
		});
		
		btn_login_forgotpassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.GONE);
				register_bg.setVisibility(View.GONE);
				login_bg.setVisibility(View.GONE);
				reset_password_bg.setVisibility(View.VISIBLE);
			}
		});
		
		btn_login_goback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.VISIBLE);
				register_bg.setVisibility(View.GONE);
				login_bg.setVisibility(View.GONE);
				reset_password_bg.setVisibility(View.GONE);
				captcha_bg.setVisibility(View.GONE);
			}
		});
		
		btn_reset_password.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_reset_password.getText().toString().trim().equals("")) {
					((EditText)edittext_reset_password).setError("Please fill in your email");
				}
				else {
					auth.sendPasswordResetEmail(edittext_reset_password.getText().toString()).addOnCompleteListener(_auth_reset_password_listener);
				}
			}
		});
		
		btn_reset_password_goback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.GONE);
				register_bg.setVisibility(View.GONE);
				login_bg.setVisibility(View.VISIBLE);
				reset_password_bg.setVisibility(View.GONE);
				captcha_bg.setVisibility(View.GONE);
			}
		});
		
		btn_verify_captcha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext_captcha.getText().toString().equals(captcha_answer)) {
					auth.createUserWithEmailAndPassword(edittext_register_email.getText().toString().trim(), edittext_register_password.getText().toString().trim()).addOnCompleteListener(AuthActivity.this, _auth_create_user_listener);
				}
				else {
					_Toast("Captcha is incorrectly");
					edittext_captcha.setText("");
					_Captcha();
				}
			}
		});
		
		btn_captcha_goback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				welcome_bg.setVisibility(View.GONE);
				register_bg.setVisibility(View.VISIBLE);
				login_bg.setVisibility(View.GONE);
				reset_password_bg.setVisibility(View.GONE);
			}
		});
		
		btn_refresh_captcha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Captcha();
			}
		});
		
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
				c = Calendar.getInstance();
				if (_success) {
					authMap = new HashMap<>();
					authMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					authMap.put("creation_timestamp", (double)(c.getTimeInMillis()));
					authMap.put("username", edittext_register_username.getText().toString());
					authMap.put("email", edittext_register_email.getText().toString());
					users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(authMap);
					_send_verification_mail();
					user.edit().putString("username", edittext_register_username.getText().toString()).commit();
					welcome_bg.setVisibility(View.GONE);
					register_bg.setVisibility(View.GONE);
					login_bg.setVisibility(View.VISIBLE);
					edittext_login_email.setText(edittext_register_email.getText().toString());
					edittext_login_password.setText(edittext_register_password.getText().toString());
					FirebaseAuth.getInstance().signOut();
				}
				else {
					_Toast(_errorMessage);
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					if (_isEmailVerified()) {
						_save_auth_token();
						i.setClass(getApplicationContext(), HomeActivity.class);
						i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						i.putExtra("username", user.getString("username", ""));
						finish();
					}
					else {
						_send_verification_mail();
						FirebaseAuth.getInstance().signOut();
					}
				}
				else {
					_Toast(_errorMessage);
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					_Toast("Password reset email sent successfully.");
				}
				else {
					_Toast("Failed to send email, please try again.");
				}
			}
		};
	}
	
	private void initializeLogic() {
		if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
			i.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(i);
			finish();
		}
		_rippleRoundStroke(btn_getstarted, "#2962FF", "#2196F3", 20, 0, "#FFFFFF");
		_rippleRoundStroke(btn_ihaveanaccount, "#333333", "#444444", 20, 0, "#FFFFFF");
		_rippleRoundStroke(btn_register, "#2962FF", "#2196F3", 20, 0, "#FFFFFF");
		_rippleRoundStroke(btn_login, "#43A047", "#2196F3", 20, 0, "#FFFFFF");
		_rippleRoundStroke(btn_reset_password, "#2962FF", "#2196F3", 20, 0, "#FFFFFF");
		_rippleRoundStroke(btn_verify_captcha, "#2962FF", "#2196F3", 20, 0, "#FFFFFF");
		_rippleRoundStroke(btn_discord, "#242424", "#333333", 40, 0, "#444444");
		linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)40, 0xFF333333));
		edittext_register_username.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		edittext_register_email.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		edittext_register_password.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		edittext_login_email.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		edittext_login_password.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		edittext_reset_password.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		edittext_captcha.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF333333));
		_circleRipple("#333333", btn_registration_goback);
		_circleRipple("#333333", btn_login_goback);
		_circleRipple("#333333", btn_reset_password_goback);
		_circleRipple("#333333", btn_captcha_goback);

		image_discord.setImageResource(R.drawable.ic_discord);
	}
	

	public boolean _valid_inputs() {
		if (edittext_register_username.getText().toString().trim().equals("")) {
			((EditText)edittext_register_username).setError("Please fill the blank");
			return (false);
		}
		if (edittext_register_email.getText().toString().trim().equals("")) {
			((EditText)edittext_register_email).setError("Please fill the blank");
			return (false);
		}
		if (edittext_register_password.getText().toString().trim().equals("")) {
			((EditText)edittext_register_password).setError("Please fill the blank");
			return (false);
		}
		return (true);
	}
	
	
	public boolean _login_valid_inputs() {
		if (edittext_login_email.getText().toString().trim().equals("")) {
			((EditText)edittext_login_email).setError("Please fill the blank");
			return (false);
		}
		if (edittext_login_password.getText().toString().trim().equals("")) {
			((EditText)edittext_login_password).setError("Please fill the blank");
			return (false);
		}
		return (true);
	}
	
	
	public void _send_verification_mail() {
		FirebaseAuth auth = FirebaseAuth.getInstance();
		com.google.firebase.auth.FirebaseUser user = 
		auth.getCurrentUser();
		
		user.sendEmailVerification().addOnCompleteListener
		(new 
		OnCompleteListener<Void>()
		{ @Override
			public void onComplete(Task task)
			{ if (task.isSuccessful()) {
					
					d = new AlertDialog.Builder(AuthActivity.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
					d.setTitle("User verification");
					d.setMessage("A confirmation email will be sent to your address.\nIf you can't find the message, check Spam.");
					d.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					d.create().show();
				} else {
					    _Toast("Unable to send verification email");
				}
			}});
		
	}
	
	
	public boolean _isEmailVerified() {
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		return user.isEmailVerified();
		
	}
	
	
	public void _save_auth_token() {
		FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
		
		user.getIdToken(true).addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
			  @Override
			  public void onSuccess(GetTokenResult result) {
				    String auth_token = result.getToken();
				files.edit().putString("auth_token", auth_token).commit();
			}
		});
	}
	
	
	public void _snackbar(final String _text, final String _dismiss) {
		com.google.android.material.snackbar.Snackbar sb = com.google.android.material.snackbar.Snackbar.make(linear_snackbar, _text, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).setDuration(8000);
		
		sb.setAction(_dismiss, new View.OnClickListener(){
			@Override
			public void onClick(View v){
				
				//paste the code that you want to perform
				
			}
		});
		
		sb.show();
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
	
	
	public void _circleRipple(final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
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
	
	
	public void _Captcha() {
		captcha_num1.setText(String.valueOf((long)(ColbuxUtil.getRandom((int)(0), (int)(9)))));
		captcha_num2.setText(String.valueOf((long)(ColbuxUtil.getRandom((int)(0), (int)(9)))));
		captcha_num3.setText(String.valueOf((long)(ColbuxUtil.getRandom((int)(0), (int)(9)))));
		captcha_num4.setText(String.valueOf((long)(ColbuxUtil.getRandom((int)(0), (int)(9)))));
		captcha_num5.setText(String.valueOf((long)(ColbuxUtil.getRandom((int)(0), (int)(9)))));
		captcha_num6.setText(String.valueOf((long)(ColbuxUtil.getRandom((int)(0), (int)(9)))));
		c1 = captcha_num1.getText().toString();
		c2 = captcha_num2.getText().toString();
		c3 = captcha_num3.getText().toString();
		c4 = captcha_num4.getText().toString();
		c5 = captcha_num5.getText().toString();
		c6 = captcha_num6.getText().toString();
		captcha_answer = c1.concat(c2.concat(c3.concat(c4.concat(c5).concat(c6))));
	}
	
}
