package br.temporeal.mapa;

import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ExemploMapa extends MapActivity {
	

	private static final String CATEGORIA = "temporeal";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(CATEGORIA,"ExemploMapa.onCreate");
		setContentView(R.layout.mapview);

		MapView mapView = (MapView) findViewById(R.id.mapa);
		MapController controlador = mapView.getController();
		
		// Faz zoom
		controlador.setZoom(16);

		/** Coorenadas GPS do Cristo Rendendor - Rio de Janeiro **/
		double latitude = -22.951285 * 1E6; /* 1000000 */
		double longitude = -43.211262 * 1E6; /* 1000000 */
		// Cria o Ponto com a Latitude e Longitude
		GeoPoint point = new GeoPoint((int) latitude, (int) longitude);
		// Centraliza o mapa no Cristo
		controlador.setCenter(point);

		mapView.setBuiltInZoomControls(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Satelite");
		menu.add(0, 1, 1, "Rua");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		MapView mapa = (MapView) findViewById(R.id.mapa);
		// Clicou no menu
		switch (item.getItemId()) {
		case 0:
			// Satelite
			mapa.setSatellite(true);
			mapa.setStreetView(false);
			mapa.setTraffic(false);
			break;
		case 1:
			// Rua
			mapa.setStreetView(true);
			mapa.setSatellite(false);
			mapa.setTraffic(false);
			break;
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MapView mapView = (MapView) findViewById(R.id.mapa);
		if (keyCode == KeyEvent.KEYCODE_S) {
			// Satelite
			mapView.setSatellite(true);
			mapView.setStreetView(false);
			mapView.setTraffic(false);
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_R) {
			// Rua
			mapView.setStreetView(true);
			mapView.setSatellite(false);
			mapView.setTraffic(false);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

	//se a  aplicacao vai tracar rota de um ponto X a um ponto Y, devemos retornar TRUE
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}