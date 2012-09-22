package br.temporeal.sms;

import br.livroandroid.utils.Notificacao;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaFazerAlgo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView t = new TextView(this);
		t.setText("Ok, podemos fazer algo aqui.");
		setContentView(t);

		Notificacao.cancelar(this);
	}
}