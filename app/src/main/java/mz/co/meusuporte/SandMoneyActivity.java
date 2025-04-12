package mz.co.meusuporte;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

public class SandMoneyActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double number = 0;
	private double saldo = 0;
	private HashMap<String, Object> send = new HashMap<>();
	private HashMap<String, Object> add = new HashMap<>();
	private double resul = 0;
	
	private ArrayList<HashMap<String, Object>> sendList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listAdd = new ArrayList<>();
	
	private LinearLayout linear1;
	private TextView textview1;
	private EditText edittext1;
	private EditText edittext2;
	private EditText edittext3;
	private TextView textview2;
	
	private SharedPreferences fundo;
	private SharedPreferences t;
	private Calendar c = Calendar.getInstance();
	private SharedPreferences bonus;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.sand_money);
		initialize(_savedInstanceState);
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
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		edittext1 = findViewById(R.id.edittext1);
		edittext2 = findViewById(R.id.edittext2);
		edittext3 = findViewById(R.id.edittext3);
		textview2 = findViewById(R.id.textview2);
		fundo = getSharedPreferences("fundo", Activity.MODE_PRIVATE);
		t = getSharedPreferences("t", Activity.MODE_PRIVATE);
		bonus = getSharedPreferences("bonus", Activity.MODE_PRIVATE);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Verifica se o campo número da conta está vazio
				if (edittext1.getText().toString().equals("")) {
					    ((EditText) edittext1).setError("Número de conta inválido");
				} else if (edittext2.getText().toString().equals("")) {  // Verifica se o campo valor está vazio
					    ((EditText) edittext2).setError("Digite um valor");
				} else if (edittext3.getText().toString().equals("")) {  // Verifica se o campo de confirmação está vazio
					    ((EditText) edittext3).setError("Campo de confirmação não pode estar vazio");
				} else {
					    // Obtém o saldo atual
					    double saldoAtual = Double.parseDouble(fundo.getString("saldo", ""));
					    double valorTransferir = Double.parseDouble(edittext2.getText().toString());
					
					    // Verifica se o saldo é suficiente
					    if (saldoAtual < valorTransferir) {
						        ((EditText) edittext2).setError("Saldo insuficiente");
						    } else {
						        // Atualiza o saldo
						        double novoSaldo = saldoAtual - valorTransferir;
						        fundo.edit().putString("saldo", String.valueOf(novoSaldo)).commit();
						
						        // Aqui você pode adicionar qualquer lógica adicional após a transferência ser bem-sucedida
						
						
						send = new HashMap<>();
						send.put("conta", edittext1.getText().toString());
						send.put("valor", edittext2.getText().toString());
						send.put("conteudo", edittext3.getText().toString());
						send.put("hora", new SimpleDateFormat("dd-MM-yyyy HH:mm").format(c.getTime()));
						send.put("status", "Concluída");
						send.put("tipo", "Transferência");
						sendList = new Gson().fromJson(t.getString("t", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						sendList.add(send);
						t.edit().putString("t", new Gson().toJson(sendList)).commit();
						add = new HashMap<>();
						add.put("titulo", "Transferência realizada com sucesso");
						add.put("hora", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getTime()));
						add.put("status", "Concluída");
						add.put("tipo", "Bônus");
						listAdd = new Gson().fromJson(bonus.getString("bonus", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						listAdd.add(add);
						bonus.edit().putString("bonus", new Gson().toJson(listAdd)).commit();
						edittext1.setText("");
						edittext2.setText("");
						edittext3.setText("");
						_ToastSuccess("Transferência efectuada com sucesso!");
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("Transferência");
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), 0);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFF5F5F5));
		edittext2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFF5F5F5));
		edittext3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFF5F5F5));
		textview2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFF5722));
		edittext3.setSingleLine(true);
	}
	
	public void _ToastSuccess(final String _message) {
		Toasty.success(getApplicationContext(), _message, Toast.LENGTH_SHORT, true).show();
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