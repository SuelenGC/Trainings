package com.hello;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DocumentoAdapter extends BaseAdapter{
	
	private List<Documento> listaDocumento;
	private LayoutInflater inflater;

	//construtor que vai receber o contexto atual que chamou esse adapter, e a lista de documentos
	//para popular
	public DocumentoAdapter(Context context, List<Documento> list){
		this.listaDocumento = list;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return listaDocumento.size();
	}

	public Documento getItem(int position) {
		return listaDocumento.get(position);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int pos, View view, ViewGroup group) {
		//coloca o layout documento_item em memoria (isso sera chamado para cada item do listview)
		view = inflater.inflate(R.layout.documento_item, null);
		
		//do layout documento_item, recupero as views que precisamos
		TextView tNomeDocumento = (TextView) view.findViewById(R.id.tNomeDocumento);
		TextView tDescDocumento = (TextView) view.findViewById(R.id.tDescDocumento);
		
		//recupero o documento que esta na posicao da lista (int pos)
		Documento doc = getItem(pos);
		
		//populas as views com os valores desse documento
		tNomeDocumento.setText(doc.nome);
		tDescDocumento.setText(doc.descricao);
		
		//retorna a view atualizada (com valores)
		return view;
	}

}
