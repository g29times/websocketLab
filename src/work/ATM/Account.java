package work.ATM;

/**
 * Created by Excuse on 2015/12/16.
 */
public class Account {

    private String name;  //用户名;
    private int value;    //账户余额;

    /**
     * 存入金额;
     *
     * @param monery
     */
    public void putMonery(int monery) {
        this.value = this.value + monery;
    }

    /**
     * 取出金额;
     *
     * @param monery
     * @return 金额;
     */
    public int getMonery(int monery) {

        //判断是否账户余额是否大于 要取出的钱;
        if (this.value > monery) {
            this.value = this.value - monery;
        } else {
            monery = this.value;  //账户余额不够时,则取出,所有的账户余额的金额.
            this.value = 0;
        }
        //返回取出的钱;
        return monery;
    }

    /**
     * 查询余额;
     *
     * @return 返回账户余额;
     */
    public int search() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
