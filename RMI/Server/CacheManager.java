package RMI.Server;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by marsik on 18.06.15.
 */

/*
Здесь мы реализуем чистку кэша, и добавление в него данных

 */
public class CacheManager {
    private ConcurrentMap<Integer, Long> cache;

    CacheManager(){
        cache = new ConcurrentLinkedHashMap.Builder<Integer, Long>()
                .maximumWeightedCapacity(1000)
                .build();
    }

    public void put(Integer id, Long value){
        cache.put(id, value);
    }

    public Long get(Integer id){
        return cache.get(id);
    }

    public boolean check(Integer id){
        return cache.containsKey(id);
    }

    public void update(Integer id, Long value){
        Long temp = cache.get(id);
        cache.replace(id, temp, temp + value);
    }


}
