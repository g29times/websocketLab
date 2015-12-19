package work.Excel;

import work.CostomerA;
import work.Customer;

/**
 * 本类相当于
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

    // 示例代码
    private static Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * 角色：观察者
     *
     * @return
     */
    public static void getInfo1() {
        // 我要一个过程信息
        Integer keyInfo;
        // 你做你的
        CostomerA.change1(null);
        // 你不用继承/实现/依赖 非侵入式
        keyInfo = customer.func();
        // 得到结果
        System.out.println(keyInfo);
        return;
    }

    /**
     * 角色：直接调用者
     *  Agency扮演一个上层 本来就调用Costomer做一些事情 利用返回值result做一些事情
     *  但是现在除了result之外 还希望一并获取keyInfo keyInfo可能是一个过程值
     *  本来隐藏在Costomer的工作过程中 但是现在为了监视keyInfo的状态 把他单独提取出来
     *
     * 本方法利用threadlocal允许Costomer对变量keyInfo做出动态改变 突破run的final限制
     *
     * 场景
     * change方法返回一个String类型数据 现在希望change方法能一并修改keyInfo的值
     * <p>
     * 方案
     * 1.携带-通过change原本的返回值携带修改后的keyInfo，回到主方法。 使用限制-必须通过对象传递， 返回值类型不是集合类的话不便修改。
     * 2.通知-在change方法中直接修改keyInfo的值，并且通知主方法。
     *
     * @return
     */
    public static String getInfo2() {
        // 需要修改的值
        Integer keyInfo = null;
        // 方法主要调用和主返回值
        String result = CostomerA.change2(keyInfo);
//         通过回调改变
//         执行顺序 - 4
        keyInfo = customer.func();
        System.out.println(keyInfo);
        return result;
    }
}
