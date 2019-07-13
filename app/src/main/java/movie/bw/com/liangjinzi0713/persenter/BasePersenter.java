package movie.bw.com.liangjinzi0713.persenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import movie.bw.com.liangjinzi0713.core.DataCall;
import movie.bw.com.liangjinzi0713.entity.Result;
import movie.bw.com.liangjinzi0713.https.OkHttpUtils;
import movie.bw.com.liangjinzi0713.model.IRquest;

/**
 * @Author：梁金子
 * @Date：2019/7/13 10:29
 * @Description：描述信息
 */
public abstract class BasePersenter {
    public DataCall dataCall;

    public BasePersenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    public void request(Object...args){
        IRquest iRquest = OkHttpUtils.getInstance().create(IRquest.class);
        getModel(iRquest,args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result o) throws Exception {
                        if (o.status.equals("0000")){
                            dataCall.success(o.result);
                        }else {
                            dataCall.fail(o);
                        }
                    }
                });
    }

    protected abstract Observable getModel(IRquest iRquest, Object...args);
}
