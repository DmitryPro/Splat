import com.caucho.hessian.server.HessianServlet;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;

/**
 * Created by ִלטענטי on 15.06.2015.
 */


public class DataManager extends HessianServlet implements AccountService{

    private LoadingCache<Integer,Long> loadingCache  = CacheBuilder.newBuilder()
            .concurrencyLevel(8)
            .expireAfterAccess(5L, TimeUnit.MINUTES)
            .maximumSize(1000L)
            .build(new CacheLoader<Integer, Long>() {
                @Override
                public Long load(Integer id) throws Exception {
                    return tableWorker.getAmount(id);
                }
            });

    private TableWorker tableWorker = new TableWorker();

    public Long getAmount(Integer id) {
        return loadingCache.getUnchecked(id);
    }

    public void addAmount(Integer id, Long value) {
        Long v = loadingCache.getUnchecked(id);
        if(v != null) {
            value += v;
        }
        loadingCache.put(id,value);
        tableWorker.addAmount(id,value);
    }
}
