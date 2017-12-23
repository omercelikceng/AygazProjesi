package app.aygazprojesi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Omer on 11.5.2017.
 */

public class YetkiliAcilisAdaptor extends BaseAdapter {
    Activity activity;
    LayoutInflater layoutInflater;
    List<YetkiliSiparisAcilis> list;

    public YetkiliAcilisAdaptor(Activity mActivity,List<YetkiliSiparisAcilis> mlist){
        activity = mActivity;
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = mlist;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View satirview;
        satirview = layoutInflater.inflate(R.layout.yetkiliacilissatiri,null);

        TextView tvAdSoyad = (TextView)satirview.findViewById(R.id.tvAdSoyad);
        TextView tvTarih = (TextView)satirview.findViewById(R.id.tvTarih);

        final YetkiliSiparisAcilis yetkiliSiparisAcilis = list.get(position);
        tvAdSoyad.setText(yetkiliSiparisAcilis.getAdSoyad().toString());
        tvTarih.setText(yetkiliSiparisAcilis.getTarih().toString());
        satirview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pozisyon = String.valueOf(position);
                Intent intent = new Intent(activity, YetkiliSatirActivity.class);
                intent.putExtra("position",pozisyon);
                activity.startActivity(intent);
            }
        });
        return satirview;
    }
}
