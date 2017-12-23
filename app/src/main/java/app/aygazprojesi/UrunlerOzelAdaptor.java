package app.aygazprojesi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Omer on 4.5.2017.
 */

public class UrunlerOzelAdaptor extends BaseAdapter {

    Activity activity;
    LayoutInflater layoutInflater;
    List<UrunBilgileri> list;
    EfektIslemleri efektIslemleri;

    public UrunlerOzelAdaptor(Activity activity, List<UrunBilgileri> mList){
        this.activity = activity;
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = mList;
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
        final View satirView;
        satirView = layoutInflater.inflate(R.layout.urunsatir,null);
        efektIslemleri = new EfektIslemleri();
        ImageView imageUrun = (ImageView) satirView.findViewById(R.id.imageUrun);
        TextView tvUrunIsim = (TextView)satirView.findViewById(R.id.tvUrunIsim);
        TextView tvUrunFiyati = (TextView)satirView.findViewById(R.id.tvUrunFiyati);
        final Button btnSepeteEkle = (Button)satirView.findViewById(R.id.btnSepeteEkle);



        final UrunBilgileri urunBilgileri = list.get(position);
        tvUrunIsim.setText(urunBilgileri.getUrunIsmi());
        tvUrunFiyati.setText(NumberFormat.getCurrencyInstance().format(urunBilgileri.getUrunFiyati()));
        String urunIsmi = urunBilgileri.getUrunIsmi().toString();

        btnSepeteEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrunBilgileri urun = list.get(position);
                Intent intent = new Intent(activity, UrunDetayi.class);
                intent.putExtra("urun",urun.getUrunIsmi());
                activity.startActivity(intent);
            }
        });

        satirView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrunBilgileri urun = list.get(position);
                Intent intent = new Intent(activity, UrunDetayi.class);
                intent.putExtra("urun",urun.getUrunIsmi());
                activity.startActivity(intent);
            }
        });

        if(urunIsmi.equals("KÜÇÜK TÜP")){
            imageUrun.setImageResource(R.drawable.kucuktup);
        }
        else if(urunIsmi.equals("EV TÜPÜ")){
            imageUrun.setImageResource(R.drawable.tombulevtupu);
        }
        else if(urunIsmi.equals("UZUN EV TÜPÜ")){
            imageUrun.setImageResource(R.drawable.uzunevtupu);
        }
        else if(urunIsmi.equals("TİCARİ TÜP")){
            imageUrun.setImageResource(R.drawable.ticaritup);
        }
        else if(urunIsmi.equals("SANAYİ TÜPÜ")){
            imageUrun.setImageResource(R.drawable.sanayitupu);
        }
        return satirView;
    }
}
