package work.ATM;

/**
 * Created by Excuse on 2015/12/17.
 */
public class FetchMoney implements Runnable {
    private Account account;  //�˻�����;
    private int monery;       //���;

    public FetchMoney() {
        super();
    }

    public FetchMoney(int monery, Account account) {
        this.account = account;
        this.monery = monery;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int currMonery = account.search();  //��ǰ���;
        synchronized (account) {
            try {
                Thread.sleep(5);   //ȡ��ѵ�ʱ��;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //ȡ�����;
            int getMonery = account.getMonery(monery);
            System.out.println("�𾴵�" + account.getName() + "�û�����!" + "��ǰ�����Ϊ:" + currMonery + "Ԫ." + "ȡ�����Ϊ:" + getMonery + "Ԫ." + "�˻����Ϊ:" + account.search() + "Ԫ");
        }
    }
}
