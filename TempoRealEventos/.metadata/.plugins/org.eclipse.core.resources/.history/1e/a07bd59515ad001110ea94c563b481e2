package br.temporeal;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;

/**
 * Classe para buscar os contatos do Content Provider nativo "content://contacts/people"
 * 
 * Utiliza a API do Android 1.x
 * 
 * @author ricardo
 *
 */
public class Agenda {

	/**
	 * Lê a agenda de contatos e retorna uma lista de Amigos
	 * 
	 * @param context
	 * @return
	 */
	public List<Amigo> buscaAmigosAgenda(Context context) {
		Uri uri = android.provider.Contacts.Phones.CONTENT_URI;

		List<Amigo> amigos = new ArrayList<Amigo>();

		// Recupera o Cursor para percorrer a lista de contatos.
		Cursor c = context.getContentResolver().query(uri, null, null, null, null);

		try {
			while (c.moveToNext()) {

				// Recupera o Nome e Telefone
				long id 	= c.getLong(c.getColumnIndexOrThrow(Contacts.People._ID));
				String nome = c.getString(c.getColumnIndexOrThrow(Contacts.People.NAME));
				String fone = c.getString(c.getColumnIndexOrThrow(Contacts.People.NUMBER));

				if(fone != null){
					fone = fone.replaceAll("-", "");
				}

				Amigo a = new Amigo(id,nome,fone);
				
				amigos.add(a);
			}
		} finally {
			// Fecha o Cursor
			c.close();
		}

		return amigos;
	}
}
