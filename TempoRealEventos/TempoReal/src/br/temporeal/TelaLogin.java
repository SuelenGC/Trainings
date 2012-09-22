package br.temporeal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.temporeal.tela.TelaListaCarros;

public class TelaLogin extends Activity implements OnClickListener {
    private static final String TAG = "temporeal";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button b = (Button) findViewById(R.id.login);
        b.setOnClickListener(this);
        
        
    }
	
	public static void mailTo(Context context, String email) {
//		if(StringUtils.isNotEmpty(email)) {
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");//message/rfc822
			intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});  
			context.startActivity(intent);
//		}
	}

	@Override
	public void onClick(View view) {
		TextView tLogin = (TextView) findViewById(R.id.campoLogin);
		TextView tSenha = (TextView) findViewById(R.id.campoSenha);
	
		String login = tLogin.getText().toString();
		String senha = tSenha.getText().toString();
	
		if("temporeal".equals(login) && "123".equals(senha)){
			Log.i(TAG, "Login realizado com sucesso");
			
//			Intent intent = new Intent(this,MenuInicial.class);
			Intent intent = new Intent(this,TelaListaCarros.class);
			intent.putExtra("login", login);
			startActivity(intent);
		} else {
			Log.e(TAG, "Erro de login, tente novamente.");

			Toast.makeText(this, "Login incorreto!", Toast.LENGTH_SHORT).show();

			finish();
		}
	}
}