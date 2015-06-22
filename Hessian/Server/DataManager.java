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
            //If value not exist in cache then we try to find it in table
            .build(new CacheLoader<Integer, Long>() {
                @Override
                public Long load(Integer id) {
                    return tableWorker.getAmount(id);
                }
            });

    private static int getCount = 0;
    private static int addCount = 0;

    public static int getAddCount() {
        return addCount;
    }

    public static int getGetCount() {
        return getCount;
    }

    public static void clearStats() {
        addCount = 0;
        getCount = 0;
    }

    private TableWorker tableWorker = new TableWorker();

    public Long getAmount(Integer id) {
        getCount++;
        try {
            Long res = loadingCache.getUnchecked(id);
            return res;
        }
        catch (NullPointerException e){
            return 0L;
        }
    }

    public synchronized void addAmount(Integer id, Long value) {
        addCount++;
        Long v;
        try {
            v = loadingCache.getUnchecked(id);
        }
        catch (NullPointerException e) {
            v = 0L;
        }
        loadingCache.put(id,v + value);
        tableWorker.addAmount(id,value);
    }
}
