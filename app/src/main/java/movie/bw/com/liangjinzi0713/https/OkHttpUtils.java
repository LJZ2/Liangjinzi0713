package movie.bw.com.liangjinzi0713.https;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author：梁金子
 * @Date：2019/7/13 10:09
 * @Description：描述信息
 */
public class OkHttpUtils {

    private final Retrofit retrofit;
    public static OkHttpUtils instance;
    public static OkHttpUtils getInstance(){
        if (instance==null){
            instance=new OkHttpUtils();
        }
        return instance;
    }

    private OkHttpUtils(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .connectTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://172.17.8.100/small/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
