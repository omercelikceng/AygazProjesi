package app.aygazprojesi;

/**
 * Created by Omer on 1.5.2017.
 */
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UrunlerVeSiparis extends Fragment {

    static int index=0;
    View rootView=null;
    ListView listView;
    FloatingActionButton btnAlisverisSepeti;
    List<UrunBilgileri> urunBilgileriList;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser==true && rootView!=null){
            if(UrunBilgileri.ticariTupSayisi>0 || UrunBilgileri.uzunEvTupuSayisi>0 || UrunBilgileri.sanayiTupSayisi>0
                    || UrunBilgileri.tombulEvTupuSayisi>0 || UrunBilgileri.kucukTupSayisi>0){
                btnAlisverisSepeti.setVisibility(View.VISIBLE);
            }
            else {
                btnAlisverisSepeti.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.urunlervesiparistab, container, false);
        listView = (ListView)rootView.findViewById(R.id.listView);
        btnAlisverisSepeti = (FloatingActionButton)rootView.findViewById(R.id.btnAlisverisSepeti);
        btnAlisverisSepeti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getContext(),Sepetim.class));
            }
        });
        if(UrunBilgileri.ticariTupSayisi>0 || UrunBilgileri.uzunEvTupuSayisi>0 || UrunBilgileri.sanayiTupSayisi>0
                || UrunBilgileri.tombulEvTupuSayisi>0 || UrunBilgileri.kucukTupSayisi>0){
            btnAlisverisSepeti.setVisibility(View.VISIBLE);
        }
        else{
            btnAlisverisSepeti.setVisibility(View.INVISIBLE);
        }
            urunEkle();


        return rootView;
    }
    private void urunEkle(){
        urunBilgileriList = new ArrayList<UrunBilgileri>();
        urunBilgileriList.add(new UrunBilgileri("KÜÇÜK TÜP"));
        urunBilgileriList.add(new UrunBilgileri("EV TÜPÜ"));
        urunBilgileriList.add(new UrunBilgileri("UZUN EV TÜPÜ"));
        urunBilgileriList.add(new UrunBilgileri("TİCARİ TÜP"));
        urunBilgileriList.add(new UrunBilgileri("SANAYİ TÜPÜ"));
        UrunlerOzelAdaptor adaptor = new UrunlerOzelAdaptor(getActivity(),urunBilgileriList);
        listView.setAdapter(adaptor);
    }
}
