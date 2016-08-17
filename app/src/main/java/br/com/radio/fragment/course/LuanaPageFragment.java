package br.com.radio.fragment.course;


import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import br.com.radio.R;


public class LuanaPageFragment extends PageFragment {


    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[]{
                new TransformItem(R.id.iv_1, true, 20),
                new TransformItem(R.id.iv_luana_1, false, 6),
                new TransformItem(R.id.iv_luana_2, true, 88),
                new TransformItem(R.id.iv_4, false, 10),
                new TransformItem(R.id.tx, false, 9),
                new TransformItem(R.id.tx_type, false, 22)
        };
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_luana_page;
    }


}
