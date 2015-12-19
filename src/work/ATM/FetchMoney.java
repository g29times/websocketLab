package work.ATM;

/**
 * Created by Excuse on 2015/12/17.
 */
public class FetchMoney implements Runnable {
    private Account account;  //账户对象;
    private int monery;       //余额;

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
        int currMonery = account.search();  //当前余额;
        synchronized (account) {
            try {
                Thread.sleep(5);   //取款发费的时间;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //取出余额;
            int getMonery = account.getMonery(monery);
            System.out.println("尊敬的" + account.getName() + "用户您好!" + "当前的余额为:" + currMonery + "元." + "取出余额为:" + getMonery + "元." + "账户余额为:" + account.search() + "元");
        }
    }
}
