package br.com.radio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.RssItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.radio.R;
import br.com.radio.util.RecyclerViewOnClickListenerHack;


/**
 * Created by david on 05/08/2016.
 */
public class RSSAdapter extends RecyclerView.Adapter<RSSAdapter.MyViewHolder> {
    private Context mContext;
    private List<RssItem> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;




    public RSSAdapter(Context c, List<RssItem> l) {
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_news, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.txtTitle.setText(mList.get(position).getTitle());
        myViewHolder.txtDescription.setText(mList.get(position).getDescription());
        myViewHolder.txtAuthor.setText(mList.get(position).getSourceName());
        myViewHolder.txtDate.setText(mList.get(position).getPubDate());

        Picasso.with(mContext)
                .load(mList.get(position).getImageUrl())
                .placeholder(R.drawable.placeholder).
                into(myViewHolder.imgPreview);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(RssItem c) {
        mList.add(c);
        notifyItemInserted(0);
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imgPreview;
        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtAuthor;
        public TextView txtDate;
        public int currentTag;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgPreview = (ImageView) itemView.findViewById(R.id.imgPreview);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtAuthor = (TextView) itemView.findViewById(R.id.txtAuthor);
            txtDate= (TextView) itemView.findViewById(R.id.txtDate);
            currentTag = 0;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getLayoutPosition());
            }
        }
    }
}
