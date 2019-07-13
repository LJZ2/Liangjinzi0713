package movie.bw.com.liangjinzi0713.model;



import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import movie.bw.com.liangjinzi0713.entity.LogEntity;
import movie.bw.com.liangjinzi0713.entity.Result;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @Author：梁金子
 * @Date：2019/7/13 10:26
 * @Description：描述信息
 */
public interface IRquest {
    @FormUrlEncoded
    @POST("user/v1/login ")
    Observable<Result<LogEntity>> login(@FieldMap HashMap<String,String> map);

}
