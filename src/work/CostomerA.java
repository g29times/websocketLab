package work;

import work.Excel.AgentGet;
import work.Excel.ExcelWorkLoad;

/**
 * Created by Excuse on 2015/12/16.
 */
public class CostomerA implements Runnable {

    static ExcelWorkLoad workLoad;
    private static AgentGet agentGet = new AgentGet(workLoad);

    public static void main(String[] args) {
        // ִ��˳�� - 1
//        agentGet.getInfo1();
//        agentGet.getInfo2();

        CostomerA costomerA = new CostomerA();
        CostomerA costomerB = new CostomerA();

//        costomerA.run();
//        costomerB.run();

        Thread a = new Thread(costomerA, "A");
        Thread b = new Thread(costomerB, "B");
        a.start();
        b.start();

    }

    /**
     * ��̬����
     * @param nomatterParam
     * @return
     */
    public static String change1(Object nomatterParam) {
        // TODO ...
        agentGet.setCustomer(new Customer() {
            @Override
            public Integer func() {
                // ִ��˳�� - 4�ڲ�
                return 1;
            }
        });
        return "AgentGet";
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
//        if(getKeyInfo() == null)
//            for(int i = 0; i < 100; i++) {
//                keyInfoHolder.set(i);
//                try {
//                    Thread.sleep(30);
//                } catch (Exception e) {
//
//                }
//            }
//        System.out.println(getKeyInfo());
    }

    /**
     * ��̬����
     * @param keyInfo
     * @return
     */
    public static String change2(Integer keyInfo) {
        // TODO ...
        // this.notifyCaller(keyInfo); ����������

        // ִ��˳�� - 2
        agentGet.setCustomer(new Customer() {
            @Override
            public Integer func() {
                // return keyInfo; // Cannot refer to a non-final variable keyInfo inside an inner class defined in a different method
                // ִ��˳�� - 4�ڲ�
                return getKeyInfo();
            }
        });


        // ִ��˳�� - 3
//        keyInfoHolder.set(10);
        return "AgentGet";
    }

//	private static Integer keyInfo = 99;

    /**
     * ��ʽһ
     * initialValue
     * return a static value or a final variable
     */
//	private static ThreadLocal<Integer> keyInfoHolder = new ThreadLocal<Integer>() {
//		Integer keyInfo = 99;
//		public Integer initialValue() {
//			return keyInfo;
//		}
//	};
    /**
     * ��ʽ��
     * keyInfoHolder.set(101);
     */
    private static ThreadLocal<Integer> keyInfoHolder = new ThreadLocal<Integer>();

    public static Integer getKeyInfo() {
        // ִ��˳�� - 4�ڲ�
        return keyInfoHolder.get();
    }

}
