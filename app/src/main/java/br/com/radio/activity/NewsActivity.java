package br.com.radio.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Date;

import br.com.radio.R;
import br.com.radio.util.Constantes;

public class NewsActivity extends AppCompatActivity {
    private String url;
    private WebView webView;
    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        url = getIntent().getStringExtra(Constantes.TAG_URL);
        loadUI();
        webView.loadUrl(url);
    }

    private void loadUI(){
        webView = (WebView)findViewById(R.id.teste) ;
        tb = (Toolbar) findViewById(R.id.tb_main);
        tb.setTitle("Notícias");
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, "Rádio CamaraFM");
        share.putExtra(Intent.EXTRA_TEXT, url);

        startActivity(Intent.createChooser(share, "Rádio CamaraFM"));
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
