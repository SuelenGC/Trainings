package br.temporeal.musica;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Simples teste do DemoView
 * 
 * @author ricardo
 * 
 */
public class ExemploVideoView extends Activity {
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		
		VideoView v = new VideoView(this);
		setContentView(v);

		v.setVideoPath("/sdcard/last_mohicans.3gp");
		v.setMediaController(new MediaController(this));
		v.requestFocus();
	}
}
