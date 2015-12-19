package work.Excel;

import websocket.chat.ChatAnnotation;
import work.Customer;

/**
 * Created by Excuse on 2015/12/16.
 */
public class AgentSet implements Runnable {

    private ExcelWorkLoad workLoad;
    private ChatAnnotation agentGet;
    private Integer total = 60;

    public AgentSet() {
    }

    public AgentSet(ExcelWorkLoad workLoad) {
        this.workLoad = workLoad;
    }

    public AgentSet(ExcelWorkLoad workLoad, ChatAnnotation agentGet) {
        this.workLoad = workLoad;
        this.agentGet = agentGet;
    }

    @Override
    public void run() {
//        agentGet.setCustomer(new Customer() {
//            @Override
//            public Integer func() {
//                return getKeyInfo();
//            }
//        });

        for(int i = 0; i < total; i++) {
            setCount(workLoad);
//            keyInfoHolder.set(i); //
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
    }

    public void setCount(ExcelWorkLoad workLoad) {
        workLoad.count();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /*********************************************/

    private static ThreadLocal<Integer> keyInfoHolder = new ThreadLocal<Integer>();

    public static Integer getKeyInfo() {
        return keyInfoHolder.get();
    }
}
