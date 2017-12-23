package app.aygazprojesi;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Omer on 30.4.2017.
 */

public class EfektIslemleri {

    public void butonaEfektVer(View v){
        Button view = (Button)v;
        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
        view.invalidate();
    }
    public void butonEfektiniSil(View v){
        Button view = (Button)v;
        view.getBackground().clearColorFilter();
        view.invalidate();
    }
}
