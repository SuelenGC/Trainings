package br.temporeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MenuInicial extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t = new TextView(this);
        t.setText("Olá Android");
        setContentView(t);
        
        Intent intent = getIntent();
        if(intent != null){
        	String login = intent.getStringExtra("login");
        	t.setText("Olá: " + login);
        }
    }
}