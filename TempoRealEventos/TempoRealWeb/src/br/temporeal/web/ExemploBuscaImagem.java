package br.temporeal.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Download the imagem de um servidor
 * 
 * @author ricardo
 * 
 */
public class ExemploBuscaImagem extends Activity implements OnClickListener, Runnable {
	private ProgressDialog dialog;
	protected static final String CATEGORIA = "livro";
//	protected static final String URL = "http://developer.android.com/assets/images/home/sdk-large.png";
	protected static final String URL = "http://chart.apis.google.com/chart?chs=150x150&cht=qr&chl=www.livroandroid.com.br&choe=UTF-8";
//	protected static final String URL = "http://chart.apis.google.com/chart?cht=bvs&chs=200x125&chd=t:10,50,60,80,40|50,60,100,40,20&chco=4d89f9,c6d9fd&chbh=20";
//	protected static final String URL = "http://chart.apis.google.com/chart?cht=lxy&chs=200x125&chd=t:0,30,60,70,90,95,100|20,30,40,50,60,70,80|10,30,40,45,52|100,90,40,20,10|-1|5,33,50,55,7&chco=3072F3,ff0000,00aaaa&chls=2,4,1&chm=s,FF0000,0,-1,5|s,0000ff,1,-1,5|s,00aa00,2,-1,5";
//	protected static final String URL = "http://chart.apis.google.com/chart?cht=p3&chd=s:Uf9a&chs=250x100&chl=January|February|March|April";
		
	protected static final int ATUALIZAR_TELA = 1;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.busca_imagem);
		Button b = (Button) findViewById(R.id.btDownload);
		b.setOnClickListener(this);
	}

	public void onClick(View v) {
		dialog = ProgressDialog.show(this, "Exemplo", "Buscando imagem, por favor aguarde...", false, true);
		// Faz download em outra Thread
		new Thread(this).start();
	}

	public void run() {
		try {
			final Bitmap imagem = download(URL);

			runOnUiThread(new Runnable() {
				public void run() {
					ImageView imgView = (ImageView) findViewById(R.id.imagem);
					imgView.setImageBitmap(imagem);
				}
			});

		} catch (Throwable e) {
			Log.i(CATEGORIA, e.getMessage(), e);
		} finally {
			dialog.dismiss();
		}
	}

	// Faz o download da Imagem
	protected Bitmap download(String url) throws IOException {

		URL u = new URL(url);
		InputStream in = u.openStream();
		Bitmap img = BitmapFactory.decodeStream(in);
		in.close();

		return img;
	}
}
