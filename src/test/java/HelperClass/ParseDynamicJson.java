package HelperClass;

import TestBase.TestBase;
import java.util.Iterator;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDynamicJson extends TestBase {
    public static Logger log=getMyLogger(ParseDynamicJson.class);
    Response response;

    public static void parseObject(JSONObject json, String key) {
        // System.out.println(json.has(key));
        System.out.println(json.get(key));
    }

    /**
     * This method is used to parse the dynamic response API
     * @author Arun Kumar
     * @param json
     * @param key
     */
    public static void getKey(JSONObject json, String key) {

        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;

        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {

                    if (json.get(nextKeys) instanceof JSONObject) {
                        if (exists == false) {
                            getKey(json.getJSONObject(nextKeys), key);
                        }

                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonarray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            String jsonarrayString = jsonarray.get(i).toString();
                            JSONObject innerJSOn = new JSONObject(jsonarrayString);

                            if (exists == false) {
                                getKey(innerJSOn, key);
                            }

                        }

                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        } else {
            parseObject(json, key);
        }

    }

    /**
     * This method is used to validate responseKeyValidationfromArray
     * @author Arun Kumar
     * @param response
     * @param key
     */
    public static void responseKeyValidationfromArray(Response response, String key) {
        try {
            JSONArray array = new JSONArray(response.getBody().asString());
            for(int i=0; i<array.length();i++) {
                JSONObject obj = array.getJSONObject(i);
                log.info(key+" Validate values are  " + obj.get(key));
            }
        } catch (Exception e) {
            log.info(e.fillInStackTrace());
        }
    }

    /**
     * This method is used to validate responseKeyValidationFromJsonObject
     * @author Arun Kumar
     * @param response
     * @param key
     */
    public static void responseKeyValidationFromJsonObject(Response response, String key) {
        try {
            JSONObject json = new JSONObject(response.getBody().asString());
            if(json.has(key) && json.get(key)!= null) {
                log.info( "Sucessfully validated value of " + key + " It is " + json.get(key));
            }else {
                log.info("Key is not availble");
            }
        } catch (Exception e) {
            log.info(e.fillInStackTrace());
        }
    }
}
