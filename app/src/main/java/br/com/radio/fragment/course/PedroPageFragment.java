package br.com.radio.fragment.course;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import br.com.radio.R;


public class PedroPageFragment extends PageFragment {


    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[]{
                new TransformItem(R.id.iv_1, true, 20),
                new TransformItem(R.id.iv_pedro_1, false, 6),
                new TransformItem(R.id.iv_pedro_2, true, 88),
                new TransformItem(R.id.iv_pedro_3, true, 88),
                new TransformItem(R.id.iv_pedro_4, true, 88),
                new TransformItem(R.id.iv_4, false, 10),
                new TransformItem(R.id.tx, false, 9),
                new TransformItem(R.id.tx_type, false, 22)
        };
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_pedro_page;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
