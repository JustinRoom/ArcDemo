package jsc.exam.com.arc.retrofit;

import io.reactivex.Observable;
import jsc.exam.com.arc.BuildConfig;
import retrofit2.http.GET;

public interface ApiService {

    @GET(BuildConfig.VERSION_URL)
    Observable<String> getVersionInfo();

}
