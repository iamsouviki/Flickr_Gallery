package ghosh687.souvik.flickrgallery.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ghosh687.souvik.flickrgallery.R;
import ghosh687.souvik.flickrgallery.activity.MainActivity;
import ghosh687.souvik.flickrgallery.modelclass.FlickrGallery;
import ghosh687.souvik.flickrgallery.modelclass.Photo;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<Photo> photos;

    public MyAdapter(List<Photo> image, Context context){
        this.context=context;
        this.photos=image;
    }

    public void setImageurlList(List<Photo> imageurlList){
        this.photos=imageurlList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_custom_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String pht=photos.get(position).getUrlS();
        if(!TextUtils.isEmpty(pht)){
            Glide.with(context).load(Uri.parse(pht)).into(holder.flickrimage);
        }
    }

    @Override
    public int getItemCount() {
        if(photos!=null) {
            return photos.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flickrimage ;

        ViewHolder(View itemView){
            super(itemView);
            flickrimage = itemView.findViewById(R.id.flickrimage);
        }
    }
}
