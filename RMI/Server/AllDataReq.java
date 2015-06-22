package RMI.Server;

/**
 * Created by marsik on 22.06.15.
 */

/*
выаваыва
 */
public class AllDataReq {
    CacheManager cm;
    DataBaseReq dbr;


    AllDataReq(){
        cm = new CacheManager();
        dbr = new DataBaseReq();
    }

    public synchronized void addAmount(Integer id, Long val){
        if (cm.check(id))
            cm.update(id, val);
        else
            cm.put(id, val);
        dbr.addAmount(id, val);

    }

    public Long getAmount(Integer id){
        Long ans;
        if(cm.check(id))
            return cm.get(id);
        else if((ans = dbr.getAmount(id)) != 0)
            return ans;
        else
            return 0l;

    }


}
