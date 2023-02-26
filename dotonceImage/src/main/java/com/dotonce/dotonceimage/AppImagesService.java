package com.dotonce.dotonceimage;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface AppImagesService {
    @Multipart
    @POST("upload_image.php")
    Call<String> UploadImage(
            @Part MultipartBody.Part file,
            @Part MultipartBody.Part folder);
}

