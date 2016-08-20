package br.com.radio.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import br.com.radio.R;
import br.com.radio.fragment.course.PresentationPagerFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplication());
        AppEventsLogger.activateApp(getApplication(),getString(R.string.facebook_app_id));
        setContentView(R.layout.activity_main);
        replaceTutorialFragment();
    }

    public void replaceTutorialFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new PresentationPagerFragment());
        fragmentTransaction.commit();
    }


}
