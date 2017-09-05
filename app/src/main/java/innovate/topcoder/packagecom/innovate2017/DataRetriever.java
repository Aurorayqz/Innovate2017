package innovate.topcoder.packagecom.innovate2017;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Aurorayqz on 2017/8/26.
 */

public class DataRetriever{
    private final static String TAG="Innovate.DataRetriever";
    public static JSONArray strToJsonArray(String content) throws JSONException{
        return new JSONArray(content.replace('\n',' '));
    }

    public List<Speaker> retrieveAllSpeakers(Activity activity) throws IOException, JSONException {
        //存储Speakers
        List<Speaker> speakerArrayList=null;
        String urls=activity.getResources().getString(R.string.feeds_speakers);

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(urls).build();
        Response response=client.newCall(request).execute();
        String responseData=response.body().string();

        JSONArray jsonArray=new JSONArray(responseData);
        Speaker speaker;

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject jsonObject=jsonArray.getJSONObject(i).getJSONObject("fields");
            speaker=new Speaker();
            speaker.setName(jsonObject.getString("name"));
            speaker.setDetails(jsonObject.getString("details"));
            speaker.setPicture(jsonObject.getString("picture"));
            /**
             * 由于sessions后面的值是一个JSONArray数组 所以得先获得这个数组，然后将数组里面得值存放 在List中
             */
            JSONArray jsonArrayTemp=jsonObject.getJSONArray("sessions");
            List<String>tmp=new ArrayList<String>();
            for (int j=0;j<jsonArrayTemp.length();j++)
            {
                tmp.add(jsonArrayTemp.getString(j));
            }
            speaker.setSessionIds(tmp);
            speaker.setTitle(jsonObject.getString("title"));
            // 将speaker放到speakerArrayList中去
            speakerArrayList.add(speaker);
        }
        return speakerArrayList;
    }
}
