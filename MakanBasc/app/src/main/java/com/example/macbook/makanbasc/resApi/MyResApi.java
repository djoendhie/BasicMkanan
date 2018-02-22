package com.example.macbook.makanbasc.resApi;

import com.example.macbook.makanbasc.model.ModelMakanan;
import com.example.macbook.makanbasc.model.ModelUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by macbook on 2/22/18.
 */

public interface MyResApi {

    @FormUrlEncoded
    @POST("loginuser.php")
    Call<ModelUser> loginUser(
            @Field("edtusername") String strUsername,
            @Field("edtpassword") String strpassword,
            @Field("vslevel") String strlevel
    );


    @GET("getdatamakanan.php")
    Call<ModelMakanan>datamakanan(
    );

}

