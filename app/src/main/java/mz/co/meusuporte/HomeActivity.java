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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.dmoral.toasty.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class HomeActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> map = new HashMap<>();
	private double exit = 0;
	private double sair = 0;
	
	private ArrayList<HashMap<String, Object>> listOp = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listAdd = new ArrayList<>();
	
	private LinearLayout linear6;
	private LinearLayout linear17;
	private LinearLayout bg;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear18;
	private GridView gridview1;
	private LinearLayout linear13;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private TextView textview5;
	private LinearLayout linear14;
	private TextView textview9;
	private ImageView imageview5;
	private TextView text_saldo;
	private TextView textview6;
	private TextView txt_ocultaSaldo;
	private LinearLayout linear19;
	private TextView textview7;
	private TextView textview8;
	
	private Intent i = new Intent();
	private SharedPreferences name;
	private SharedPreferences fundo;
	private TimerTask time;
	private SharedPreferences bonus;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear6 = findViewById(R.id.linear6);
		linear17 = findViewById(R.id.linear17);
		bg = findViewById(R.id.bg);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear18 = findViewById(R.id.linear18);
		gridview1 = findViewById(R.id.gridview1);
		linear13 = findViewById(R.id.linear13);
		linear15 = findViewById(R.id.linear15);
		linear16 = findViewById(R.id.linear16);
		textview5 = findViewById(R.id.textview5);
		linear14 = findViewById(R.id.linear14);
		textview9 = findViewById(R.id.textview9);
		imageview5 = findViewById(R.id.imageview5);
		text_saldo = findViewById(R.id.text_saldo);
		textview6 = findViewById(R.id.textview6);
		txt_ocultaSaldo = findViewById(R.id.txt_ocultaSaldo);
		linear19 = findViewById(R.id.linear19);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		name = getSharedPreferences("name", Activity.MODE_PRIVATE);
		fundo = getSharedPreferences("fundo", Activity.MODE_PRIVATE);
		bonus = getSharedPreferences("bonus", Activity.MODE_PRIVATE);
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), NotifyActivity.class);
				startActivity(i);
			}
		});
		
		txt_ocultaSaldo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), AdicionarActivity.class);
				startActivity(i);
			}
		});
		
		textview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_ToastSuccess("Click para pagar INSS de forma directa e extrair a factura online no mesmo aplicativo!");
			}
		});
	}
	
	private void initializeLogic() {
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		text_saldo.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		txt_ocultaSaldo.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		_data();
		time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_auth();
						listAdd = new Gson().fromJson(bonus.getString("bonus", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						textview9.setText(String.valueOf((long)(listAdd.size())));
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(time, (int)(0), (int)(1000));
		gridview1.setAdapter(new Gridview1Adapter(listOp));
		gridview1.setNumColumns((int)2);
		gridview1.setColumnWidth((int)8);
		gridview1.setVerticalSpacing((int)15);
		gridview1.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		txt_ocultaSaldo.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFFFFFF, 0xFFFF5722));
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		textview8.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		textview7.setText("pague inss fácil e seguro".toUpperCase());
		linear18.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFF5722, 0xFFFFFFFF));
		textview8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFFF5722));
		textview9.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
	}
	
	public void _data() {
		map = new HashMap<>();
		map.put("title", "Transferir dinheiro");
		map.put("event", "transferir");
		map.put("img", "t");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Taxa municipal");
		map.put("event", "municipio");
		map.put("img", "m");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Empréstimo");
		map.put("event", "emprestimo");
		map.put("img", "e");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Pagamentos");
		map.put("event", "seguro");
		map.put("img", "e");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Históricos");
		map.put("event", "historico");
		map.put("img", "c");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Minha conta");
		map.put("event", "conta");
		map.put("img", "e");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Contacto");
		map.put("event", "contacto");
		map.put("img", "c");
		listOp.add(map);
		map = new HashMap<>();
		map.put("title", "Suporte");
		map.put("event", "suporte");
		map.put("img", "e");
		listOp.add(map);
		gridview1.setAdapter(new Gridview1Adapter(listOp));
		gridview1.setNumColumns((int)2);
		gridview1.setColumnWidth((int)1);
		gridview1.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gridview1.setVerticalSpacing((int)20);
		gridview1.setHorizontalScrollBarEnabled(false);
		gridview1.setVerticalScrollBarEnabled(false);
		
	}
	
	
	public void _auth() {
		if (fundo.contains("saldo")) {
			if (fundo.getString("saldo", "").equals("")) {
				text_saldo.setText("00.00");
			}
			else {
				double saldoAtual = Double.parseDouble(fundo.getString("saldo", ""));
				
				// Formatando o saldo com duas casas decimais
				String saldoFormatado = String.format("%.2f", saldoAtual);
				
				// Agora a variável
				text_saldo.setText(saldoFormatado);
			}
		}
		else {
			text_saldo.setText("00.00");
		}
		if (name.contains("name")) {
			if (name.getString("name", "").equals("")) {
				textview5.setText("OLÁ, USER-022929");
			}
			else {
				textview5.setText("Olá, ".concat(name.getString("name", "")).toUpperCase());
			}
		}
		else {
			
		}
	}
	
	
	public void _ToastSuccess(final String _message) {
		Toasty.success(getApplicationContext(), _message, Toast.LENGTH_SHORT, true).show();
	}
	
	
	public void _messageWarning(final String _message) {
		Toasty.warning(getApplicationContext(), _message, Toast.LENGTH_SHORT, true).show();
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.custom, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(listOp.get((int)_position).get("title").toString());
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (listOp.get((int)_position).get("event").toString().equals("transferir")) {
						i.setAction(Intent.ACTION_VIEW);
						i.setClass(getApplicationContext(), SandMoneyActivity.class);
						startActivity(i);
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("conta")) {
						i.setAction(Intent.ACTION_VIEW);
						i.setClass(getApplicationContext(), MinhContaActivity.class);
						startActivity(i);
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("emprestimo")) {
						_ToastSuccess("Click para pagina de empréstimo da instituição para o usuário.");
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("contacto")) {
						_ToastSuccess("Click para página de contacto");
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("suporte")) {
						_ToastSuccess("Click para pagina de suporte");
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("municipio")) {
						_ToastSuccess("Click para pagina para pagar taxa do município diariamente directo em vez do dinheiro físico.");
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("seguro")) {
						i.setAction(Intent.ACTION_VIEW);
						i.setClass(getApplicationContext(), PagamentoActivity.class);
						startActivity(i);
					}
					else {
						
					}
					if (listOp.get((int)_position).get("event").toString().equals("historico")) {
						i.setAction(Intent.ACTION_VIEW);
						i.setClass(getApplicationContext(), TrancacoesActivity.class);
						startActivity(i);
					}
					else {
						
					}
				}
			});
			linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)2, 0xFFFF5722, 0xFFFF5722));
			
			return _view;
		}
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