package com.hello;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class TelaDetalhesDocumento extends Activity implements OnClickListener{
	
	private WebView webview;
	private Button btHttp;
	private Button btWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tela_detalhes_documento);
		
		//recupera o documento que veio como parametro
		Documento doc = (Documento) getIntent().getSerializableExtra("documento");
		
		Log.i("temporeal", "Documento que chegou como parametro: " + doc.nome);
		
		TextView tNomeDoc = (TextView) findViewById(R.id.tNomeDocumento);
		TextView tDescDoc = (TextView) findViewById(R.id.tDescDocumento);
		
		tNomeDoc.setText(doc.nome);
		tDescDoc.setText(doc.descricao);	
		
		webview = (WebView) findViewById(R.id.webview);
		
		btWebView = (Button) findViewById(R.id.btWebView);
		btHttp = (Button) findViewById(R.id.btHttp);
		
		btWebView.setOnClickListener(this);
		btHttp.setOnClickListener(this);
	}

	public void onClick(View view) {
		//se for http abre o browser na url especifica
		if(view == btHttp){
			
			//abre o browser
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(intent);
			
		//se for webview carrega url dentro da aplicacao
		}else if(view == btWebView){
			
			//carrega no webview, a url
			webview.loadUrl("http://www.temporeal.com.br");
			
		}
	}
}
