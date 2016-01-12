package work.Excel.api;

/**
 * Excel������Ϊ�ӿ�
 * Created by Excuse on 2016/1/11.
 */
public interface ExcelAPI {

    /** ������� �������� ׼�� */
    int PROCESS_STAGE_PREPARE = 0;  // ׼��
    /** ������� �������� ��ʼ */
    int PROCESS_STAGE_START = 1;
    /** ������� �������� ������ */
    int PROCESS_STAGE_PROCESS = 2;  // ������
    /** ������� �������� ���� */
    int PROCESS_STAGE_END = 3;
    /** ������� �������� �ر� */
    int PROCESS_STAGE_CLOSE = 4;    // �ر�

    /** ״̬�� �ɹ� */
    int STATUS_SUCCESS = 1; //
    /** �����쳣 ��Ȼ���Դ��� ����¼ */
    int STATUS_ERROR = 0;
    /** �������� �˳� */
    int STATUS_FAILED = -1;

    /**
     * ��ȡExcel�ļ�
     * @return
     */
    Excel getExcel();

    /**
     * ����Excel�ļ�
     * @param source Դ�ļ�
     * @param process �ص�����
     * @return
     */
    Object importExcel(Excel source, Function process);

    /**
     * ����ΪExcel�ļ�
     * @return
     */
    Excel exportExcel(Object data);

    /**
     * ת��Excel�ļ�
     * @param source Դ�ļ�
     * @param data
     * @return
     */
    Excel convertExcel(Excel source, Object data);

}
