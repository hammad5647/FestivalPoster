package com.example.festival_posters;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FestivalPage extends AppCompatActivity {
    TextView festName, businessNameOnImage, numberOnImage, emailOnImage, okButton, fon1, fon2, fon3, fon4, fon5;
    ImageView image1, image2, image3, image4, image5, festMainImage, backBtn;
    Button saveBtn, shareBtn;
    TextInputEditText enterBusiness, enterMobile, enterEmail;
    LinearLayout bgBlack, bgWhite, bgGrey, bgYellow, bgGreen, bgBlue, bgOrange, bgPurple, fontStyleOkBtn, fontColorOkBtn;
    RelativeLayout businessName, mobileNumber, emailAddrss, festImageView;
    LinearLayout editTxt, editColor, editFont;
    Uri Uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_page);
        festPage_Bind();
        getData();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String business_name = enterBusiness.getText().toString();
                String mobile_number = enterMobile.getText().toString();
                String email_address = enterEmail.getText().toString();

                businessNameOnImage.setText(business_name);
                numberOnImage.setText(mobile_number);
                emailOnImage.setText(email_address);

                if (business_name.isEmpty()) {
                    enterBusiness.setError("Business Name Required");
                } else if (mobile_number.isEmpty()) {
                    enterMobile.setError("Mobile Number Required");
                } else if (email_address.isEmpty()) {
                    enterEmail.setError("Email Required");
                }
            }
        });
        editColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textColorDialogue();
            }
        });
        editFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontStyle();
            }
        });

        getOnBackPressedDispatcher().addCallback(FestivalPage.this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                alertBox();
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
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

    Bitmap convertImage() {

        festImageView.setDrawingCacheEnabled(true);

        Bitmap bitmap = Bitmap.createBitmap(festImageView.getDrawingCache());

        festImageView.setDrawingCacheEnabled(false);

        return bitmap;
    }

    void share() {

        Bitmap bitmap = convertImage();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");
        Uri imageuri = Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageuri);
        startActivity(Intent.createChooser(share, "select"));

    }

    void save() {

        Bitmap bitmap = convertImage();
        SimpleDateFormat sdf = new SimpleDateFormat("hh_mm_ss_dd_MM_yyyy");
        Date date = new Date();
        String image = sdf.format(date);

        File filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String path = filename.getPath() + "/" + image + ".png";
        File file = new File(path);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void festPage_Bind() {
        //BarName
        festName = findViewById(R.id.festName);
        backBtn = findViewById(R.id.backBtn);

        //image_Id
        festMainImage = findViewById(R.id.festMainImage);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);

        //imageTextViews
        businessNameOnImage = findViewById(R.id.businessNameOnImage);
        numberOnImage = findViewById(R.id.numberOnImage);
        emailOnImage = findViewById(R.id.emailOnImage);

        //edits binding
        enterBusiness = findViewById(R.id.enterBusiness);
        enterMobile = findViewById(R.id.enterMobile);
        enterEmail = findViewById(R.id.enterEmail);

        businessName = findViewById(R.id.businessName);
        mobileNumber = findViewById(R.id.mobileNumber);
        emailAddrss = findViewById(R.id.emailAddress);
        okButton = findViewById(R.id.okButton);
        shareBtn = findViewById(R.id.shareBtn);
        saveBtn = findViewById(R.id.saveBtn);

        editTxt = findViewById(R.id.editTxt);
        editColor = findViewById(R.id.editColor);
        editFont = findViewById(R.id.editFont);


    }

    void fontStyle() {
        Dialog dialog = new Dialog(FestivalPage.this);
        dialog.setContentView(R.layout.editfontstyle);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        fontStyleOkBtn = dialog.findViewById(R.id.fontStyleOkBtn);
        fontStyleOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        fon1 = dialog.findViewById(R.id.font1);
        fon2 = dialog.findViewById(R.id.font2);
        fon3 = dialog.findViewById(R.id.font3);
        fon4 = dialog.findViewById(R.id.font4);
        fon5 = dialog.findViewById(R.id.font5);

        fon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Typeface dancingFont = ResourcesCompat.getFont(FestivalPage.this, R.font.dancingfont);
                businessNameOnImage.setTypeface(dancingFont);
                numberOnImage.setTypeface(dancingFont);
                emailOnImage.setTypeface(dancingFont);
            }
        });
        fon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface dancingFont = ResourcesCompat.getFont(FestivalPage.this, R.font.sedanregular);
                businessNameOnImage.setTypeface(dancingFont);
                numberOnImage.setTypeface(dancingFont);
                emailOnImage.setTypeface(dancingFont);
            }
        });
        fon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface dancingFont = ResourcesCompat.getFont(FestivalPage.this, R.font.dmserifregular);
                businessNameOnImage.setTypeface(dancingFont);
                numberOnImage.setTypeface(dancingFont);
                emailOnImage.setTypeface(dancingFont);
            }
        });
        fon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface dancingFont = ResourcesCompat.getFont(FestivalPage.this, R.font.dancingfont);
                businessNameOnImage.setTypeface(dancingFont);
                numberOnImage.setTypeface(dancingFont);
                emailOnImage.setTypeface(dancingFont);
            }
        });
        fon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface dancingFont = ResourcesCompat.getFont(FestivalPage.this, R.font.dancingfont);
                businessNameOnImage.setTypeface(dancingFont);
                numberOnImage.setTypeface(dancingFont);
                emailOnImage.setTypeface(dancingFont);
            }
        });
    }

    void textColorDialogue() {
        Dialog dialog = new Dialog(FestivalPage.this);
        dialog.setContentView(R.layout.textcolordialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        fontColorOkBtn = dialog.findViewById(R.id.fontColorOkBtn);

        bgBlack = dialog.findViewById(R.id.bgBlack);
        bgWhite = dialog.findViewById(R.id.bgWhite);
        bgGrey = dialog.findViewById(R.id.bgGrey);
        bgYellow = dialog.findViewById(R.id.bgYellow);
        bgGreen = dialog.findViewById(R.id.bgGreen);
        bgBlue = dialog.findViewById(R.id.bgBlue);
        bgOrange = dialog.findViewById(R.id.bgOrange);
        bgPurple = dialog.findViewById(R.id.bgPurple);

        fontColorOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        bgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                businessNameOnImage.setTextColor(getColor(R.color.black));
                emailOnImage.setTextColor(getColor(R.color.black));
                numberOnImage.setTextColor(getColor(R.color.black));
            }
        });
        bgWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                businessNameOnImage.setTextColor(getColor(R.color.white));
                emailOnImage.setTextColor(getColor(R.color.white));
                numberOnImage.setTextColor(getColor(R.color.white));

            }
        });
        bgGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNameOnImage.setTextColor(getColor(R.color.grey));
                emailOnImage.setTextColor(getColor(R.color.grey));
                numberOnImage.setTextColor(getColor(R.color.grey));

            }
        });
        bgYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNameOnImage.setTextColor(getColor(R.color.yellow));
                emailOnImage.setTextColor(getColor(R.color.yellow));
                numberOnImage.setTextColor(getColor(R.color.yellow));

            }
        });
        bgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNameOnImage.setTextColor(getColor(R.color.green));
                emailOnImage.setTextColor(getColor(R.color.green));
                numberOnImage.setTextColor(getColor(R.color.green));

            }
        });
        bgBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNameOnImage.setTextColor(getColor(R.color.blue));
                emailOnImage.setTextColor(getColor(R.color.blue));
                numberOnImage.setTextColor(getColor(R.color.blue));

            }
        });
        bgOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNameOnImage.setTextColor(getColor(R.color.orange));
                emailOnImage.setTextColor(getColor(R.color.orange));
                numberOnImage.setTextColor(getColor(R.color.orange));

            }
        });
        bgPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businessNameOnImage.setTextColor(getColor(R.color.purple));
                emailOnImage.setTextColor(getColor(R.color.purple));
                numberOnImage.setTextColor(getColor(R.color.purple));

            }
        });
    }

    void getData() {
        Intent intent1 = getIntent();
        String festmainName = intent1.getStringExtra("Name");

        festName.setText(festmainName);

        int main_image = intent1.getIntExtra("mainImage", 0);
        int image_1 = intent1.getIntExtra("moreImage1", 0);
        int image_2 = intent1.getIntExtra("moreImage2", 0);
        int image_3 = intent1.getIntExtra("moreImage3", 0);
        int image_4 = intent1.getIntExtra("moreImage4", 0);
        int image_5 = intent1.getIntExtra("moreImage5", 0);

        Picasso.get().load(main_image).into(festMainImage);
        Picasso.get().load(image_1).into(image1);
        Picasso.get().load(image_2).into(image2);
        Picasso.get().load(image_3).into(image3);
        Picasso.get().load(image_4).into(image4);
        Picasso.get().load(image_5).into(image5);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image_1).into(festMainImage);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image_2).into(festMainImage);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image_3).into(festMainImage);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image_4).into(festMainImage);
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(image_5).into(festMainImage);
            }
        });

    }
}