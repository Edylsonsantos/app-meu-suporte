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
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

public class AdicionarActivity extends AppCompatActivity {
	
	private double sald = 0;
	private HashMap<String, Object> add = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listAdd = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview2;
	private EditText edittext1;
	private TextView textview3;
	
	private SharedPreferences saldo;
	private SharedPreferences fundo;
	private SharedPreferences bonus;
	private Calendar c = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.adicionar);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		textview2 = findViewById(R.id.textview2);
		edittext1 = findViewById(R.id.edittext1);
		textview3 = findViewById(R.id.textview3);
		saldo = getSharedPreferences("sldo", Activity.MODE_PRIVATE);
		fundo = getSharedPreferences("fundo", Activity.MODE_PRIVATE);
		bonus = getSharedPreferences("bonus", Activity.MODE_PRIVATE);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				sald = Double.parseDouble(fundo.getString("saldo", "")) + Double.parseDouble(edittext1.getText().toString());
				fundo.edit().putString("saldo", String.valueOf((long)(sald))).commit();
				add = new HashMap<>();
				add.put("titulo", "Recebeu ".concat(String.valueOf((long)(Double.parseDouble(edittext1.getText().toString()))).concat("MZN, adicionado na sua conta, saldo actual ".concat(String.valueOf((long)(Double.parseDouble(String.valueOf((long)(sald))))).concat("MZN")))));
				add.put("hora", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getTime()));
				add.put("status", "Concluída");
				add.put("tipo", "Deposito");
				listAdd = new Gson().fromJson(bonus.getString("bonus", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listAdd.add(add);
				bonus.edit().putString("bonus", new Gson().toJson(listAdd)).commit();
				_ToastSuccess("Fundo adicionado com sucesso!");
				finish();
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				edittext1.addTextChangedListener(new TextWatcher() {
					    private boolean isUpdating = false;
					
					    @Override
					    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
						    }
					
					    @Override
					    public void onTextChanged(CharSequence s, int start, int before, int count) {
						    }
					
					    @Override
					    public void afterTextChanged(Editable s) {
						        if (isUpdating) {
							            return;  // Evita loops se o campo já está sendo atualizado
							        }
						
						        isUpdating = true;  // Marca como verdadeiro para evitar loops
						
						        try {
							            String input = s.toString().replaceAll("[,.]", "");  // Remove vírgulas e pontos
							
							            if (!input.isEmpty()) {
								                double parsed = Double.parseDouble(input);  // Converte para número
								                String formatted = String.format(Locale.US, "%.2f", parsed / 100);  // Formata para 2 casas decimais
								
								                // Apenas atualiza o texto se ele tiver mudado
								                if (!s.toString().equals(formatted)) {
									                    edittext1.setText(formatted);  // Define o texto formatado
									                    edittext1.setSelection(formatted.length());  // Move o cursor para o final
									                }
								            }
							        } catch (NumberFormatException e) {
							            // Trate exceções de formato numérico
							        }
						
						        isUpdating = false;  // Libera a flag de atualização
						    }
				});
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		textview2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFFFFFF, 0xFFFF5722));
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
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