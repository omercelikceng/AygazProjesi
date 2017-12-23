package app.aygazprojesi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SifreYenileActivity extends AppCompatActivity {


    private EditText etMail;
    private Button btnSifreYenile;
    private FirebaseAuth firebaseAuth;
    private EfektIslemleri efektIslemleri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_yenile);
        baslangic();
        efektIslemleri = new EfektIslemleri();
        btnSifreYenile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.sendPasswordResetEmail(etMail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SifreYenileActivity.this,"Mail adresinize gelen link ile şifrenizi yenileyebilirsiniz.",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        } else{
                            Toast.makeText(SifreYenileActivity.this,"Bir hata oluştu.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnSifreYenile.setOnTouchListener(new View.OnTouchListener() {
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
        etMail=(EditText)findViewById(R.id.etMail);
        btnSifreYenile = (Button)findViewById(R.id.btnSifreYenile);
    }
}
