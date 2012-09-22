package br.temporeal.mapa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

/**
 * Simples Overlay que desenha a imagem fornecida na coordenada
 * 
 * @author ricardo
 * 
 */
public class ImagemOverlay extends Overlay {

	private Paint paint = new Paint();

	// recurso da imagem (R.drawable.?)
	private final int imagemId;

	protected GeoPoint coordenada;

	public ImagemOverlay(GeoPoint coordenada, int resId) {
		this.coordenada = coordenada;
		this.imagemId = resId;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
	
		if (coordenada != null) {
			
//			GeoPoint geopoint = mapView.getProjection().fromPixels(100, 100);
	
			// Converte as coordenadas para pixels
			Point p = mapView.getProjection().toPixels(coordenada, null);
	
			Bitmap bitmap = BitmapFactory.decodeResource(mapView.getResources(), this.imagemId);
	
			// Desenha a imagem na coordenada x/y.
			int x = getX(p, bitmap);
			int y = getY(p, bitmap);
	
			canvas.drawBitmap(bitmap, x, y, paint);
		}
	}

	protected int getY(Point p, Bitmap bitmap) {
		return p.y - bitmap.getHeight() / 2;
	}

	protected int getX(Point p, Bitmap bitmap) {
		return p.x - bitmap.getWidth() / 2;
	}

	@Override
	public boolean onTap(GeoPoint geoPoint, MapView mapView) {
		Log.i("temporeal", "onTap: " + geoPoint);
		if (coordenada == null) {
			return false;
		}
		Point ponto = mapView.getProjection().toPixels(this.coordenada, null);

		Bitmap bitmap = BitmapFactory.decodeResource(mapView.getResources(), this.imagemId);

		// Cria o retângulo imaginário ao redor da imagem
		RectF recf = new RectF(ponto.x - 5 - bitmap.getWidth(), ponto.y - 5 - bitmap.getHeight(), ponto.x + 5 + bitmap.getWidth(), ponto.y + 5 + bitmap.getHeight());

		// Converte para ponto em pixels
		Point newPoint = mapView.getProjection().toPixels(geoPoint, null);

		// Verifica se o ponto está contido no retângulo
		boolean ok = recf.contains(newPoint.x, newPoint.y);
		if (ok) {
			clicou(mapView, geoPoint);
			return true;
		}

		return super.onTap(geoPoint, mapView);
	}

	/**
	 * Trata o clique no Overlay
	 * 
	 * @param mapView
	 * @param geoPoint
	 */
	protected void clicou(MapView mapView, GeoPoint geoPoint) {
		Toast.makeText(mapView.getContext(), "Clicou sobre o Overlay: " + geoPoint, Toast.LENGTH_SHORT).show();
	}

	public void setCoordenada(GeoPoint coordenada) {
		this.coordenada = coordenada;
	}

	public GeoPoint getCoordenada() {
		return coordenada;
	}

	public int getImagemId() {
		return imagemId;
	}
}
