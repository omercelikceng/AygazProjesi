package app.aygazprojesi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Yetkili extends AppCompatActivity {

    static List<YetkiliSiparisBilgileri> list;
    ListView listView;
    Activity activity;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    ArrayList<String> tarih = new ArrayList<>();
    ArrayList<String> siparisler = new ArrayList<String>();
    ArrayList<String> idDizisi = new ArrayList<String>();
    List<YetkiliSiparisAcilis> list2;
    ProgressDialog pd;
    Button btnOturumKapat;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yetkili);
        btnOturumKapat = (Button)findViewById(R.id.btnOturumKapat);
        btnOturumKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        activity = this;
        listView = (ListView)findViewById(R.id.listView);
        pd = new ProgressDialog(Yetkili.this);
        pd.setMessage("İşlem Gerçekleştiriliyor.Lütfen Bekleyiniz..");
        pd.show();
        databaseReference = FirebaseDatabase.getInstance().getReference("Siparisler");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key : keys) {
                    tarih.add(key.getKey().toString());
                    String siparis="";
                    if(!key.child("evTupu").getValue().toString().equals("0")){
                        siparis += key.child("evTupu").getValue().toString()+" Ev Tüpü , ";
                    }
                    if(!key.child("kucukTup").getValue().toString().equals("0")){
                        siparis += key.child("kucukTup").getValue().toString()+" Küçük Tüp , ";
                    }
                    if(!key.child("sanayiTupu").getValue().toString().equals("0")){
                        siparis += key.child("sanayiTupu").getValue().toString()+" Sanayi Tüpü , ";
                    }
                    if(!key.child("ticariTup").getValue().toString().equals("0")){
                        siparis += key.child("ticariTup").getValue().toString()+" Ticari Tüp , ";
                    }
                    if(!key.child("uzunEvTupu").getValue().toString().equals("0")){
                        siparis += key.child("uzunEvTupu").getValue().toString()+" Uzun Ev Tüpü , ";
                    }
                   // Toast.makeText(activity,key.child("id").getValue().toString(),Toast.LENGTH_SHORT).show();
                    idDizisi.add(key.child("id").getValue().toString());
                    siparisler.add(siparis);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("KullaniciBilgileri");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<YetkiliSiparisBilgileri>();
                list2 = new ArrayList<YetkiliSiparisAcilis>();
                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key : keys) {
                    for(int i=0; i<idDizisi.size(); i++){
                        if(idDizisi.get(i).toString().equals(key.getKey().toString())){
                            list2.add(new YetkiliSiparisAcilis(key.child("adSoyad").getValue().toString(),tarih.get(i).toString()));
                            list.add(new YetkiliSiparisBilgileri(siparisler.get(i).toString(),
                                    key.child("adres").getValue().toString(),
                                    key.child("adSoyad").getValue().toString(),
                                    key.child("tel").getValue().toString(),tarih.get(i).toString()));

                        }
                    }
                }
                YetkiliAcilisAdaptor yetkiliAcilisAdaptor = new YetkiliAcilisAdaptor(activity,list2);
                listView.setAdapter(yetkiliAcilisAdaptor);
                pd.cancel();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


                }



}
