package com.brandonjf.volleycupid.okclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by brandon on 10/14/15.
 */
public class AuthResponse {

    @SerializedName("access_token")
    @Expose
    public String access_token;
    @SerializedName("token_type")
    @Expose
    public String token_type;
    @SerializedName("expires_in")
    @Expose
    public String expires_in;
    @SerializedName("refresh_token")
    @Expose
    public String refresh_token;
    private Long expiration_time;

    public void configureExpirationTime(){
        Long currentTime = System.currentTimeMillis() / 1000L;
        Long l = Long.parseLong(expires_in, 10);
        this.expiration_time = currentTime + l;
    }

   public String getExpirationTime(){
       return String.valueOf(this.expiration_time);
   }

    public static boolean isPastExpiration(String expiresAt){
        Long currentTime = System.currentTimeMillis() / 1000L;
        if( currentTime > Long.parseLong(expiresAt)){
            return true;
        } else {
            return false;
        }
    }


}
