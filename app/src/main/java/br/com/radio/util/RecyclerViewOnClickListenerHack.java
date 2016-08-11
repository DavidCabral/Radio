package br.com.radio.util;

import android.view.View;

/**
 * Created by David on 07/08/2016.
 */

public interface RecyclerViewOnClickListenerHack {
    void onClickListener(View view, int position);
    void onLongPressClickListener(View view, int position);
}
