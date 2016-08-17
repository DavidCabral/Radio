package br.com.radio.fragment.course;


import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import br.com.radio.R;


public class DavidPageFragment extends PageFragment {


    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[]{
                new TransformItem(R.id.iv_dav_1, true, 20),
                new TransformItem(R.id.iv_dav_2, false, 6),
                new TransformItem(R.id.iv_dav_3, true, 88),
                new TransformItem(R.id.iv_dav_4, false, 10),
                new TransformItem(R.id.iv_dav_5, false, 9),
                new TransformItem(R.id.iv_dav_6, false, 22)
        };
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_david_page;
    }


}
