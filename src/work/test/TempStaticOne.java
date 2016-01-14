package work.test;

import org.junit.Test;

/**
 * 网上摘抄测试静态化块代码 有内存泄漏 待查
 * Created by Excuse on 2016/1/13.
 */
public class TempStaticOne {
    //自定义变量
    public int num=0;
    //创建的一个对象但是并没有实例化,我们把实例化交给非静态初始化块
    public TempStaticOne temp;
    //非静态初始化块
    {
        System.out.println("我是非静态初始化块..");
        this.num=1;
        this.temp = new TempStaticOne();
    }
    //静态初始化块
    static{
        System.out.println("我是静态初始化块...");
    }
    //构造函数
    public TempStaticOne(){
        System.out.println("我是构造方法..");
    }
    //自定义方法
    public void Show(){
        System.out.println("变量最终结果为:"+num);
    }

    @Test
    public void test() {
        Show();
    }
}
