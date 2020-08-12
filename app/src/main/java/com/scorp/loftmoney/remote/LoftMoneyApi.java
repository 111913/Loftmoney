package com.scorp.loftmoney.remote;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoftMoneyApi {

    //REST RESTFul
    //https://verdant-violet.glitch.me/items?type=expense
    //https://verdant-violet.glitch.me/items?type=income
    @GET("./items")
    Single<List<LoftMoneyItem>> getItem(@Query("auth-token") String token, @Query("type") String type);

    //https://verdant-violet.glitch.me/items/add?price=1200&name=сковородка&type=expense
    @POST("./items/add")
    @FormUrlEncoded
    Completable addItem(@Field("auth-token") String token,
                        @Field("price") String price,
                        @Field("name") String name,
                        @Field("type") String type);
}
