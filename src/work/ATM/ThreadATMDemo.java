package work.ATM;

/**
 * Created by Excuse on 2015/12/17.
 */
public class ThreadATMDemo {
    /**
     * ������;
     * @param args
     */
    public static void main(String[] args) {

        Account account = new Account();
        //�����û���;
        account.setName("TEST");
        //��ʼ�����;
        account.setValue(0);

        Thread saver1 = new Thread(new SaveMoney(100,account));
        Thread saver2 = new Thread(new SaveMoney(200,account));
        Thread fetcher = new Thread(new FetchMoney(500,account));

        saver1.start();
        saver2.start();
        fetcher.start();

        //����100;

        //����200;

        //ȡ��500;
    }
}
