package work.ATM;

/**
 * Created by Excuse on 2015/12/17.
 */
public class SaveMoney implements Runnable {

    private Account account;  //�˻�����;
    private int money;        //���;

    public SaveMoney() {
        super();
    }

    public SaveMoney(int money, Account account) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int currMonery = account.search(); //��ѯ�˻����;

        //ͬ������,ֻ�����̲߳���;
        synchronized (account) {
            try {
                Thread.sleep(5);  //��ѯ��ʱ��,���ѵ�ʱ��;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            account.putMonery(this.money);   //������;
            //��������Ϣ;
            System.out.println("�𾴵�" + account.getName() + "�û�����!" + "��ǰ�����Ϊ:" + currMonery + "Ԫ." + "�������Ϊ:" + this.money + "Ԫ." + "�˻����Ϊ:" + account.search() + "Ԫ");
        }
    }
}
