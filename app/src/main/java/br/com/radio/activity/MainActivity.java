package br.com.radio.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import br.com.radio.R;
import br.com.radio.fragment.CreditsFragment;
import br.com.radio.fragment.NewsFragment;
import br.com.radio.fragment.RadioFragment;
import br.com.radio.util.Constantes;
import br.com.radio.util.OnFragmentInteractionListener;
import br.com.radio.util.RadioStatus;
import co.mobiwise.library.radio.RadioListener;
import co.mobiwise.library.radio.RadioManager;

public class MainActivity extends AppCompatActivity implements RadioListener , OnFragmentInteractionListener {
    private Drawer result = null;
    private Toolbar tb;
    private RadioManager mRadioManager = RadioManager.with(this);

    @Override
    protected void onResume() {
        super.onResume();
        mRadioManager.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRadioManager.disconnect();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUI(savedInstanceState);
        loadRadio();
    }

    private void loadUI(Bundle savedInstanceState) {
        tb = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(tb);
        createDrawer(savedInstanceState);
        loadFragment(RadioFragment.newInstance());
        result.setSelection(1);
    }



    private void loadRadio() {
        mRadioManager.registerListener(this);
        mRadioManager.setLogging(false);
    }

    @Override
    public void playOrPause() {
        if (!mRadioManager.isPlaying())
            mRadioManager.startRadio(Constantes.RADIO_URL[0]);
        else
            mRadioManager.stopRadio();
    }

    @Override
    public boolean isPlaying() {
        return mRadioManager.isPlaying();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.rl_fragment_container, fragment);
        ft.commit();
    }

    private void createDrawer(Bundle savedInstanceState) {
        String[] menu = getResources().getStringArray(R.array.menu);
        String[] news = getResources().getStringArray(R.array.news);

        result = new DrawerBuilder(this)
                .withActivity(this)
                .withToolbar(tb)
                .withHeader(R.layout.header)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(menu[0]).withIdentifier(1).withIcon(FontAwesome.Icon.faw_legal),
                        new ExpandableDrawerItem().withName(menu[1]).withIcon(FontAwesome.Icon.faw_newspaper_o).withIdentifier(2).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName(news[0]).withLevel(2).withIcon(FontAwesome.Icon.faw_play).withIdentifier(2000),
                                new SecondaryDrawerItem().withName(news[1]).withLevel(2).withIcon(FontAwesome.Icon.faw_play).withIdentifier(2001),
                                new SecondaryDrawerItem().withName(news[2]).withLevel(2).withIcon(FontAwesome.Icon.faw_adjust).withIdentifier(2002),
                                new SecondaryDrawerItem().withName(news[3]).withLevel(2).withIcon(FontAwesome.Icon.faw_play).withIdentifier(2003)
                        ),
                        new PrimaryDrawerItem().withName(menu[2]).withIcon(FontAwesome.Icon.faw_car).withIdentifier(2),
                        new PrimaryDrawerItem().withName(menu[3]).withIcon(FontAwesome.Icon.faw_archive).withIdentifier(3),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(menu[4]).withIcon(FontAwesome.Icon.faw_close).withIdentifier(4)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        int id = (int) drawerItem.getIdentifier();

                        switch (id){
                            case 1 :
                                loadFragment(RadioFragment.newInstance());
                                break;
                            case 2000 :
                                loadFragment(NewsFragment.newInstance());
                                break;
                            case 3 :
                                loadFragment(new CreditsFragment());
                                break;
                            case 4 :
                                close();

                        }

                        if (drawerItem instanceof Nameable) {
                            tb.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                        }

                        return false;
                    }


                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .build();

    }

    private void close(){
        mRadioManager.disconnect();
        mRadioManager.closeNotification();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public RadioManager getmRadioManager() {
        return mRadioManager;
    }

    private void useMethodFragmentRadio(RadioStatus status) {
        if (!(getSupportFragmentManager().findFragmentById(R.id.rl_fragment_container) instanceof RadioFragment))
            return;

        RadioFragment radioFragment = (RadioFragment) getSupportFragmentManager().findFragmentById(R.id.rl_fragment_container);

        switch (status) {
            case ON_STARTED:
                radioFragment.onRadioStarted();
                break;
            case ON_LOADING:
                radioFragment.onRadioLoading();
                break;
            case ON_STOPPED:
                radioFragment.onRadioStopped();
                break;
            case ON_ERROR:
                radioFragment.onRadioStopped();
                break;
        }
    }

    @Override
    public void onRadioLoading() {
        useMethodFragmentRadio(RadioStatus.ON_LOADING);
    }

    @Override
    public void onRadioConnected() {

    }

    @Override
    public void onRadioStarted() {
        useMethodFragmentRadio(RadioStatus.ON_STARTED);
    }

    @Override
    public void onRadioStopped() {
        useMethodFragmentRadio(RadioStatus.ON_STOPPED);
    }

    @Override
    public void onMetaDataReceived(String s, String s2) {
        Log.e("onMetaDataReceived", "" + s + " " + s2);
    }

    @Override
    public void onError() {
        useMethodFragmentRadio(RadioStatus.ON_ERROR);
    }



}
