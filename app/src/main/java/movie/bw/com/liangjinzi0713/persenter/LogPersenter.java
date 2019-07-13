package movie.bw.com.liangjinzi0713.persenter;

import java.util.HashMap;

import io.reactivex.Observable;
import movie.bw.com.liangjinzi0713.core.DataCall;
import movie.bw.com.liangjinzi0713.model.IRquest;

/**
 * @Author：梁金子
 * @Date：2019/7/13 10:32
 * @Description：描述信息
 */
public class LogPersenter extends BasePersenter {
    public LogPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(IRquest iRquest, Object... args) {
        return iRquest.login((HashMap<String,String>)args[0]);
    }
}
