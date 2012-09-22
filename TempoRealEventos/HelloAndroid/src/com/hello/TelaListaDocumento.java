package com.hello;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TelaListaDocumento extends Activity implements OnItemClickListener, Runnable{
	
	private ProgressDialog progressDialog;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tela_lista_documento);
		
		//recupera da intent o parametro passado na tela MainActivity
		String loginParametro = getIntent().getStringExtra("login");
		
		//recupera o TextView
		TextView tBoasVindas = (TextView) findViewById(R.id.labelBoasVindas);
		
		//atribui o text do nosso labelBoasVindas
		tBoasVindas.setText("Bem vindo, " + loginParametro);
		
		//recupera o listview do arquivo de layout
		listView = (ListView) findViewById(R.id.listview);
		listView.setOnItemClickListener(this);
		
		progressDialog = ProgressDialog.show(this, "Informacao", "Buscando documentos. Aguarde...");
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		//pega o documento do adapter no item clicado
		Documento documento = (Documento) adapter.getAdapter().getItem(pos);	
		
		//motra log/alerta do documento selecionado
		Log.i("temporeal", "Documento selecionado: " + documento.nome + " / " + documento.descricao);
		Toast toast = Toast.makeText(this, "Documento selecionado: " + documento.nome + " / " + documento.descricao, Toast.LENGTH_LONG);
		toast.show();
		
		//abre a tela de detalhes
		Intent intent = new Intent(this, TelaDetalhesDocumento.class);
		
		Bundle param = new Bundle();
		param.putSerializable("documento", documento);
		
		intent.putExtras(param);
		startActivity(intent);
	}
	
	public void run() {
		//crio a lista de documentos
		List<Documento> list = null;

		try {
			
			Thread.sleep(5000);
			
			list = Documento.getDocumentosWeb();
			
			//crio o adapter para o listview
			final DocumentoAdapter docAdapter = new DocumentoAdapter(this, list);
			
			//vai atualizar o listview que esta rodando na Thread principal da activity, mesmo nesse momento a execucao
			//estar em uma thread diferente Thread t = new Thread(this);
			runOnUiThread(new Runnable() {
				
				public void run() {
					//adiciono o adapter no listview
					listView.setAdapter(docAdapter);					
				}
			});
		
		} catch (IOException e) {
			Log.e("temporeal", e.getMessage());

		//independente de dar certo ou nao a busca, eu fecho o ProgressDialog
		}catch (InterruptedException e) {
			Log.e("temporeal", e.getMessage());

		//independente de dar certo ou nao a busca, eu fecho o ProgressDialog
		}finally{
			progressDialog.dismiss();
		}
		
	}
}
