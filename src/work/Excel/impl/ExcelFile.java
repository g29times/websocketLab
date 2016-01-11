package work.Excel.impl;

import work.Excel.api.Excel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel �ļ� ʵ����
 * Created by Excuse on 2016/1/11.
 */
public class ExcelFile extends AbsExcelFile {


    /*** �汾 */
    private String version;
    /*** ��׺ */
    private String extention;
    /*** ·�� */
    private String sourcePath;
    /*** ���� */
    private String fileName;
    /*** ��ʼλ�� */
    private String startNum;


    public File getExcel() {
        return new File(fileName) == null ? new File(sourcePath + fileName) : new File(fileName);
    }

    @Override
    public void setName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return this.fileName;
    }

    @Override
    public Map getInfo() {
        Map infoMap = new HashMap<>();
        infoMap.put("startNum", startNum);
        return infoMap;
    }

    @Override
    public void setInfo(Map info) {
        this.startNum = (String)info.get("startNum");
    }
}
