package work.test;

import org.junit.Test;

/**
 * ����ժ�����Ծ�̬������� ���ڴ�й© ����
 * Created by Excuse on 2016/1/13.
 */
public class TempStaticOne {
    //�Զ������
    public int num=0;
    //������һ�������ǲ�û��ʵ����,���ǰ�ʵ���������Ǿ�̬��ʼ����
    public TempStaticOne temp;
    //�Ǿ�̬��ʼ����
    {
        System.out.println("���ǷǾ�̬��ʼ����..");
        this.num=1;
        this.temp = new TempStaticOne();
    }
    //��̬��ʼ����
    static{
        System.out.println("���Ǿ�̬��ʼ����...");
    }
    //���캯��
    public TempStaticOne(){
        System.out.println("���ǹ��췽��..");
    }
    //�Զ��巽��
    public void Show(){
        System.out.println("�������ս��Ϊ:"+num);
    }

    @Test
    public void test() {
        Show();
    }
}
