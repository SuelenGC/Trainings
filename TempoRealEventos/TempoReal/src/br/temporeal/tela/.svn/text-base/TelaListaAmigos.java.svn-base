package br.temporeal.tela;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.temporeal.Agenda2;
import br.temporeal.Amigo;

/**
 * Exemplo de ContentProvider nativo para ler os contatos da agenda
 * 
 * @author rlecheta
 * 
 */
public class TelaListaAmigos extends ListActivity {

	private List<Amigo> amigos;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Agenda2 rep = new Agenda2();

		amigos = rep.buscaAmigosAgenda(this);

		List<String> nomes = new ArrayList<String>();
		for (Amigo a : amigos) {
			nomes.add(a.getDesc());
		}

		// Utiliza o adaptador ArrayAdapter, para exibir o array de Strings na tela.
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nomes);

		setListAdapter(adaptador);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Amigo amigo = amigos.get(position);

		String nomeAmigo = amigo.nome;
		String foneAmigo = amigo.fone;

		Toast.makeText(this, "Amigo[" + nomeAmigo + "]: " + foneAmigo, Toast.LENGTH_SHORT).show();
	}
}