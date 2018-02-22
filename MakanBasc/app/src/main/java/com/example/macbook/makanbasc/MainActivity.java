package com.example.macbook.makanbasc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.macbook.makanbasc.adapter.ListMakananAdapter;
import com.example.macbook.makanbasc.helper.MyConstant;
import com.example.macbook.makanbasc.helper.MyFunction;
import com.example.macbook.makanbasc.helper.SessionManager;
import com.example.macbook.makanbasc.model.MakananItem;
import com.example.macbook.makanbasc.model.ModelMakanan;
import com.example.macbook.makanbasc.model.ModelUser;
import com.example.macbook.makanbasc.resApi.MyResApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends SessionManager {

    @BindView(R.id.daftarmakanan)
    RecyclerView daftarmakanan;

    RecyclerView.LayoutManager layoutManager;

    List<MakananItem>makananlist;

    String pesan, sukses, makanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(c);
        daftarmakanan.setLayoutManager(layoutManager);

        getdatamakanan();


    }

    private void getdatamakanan() {
        final ProgressDialog dialog = ProgressDialog.show(c, "Proses Get Data", "Loading Bang...");

        //inisialisasi retro
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyConstant.BASE_URL)
                //gson yg memparsing dari json pada suatu services menjadi java object lol
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyResApi api = retrofit.create(MyResApi.class);

        Call<ModelMakanan> modelMakananCall = api.datamakanan();

        modelMakananCall.enqueue(new Callback<ModelMakanan>() {


            @Override
            public void onResponse(Call<ModelMakanan> call, Response<ModelMakanan> response) {

                dialog.dismiss();
                String isi = response.body().getPesan();
                String daet = response.body().getSukses();
                if (response.isSuccessful()){
                    String pesan = response.body().getPesan();
                    String sukses= response.body().getSukses();
                    dialog.dismiss();
                    if (sukses.equals("true")){
                        mytoast(pesan);
                        makananlist = response.body().getMakanan();
                        String[] items = new String[makananlist.size()];
                        for (int i=0;i<makananlist.size();i++){
                            items[i] =makananlist.get(i).getNama1();
                        }

                        ListMakananAdapter adapter = new ListMakananAdapter(c, makananlist);

                        daftarmakanan.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<ModelMakanan> call, Throwable t) {

                mytoast("GAgal bg sabar" + t.getMessage());
                dialog.dismiss();
            }
        });




    }
}
