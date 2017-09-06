package innovate.topcoder.packagecom.innovate2017;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SpeakerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG", "firstok11");
        setContentView(R.layout.activity_speaker_list);
        Log.i("TAG", "firstok");
        List<Speaker> Speakers=retrieveAllSpeakers(this);
    }

    private final static String TAG="Innovate.DataRetriever";
    public static JSONArray strToJsonArray(String content) throws JSONException {
        return new JSONArray(content.replace('\n', ' '));
    }

    public List<Speaker> retrieveAllSpeakers(Context context) {

        // 存储Speakers，用于将数据返回
        List<Speaker> speakerArrayList = null;


        try {
            String url = context.getString(R.string.feeds_speakers);
            String fileName = context.getResources().getString(
                    R.string.speakersfilename);
            Log.i("TAG", "firstok2");
            // 获取数据
            InputStreamReader inputStreamReader;

            inputStreamReader = new InputStreamReader(getAssets().open(
                    "speakers.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line;
            Log.i("TAG", "firstok3");
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();

            Log.i("TAG", stringBuilder.toString());

            JSONArray jsonArray = strToJsonArray(stringBuilder.toString());

            speakerArrayList = new ArrayList<Speaker>();
            Speaker speaker;

            for (int i = 0; i < jsonArray.length(); i++) {
                /**
                 * 服务器返回的数据是JSONArray，在JSONArray里面有不同的JSONObject，
                 * 在JSONObject中“fields“名称后面得值是JSONObject， 我们需要的就是这个JSONObject
                 */
                JSONObject jsonObject = jsonArray.getJSONObject(i)
                        .getJSONObject("fields");
                // 初始化Speaker
                speaker = new Speaker();
                // 获得jsonObj中的相应名称后面的值，并保存在speaker中的相应域中
                speaker.setName(jsonObject.getString("name"));
                speaker.setDetails(jsonObject.getString("details"));
                speaker.setPicture(jsonObject.getString("picture"));
                /**
                 * 由于sessions后面的值是一个JSONArray数组 所以得先获得这个数组，然后将数组里面得值存放 在List中
                 */
                JSONArray jsonArrayTemp = jsonObject.getJSONArray("sessions");
                List<String> tmp = new ArrayList<String>();
                for (int j = 0; j < jsonArrayTemp.length(); j++) {
                    tmp.add(jsonArrayTemp.getString(j));
                }
                speaker.setSessionIds(tmp);
                // 获得jsonObj中的"title"名称后面得值，并保存在speaker中的title域中
                speaker.setTitle(jsonObject.getString("title"));
                // 将speaker放到speakerArrayList中去
                speakerArrayList.add(speaker);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.i(TAG, "retrieve speakers");
        return speakerArrayList;
    }
}
