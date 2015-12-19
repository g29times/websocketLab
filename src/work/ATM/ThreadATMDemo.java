package work.ATM;

/**
 * Created by Excuse on 2015/12/17.
 */
public class ThreadATMDemo {
    /**
     * 主方法;
     * @param args
     */
    public static void main(String[] args) {

        Account account = new Account();
        //设置用户名;
        account.setName("TEST");
        //初始化余额;
        account.setValue(0);

        Thread saver1 = new Thread(new SaveMoney(100,account));
        Thread saver2 = new Thread(new SaveMoney(200,account));
        Thread fetcher = new Thread(new FetchMoney(500,account));

        saver1.start();
        saver2.start();
        fetcher.start();

        //存入100;

        //存入200;

        //取出500;
    }
}
