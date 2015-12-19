package work.ATM;

/**
 * Created by Excuse on 2015/12/17.
 */
public class SaveMoney implements Runnable {

    private Account account;  //账户对象;
    private int money;        //金额;

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
        int currMonery = account.search(); //查询账户余额;

        //同步对象,只允许单线程操作;
        synchronized (account) {
            try {
                Thread.sleep(5);  //查询的时候,发费得时间;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            account.putMonery(this.money);   //插入金额;
            //输出存款信息;
            System.out.println("尊敬的" + account.getName() + "用户您好!" + "当前的余额为:" + currMonery + "元." + "存入余额为:" + this.money + "元." + "账户余额为:" + account.search() + "元");
        }
    }
}
