package com.example.macbook.makanbasc.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbook.makanbasc.R;
import com.example.macbook.makanbasc.helper.MyConstant;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMakananActivity extends AppCompatActivity {

    @BindView(R.id.imgmakanan)
    ImageView imgmakanan;
    @BindView(R.id.txtnama)
    TextView txtnama;
    @BindView(R.id.txtharga)
    TextView txtharga;
    @BindView(R.id.txtstatus)
    TextView txtstatus;
    @BindView(R.id.txtdetail)
    TextView txtdetail;

    Typeface gayatulisan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);
        ButterKnife.bind(this);

        gayatulisan = Typeface.createFromAsset(getAssets(),"font/harvest.ttf");
        txtnama.setTypeface(gayatulisan);
        txtdetail.setTypeface(gayatulisan);
        txtdetail.setTypeface(gayatulisan);
        txtstatus.setTypeface(gayatulisan);

        Intent terima = getIntent();
        txtnama.setText("nama"+terima.getStringExtra("nm"));
        txtharga.setText("harga"+terima.getStringExtra("hm"));
        txtdetail.setText("detail"+terima.getStringExtra("dm"));
        txtstatus.setText("status"+terima.getStringExtra("sm"));

        Picasso.with(DetailMakananActivity.this).load(MyConstant.IMAGE_URL+terima
                .getStringExtra("gm")).into((imgmakanan));

    }

}
