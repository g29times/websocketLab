package work.Excel.api;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Excel文件基础信息规范
 * Created by Excuse on 2016/1/11.
 */
public interface Excel<T>  {

    String DEMO_FILE_PATH = "src/work/test/resources/3.xls";
    String DATE_FORMAT = "yyyy-MM-dd";
    String START_NUM_STR = "startNum";
    String TITLE_NUM_STR = "titleNum";

    /**********************
     * EXCEL数据结构 List<Map>
     *
     *          Title
     * Key	    Col1	Col2
     * 1&1	    11	    12
     * 2&2	    21	    22
     * 3&3	    31	    32
     *
     **********************/

    /**
     * Key ： ColName & Index
     * the key may be an index or some composed property sets
     * @param key
     */
    void setKey(Object key);
    String getKey();

    void setName(String fileName);
    String getName();

    void setInfo(Map info);
    Map getInfo();

    File getExcel();

    List getData();
    void setData(List data);

}
