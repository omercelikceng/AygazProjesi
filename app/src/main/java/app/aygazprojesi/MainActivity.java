package app.aygazprojesi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMail;
    private EditText etSifre;
    private Button btnKaydol;
    private Button btnGirisYap;
    private TextView tvSifreUnuttum;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EfektIslemleri efektIslemleri;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        efektIslemleri = new EfektIslemleri();
        baslangic();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser kullanici = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("KullaniciBilgileri");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if(kullanici!=null){
            if(kullanici.getEmail().equals("omercelik.ceng@gmail.com")){
                finish();
                startActivity(new Intent(getApplicationContext(),Yetkili.class));
            }else{
                finish();
                startActivity(new Intent(getApplicationContext(), AnaActivity.class));
            }
        }

        btnKaydol.setOnClickListener(this);
        btnGirisYap.setOnClickListener(this);
        tvSifreUnuttum.setOnClickListener(this);

        btnKaydol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        efektIslemleri.butonEfektiniSil(v);
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        efektIslemleri.butonaEfektVer(v);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        efektIslemleri.butonEfektiniSil(v);
                        break;
                    }
                }
                return false;
            }
        });

        btnGirisYap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:{
                        efektIslemleri.butonEfektiniSil(v);
                        break;
                    }
                    case MotionEvent.ACTION_DOWN:{
                        efektIslemleri.butonaEfektVer(v);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        efektIslemleri.butonEfektiniSil(v);
                        break;
                    }
                }
                return false;
            }
        });


    }


    private void baslangic(){
        etMail = (EditText)findViewById(R.id.etMail);
        etSifre = (EditText)findViewById(R.id.etSifre);
        btnGirisYap = (Button)findViewById(R.id.btnGirisYap);
        btnKaydol = (Button)findViewById(R.id.btnKaydol);
        tvSifreUnuttum = (TextView)findViewById(R.id.tvSifreUnuttum);
    }

    private void girisIsleminiYap(){
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();
        if(TextUtils.isEmpty(etMail.getText().toString())||TextUtils.isEmpty(etSifre.getText().toString())){
            Toast.makeText(this,"Lütfen gerekli alanları doldurunuz.",Toast.LENGTH_SHORT).show();
        }
        else {
            String eMail = etMail.getText().toString().trim();
            String sifre = etSifre.getText().toString().trim();

            firebaseAuth.signInWithEmailAndPassword(eMail, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        if(firebaseAuth.getCurrentUser()==null) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            if (firebaseAuth.getCurrentUser().getEmail().equals("omercelik.ceng@gmail.com")) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), Yetkili.class));
                                pd.cancel();
                            } else {
                                finish();
                                startActivity(new Intent(getApplicationContext(), AnaActivity.class));
                                pd.cancel();
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Giriş işlemi başarısız", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if(v==btnGirisYap){
            girisIsleminiYap();
        }
        if(v==btnKaydol){
            startActivity(new Intent(getApplicationContext(),KayitOlActivity.class));
        }
        if(v==tvSifreUnuttum){
            startActivity(new Intent(getApplicationContext(),SifreYenileActivity.class));
        }
    }
}
