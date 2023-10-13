package com.achpay.wallet.util;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hy on 2017/10/21.
 */

public class NumAddUtils {

    private final String savenumadd = "SAVE_NUM_ADD";

    private static NumAddUtils loadUtills = null;
    private final Activity mContext;
    private JSONObject json;
    private long MINUTE_TIME = 60;
    private long HOUR_TIME = MINUTE_TIME * 60;
    private long SIX_HOUR_TIME = HOUR_TIME * 6;


    public static NumAddUtils getInstance(Activity context) {
        if (loadUtills == null) {
            loadUtills = new NumAddUtils(context);
        }
        return loadUtills;
    }


    public NumAddUtils(Activity context) {
        mContext = context;
    }


    public int defaultDataType() {

        if (getUpdataTime() == 0) {
            int rankcount = (int) (2000 + Math.random() * (2500 - 2000 + 1));
            long time = System.currentTimeMillis() / 1000L;
            try {
                json = new JSONObject();
                json.put("rankcount", rankcount);
                json.put("updataTime", time);

                saveData(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        long curTime = System.currentTimeMillis() / 1000L;

        long differTime = (curTime - getUpdataTime()) / MINUTE_TIME;
        if (differTime > 1) {
            int ranCount = (int) (1 + Math.random() * (10 - 1 + 1));

            setRankCount(getRankcount() + ranCount);
            setUpdataTime(curTime);
        }

        return getRankcount();
    }

    public int getRankcount() {
        if (null == json) json = getData();
        int rankcount = 0;
        try {
            rankcount = json.getInt("rankcount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rankcount;
    }

    private long getUpdataTime() {
        if (null == json) json = getData();
        long updataTime = 0;
        try {
            updataTime = json.getLong("updataTime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return updataTime;
    }


    private void setRankCount(int rankCount) {
        try {
            JSONObject json = getData();
            json.put("rankcount", rankCount);
            saveData(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void setUpdataTime(long time) {
        try {
            JSONObject json = getData();
            json.put("updataTime", time);
            saveData(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getData() {
        json = ACache.get(mContext).getAsJSONObject(savenumadd);
        if (null == json) json = new JSONObject();
        return json;
    }

    private void saveData(JSONObject json) {
        ACache.get(mContext).put(savenumadd, json.toString());

    }

}
