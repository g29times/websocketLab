package work.ATM;

/**
 * Created by Excuse on 2015/12/16.
 */
public class Account {

    private String name;  //�û���;
    private int value;    //�˻����;

    /**
     * ������;
     *
     * @param monery
     */
    public void putMonery(int monery) {
        this.value = this.value + monery;
    }

    /**
     * ȡ�����;
     *
     * @param monery
     * @return ���;
     */
    public int getMonery(int monery) {

        //�ж��Ƿ��˻�����Ƿ���� Ҫȡ����Ǯ;
        if (this.value > monery) {
            this.value = this.value - monery;
        } else {
            monery = this.value;  //�˻�����ʱ,��ȡ��,���е��˻����Ľ��.
            this.value = 0;
        }
        //����ȡ����Ǯ;
        return monery;
    }

    /**
     * ��ѯ���;
     *
     * @return �����˻����;
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
