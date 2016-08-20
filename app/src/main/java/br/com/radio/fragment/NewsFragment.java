package br.com.radio.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.OnRssLoadListener;
import com.crazyhitty.chdev.ks.rssmanager.RssItem;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.ArrayList;
import java.util.List;

import br.com.radio.R;
import br.com.radio.activity.NewsActivity;
import br.com.radio.adapter.RSSAdapter;
import br.com.radio.util.Constantes;
import br.com.radio.util.OnFragmentInteractionListener;
import br.com.radio.util.RecyclerViewOnClickListenerHack;

public class NewsFragment extends Fragment implements OnRssLoadListener, RecyclerViewOnClickListenerHack, SwipeRefreshLayout.OnRefreshListener {
    private OnFragmentInteractionListener mListener;
    private final static String PARAMETRO_URL = "URL";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FrameLayout frameLayout;
    protected List<RssItem> mList;
    private String url;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String url) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(PARAMETRO_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            url = getArguments().getString(PARAMETRO_URL);

        mList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_news, container, false);
        initializeUI(view);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        RSSAdapter adapter = new RSSAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        loadFeeds();
        //listEmpty();

        return view;
    }

    private void atualizaLista(List<RssItem> list) throws Exception {
        RSSAdapter adapter = (RSSAdapter) mRecyclerView.getAdapter();
        //limpa a lista
        adapter.clearData();
        //insere os objetos na lista
        for (int i = 0; i < list.size(); i++) {
            adapter.addListItem(list.get(i), 0);
        }
        mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
    }

    public void initializeUI(View v) {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_list);
        frameLayout = (FrameLayout) v.findViewById(R.id.cl_frame);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.srl_swipe);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    //load feeds
    private void loadFeeds() {
        mSwipeRefreshLayout.setRefreshing(true);
        String[] urlArr = {url};
        new RssReader(getActivity())
                .showDialog(false)
                .urls(urlArr)
                .parse(this);
    }

    @Override
    public void onSuccess(final List<RssItem> rssItems) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    rssItems.get(0).getLink();
                    atualizaLista(rssItems);
                    mSwipeRefreshLayout.setRefreshing(false);
                    listEmpty();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onFailure(String message) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    mSwipeRefreshLayout.setRefreshing(false);
                    listEmpty();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClickListener(View view, int position) {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        intent.putExtra(Constantes.TAG_URL, mList.get(position).getLink());
        startActivity(intent);
    }

    private void listEmpty() {
        mRecyclerView.setVisibility(mList.isEmpty() ? View.GONE : View.VISIBLE);
        if (mList.isEmpty()) {
            TextView tv = new TextView(getActivity());
            tv.setText(R.string.sem_noticias);
            tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimarytext));
            tv.setId(0);
            tv.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            tv.setGravity(Gravity.CENTER);
            frameLayout.addView(tv);
        } else if (frameLayout.findViewById(0) != null) {
            frameLayout.removeView(frameLayout.findViewById(0));
        }
    }

    @Override
    public void onLongPressClickListener(View view, int position) {

    }

    @Override
    public void onRefresh() {
        loadFeeds();
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh) {
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv,
                                rv.getChildAdapterPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(), e.getY());

                    if (cv != null && mRecyclerViewOnClickListenerHack != null) {
                        mRecyclerViewOnClickListenerHack.onClickListener(cv,
                                rv.getChildAdapterPosition(cv));
                    }

                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
