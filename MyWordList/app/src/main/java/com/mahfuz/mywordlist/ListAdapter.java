package com.mahfuz.mywordlist;

/**
 * Created by IslamMha on 9/10/2014.
 */
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import java.util.List;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.util.Log;

public class ListAdapter extends BaseAdapter {
    List<Word> words ;
    private static LayoutInflater inflater=null;
    Holder holder = null ;
    Integer[] imgArray = {
            R.drawable.a , R.drawable.b , R.drawable.c,R.drawable.d,
            R.drawable.e , R.drawable.f ,R.drawable.g,
            R.drawable.h , R.drawable.i  ,R.drawable.j,
            R.drawable.k , R.drawable.l ,R.drawable.m,
            R.drawable.n , R.drawable.o ,R.drawable.p,
            R.drawable.q , R.drawable.r , R.drawable.s,R.drawable.t,
            R.drawable.u , R.drawable.v , R.drawable.w ,R.drawable.x,
            R.drawable.y , R.drawable.z
    };
    ListAdapter(Context c,List<Word> wss){
        words = wss;
        inflater = (LayoutInflater)c.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Object getItem(int i) {
        return words.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=view;
        if(view==null)
            vi = inflater.inflate(R.layout.list_item, null);

        if(holder==null){
            holder = new Holder(vi);
        }

        Word ww = words.get(i);
        ImageView img = (ImageView)vi.findViewById(R.id.imageView);
        TextView tx_word = (TextView)vi.findViewById(R.id.textView2);
        TextView tx_mean = (TextView)vi.findViewById(R.id.textView3);
        RatingBar rb_rate = (RatingBar)vi.findViewById(R.id.ratingBar);

        rb_rate.setRating(ww.getRate());

        tx_word.setText(ww.getValue());
        tx_mean.setText(ww.getMeaning());
        char[] tag = ww.getTag().toUpperCase().toCharArray();

        Log.d("TAG" , "tag = " + words.get(i).getTag() + " C = " + (tag[0]-'A'));

        img.setImageResource(imgArray[tag[0]-'A']);

        return vi;
    }
    public static class Holder {
        TextView tx_word ;
        TextView tx_mean ;
        ImageView img ;

        public Holder(View v){
            img = (ImageView)v.findViewById(R.id.imageView);
            tx_word = (TextView)v.findViewById(R.id.textView2);
            tx_mean = (TextView)v.findViewById(R.id.textView3);

        }
    }
}
