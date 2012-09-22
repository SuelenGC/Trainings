package br.temporeal.camera;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class TelaFoto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.foto);

		ImageView img = (ImageView) findViewById(R.id.imagem);

		//Bitmap bitmap = (Bitmap) getIntent().getExtras().get("data");
		//img.setImageBitmap(bitmap);
		
		Uri uri = getIntent().getData();
		img.setImageURI(uri);
	}
}