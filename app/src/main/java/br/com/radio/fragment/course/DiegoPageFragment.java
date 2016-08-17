package br.com.radio.fragment.course;


import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import br.com.radio.R;


public class DiegoPageFragment extends PageFragment {


    @Override
    protected TransformItem[] provideTransformItems() {
        return new TransformItem[]{
                new TransformItem(R.id.ivFirstImage, true, 20),
                new TransformItem(R.id.ivSecondImage, false, 6),
                new TransformItem(R.id.ivThirdImage, true, 8),
                new TransformItem(R.id.ivFourthImage, false, 10),
                new TransformItem(R.id.tx_type, false, 9),
                new TransformItem(R.id.tx_name, false, 22),
                new TransformItem(R.id.ivSixthImage, false, 9),
                new TransformItem(R.id.ivSeventhImage, false, 14),
                new TransformItem(R.id.ivEighthImage, false, 7)
        };
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_diego_page;
    }


}
