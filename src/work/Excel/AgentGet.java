package work.Excel;

import work.CostomerA;
import work.Customer;

/**
 * �����൱��
 * Created by Excuse on 2015/12/16.
 */
public class AgentGet implements Runnable {

    ExcelWorkLoad workLoad;

    public AgentGet(ExcelWorkLoad workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            getCount(workLoad);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
        }
    }

    public void getCount(ExcelWorkLoad workLoad) {
        System.out.println(workLoad.getCountNum());
    }

    /*********************************************/

    // ʾ������
    private static Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * ��ɫ���۲���
     *
     * @return
     */
    public static void getInfo1() {
        // ��Ҫһ��������Ϣ
        Integer keyInfo;
        // �������
        CostomerA.change1(null);
        // �㲻�ü̳�/ʵ��/���� ������ʽ
        keyInfo = customer.func();
        // �õ����
        System.out.println(keyInfo);
        return;
    }

    /**
     * ��ɫ��ֱ�ӵ�����
     *  Agency����һ���ϲ� �����͵���Costomer��һЩ���� ���÷���ֵresult��һЩ����
     *  �������ڳ���result֮�� ��ϣ��һ����ȡkeyInfo keyInfo������һ������ֵ
     *  ����������Costomer�Ĺ��������� ��������Ϊ�˼���keyInfo��״̬ ����������ȡ����
     *
     * ����������threadlocal����Costomer�Ա���keyInfo������̬�ı� ͻ��run��final����
     *
     * ����
     * change��������һ��String�������� ����ϣ��change������һ���޸�keyInfo��ֵ
     * <p>
     * ����
     * 1.Я��-ͨ��changeԭ���ķ���ֵЯ���޸ĺ��keyInfo���ص��������� ʹ������-����ͨ�����󴫵ݣ� ����ֵ���Ͳ��Ǽ�����Ļ������޸ġ�
     * 2.֪ͨ-��change������ֱ���޸�keyInfo��ֵ������֪ͨ��������
     *
     * @return
     */
    public static String getInfo2() {
        // ��Ҫ�޸ĵ�ֵ
        Integer keyInfo = null;
        // ������Ҫ���ú�������ֵ
        String result = CostomerA.change2(keyInfo);
//         ͨ���ص��ı�
//         ִ��˳�� - 4
        keyInfo = customer.func();
        System.out.println(keyInfo);
        return result;
    }
}
