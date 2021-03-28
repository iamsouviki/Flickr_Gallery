package ghosh687.souvik.flickrgallery.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ghosh687.souvik.flickrgallery.activity.MainActivity;
import ghosh687.souvik.flickrgallery.adapter.MyAdapter;
import ghosh687.souvik.flickrgallery.api.FlickrApi;
import ghosh687.souvik.flickrgallery.modelclass.FlickrGallery;
import ghosh687.souvik.flickrgallery.modelclass.Photo;
import ghosh687.souvik.flickrgallery.modelclass.Photos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageslistViewModel extends ViewModel {

    private MutableLiveData<List<Photo>> imageslist;

    public ImageslistViewModel(){
        imageslist = new MutableLiveData<>();
    }

    public MutableLiveData<List<Photo>> getImageslist(){
        return imageslist;
    }

    public void getdata() {
        Call<FlickrGallery> galleryCall = FlickrApi.getPostService().getImages();
        galleryCall.enqueue(new Callback<FlickrGallery>() {
            @Override
            public void onResponse(Call<FlickrGallery> call, Response<FlickrGallery> response) {
                if(response.body().getStat().equals("ok")){
                    imageslist.postValue((response.body().getPhotos().getPhoto()));
                }
            }

            @Override
            public void onFailure(Call<FlickrGallery> call, Throwable t) {
                imageslist.postValue(null);
            }
        });
    }
}
