package movie.bw.com.liangjinzi0713.core;

import movie.bw.com.liangjinzi0713.entity.Result;

/**
 * @Author：梁金子
 * @Date：2019/7/13 10:25
 * @Description：描述信息
 */
public interface DataCall<T> {
    void success(T data);
    void fail(Result data);
}
