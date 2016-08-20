package br.com.radio.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.aboutlibraries.LibsBuilder;

import br.com.radio.R;
import br.com.radio.adapter.ViewPagerAdapter;
import br.com.radio.entity.Person;
import br.com.radio.util.UpdateTab;

public class CreditsFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTableLayout;
    public CreditsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        loadUI(view);
        return view;
    }

    private void loadUI(View view){
        setupViewPager(view);
        setupTabLayout(view);
    }

    private void setupTabLayout(View view){
        mTableLayout = (TabLayout) view.findViewById(R.id.tab_credits);
        mTableLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mTableLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.accent));
        mTableLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //adicionando a tab alunos
        adapter.addFragment(PersonFragment.newInstance(getDataPerson(true)),getString(R.string.alunos));
        //adicionando a tab diego
        adapter.addFragment(PersonFragment.newInstance(getDataPerson(false)),getString(R.string.professor));
        //adicionando a tab bibliotecas
        adapter.addFragment(new LibsBuilder().supportFragment(),getString(R.string.bibliotecas));

        mViewPager.setAdapter(adapter);

        listenerPage();
    }

    private void listenerPage(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float v, final int i2) {
            }

            @Override
            public void onPageSelected(final int position) {
                if(!(mViewPager.getAdapter() instanceof  ViewPagerAdapter))
                    return;
                if(!(((ViewPagerAdapter) mViewPager.getAdapter()).getItem(position) instanceof  UpdateTab))
                    return;

                UpdateTab fragment = (UpdateTab) ((ViewPagerAdapter) mViewPager.getAdapter()).getItem(position);
                if (fragment != null) {
                    fragment.update();
                }
            }

            @Override
            public void onPageScrollStateChanged(final int position) {

            }
        });
    }

    private Person[] getDataPerson(boolean students ){
        if(students){
            Person   person[] =  {
                                 new Person("David Cabral" , getString(R.string.aluno),R.drawable.david),
                                 new Person("Ana Catarina" , getString(R.string.aluno),R.drawable.ana),
                                 new Person("Gilmar" , getString(R.string.aluno),R.drawable.gilmar),
                                 new Person("Luana Cristina" , getString(R.string.aluno),R.drawable.luana),
                                 new Person("Pedro Jorge" , getString(R.string.aluno),R.drawable.pedro)
                               };
            return person;
        }

        Person   person[] =  {
                                new Person("Diego souza" , getString(R.string.professor),R.drawable.diego)
                             };
        return person;


    }



}
