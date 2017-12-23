package app.aygazprojesi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 9.5.2017.
 */

public class SepetOzelAdaptor extends BaseAdapter {
    Activity activity;
    LayoutInflater layoutInflater;
    List<UrunBilgileri> list;
    AlertDialog ad;
    static String[] secenekler = {"Ürün Detayına Git,Adet Güncelle,Sepetten Çıkar,Vazgeç"};

    public SepetOzelAdaptor(Activity mActivity,List<UrunBilgileri> mlist){
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
        final int sepetPosition=position;
        satirview = layoutInflater.inflate(R.layout.sepetsatir,null);
        TextView tvUrunAdeti = (TextView)satirview.findViewById(R.id.tvUrunAdeti);
        final TextView tvUrunIsmi = (TextView)satirview.findViewById(R.id.tvUrunIsim);
        TextView tvUrunFiyati = (TextView)satirview.findViewById(R.id.tvUrunFiyati);


        final UrunBilgileri urunBilgileri = list.get(position);
        tvUrunIsmi.setText(urunBilgileri.getUrunIsmi().toString());
        final String urunIsmi = urunBilgileri.getUrunIsmi().toString();
        if(urunIsmi.equals("KÜÇÜK TÜP")){
            tvUrunAdeti.setText(""+UrunBilgileri.kucukTupSayisi);
        }
        else if(urunIsmi.equals("EV TÜPÜ")){
            tvUrunAdeti.setText(""+UrunBilgileri.tombulEvTupuSayisi);
        }
        else if(urunIsmi.equals("UZUN EV TÜPÜ")){
            tvUrunAdeti.setText(""+UrunBilgileri.uzunEvTupuSayisi);
        }
        else if(urunIsmi.equals("TİCARİ TÜP")){
            tvUrunAdeti.setText(""+UrunBilgileri.ticariTupSayisi);
        }
        else if(urunIsmi.equals("SANAYİ TÜPÜ")){
            tvUrunAdeti.setText(""+UrunBilgileri.sanayiTupSayisi);
        }
        int urunAdeti = Integer.parseInt(tvUrunAdeti.getText().toString());
        int urunFiyati = urunAdeti*urunBilgileri.getUrunFiyati();
        tvUrunFiyati.setText(NumberFormat.getCurrencyInstance().format(urunFiyati));

        satirview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                final View mView = activity.getLayoutInflater().inflate(R.layout.activity_sepet_secenekleri,null);
                final ListView listView = (ListView)mView.findViewById(R.id.listView);

                String []secenekler = {"Ürün Detayına Git","Adet Güncelle","Sepetten Çıkar","Vazgeç"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mView.getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,secenekler);
                listView.setAdapter(adapter);

                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position==0){
                            dialog.cancel();

                            UrunBilgileri urun = list.get(sepetPosition);
                            Intent intent = new Intent(activity, UrunDetayi.class);
                            intent.putExtra("urun",urun.getUrunIsmi());
                            activity.startActivity(intent);
                        }
                        else if(position==1){
                            dialog.cancel();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                            final View mView1 = activity.getLayoutInflater().inflate(R.layout.activity_alert_urun_adeti,null);
                            final EditText etUrunAdeti = (EditText)mView1.findViewById(R.id.etUrunAdeti);
                            Button btnTamam = (Button)mView1.findViewById(R.id.btnTamam);


                            builder1.setView(mView1);
                            final AlertDialog dialog1 = builder1.create();
                            dialog1.show();
                            btnTamam.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(TextUtils.isEmpty(etUrunAdeti.getText().toString()) || etUrunAdeti.getText().toString().equals("0")){
                                        Toast.makeText(activity,"Geçerli bir değer giriniz.",Toast.LENGTH_SHORT).show();
                                    }else {
                                        dialog1.cancel();
                                        UrunBilgileri urun = list.get(sepetPosition);
                                        if (urunIsmi.equals("KÜÇÜK TÜP")) {
                                            UrunBilgileri.kucukTupSayisi = Integer.parseInt(etUrunAdeti.getText().toString());
                                        } else if (urunIsmi.equals("EV TÜPÜ")) {
                                            UrunBilgileri.tombulEvTupuSayisi = Integer.parseInt(etUrunAdeti.getText().toString());
                                        } else if (urunIsmi.equals("UZUN EV TÜPÜ")) {
                                            UrunBilgileri.uzunEvTupuSayisi = Integer.parseInt(etUrunAdeti.getText().toString());
                                        } else if (urunIsmi.equals("TİCARİ TÜP")) {
                                            UrunBilgileri.ticariTupSayisi = Integer.parseInt(etUrunAdeti.getText().toString());
                                        } else if (urunIsmi.equals("SANAYİ TÜPÜ")) {
                                            UrunBilgileri.sanayiTupSayisi = Integer.parseInt(etUrunAdeti.getText().toString());
                                        }
                                        TextView tvToplamFiyat = (TextView)activity.findViewById(R.id.tvToplamFiyat);
                                        tvToplamFiyat.setText(NumberFormat.getCurrencyInstance().format(fiyatiHesapla()));
                                    }
                                }
                            });
                        }
                        else if(position==2){
                            ListView listView1 = (ListView)satirview.findViewById(R.id.listView);
                            dialog.cancel();
                            if(urunIsmi.equals("KÜÇÜK TÜP")){
                                UrunBilgileri.kucukTupSayisi=0;
                            }
                            else if(urunIsmi.equals("EV TÜPÜ")){
                                UrunBilgileri.tombulEvTupuSayisi=0;
                            }
                            else if(urunIsmi.equals("UZUN EV TÜPÜ")){
                                UrunBilgileri.uzunEvTupuSayisi=0;
                            }
                            else if(urunIsmi.equals("TİCARİ TÜP")){
                                UrunBilgileri.ticariTupSayisi=0;
                            }
                            else if(urunIsmi.equals("SANAYİ TÜPÜ")){
                                UrunBilgileri.sanayiTupSayisi=0;
                            }
                            if(UrunBilgileri.kucukTupSayisi==0&& UrunBilgileri.tombulEvTupuSayisi==0
                                    && UrunBilgileri.uzunEvTupuSayisi==0 && UrunBilgileri.ticariTupSayisi==0
                                    && UrunBilgileri.sanayiTupSayisi==0){
                                activity.finish();
                                activity.startActivity(new Intent(activity.getApplicationContext(),AnaActivity.class));
                            }
                            list.remove(sepetPosition);
                            notifyDataSetChanged();


                            TextView tvToplamFiyat = (TextView)activity.findViewById(R.id.tvToplamFiyat);
                            tvToplamFiyat.setText(NumberFormat.getCurrencyInstance().format(fiyatiHesapla()));
                        }
                        else if(position==3){
                            dialog.cancel();
                        }

                    }
                });
            }
        });

        return satirview;
    }

    private int fiyatiHesapla(){
        int toplamFiyat=0;
        List<UrunBilgileri> urunBilgileriList;
        UrunBilgileri urunBilgileri;
        urunBilgileriList = new ArrayList<UrunBilgileri>();
        if(UrunBilgileri.kucukTupSayisi>0){
            urunBilgileriList.add(new UrunBilgileri("KÜÇÜK TÜP"));
            urunBilgileri = new UrunBilgileri("KÜÇÜK TÜP");
            toplamFiyat+= urunBilgileri.getUrunFiyati()*UrunBilgileri.kucukTupSayisi;
        }
        if(UrunBilgileri.sanayiTupSayisi>0){
            urunBilgileriList.add(new UrunBilgileri("SANAYİ TÜPÜ"));
            urunBilgileri = new UrunBilgileri("SANAYİ TÜPÜ");
            toplamFiyat+= urunBilgileri.getUrunFiyati()*UrunBilgileri.sanayiTupSayisi;
        }
        if(UrunBilgileri.ticariTupSayisi>0){
            urunBilgileriList.add(new UrunBilgileri("TİCARİ TÜP"));
            urunBilgileri = new UrunBilgileri("TİCARİ TÜP");
            toplamFiyat+= urunBilgileri.getUrunFiyati()*UrunBilgileri.ticariTupSayisi;
        }
        if(UrunBilgileri.tombulEvTupuSayisi>0){
            urunBilgileriList.add(new UrunBilgileri("EV TÜPÜ"));
            urunBilgileri = new UrunBilgileri("EV TÜPÜ");
            toplamFiyat+= urunBilgileri.getUrunFiyati()*UrunBilgileri.tombulEvTupuSayisi;
        }
        if(UrunBilgileri.uzunEvTupuSayisi>0){
            urunBilgileriList.add(new UrunBilgileri("UZUN EV TÜPÜ"));
            urunBilgileri = new UrunBilgileri("UZUN EV TÜPÜ");
            toplamFiyat+= urunBilgileri.getUrunFiyati()*UrunBilgileri.uzunEvTupuSayisi;
        }
        return toplamFiyat;
    }
}
