package br.temporeal.tela;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import br.temporeal.domain.Carro;
import br.temporeal.domain.RepositorioCarro;
import br.temporeal.domain.RepositorioCarroScript;

/**
 * Activity que demonstra o cadastro de carros:
 * 
 * - ListActivity: para listar os carros - RepositorioCarro para salvar os dados
 * no banco - Carro
 * 
 * @author rlecheta
 * 
 */
public class TelaListaCarros extends ListActivity {
	public static RepositorioCarro repositorio;

	private List<Carro> carros;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		repositorio = new RepositorioCarroScript(this);
		atualizarLista();
	}

	protected void atualizarLista() {
		// Pega a lista de carros e exibe na tela
		carros = repositorio.listarCarros();

		// Adaptador de lista customizado para cada linha de um carro
		setListAdapter(new CarroListAdapter(this, carros));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int posicao, long id) {
		super.onListItemClick(l, v, posicao, id);
		Carro carro = carros.get(posicao);
		Intent it = new Intent(this, TelaFormularioCarro.class);
		it.putExtra(Carro._ID, carro.id);
		startActivityForResult(it, 1);
	}

	@Override
	protected void onActivityResult(int codigo, int codigoRetorno, Intent it) {
		super.onActivityResult(codigo, codigoRetorno, it);

		// Quando a activity EditarCarro retornar, seja se foi para adicionar vamos atualizar a lista
		if (codigoRetorno == RESULT_OK) {
			// atualiza a lista na tela
			atualizarLista();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// Fecha o banco
		repositorio.fechar();
	}
}