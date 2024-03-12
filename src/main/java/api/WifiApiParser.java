package api;

import com.google.gson.Gson;

public class WifiApiParser {
    private Gson gson = new Gson();

    public WifiDto parse(String json){
        try{
            BaseDto wifiDto = gson.fromJson(json, BaseDto.class);
            return wifiDto.getTbPublicWifiInfo();

        }catch (Exception e){
            throw new RuntimeException();
        }


    }
}