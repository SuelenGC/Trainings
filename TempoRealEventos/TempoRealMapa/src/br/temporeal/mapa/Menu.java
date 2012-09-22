package br.temporeal.mapa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de MapView
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {

	private static final String[] ops = new String[] {
		"Mapa Cristo","Mapa GPS","Sair"};

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
				startActivity(new Intent(this,ExemploMapa.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploMapaGPS.class));
				break;

			default:
				finish();
		}
	}
}