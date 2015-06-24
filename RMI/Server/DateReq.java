import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/*
В данном классе реализовано кэширование запросов.
Используется кэш, реализованный в библиотеке Caffeine.
 */

/**
 * Created by marsik on 22.06.15.
 */
public class DateReq {
    DataBaseReq dbr = new DataBaseReq();
    LoadingCache<Integer, Long> cache;
    public static Long getCount = 0l;
    public static Long addCount = 0l;


    DateReq() {
        cache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, Long>() {
                    public Long load(Integer id) {
                        return dbr.getAmount(id);
                    }
                });
    }

    public synchronized void addAmount(Integer id, Long val) {
        addCount++;
        Long temp = cache.get(id);
        cache.put(id, val + temp);
        dbr.addAmount(id, val);
    }


    public Long getAmount(Integer id) {
        getCount++;
        return cache.get(id);
    }

    public static void Clean() {
        getCount = 0l;
        addCount = 0l;

    }

    public static Long getReq() {
        return getCount;
    }

    public static Long addReq() {
        return addCount;
    }

    public void incAddReq(){
        addCount++;
    }

    public void incGetReq(){
        getCount++;
    }


}
