package br.temporeal.musica;

import javax.crypto.ExemptionMechanismSpi;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de Mp3 e vídeo
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] {
		"Tocar Mp3","Reproduzir Video","Sair"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,layout, ops);
		this.setListAdapter(adaptador);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,TempoRealMusica.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploVideoView.class));
				break;

			default:
				finish();
		}
	}
}