package app.aygazprojesi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class YetkiliSatirActivity extends AppCompatActivity {


    TextView tvTarih,tvSiparis,tvAdres,tvTel,tvAdSoyad;
    String position;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yetkili_satir);
        baslangic();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pd = new ProgressDialog(YetkiliSatirActivity.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                position= null;
            } else {
                position= extras.getString("position");
            }
        } else {
            position= (String) savedInstanceState.getSerializable("position");
        }
        Toast.makeText(this,position,Toast.LENGTH_SHORT).show();
        int index = Integer.parseInt(position);
        YetkiliSiparisBilgileri yetkiliSiparisBilgileri= Yetkili.list.get(index);
        tvAdres.setText(yetkiliSiparisBilgileri.getAdres().toString());
        tvAdSoyad.setText(yetkiliSiparisBilgileri.getAdSoyad().toString());
        tvSiparis.setText(yetkiliSiparisBilgileri.getSiparis().toString());
        tvTel.setText(yetkiliSiparisBilgileri.getTel().toString());
        tvTarih.setText(yetkiliSiparisBilgileri.getTarih().toString());
        pd.cancel();
    }

    private void baslangic(){
        tvAdres = (TextView)findViewById(R.id.tvAdres);
        tvAdSoyad = (TextView)findViewById(R.id.tvAdSoyad);
        tvSiparis = (TextView)findViewById(R.id.tvSiparisler);
        tvTel = (TextView)findViewById(R.id.tvTel);
        tvTarih = (TextView)findViewById(R.id.tvTarih);
    }

}
