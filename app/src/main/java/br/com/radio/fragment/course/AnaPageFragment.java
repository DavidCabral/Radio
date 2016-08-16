package br.com.radio.fragment.course;


import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import br.com.radio.R;


public class AnaPageFragment extends PageFragment {


    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[0];
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_ana_page;
    }


}
