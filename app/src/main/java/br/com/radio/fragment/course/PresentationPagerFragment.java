package br.com.radio.fragment.course;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cleveroad.slidingtutorial.PageFragment;
import com.cleveroad.slidingtutorial.SimplePagerFragment;

import br.com.radio.R;
import br.com.radio.activity.RadioActivity;

public class PresentationPagerFragment extends SimplePagerFragment {

	@Override
	protected int getPagesCount() {
		return 7;
	}

	@Override
	protected PageFragment getPage(int position) {
		if(position == 0)
			return new AppPageFragment();
		else if (position == 1)
			return new DiegoPageFragment();
		else if (position == 2)
			return new AnaPageFragment();
		else if (position == 3)
			return new DavidPageFragment();
		else if (position == 4)
			return new GilmarPageFragment();
		else if (position == 5)
			return new LuanaPageFragment();
		else if (position == 6)
			return new PedroPageFragment();

		throw new IllegalArgumentException("Unknown position: " + position);



	}

	@ColorInt
	@Override
	protected int getPageColor(int position) {
		if (position == 0)
			return ContextCompat.getColor(getContext(), R.color.material_drawer_background);
		if (position == 1)
			return ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
		if (position == 2)
			return ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);
		if (position == 3)
			return ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
		if (position == 4)
			return ContextCompat.getColor(getContext(), android.R.color.holo_purple);
		if (position == 5)
			return ContextCompat.getColor(getContext(), android.R.color.darker_gray);
		if (position == 6)
			return ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
		return Color.TRANSPARENT;
	}

	@Override
	protected boolean isInfiniteScrollEnabled() {
		return true;
	}

	@Override
	protected boolean onSkipButtonClicked(View skipButton) {

		startActivity(new Intent(getActivity(), RadioActivity.class));
		getActivity().finish();

		return true;
	}
}
