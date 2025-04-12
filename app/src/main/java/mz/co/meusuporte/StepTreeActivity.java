package mz.co.meusuporte;

import android.animation.*;
import android.animation.ObjectAnimator;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import es.dmoral.toasty.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class StepTreeActivity extends AppCompatActivity {
	
	private HashMap<String, Object> add = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview1;
	private ListView listview1;
	private LinearLayout linear3;
	private TextView textview3;
	private LinearLayout linear4;
	
	private ObjectAnimator a = new ObjectAnimator();
	private Intent i = new Intent();
	private SharedPreferences pais;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.step_tree);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		textview1 = findViewById(R.id.textview1);
		listview1 = findViewById(R.id.listview1);
		linear3 = findViewById(R.id.linear3);
		textview3 = findViewById(R.id.textview3);
		linear4 = findViewById(R.id.linear4);
		pais = getSharedPreferences("pais", Activity.MODE_PRIVATE);
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), StepTreeActivity.class);
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)200, 0xFFFF5722));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_black.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		listview1.setHorizontalScrollBarEnabled(false);
		listview1.setVerticalScrollBarEnabled(false);
		
		add = new HashMap<>();
		add.put("pais", "Nampula");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Manica");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Sofala");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Tete");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Zambézia ");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Maputo");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Cabo Delgado");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Inhambane");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Niassa");
		listmap.add(add);
		add = new HashMap<>();
		add.put("pais", "Gaza");
		listmap.add(add);
		final Handler handler = new Handler(); // Torna o handler final
		//final TextView textView = findViewById(R.id.textView); // Substitua pelo seu TextView
		final String textToSpell = "Qual é a sua província?"; // O texto que você quer soletrar
		final int delay = 50; // Intervalo entre as letras (em milissegundos)
		
		final int[] index = {0}; // Indexador para a posição da letra atual
		
		// Runnable para adicionar uma letra de cada vez
		handler.postDelayed(new Runnable() {
			    @Override
			    public void run() {
				        // Adiciona a próxima letra ao TextView
				        textview1.setText(textToSpell.substring(0, index[0] + 1));
				        index[0]++;
				
				        // Continua enquanto ainda houver letras
				        if (index[0] < textToSpell.length()) {
					            handler.postDelayed(this, delay); // Chama de novo após o delay
					        }
				    }
		}, delay);
		
		textview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFFFFFFF));
		listview1.setAdapter(new Listview1Adapter(listmap));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
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
				_view = _inflater.inflate(R.layout.step_2, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final RadioButton radiobutton1 = _view.findViewById(R.id.radiobutton1);
			
			linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)10, 0xFFF5F5F5));
			textview1.setText(listmap.get((int)_position).get("pais").toString());
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), 0);
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					pais.edit().putString("provincia", listmap.get((int)_position).get("pais").toString()).commit();
					i.setAction(Intent.ACTION_VIEW);
					i.setClass(getApplicationContext(), DstepFiveActivity.class);
					startActivity(i);
				}
			});
			
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