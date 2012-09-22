package br.temporeal;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Classe para buscar os contatos do Content Provider nativo "content://contacts/people"
 * 
 * Utiliza a API do Android 2.x
 * 
 * @author ricardo
 *
 */
public class Agenda2 {

	/**
	 * Lê a agenda de contatos e retorna uma lista de Amigos
	 * 
	 * @param context
	 * @return
	 */
	public List<Amigo> buscaAmigosAgenda(Context context) {
		Uri uri = ContactsContract.Contacts.CONTENT_URI;

		List<Amigo> amigos = new ArrayList<Amigo>();

		// Recupera o Cursor para percorrer a lista de contatos.
		Cursor c = context.getContentResolver().query(uri, null, null, null, null);

		try {
			while (c.moveToNext()) {

				// Recupera o Nome e Telefone
				long id 	= c.getLong(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
				String nome = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

				boolean temFone = "1".equals(c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
				
				String fone = temFone ? readFone(context, id): "";
				
				Amigo a = new Amigo(id,nome,fone);
				
				amigos.add(a);
			}
		} finally {
			// Fecha o Cursor
			c.close();
		}

		return amigos;
	}

	private String readFone(Context context,long id) {

		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
						+ id, null, null);

		try {
			while (cursor.moveToNext()) {
				String fone = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				
				// pode ter mais de 1.. mas isto é uma brincadeira
				return fone;
			}
		} finally {
			cursor.close();
		}
		return "";
	}
}
