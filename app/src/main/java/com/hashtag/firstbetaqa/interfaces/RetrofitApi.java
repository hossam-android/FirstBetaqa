package com.hashtag.firstbetaqa.interfaces;




import com.hashtag.firstbetaqa.Model.AreaDataModel;
import com.hashtag.firstbetaqa.Model.CitiesDataModel;
import com.hashtag.firstbetaqa.Model.CommentDataModel;
import com.hashtag.firstbetaqa.Model.ContactUsDataModel;
import com.hashtag.firstbetaqa.Model.DefaultDataModel;
import com.hashtag.firstbetaqa.Model.MainCatsDataModel;
import com.hashtag.firstbetaqa.Model.NewsDataModel;
import com.hashtag.firstbetaqa.Model.NewsDetailsDataModel;
import com.hashtag.firstbetaqa.Model.OfferDataModel;
import com.hashtag.firstbetaqa.Model.OfferDetailsDataModel;
import com.hashtag.firstbetaqa.Model.PageDataModel;
import com.hashtag.firstbetaqa.Model.PartnersDataModel;
import com.hashtag.firstbetaqa.Model.RegionDataModel;
import com.hashtag.firstbetaqa.Model.RequestCardDataModel;
import com.hashtag.firstbetaqa.Model.SearchDataModel;
import com.hashtag.firstbetaqa.Model.SendTokenDataModel;
import com.hashtag.firstbetaqa.Model.StoreDetailsDataModel;
import com.hashtag.firstbetaqa.Model.SubCatsDataModel;
import com.hashtag.firstbetaqa.Model.SubCountCatsDataModel;
import com.hashtag.firstbetaqa.Model.SubRegionDataModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitApi {


    @GET("api.php?mod=menu1")
    Call<List<MainCatsDataModel>> getMainCats(@Query("lang") String lng);

    @GET("api.php?mod=regions")
    Call<List<SubCatsDataModel>> getSubCats(@Query("cid") String cid, @Query("lang") String lng);

    @GET("api.php?mod=cities2")
    Call<List<RegionDataModel>> getRegionForSubCats(@Query("cid") String cid, @Query("region") String region, @Query("lang") String lng);

    @GET("api.php?mod=market")
    Call<List<SubRegionDataModel>> getSubRegion(@Query("cid") String cid, @Query("city") String city, @Query("lang") String lng);

    @GET("api.php?mod=market")
    Call<StoreDetailsDataModel> getStoreDetails(@Query("itemid") String itemid, @Query("lang") String lng);

    @GET("api.php?mod=news")
    Call<List<NewsDataModel>> getNews(@Query("cid") String cid, @Query("lang") String lng, @Query("page") int page);

    @GET("api.php?mod=news")
    Call<NewsDetailsDataModel> getNewsDetails(@Query("nid") String nid, @Query("lang") String lng);

    @GET("api.php?mod=comments")
    Call<List<CommentDataModel>> getComments(@Query("newsid") String newsid, @Query("lang") String lng);

    @GET("api.php?mod=socialmedia")
    Call<ContactUsDataModel> getMyInformation(@Query("lang") String lng);

    @GET("api.php?mod=news")
    Call<List<OfferDataModel>> getOffers(@Query("cid") String cid, @Query("lang") String lng, @Query("page") int page);

    @GET("api.php?mod=news")
    Call<OfferDetailsDataModel> getOfferDetails(@Query("nid") String nid, @Query("lang") String lng);

    @GET("api.php?mod=searchm")
    Call<List<SearchDataModel>> search(@Query("q") String q, @Query("city") String city, @Query("district") String district, @Query("lang") String lng, @Query("page") int page);

    @GET
    Call<List<RequestCardDataModel>> requestCardInfo(@Url String url, @Query("lang") String lng);

    @FormUrlEncoded
    @POST("api.php?mod=commentadd")
    Call<DefaultDataModel> sendComment(@Field("newsid") String newsid,
                                       @Field("username") String username,
                                       @Field("email") String email,
                                       @Field("content") String content,
                                       @Field("lang") String lng

    );

    @GET
    Call<List<PageDataModel>> getPageContent(@Url String url, @Query("lang") String lng);

    @FormUrlEncoded
    @POST("api.php?mod=contactus")
    Call<DefaultDataModel> sendContactUs(@Field("email") String email,
                                         @Field("name") String name,
                                         @Field("phone") String phone,
                                         @Field("message") String message,
                                         @Field("lang") String lng

    );

    @FormUrlEncoded
    @POST("api.php?mod=requestcard")
    Call<DefaultDataModel> sendCard(@Field("fullname") String fullname,
                                    @Field("address") String address,
                                    @Field("phone") String phone,
                                    @Field("moreinfo") String moreinfo

    );


    @FormUrlEncoded
    @POST("api.php?mod=storejoin")
    Call<DefaultDataModel> sendJoin(@Field("fullname") String fullname,
                                    @Field("email") String email,
                                    @Field("phone") String phone,
                                    @Field("moreinfo") String moreinfo,
                                    @Field("store_name") String store_name,
                                    @Field("store_address") String store_address

    );

    @GET("api.php?mod=android")
    Call<SendTokenDataModel> sendToken(@Query("token") String token);

    @GET("api.php?mod=cities")
    Call<List<CitiesDataModel>> getCities(@Query("lang") String lang);

    @GET
    Call<List<AreaDataModel>> getArea(@Url String url, @Query("lang") String lang);

    @GET
    Call<List<PartnersDataModel>> getPartners(@Url String url, @Query("lang") String lang);

    @GET("api.php?mod=econcats")
    Call<List<AreaDataModel>> getSectors(@Query("lang") String lang);

    @Multipart
    @POST("api.php?mod=diradd")
    Call<DefaultDataModel> joinPartners(@Part("company") RequestBody company,
                                        @Part("city") RequestBody city,
                                        @Part("site") RequestBody site,
                                        @Part("employees") RequestBody employees,
                                        @Part("category") RequestBody category,
                                        @Part("name") RequestBody name,
                                        @Part("email") RequestBody email,
                                        @Part("jobtitle") RequestBody jobtitle,
                                        @Part("phone") RequestBody phone,
                                        @Part("moreinfo") RequestBody moreinfo,
                                        @Part MultipartBody.Part photo
    );

    @GET
    Call<List<SubCountCatsDataModel>> getSubCountCats(@Url String url, @Query("lang") String lang);

}
