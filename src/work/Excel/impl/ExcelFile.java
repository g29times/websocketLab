package work.Excel.impl;

import work.Excel.api.Excel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel 文件 实现类
 * Created by Excuse on 2016/1/11.
 */
public class ExcelFile extends AbsExcelFile {


    /*** 数据集 */
    private List data;
    /*** 版本 */
    private String version;
    /*** 后缀 */
    private String extention;
    /*** 路径 */
    private String sourcePath;
    /*** 名称 */
    private String fileName;
    /*** 起始位置 */
    private String startNum;
    /*** 标题位置 */
    private String titleNum;


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
        infoMap.put(START_NUM_STR, startNum);
        infoMap.put(TITLE_NUM_STR, titleNum);
        return infoMap;
    }

    @Override
    public void setInfo(Map info) {
        this.startNum = (String)info.get(START_NUM_STR);
        this.titleNum = (String)info.get(TITLE_NUM_STR);
    }

    @Override
    public void setData(List data) {
        this.data = data;
    }

    @Override
    public List getData() {
        return this.data;
    }
}
