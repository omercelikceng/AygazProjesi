package app.aygazprojesi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sepetim extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    List<UrunBilgileri> urunBilgileriList;
    private Button btnSepetiBosalt;
    private Button btnSepetiOnayla;
    private TextView tvToplamFiyat;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;
    SepetOzelAdaptor sepetOzelAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepetim);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView)findViewById(R.id.listView);
        baslangic();
        urunleriEkle();

    }


    private void baslangic(){
        btnSepetiBosalt = (Button)findViewById(R.id.btnSepetiBosalt);
        btnSepetiOnayla = (Button)findViewById(R.id.btnSepetiOnayla);
        tvToplamFiyat = (TextView)findViewById(R.id.tvToplamFiyat);
        btnSepetiOnayla.setOnClickListener(this);
        btnSepetiBosalt.setOnClickListener(this);
    }

    private void urunleriEkle(){
        int toplamFiyat=0;
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
        sepetOzelAdaptor = new SepetOzelAdaptor(this,urunBilgileriList);
        listView.setAdapter(sepetOzelAdaptor);
        tvToplamFiyat.setText(""+ NumberFormat.getCurrencyInstance().format(toplamFiyat));
    }

    @Override
    public void onClick(View v) {
        if(v==btnSepetiBosalt){
            tupSayilariniSifirla();
            finish();
            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
        }
        else if(v==btnSepetiOnayla){
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance().getReference("Siparisler");
            FirebaseUser kullanici = firebaseAuth.getCurrentUser();
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            SepetBilgileri sepetBilgileri = new SepetBilgileri(kullanici.getUid(),UrunBilgileri.kucukTupSayisi,
                    UrunBilgileri.tombulEvTupuSayisi,UrunBilgileri.uzunEvTupuSayisi,
                    UrunBilgileri.ticariTupSayisi,UrunBilgileri.sanayiTupSayisi);
            firebaseDatabase.child(currentDateTimeString).setValue(sepetBilgileri);
            tupSayilariniSifirla();
            Toast.makeText(this,"Siparişiniz alınmıştır. En kısa sürede ulaştırılacaktır.",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(),AnaActivity.class));
        }
    }
    private void tupSayilariniSifirla(){
        UrunBilgileri.kucukTupSayisi=0;
        UrunBilgileri.tombulEvTupuSayisi=0;
        UrunBilgileri.sanayiTupSayisi=0;
        UrunBilgileri.uzunEvTupuSayisi=0;
        UrunBilgileri.ticariTupSayisi=0;
    }
}
