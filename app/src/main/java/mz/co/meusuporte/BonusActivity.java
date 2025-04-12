package mz.co.meusuporte;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import es.dmoral.toasty.*;
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class BonusActivity extends AppCompatActivity {
	
	private HashMap<String, Object> add = new HashMap<>();
	private HashMap<String, Object> send = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listAdd = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listSend = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	
	private Intent i = new Intent();
	private SharedPreferences fundo;
	private SharedPreferences bonus;
	private Calendar c = Calendar.getInstance();
	private SharedPreferences t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.bonus);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		fundo = getSharedPreferences("fundo", Activity.MODE_PRIVATE);
		bonus = getSharedPreferences("bonus", Activity.MODE_PRIVATE);
		t = getSharedPreferences("t", Activity.MODE_PRIVATE);
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), HomeActivity.class);
				fundo.edit().putString("saldo", String.valueOf((long)(Double.parseDouble("1000.00")))).commit();
				add = new HashMap<>();
				add.put("titulo", "Parabéns, recebeu um subsídio 1,000.00MT de iniciante do Instituto de Industria e Comércio");
				add.put("hora", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getTime()));
				add.put("status", "Concluída");
				add.put("tipo", "Bônus");
				listAdd.add(add);
				bonus.edit().putString("bonus", new Gson().toJson(listAdd)).commit();
				send = new HashMap<>();
				send.put("conta", "Recebido bônus de boas vinda do Instituto de Industria e Comércio");
				send.put("valor", String.valueOf((long)(Double.parseDouble("1000"))));
				send.put("conteudo", "");
				send.put("hora", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(c.getTime()));
				send.put("status", "Concluída");
				send.put("tipo", "Bônus");
				send.put("id", "ID: ".concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(1000), (int)(1000000))))));
				listSend.add(send);
				t.edit().putString("t", new Gson().toJson(listSend)).commit();
				_ToastSuccess("Bônus recebido com sucesso!");
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		textview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFFFFFF, 0xFFFF5722));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_black.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
	}
	
	public void _ToastSuccess(final String _message) {
		Toasty.success(getApplicationContext(), _message, Toast.LENGTH_SHORT, true).show();
	}
	
	
	public void _messageError(final String _message) {
		Toasty.error(getApplicationContext(), _message, Toast.LENGTH_SHORT, true).show();
	}
	
	
	public void _messageWarning(final String _message) {
		Toasty.warning(getApplicationContext(), _message, Toast.LENGTH_SHORT, true).show();
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}