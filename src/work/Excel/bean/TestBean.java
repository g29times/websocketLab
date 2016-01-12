package work.Excel.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Excuse on 2016/1/12.
 */
public class TestBean implements Serializable {

    private int pro1; // key
    private String pro2;
    private Date pro3;

    public int getPro1() {
        return pro1;
    }

    public void setPro1(int pro1) {
        this.pro1 = pro1;
    }

    public String getPro2() {
        return pro2;
    }

    public void setPro2(String pro2) {
        this.pro2 = pro2;
    }

    public Date getPro3() {
        return pro3;
    }

    public void setPro3(Date pro3) {
        this.pro3 = pro3;
    }

    public void save() {
        System.out.println("SAVED");
    }
}
