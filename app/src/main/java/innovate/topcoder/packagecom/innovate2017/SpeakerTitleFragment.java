package innovate.topcoder.packagecom.innovate2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aurorayqz on 2017/9/6.
 */
public class SpeakerTitleFragment extends Fragment{


    public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.ViewHolder> {


        private List<newSpeaker> mSpeakerList;

         class ViewHolder extends RecyclerView.ViewHolder{
            View speakerView;
            ImageView speakerimage;
            TextView speakername;
            TextView speakertitle;

            public ViewHolder(View view){
                super(view);
                speakerView=view;
                speakerimage=(ImageView)view.findViewById(R.id.speaker_img);
                speakername=(TextView)view.findViewById(R.id.speaker_name);
                speakertitle=(TextView)view.findViewById(R.id.speaker_title);
            }
        }

        public SpeakerAdapter(List<newSpeaker> speakerList){
            mSpeakerList=speakerList;
        }

        @Override
        public SpeakerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.speak_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            holder.speakerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=holder.getAdapterPosition();
                    newSpeaker speaker=mSpeakerList.get(holder.getAdapterPosition());
                    Log.d("TAG","what the fuck");
                    //DetailActivity.actionStart(getActivity());
                    //item item=this.items.get(position);
                    Intent intent=new Intent (view.getContext(),DetailActivity.class);
                    intent.putExtra("name",speaker.getName());
                    Log.d("TAG1","name+"+speaker.getName());
                    intent.putExtra("title",speaker.getTitle());
                    Log.d("TAG1","title+"+speaker.getTitle());
                    intent.putExtra("detail",speaker.getDetail());
                    Log.d("TAG1","detail+"+speaker.getDetail());
                    intent.putExtra("imageId",String.valueOf(speaker.getImageId()));
                    Log.d("TAG1","imageId+"+speaker.getImageId());
                    view.getContext().startActivity(intent);
                    Log.d("TAG","what the fuck2");
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(SpeakerAdapter.ViewHolder holder, int position) {
            newSpeaker speaker=mSpeakerList.get(position);
            holder.speakerimage.setImageResource(speaker.getImageId());
            holder.speakername.setText(speaker.getName());
            holder.speakertitle.setText(speaker.getTitle());
        }

        @Override
        public int getItemCount() {
            return mSpeakerList.size();
        }


    }
}




