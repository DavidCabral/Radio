package br.com.radio.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.radio.R;
import br.com.radio.util.OnFragmentInteractionListener;
import br.com.radio.util.PlayPauseDrawable;

public class RadioFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private PlayPauseDrawable mPlayPauseDrawable;
    private ImageView playStop;
    private TextView mTextViewControl;

    public RadioFragment() {
        // Required empty public constructor
    }

    public static RadioFragment newInstance() {
        RadioFragment fragment = new RadioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_radio, container, false);
        initializeUI(view);
        return view;
    }

    public void initializeUI(View v) {
        playStop = (ImageView) v.findViewById(R.id.iv_play_stop);
        mTextViewControl = (TextView) v.findViewById(R.id.tx_conectando);
        mPlayPauseDrawable = new PlayPauseDrawable(lineWidth(), Color.WHITE, Color.BLUE,300 , mListener.isPlaying());
        mTextViewControl.setVisibility(View.GONE);
        playStop.setImageDrawable(mPlayPauseDrawable);
        playStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.playOrPause();
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onRadioLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewControl.setVisibility(View.VISIBLE);
                mTextViewControl.setText(R.string.conect);
            }
        });
    }

    public void onRadioStarted() {
        Log.i("onRadioStarted","onRadioStarted");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewControl.setVisibility(View.GONE);
                mPlayPauseDrawable.toggle(true);
            }
        });
    }

    public void onRadioStopped() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewControl.setVisibility(View.GONE);
                mPlayPauseDrawable.toggle(false);
            }
        });
    }

    public void onRadioError(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewControl.setVisibility(View.VISIBLE);
                mTextViewControl.setTextColor(Color.RED);
                mTextViewControl.setText(R.string.error_conexao_radio);
                mPlayPauseDrawable.toggle(false);
            }
        });
    }

    private int lineWidth(){
        int dpi = (int)(getResources().getDisplayMetrics().density * 160f);
        if(dpi<=240){
            return 15;
        }else if (dpi == 320)
            return 30;

        return 60;
    }
}
