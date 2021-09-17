/**
 *  Retrofit.java
 *
 *  This interface class represents POST request.
 *
 *  Created by
 *  Thitiporn Sukpartcharoen
 *
 *  11 September 2021
 */
package com.example.myapplication;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("/api/mobiles")
    Call<List<Mobile>> getMobileAll();
    @GET("/api/mobiles/{mobile_id}/images/")
    Call<List<Image>> getMobileImage(@Path("mobile_id") int mobile_id);
}
