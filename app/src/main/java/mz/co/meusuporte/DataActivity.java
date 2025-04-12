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
import es.dmoral.toasty.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class DataActivity extends AppCompatActivity {
	
	private LinearLayout linear10;
	private LinearLayout linear11;
	private TextView textview1;
	private LinearLayout linear16;
	private LinearLayout linear14;
	private EditText edittext1;
	private TextView textview8;
	private EditText edittext2;
	private TextView textview9;
	private EditText edittext3;
	private LinearLayout linear15;
	private TextView textview7;
	private LinearLayout linear18;
	
	private SharedPreferences data;
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.data);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear10 = findViewById(R.id.linear10);
		linear11 = findViewById(R.id.linear11);
		textview1 = findViewById(R.id.textview1);
		linear16 = findViewById(R.id.linear16);
		linear14 = findViewById(R.id.linear14);
		edittext1 = findViewById(R.id.edittext1);
		textview8 = findViewById(R.id.textview8);
		edittext2 = findViewById(R.id.edittext2);
		textview9 = findViewById(R.id.textview9);
		edittext3 = findViewById(R.id.edittext3);
		linear15 = findViewById(R.id.linear15);
		textview7 = findViewById(R.id.textview7);
		linear18 = findViewById(R.id.linear18);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 2) {
					
					    int valor = Integer.parseInt(edittext1.getText().toString());
					    
					    if (valor <= 0 || valor > 31) {
						        ((EditText)edittext1).setError("Dia inválido");
						    } else {
						        edittext2.requestFocus();
						    }
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext2.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 2) { 
					    // Converte o texto em número
					    int mes = Integer.parseInt(edittext2.getText().toString());
					
					    // Verifica se o mês é inválido (fora do intervalo 1-12)
					    if (mes < 1 || mes > 12) {
						        ((EditText)edittext2).setError("Mês inválido");
						    } else {
						        // Se o mês for válido, muda o foco para o próximo campo
						        edittext3.requestFocus();
						    }
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext3.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() == 4) {
					
					    // Armazenar o valor do ano apenas uma vez
					    int ano = Integer.parseInt(edittext3.getText().toString());
					
					    // Verificar se o ano é maior que 1950 e menor que 2005
					    if (ano > 1950 && ano < 2005) {
						        // Se o ano for válido, definir foco no próximo campo e habilitar o textview6
						        edittext1.requestFocus();
						        data.edit().putString("data", edittext1.getText().toString().concat("-".concat(edittext2.getText().toString().concat("-".concat(edittext3.getText().toString()))))).commit();
						        i.setAction(Intent.ACTION_VIEW);
						        i.setClass(getApplicationContext(), StepTwoActivity.class);
						        startActivity(i);
						    } else {
						        // Se o ano for inválido, mostrar erro e limpar o campo
						        ((EditText)edittext3).setError("Ano inválido");
						        edittext3.setText("");
						    }
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), DataActivity.class);
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_black.ttf"), 0);
		final Handler handler = new Handler(); // Torna o handler final
		//final TextView textView = findViewById(R.id.textView); // Substitua pelo seu TextView
		final String textToSpell = "Digite sua data de nascimento, dia, mês e ano"; // O texto que você quer soletrar
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
		
		textview7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFFFFFFF));
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		edittext2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		edittext3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
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