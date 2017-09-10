package innovate.topcoder.packagecom.innovate2017;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class SpeakerListActivity extends AppCompatActivity {

    private List<Speaker> Speakers;
    public List<newSpeaker> ShowSpeaker=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG", "firstok11");
        setContentView(R.layout.activity_speaker_list);
        Log.i("TAG", "firstok");
        Speakers=retrieveAllSpeakers(this);
        initSpeakers();

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SpeakerTitleFragment.SpeakerAdapter adapter=new SpeakerTitleFragment().new SpeakerAdapter(ShowSpeaker);
        recyclerView.setAdapter(adapter);

        ImageView home_icon=(ImageView)findViewById(R.id.home_icon);
        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(SpeakerListActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageView info_icon=(ImageView)findViewById(R.id.info_icon);
        info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(SpeakerListActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
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

    //获取图片名称获取图片的资源id
    public int getResourceByReflect(String imageName){
        Class drawable  =  R.drawable.class;
        Field field = null;
        int r_id ;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
          //  Log.d("TAG",r_id));
        } catch (Exception e) {
            r_id=R.drawable.default_speaker;
            Log.e("TAG", "PICTURE NOT　FOUND！");
        }
        return r_id;
    }



    private void initSpeakers(){
        for(Speaker speaker:Speakers)
        {
            //图片名字符串截取从19位到字符串总长位
            String photoName;
            photoName=speaker.getPicture().substring(19,speaker.getPicture().length()-4).toLowerCase();
            if((photoName.charAt(0)>='0')&&(photoName.charAt(0)<='9'))
            {
                photoName="x"+photoName;
            }
            newSpeaker temSpeaker=new newSpeaker(speaker.getName(),speaker.getTitle(),speaker.getDetails(),
                    getResourceByReflect(photoName));
            Log.d("TAG",photoName);

            ShowSpeaker.add(temSpeaker);
        }
    }

}
