package work.demo.serialize;

import org.junit.Test;

import java.io.*;
import java.text.MessageFormat;

/**
 * Created by Mono on 2016/1/2.
 * http://www.cnblogs.com/xdp-gacl/p/3777987.html
 * 《深入分析Java Web内幕》
 */
public class Serialize implements Serializable {
    private static final long serialVersionUID = -6849794470754660011L;

    public int num = 139031054;

    public int getNum() {
        return num;
    }

    @Test
    public void serialize() {
        try{
            FileOutputStream fos = new FileOutputStream("src/work/demo/serialize/serialize.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();
            oos.writeObject(serialize);
//            fos.write(123);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void deserialize() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                    new File("src/work/demo/serialize/serialize.dat")));
            Serialize object = (Serialize) ois.readObject();
            System.out.println(object.num);
            System.out.println(MessageFormat.format("num={0}",
                    object.getNum()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
