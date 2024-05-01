package com.example.festival_posters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.InputQueue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    RelativeLayout navratriFest, ramnavmiFest, eidFest, diwaliFest, utranFest, eidulAdhaFest, rakshabandhanFest, dhanterasFest, gudipadwaFest, holiFest;
    ImageView menuBtn, searchBtn, navratriImage, ramnavmiImage, eidImage, diwaliImage, utranImage, eidulAdhaImage, rakshabandhanImage, dhanterasImage, gudipadwaImage, holiImage;
    TextView navartriName, ramnavmiName, eidName, diwaliName, dhanterasName, eiduladhaName, gudipadwaName, rakshabandhanName, holiName, makarsakrantiName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        init_IdBinding();

        getOnBackPressedDispatcher().addCallback(MainActivity.this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                alertBox();
            }
        });

        navratriFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navratri_Fest();
            }
        });

        ramnavmiFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ramnavmi_fest();
            }
        });

        eidFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eid_Fest();
            }
        });

        diwaliFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diwali_Fest();
            }
        });

        utranFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utran_Fest();
            }
        });

        eidulAdhaFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eid_ulAdha_Fest();
            }
        });

        rakshabandhanFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rakshabandhan_Fest();
            }
        });

        dhanterasFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dhanteras_Fest();
            }
        });

        gudipadwaFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gudipadwa_Fest();
            }
        });

        holiFest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holi_Fest();
            }
        });

    }

    void alertBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are You Sure ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void init_IdBinding() {

        //FestivalId//
        menuBtn = findViewById(R.id.menuBtn);
        searchBtn = findViewById(R.id.searchBtn);
        navratriFest = findViewById(R.id.navratriFest);
        ramnavmiFest = findViewById(R.id.ramnavmifest);
        eidFest = findViewById(R.id.eidFest);
        diwaliFest = findViewById(R.id.diwaliFest);
        utranFest = findViewById(R.id.utranFest);
        eidulAdhaFest = findViewById(R.id.eidulAdhaFest);
        rakshabandhanFest = findViewById(R.id.rakshabandhanFest);
        dhanterasFest = findViewById(R.id.dhanterasFest);
        gudipadwaFest = findViewById(R.id.gudipadwaFest);
        holiFest = findViewById(R.id.holiFest);

        //imageId//
        navratriImage = findViewById(R.id.navratriImage);
        ramnavmiImage = findViewById(R.id.ramnavmiImage);
        eidImage = findViewById(R.id.eidImage);
        diwaliImage = findViewById(R.id.diwaliImage);
        utranImage = findViewById(R.id.utranImage);
        eidulAdhaImage = findViewById(R.id.eidulAdhaImage);
        rakshabandhanImage = findViewById(R.id.rakshabandhanImage);
        dhanterasImage = findViewById(R.id.dhanterasImage);
        gudipadwaImage = findViewById(R.id.gudipadwaImage);
        holiImage = findViewById(R.id.holiImage);
        //festivalNameId//
        navartriName = findViewById(R.id.navratriName);
        ramnavmiName = findViewById(R.id.ramnavmiName);
        eidName = findViewById(R.id.eidName);
        diwaliName = findViewById(R.id.diwaliName);
        makarsakrantiName = findViewById(R.id.makarsakrantiName);
        eiduladhaName = findViewById(R.id.eiduladhaName);
        rakshabandhanName = findViewById(R.id.rakshabandhanName);
        dhanterasName = findViewById(R.id.dhanterasName);
        gudipadwaName = findViewById(R.id.gudipadwaName);
        holiName = findViewById(R.id.holiName);
    }

    void navratri_Fest() {
        String name_navratri = navartriName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.navratri);


        intent.putExtra("moreImage1", R.drawable.navratri2);
        intent.putExtra("moreImage2", R.drawable.navratri1);
        intent.putExtra("moreImage3", R.drawable.navratri3);
        intent.putExtra("moreImage4", R.drawable.navratri4);
        intent.putExtra("moreImage5", R.drawable.navratri5);


        intent.putExtra("Name", name_navratri);
        startActivity(intent);
    }

    void ramnavmi_fest() {
        String name_ramnavmi = ramnavmiName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.ramnavmi1);

        intent.putExtra("moreImage1", R.drawable.ramnavmi1);
        intent.putExtra("moreImage2", R.drawable.ramnavmi2);
        intent.putExtra("moreImage3", R.drawable.ramnavmi3);
        intent.putExtra("moreImage4", R.drawable.ramnavmi4);
        intent.putExtra("moreImage5", R.drawable.ramnavmi5);

        intent.putExtra("Name", name_ramnavmi);
        startActivity(intent);
    }

    void eid_Fest() {
        String name_eid = eidName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.eidulfitr);

        intent.putExtra("moreImage1", R.drawable.eid5);
        intent.putExtra("moreImage2", R.drawable.eid2);
        intent.putExtra("moreImage3", R.drawable.eid3);
        intent.putExtra("moreImage4", R.drawable.eid4);
        intent.putExtra("moreImage5", R.drawable.eid1);

        intent.putExtra("Name", name_eid);
        startActivity(intent);
    }

    void diwali_Fest() {
        String name_diwali = diwaliName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.diwali);

        intent.putExtra("moreImage1", R.drawable.diwali5);
        intent.putExtra("moreImage2", R.drawable.diwali2);
        intent.putExtra("moreImage3", R.drawable.diwali3);
        intent.putExtra("moreImage4", R.drawable.diwali4);
        intent.putExtra("moreImage5", R.drawable.diwali1);

        intent.putExtra("Name", name_diwali);
        startActivity(intent);
    }

    void utran_Fest() {
        String name_utran = makarsakrantiName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.utran);

        intent.putExtra("moreImage1", R.drawable.utran5);
        intent.putExtra("moreImage2", R.drawable.utran2);
        intent.putExtra("moreImage3", R.drawable.utran3);
        intent.putExtra("moreImage4", R.drawable.utran4);
        intent.putExtra("moreImage5", R.drawable.utran1);

        intent.putExtra("Name", name_utran);
        startActivity(intent);
    }

    void eid_ulAdha_Fest() {
        String name_eiduladha = eiduladhaName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.eiduladha);

        intent.putExtra("moreImage1", R.drawable.eidaladha5);
        intent.putExtra("moreImage2", R.drawable.eidaladha2);
        intent.putExtra("moreImage3", R.drawable.eidaladha3);
        intent.putExtra("moreImage4", R.drawable.eidaladha4);
        intent.putExtra("moreImage5", R.drawable.eidaladha1);

        intent.putExtra("Name", name_eiduladha);
        startActivity(intent);
    }

    void rakshabandhan_Fest() {
        String name_raksha = rakshabandhanName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.rakshabandhan);

        intent.putExtra("moreImage1", R.drawable.rakshabandhan3);
        intent.putExtra("moreImage2", R.drawable.rakshabandhan2);
        intent.putExtra("moreImage3", R.drawable.rakshabandhan1);
        intent.putExtra("moreImage4", R.drawable.rakshabandhan4);
        intent.putExtra("moreImage5", R.drawable.rakshabandhan5);

        intent.putExtra("Name", name_raksha);
        startActivity(intent);
    }

    void dhanteras_Fest() {
        String name_dhanteras = dhanterasName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.dhanteras);

        intent.putExtra("moreImage1", R.drawable.dhanteras5);
        intent.putExtra("moreImage2", R.drawable.dhanteras2);
        intent.putExtra("moreImage3", R.drawable.dhanteras3);
        intent.putExtra("moreImage4", R.drawable.dhanteras4);
        intent.putExtra("moreImage5", R.drawable.dhanteras1);

        intent.putExtra("Name", name_dhanteras);
        startActivity(intent);
    }

    void gudipadwa_Fest() {
        String name_gudipadwa = gudipadwaName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.gudipadwa);

        intent.putExtra("moreImage1", R.drawable.gudipadwa5);
        intent.putExtra("moreImage2", R.drawable.gudipadwa2);
        intent.putExtra("moreImage3", R.drawable.gudipadwa3);
        intent.putExtra("moreImage4", R.drawable.gudipadwa4);
        intent.putExtra("moreImage5", R.drawable.gudipadwa1);

        intent.putExtra("Name", name_gudipadwa);
        startActivity(intent);
    }

    void holi_Fest() {
        String name_holi = holiName.getText().toString();

        Intent intent = new Intent(MainActivity.this, FestivalPage.class);
        intent.putExtra("mainImage", R.drawable.holi);

        intent.putExtra("moreImage1", R.drawable.holi3);
        intent.putExtra("moreImage2", R.drawable.holi2);
        intent.putExtra("moreImage3", R.drawable.holi1);
        intent.putExtra("moreImage4", R.drawable.holi4);
        intent.putExtra("moreImage5", R.drawable.holi5);

        intent.putExtra("Name", name_holi);
        startActivity(intent);
    }
}