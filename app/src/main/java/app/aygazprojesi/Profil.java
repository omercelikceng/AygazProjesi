package app.aygazprojesi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profil extends Fragment implements View.OnClickListener {

    static int i=0;
    Button btnCikisYap,btnGuncelle;
    TextView tvEMail;
    EditText etAdSoyad,etAdres,etTel;
    private View rootView=null;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("KullaniciBilgileri");
    private FirebaseUser firebaseUser;
    ProgressDialog progressDialog;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        progressDialog = ProgressDialog.show(Profil.this.getActivity(), "Profil Bilgileri", "Yükleniyor.", true);
        progressDialog.show();
        databaseReference.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                KullaniciBilgileri kullaniciBilgileri = dataSnapshot.getValue(KullaniciBilgileri.class);
                tvEMail.setText(firebaseUser.getEmail());
                etTel.setText(kullaniciBilgileri.getTel());
                etAdSoyad.setText((kullaniciBilgileri.getAdSoyad()));
                etAdres.setText((kullaniciBilgileri.getAdres()));
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
/* Tab'a tıklama işlemi yapıldığında çalışır.
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser==true && rootView!=null){
            Toast.makeText(getActivity(), "profil", Toast.LENGTH_SHORT).show();

        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profiltab, container, false);
        baslangic();
        btnGuncelle.setOnClickListener(this);
        btnCikisYap.setOnClickListener(this);


        return rootView;
    }

    private void baslangic(){
        btnCikisYap = (Button)rootView.findViewById(R.id.btnCikisYap);
        btnGuncelle =(Button)rootView.findViewById(R.id.btnGuncelle);
        tvEMail = (TextView)rootView.findViewById(R.id.tvEMail);
        etAdres = (EditText)rootView.findViewById(R.id.etAdres);
        etAdSoyad = (EditText)rootView.findViewById(R.id.etAdSoyad);
        etTel = (EditText)rootView.findViewById(R.id.etTel);
    }

    private void bilgileriGuncelle(){
        KullaniciBilgileri kullaniciBilgileri = new KullaniciBilgileri(etAdSoyad.getText().toString(),
                etAdres.getText().toString(),etTel.getText().toString(),false);
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.child(firebaseUser.getUid()).setValue(kullaniciBilgileri);
        Toast.makeText(getActivity(),"Güncelleme işlemi başarılı.",Toast.LENGTH_SHORT).show();
        rootView.requestFocus();
    }
    @Override
    public void onClick(View v) {
        if(v==btnCikisYap){
            firebaseAuth.signOut();
            this.getActivity().finish();
            startActivity(new Intent(getContext(),MainActivity.class));
        }
        if(v==btnGuncelle){
            bilgileriGuncelle();
        }
    }
}
