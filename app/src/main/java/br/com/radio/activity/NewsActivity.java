package br.com.radio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import br.com.radio.R;
import br.com.radio.util.Constantes;
import br.com.radio.util.CustomWebViewClient;

public class NewsActivity extends AppCompatActivity {
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        url = getIntent().getStringExtra(Constantes.TAG_URL);
        loadUI();
        loadWebView( url );
    }

    private void loadUI(){

        Toolbar tb = (Toolbar) findViewById(R.id.tb_main);
        tb.setTitle("Notícias");
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void loadWebView(String url){
        WebView webView = (WebView) findViewById(R.id.teste);
        webView.getSettings().setJavaScriptEnabled( true );
        webView.setHorizontalScrollBarEnabled(true);
        webView.loadUrl( url );
        webView.setWebViewClient( new CustomWebViewClient() );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }else if (id == R.id.shared){
            shared();
        }
        return true;
    }

    private void shared(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(sharingIntent, ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }


}
