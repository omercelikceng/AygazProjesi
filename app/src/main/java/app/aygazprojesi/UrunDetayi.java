package app.aygazprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.NumberFormat;

public class UrunDetayi extends AppCompatActivity {

    private UrunBilgileri urunBilgileri;
    private ImageView imageUrun;
    private TextView tvFiyat;
    private TextView tvUrunIsmi;
    private Spinner spUrunAdeti;
    private Button btnSepeteEkle;
    private TextView tvUrunDetayi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detayi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        baslangic();
        String urunIsmiAl;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                urunIsmiAl= null;
            } else {
                urunIsmiAl= extras.getString("urun");
            }
        } else {
            urunIsmiAl= (String) savedInstanceState.getSerializable("urun");
        }
        elementleriDoldur(urunIsmiAl);

    }

    private void elementleriDoldur(String urunIsmi){
        urunBilgileri = new UrunBilgileri(urunIsmi);
        tvUrunIsmi.setText(urunBilgileri.getUrunIsmi());
        tvFiyat.setText(NumberFormat.getCurrencyInstance().format(urunBilgileri.getUrunFiyati()));
        tvUrunDetayi.setText(urunBilgileri.getUrunBilgisi());

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

    }

    private void sepeteEklemeIslemi(){
        int adet = Integer.parseInt(spUrunAdeti.getSelectedItem().toString());
        if(tvUrunIsmi.getText().equals("KÜÇÜK TÜP"))
            UrunBilgileri.kucukTupSayisi += adet;
        else if(tvUrunIsmi.getText().equals("EV TÜPÜ"))
            UrunBilgileri.tombulEvTupuSayisi +=  adet;
        else if(tvUrunIsmi.getText().equals("UZUN EV TÜPÜ"))
            UrunBilgileri.uzunEvTupuSayisi += adet;
        else if(tvUrunIsmi.getText().equals("TİCARİ TÜP"))
            UrunBilgileri.ticariTupSayisi += adet;
        else if(tvUrunIsmi.getText().equals("SANAYİ TÜPÜ"))
            UrunBilgileri.sanayiTupSayisi += adet;

        finish();
        startActivity(new Intent(getApplicationContext(),AnaActivity.class));
    }

    private void baslangic(){
        imageUrun = (ImageView)findViewById(R.id.imageUrun);
        tvFiyat = (TextView)findViewById(R.id.tvUrunFiyati);
        tvUrunIsmi = (TextView)findViewById(R.id.tvUrunIsim);
        tvUrunDetayi = (TextView)findViewById(R.id.tvUrunDetayi);
        spUrunAdeti = (Spinner) findViewById(R.id.spAdet);
        btnSepeteEkle = (Button)findViewById(R.id.btnSepeteEkle);
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.urunadeti,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUrunAdeti.setAdapter(adapter);
        spUrunAdeti.setSelection(0);

        spUrunAdeti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String urunDizisi[]= getResources().getStringArray(R.array.urunadeti);
                int fiyat = Integer.parseInt(urunDizisi[position].toString()) * urunBilgileri.getUrunFiyati() ;
                tvFiyat.setText(NumberFormat.getCurrencyInstance().format(fiyat));

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSepeteEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sepeteEklemeIslemi();
            }
        });
    }
}
