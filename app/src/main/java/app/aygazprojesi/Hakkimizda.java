package app.aygazprojesi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Omer on 1.5.2017.
 */

public class Hakkimizda  extends Fragment implements View.OnClickListener {

    private ImageView imageAdres,imageTel,imageMail;
    private TextView tvAdres,tvTel,tvMail;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.hakkimizdatab, container, false);
        baslangic();
        return rootView;
    }

    private void baslangic(){
        imageAdres = (ImageView)rootView.findViewById(R.id.imageKonum);
        imageMail = (ImageView)rootView.findViewById(R.id.imageMail);
        imageTel = (ImageView)rootView.findViewById(R.id.imageTel);
        tvAdres = (TextView)rootView.findViewById(R.id.tvKonum);
        tvMail = (TextView)rootView.findViewById(R.id.tvMail);
        tvTel = (TextView)rootView.findViewById(R.id.tvTel);
        tvTel.setOnClickListener(this);
        tvMail.setOnClickListener(this);
        tvAdres.setOnClickListener(this);
        imageAdres.setOnClickListener(this);
        imageMail.setOnClickListener(this);
        imageTel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==imageAdres || v==tvAdres){

        }
        if(v==imageMail || v==tvMail){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto: aygazuygulamasi@gmail.com"));
            startActivity(Intent.createChooser(emailIntent, "Iletişime geç."));
        }
        if(v==imageTel || v==tvTel){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0554-379-22-05"));
            startActivity(intent);
        }
    }
}