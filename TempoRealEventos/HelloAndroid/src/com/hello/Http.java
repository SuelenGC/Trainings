package com.hello;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import android.util.Log;

/**
 * 
 * @author Jonas Baggio
 * @create 18/05/2012
 */
public class Http {
	private static final boolean LOG_ON = true;
	private static final String TAG = "Http";
	/**
	 * 30 segundos
	 */
	public static int TIMEOUT = 30000;
	/**
	 * UTF-8
	 * ISO-8859-1
	 */
	public static String CHARSET = "UTF-8";

	public String doGet(String url, Map<String,String> params) throws IOException {
		return doGet(url,params,CHARSET);
	}

	public String doGet(String url, Map<String,String> params, String charset) throws IOException {
		String queryString = getQueryString(params);
		if(queryString != null){
			url += "?" + queryString;
		}
		String s = doGet(url,charset);
		return s;
	}
	
	public final String doGet(String url) throws IOException {
		return doGet(url,CHARSET);
	}
	
	public final String doGet(String url, String charset) throws IOException {
		if(LOG_ON) {
			Log.v(TAG, "----------------------------------------");
			Log.d(TAG, "Http.doGet: " + url);
		}

		// Cria a URL
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setReadTimeout(TIMEOUT);

		// Configura a requisição para get
		// connection.setRequestProperty("Request-Method","GET");
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(false);
		conn.connect();
		
		InputStream in = conn.getInputStream();

		// String arquivo = readBufferedString(sb, in);
		String s = toString(in,charset);

		if(LOG_ON) {
			Log.v(TAG,"[" + conn.getResponseCode() + " - "+conn.getResponseMessage()+"]");
			Log.v(TAG,"[" + s + "]");
			Log.v(TAG, "----------------------------------------");
		}
		
		try {
			conn.disconnect();
		} catch (Exception e) {
			Log.e(TAG, "HttpConnectionImpl conn.disconnect(): " + e.getMessage());
		}
		
		try {
			if(in != null) {
				in.close();
			}
		} catch (Exception e) {
			Log.e(TAG, "HttpConnectionImpl in.close(): " + e.getMessage());
		}

		return s;
	}

	public final byte[] doGetImagem(String url) throws IOException {
		if(LOG_ON) {
			Log.d(TAG, "Http.downloadImagem: " + url);
		}

		// Cria a URL
		URL u = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setReadTimeout(TIMEOUT);

		// Configura a requisição para get
		conn.setRequestProperty("Request-Method", "GET");
		conn.setDoInput(true);
		conn.setDoOutput(false);

		conn.connect();

		InputStream in = conn.getInputStream();

		// String arquivo = readBufferedString(sb, in);
		byte[] bytes = toBytes(in);

		if(LOG_ON) {
			Log.d(TAG, "imagem retornada com: " + bytes.length + " bytes");
		}

		conn.disconnect();

		return bytes;
	}
	
	public String doPost(String url, Map<String,String> params) throws IOException {
		return doPost(url, params, CHARSET);
	}
	
	public String doPost(String url, Map<String,String> params, String charset) throws IOException {
		String queryString = Http.getQueryString(params);
		String texto = doPost(url, queryString,charset);
		return texto;
	}

	// Faz um requsição POST na URL informada e retorna o texto
	// Os parâmetros são enviados ao servidor
	public String doPost(String url, String params, String charset) throws IOException {
		if(LOG_ON){
			Log.d(TAG, "Http.doPost: " + url + "?" + params);
		}
		URL u = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setReadTimeout(TIMEOUT);
		
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		conn.connect();

		if(params != null) {
			OutputStream out = conn.getOutputStream();
			byte[] bytes = params.getBytes(charset);
			out.write(bytes);
			out.flush();
			out.close();
		}

		InputStream in = conn.getInputStream();

		// le o texto
		String s = toString(in,charset);
		
		if(LOG_ON) {
			Log.v(TAG,"[" + conn.getResponseCode() + " - "+conn.getResponseMessage()+"]");
			Log.v(TAG,"[" + s + "]");
			Log.v(TAG, "----------------------------------------");
		}

		try {
			conn.disconnect();
		} catch (Exception e) {
			Log.e(TAG, "HttpConnectionImpl conn.disconnect(): " + e.getMessage());
		}

		try {
			if(in != null) {
				in.close();
			}
		} catch (Exception e) {
			Log.e(TAG, "HttpConnectionImpl in.close(): " + e.getMessage());
		}

		return s;
	}
	
	public static String getQueryString(Map<String,String> params) throws IOException {
		if (params == null || params.size() == 0) {
			return null;
		}
		String urlParams = null;
		for (String chave : params.keySet()) {
			Object objValor = params.get(chave);
			if (objValor != null) {
				String valor = objValor.toString();
				urlParams = urlParams == null ? "" : urlParams + "&";
				urlParams += chave + "=" + valor;
			}
		}
		return urlParams;
	
	}
	
	public static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				bos.write(buf, 0, len);
			}

			byte[] bytes = bos.toByteArray();
			return bytes;
		} finally {
			bos.close();
		}
	}

	// Faz a leitura do texto da InputStream retornada
	public static String toString(InputStream in) throws IOException {
		byte[] bytes = toBytes(in);
		String texto = new String(bytes);
		return texto;
	}

	public static String toString(InputStream in, String charset) throws IOException {
		byte[] bytes = toBytes(in);
		String texto = new String(bytes, charset);
		return texto;
	}
}
