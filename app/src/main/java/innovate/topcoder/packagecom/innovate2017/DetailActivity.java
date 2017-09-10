package innovate.topcoder.packagecom.innovate2017;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static innovate.topcoder.packagecom.innovate2017.R.string.detail;

public class DetailActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
       /* Intent intent=new Intent(context,DetailActivity.class,String name,String title);
        intent.putExtra("name",name);
        Log.d("TAGDetailActivity",name);*//*/*
        //intent.putExtra("detail",detail);
        //Log.d("TAGDetailActivity",detail);*/
        /*intent.putExtra("title",title);
        Log.d("TAGDetailActivity",title);*/
        //intent.putExtra("imageId",imageId);
        //context.startActivity(intent);
    }

    private TextView nameTextView;
    private TextView detailTextView;
    private TextView titleTextView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAGDetailActivity","sucessful0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d("TAGDetailActivity","sucessful");
        String name=getIntent().getStringExtra("name");
        String detail=getIntent().getStringExtra("detail");
        String title=getIntent().getStringExtra("title");
        int imageId=Integer.parseInt(getIntent().getStringExtra("imageId"));
        nameTextView=(TextView)findViewById(R.id.speaker_name);
        titleTextView=(TextView)findViewById(R.id.speaker_title);
        detailTextView=(TextView)findViewById(R.id.speaker_detail);
        //设置滚动条
        detailTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        imageView=(ImageView)findViewById(R.id.speaker_img);
        nameTextView.setText(name);
        titleTextView.setText(title);
        detailTextView.setText(detail);
        imageView.setImageResource(imageId);
        Log.d("TAGDetailActivity","sucessful2");

        ImageView home_icon=(ImageView)findViewById(R.id.home_icon);
        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DetailActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageView schedule_arrow_left=(ImageView)findViewById(R.id.schedule_arrow_left);
        schedule_arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DetailActivity.this,SpeakerListActivity.class);
                startActivity(intent);
            }
        });

        ImageView info_icon=(ImageView)findViewById(R.id.info_icon);
        info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(DetailActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }




}
