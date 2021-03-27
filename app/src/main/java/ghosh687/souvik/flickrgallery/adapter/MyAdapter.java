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

import ghosh687.souvik.flickrgallery.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<String> photos;

    public MyAdapter(ArrayList<String> image, Context context){
        this.context=context;
        this.photos=image;
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
        if(!TextUtils.isEmpty(photos.get(position))){
            Glide.with(context).load(Uri.parse(photos.get(position))).into(holder.flickrimage);
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flickrimage ;

        ViewHolder(View itemView){
            super(itemView);
            flickrimage = itemView.findViewById(R.id.flickrimage);
        }
    }
}
