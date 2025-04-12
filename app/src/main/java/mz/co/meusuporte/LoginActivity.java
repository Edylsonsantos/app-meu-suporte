package mz.co.meusuporte;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.widget.Button;
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

public class LoginActivity extends AppCompatActivity {
	
	private LinearLayout linear10;
	private LinearLayout linear15;
	private TextView textview1;
	private TextView textview2;
	private EditText edittext1;
	private Button button1;
	private LinearLayout linear14;
	private TextView textview4;
	private TextView textview5;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear10 = findViewById(R.id.linear10);
		linear15 = findViewById(R.id.linear15);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		edittext1 = findViewById(R.id.edittext1);
		button1 = findViewById(R.id.button1);
		linear14 = findViewById(R.id.linear14);
		textview4 = findViewById(R.id.textview4);
		textview5 = findViewById(R.id.textview5);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String celular = edittext1.getText().toString().trim(); // Remove espaços em branco
				
				// Verifica se o campo está vazio
				if (celular.isEmpty()) {
					    edittext1.setError("Número de celular inválido");
				} else {
					    // Verifica se o número tem 9 dígitos e começa com 82, 83, 84, 85, 86 ou 87
					    if (celular.length() == 9 && 
					        (celular.startsWith("82") || celular.startsWith("83") || 
					         celular.startsWith("84") || celular.startsWith("85") || 
					         celular.startsWith("86") || celular.startsWith("87"))) {
						        
						        // Se o número for válido, continue para a próxima atividade
						        Intent i = new Intent(Intent.ACTION_VIEW);
						        i.setClass(getApplicationContext(), PinActivity.class);
						        startActivity(i);
						    } else {
						        // Número de celular inválido
						        edittext1.setError("Número de celular inválido");
						    }
				}
				
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.setClass(getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
	}
	
	private void initializeLogic() {
		final Handler handler = new Handler(); // Torna o handler final
		//final TextView textView = findViewById(R.id.textView); // Substitua pelo seu TextView
		final String textToSpell = "Bem vindo, de volta!"; // O texto que você quer soletrar
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
		
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_extra_bold.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.ttf"), 0);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_medium.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), 0);
		edittext1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)2, 0xFFFF5722, 0xFFFFFFFF));
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFF5722));
		linear15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)200, 0xFFFF5722));
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