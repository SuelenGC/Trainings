package br.temporeal.sms;

import br.livroandroid.utils.Notificacao;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * BroadcastReceiver que exibe um alerta Toast quando uma mensagem é recebida
 * 
 */
public class ReceberMensagem extends BroadcastReceiver {
	private static final String CATEGORIA = "temporeal";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(CATEGORIA, ">" + intent.getAction());

		String mensagem = intent.getStringExtra("msg");

		String texto = "ReceberSms: recebeu mensagem -> " + mensagem;

		Log.i(CATEGORIA, texto);
		
		if(mensagem != null) {
//			Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();

			Notificacao n = new Notificacao(context,R.drawable.smile,new Intent("FAZER_ALGO"));

			n.criarNotificacao("Mensagem recebida", "Teste de Notificação", "Por favor leia esta notificação");
		}
	}
}
