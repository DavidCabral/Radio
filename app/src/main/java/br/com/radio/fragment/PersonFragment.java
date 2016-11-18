package br.com.radio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.radio.R;
import br.com.radio.adapter.PersonAdapter;
import br.com.radio.entity.Person;
import br.com.radio.util.UpdateTab;

public class PersonFragment extends Fragment implements UpdateTab{
    private static final String ARG_PARAM = "mPerson";
    private Person[] mPerson;

    public PersonFragment() {
    }
    public static PersonFragment newInstance(Person[] mPerson) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putParcelableArray(ARG_PARAM, mPerson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPerson = (Person[]) getArguments().getParcelableArray(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_person, container, false);
        loadUI(view);
        return view;
    }

    private void loadUI(View view){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_person);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PersonAdapter personAdapter = new PersonAdapter(mPerson , getContext());
        recyclerView.setAdapter(personAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void update() {

    }

}
