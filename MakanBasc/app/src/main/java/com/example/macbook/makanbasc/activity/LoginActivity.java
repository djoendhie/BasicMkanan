package com.example.macbook.makanbasc.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.macbook.makanbasc.MainActivity;
import com.example.macbook.makanbasc.R;
import com.example.macbook.makanbasc.helper.MyConstant;
import com.example.macbook.makanbasc.helper.MyFunction;
import com.example.macbook.makanbasc.helper.SessionManager;
import com.example.macbook.makanbasc.model.ModelUser;
import com.example.macbook.makanbasc.resApi.MyResApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends SessionManager {

    @BindView(R.id.regUsername)
    EditText regUsername;
    @BindView(R.id.regPass)
    TextInputEditText regPass;
    @BindView(R.id.regAdmin)
    RadioButton regAdmin;
    @BindView(R.id.regUserbiasa)
    RadioButton regUserbiasa;
    @BindView(R.id.regBtnLogin)
    Button regBtnLogin;
    @BindView(R.id.regBtnRegister)
    Button regBtnRegister;

    String strLevel;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (regAdmin.isChecked()){
            strLevel="admin";
        }else {
            strLevel = "user Biasa";
        }
    }

    @OnClick({R.id.regAdmin, R.id.regUserbiasa, R.id.regBtnLogin, R.id.regBtnRegister})
    public void onViewClicked(View view) {
        String username = regUsername.getText().toString();
        String password = regPass.getText().toString();
        switch (view.getId()) {
            case R.id.regAdmin:
              strLevel = "Admin";
                break;
            case R.id.regUserbiasa:
                strLevel = "User Biasa";
                break;
            case R.id.regBtnLogin:

                if (TextUtils.isEmpty(username)){
                    regUsername.setError("username tidak boleh kosong");
                }else if (TextUtils.isEmpty(password)){
                    regPass.setError("password tidak boleh kosong");
                }else if (password.length()<6){
                    regPass.setError("minimal password 6 karakter");
                }else {
                    loginuser();
                }
                break;
            case R.id.regBtnRegister:
                break;
        }
    }

    private void loginuser() {
        final ProgressDialog dialog = ProgressDialog.show(c, "Proses Get Data", "Loading Bang...");

        //inisialisasi retro
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyConstant.BASE_URL)
                //gson yg memparsing dari json pada suatu services menjadi java object lol
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MyResApi api = retrofit.create(MyResApi.class);
        Call<ModelUser> modelUserCall = api.loginUser(username,password,strLevel);

         modelUserCall.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                //isi e
                dialog.dismiss();
                String result = response.body().getResult();
                String msg = response.body().getMsg();
                if (response.isSuccessful()) {
                    if (result.equals("1")) {
                        mytoast(msg);
                        aksesclass(MainActivity.class);

                        String iduser = response.body().getUser().getIdUser();
                        sessionManager.setIdUser(iduser);
                        finish();
                    } else {
                        mytoast(msg);
                    }
                }

            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {

                mytoast("gagal bg"+t.getMessage());
                dialog.dismiss();
            }
        });


    }
}













