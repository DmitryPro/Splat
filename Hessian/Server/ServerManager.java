import com.caucho.hessian.server.HessianServlet;

import java.util.Date;

/**
 * Created by ִלטענטי on 21.06.2015.
 */
public class ServerManager extends HessianServlet implements StatisticService{

    Date date;

    @Override
    public void init() {
        date = new Date();
    }

    public double getStat() {
        return DataManager.getGetCount() * 1.0 / (new Date().getTime() - date.getTime()) * 60000L;
    }

    public double addStat() {
        return DataManager.getAddCount() * 1.0 / (new Date().getTime() - date.getTime()) * 60000L;
    }

    public long allStat() {
        return DataManager.getGetCount() + DataManager.getAddCount();
    }

    public void clear() {
        DataManager.clearStats();
        date = new Date();
    }
}
