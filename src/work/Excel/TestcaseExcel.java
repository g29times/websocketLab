package work.Excel;

/**
 * Created by Excuse on 2015/12/17.
 */
public class TestcaseExcel {

    public static void main(String[] args) {
        ExcelWorkLoad workLoad = new ExcelWorkLoad();
        workLoad.setCountNum(0);

        AgentSet agentSet = new AgentSet(workLoad);
        AgentGet agentGet = new AgentGet(workLoad);
        Thread setThread = new Thread(agentSet);
        Thread getThread = new Thread(agentGet);
        setThread.start();
        getThread.start();

    }
}
