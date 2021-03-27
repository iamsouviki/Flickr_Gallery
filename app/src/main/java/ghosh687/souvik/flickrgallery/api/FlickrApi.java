package ghosh687.souvik.flickrgallery.api;

import ghosh687.souvik.flickrgallery.modelclass.FlickrGallery;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class FlickrApi {
    final public static String JSON_URL = "https://api.flickr.com/services/rest/";

    public static PostService postService = null;


    public static PostService getPostService(){

        if(postService==null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(JSON_URL).addConverterFactory(GsonConverterFactory.create()).build();
            postService=retrofit.create(PostService.class);
        }

        return postService;
    }


    public interface PostService{
        @GET("?method=flickr.photos.getRecent&per_page=35&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
        Call<FlickrGallery> getImages();
    }

}
