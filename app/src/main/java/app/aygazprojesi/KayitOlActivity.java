package app.aygazprojesi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KayitOlActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMail,etSifre,etAdres,etTel,etAdSoyad;
    private Button btnKaydol;
    private EfektIslemleri efektIslemleri;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        efektIslemleri = new EfektIslemleri();
        baslangic();
        btnKaydol.setOnClickListener(this);
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
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("KullaniciBilgileri");
    }

    private void baslangic(){
        etMail = (EditText)findViewById(R.id.etMail);
        etSifre = (EditText)findViewById(R.id.etSifre);
        etAdres = (EditText)findViewById(R.id.etAdres);
        etTel = (EditText)findViewById(R.id.etTel);
        etAdSoyad = (EditText)findViewById(R.id.etAdSoyad);
        btnKaydol = (Button)findViewById(R.id.btnKaydol);

    }

    private void kayitIsleminiYap() {
        if (TextUtils.isEmpty(etAdSoyad.getText().toString()) || TextUtils.isEmpty(etMail.getText().toString())
                || TextUtils.isEmpty(etSifre.getText().toString()) || TextUtils.isEmpty(etAdres.getText().toString())
                || TextUtils.isEmpty(etTel.getText().toString())) {
            Toast.makeText(this, "Lütfen gerekli alanları doldurunuz.", Toast.LENGTH_SHORT).show();
        }
        else {
        String eMail = etMail.getText().toString().trim();
        String sifre = etSifre.getText().toString().trim();

        if (!eMail.contains("@") || TextUtils.isEmpty(eMail) || !eMail.endsWith(".com")) {
            Toast.makeText(this, "Bir mail adresi girdiğinizden emin olunuz.", Toast.LENGTH_SHORT).show();
        } else if (sifre.length() <= 5 || TextUtils.isEmpty(sifre)) {
            Toast.makeText(this, "Şifreniz en az 6 haneli olmalıdır.", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(eMail, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        KullaniciBilgileri kullaniciBilgileri = new KullaniciBilgileri(
                                etAdSoyad.getText().toString(), etAdres.getText().toString(), etTel.getText().toString(),false);
                        FirebaseUser kullanici = firebaseAuth.getCurrentUser();
                        databaseReference.child(kullanici.getUid()).setValue(kullaniciBilgileri);
                        Toast.makeText(KayitOlActivity.this, "Başarılı", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), AnaActivity.class));
                    } else {
                        Toast.makeText(KayitOlActivity.this, "Başarısız", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    }
    @Override
    public void onClick(View v) {
        if (v == btnKaydol) {
            kayitIsleminiYap();
        }
    }
}
