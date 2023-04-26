package com.abym.abha.Network;

import com.google.gson.JsonElement;
import java.util.ArrayList;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({ "Content-Type: application/json; charset=utf-8"})
    @POST("{url}")
    Call<ResponseBody> abhaRequest(@Path("url") String url, @Body RequestBody requestBody);

    @Headers({ "Content-Type: application/json; charset=utf-8"})
    @GET("{url}")
    Call<ResponseBody> abhaGetRequest(@Path("url") String url);

}

