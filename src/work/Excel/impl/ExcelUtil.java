package work.Excel.impl;

import org.junit.Test;
import work.Excel.api.Excel;
import work.Excel.api.Function;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel 处理逻辑 实现类
 * Created by Excuse on 2016/1/11.
 */
public class ExcelUtil extends AbsFunction {

    @Test
    public void testImport() {
        Excel source = createExcel();
        // 业务信息
        Map infoMap = new HashMap<>();
        infoMap.put("startNum", "2");
        source.setInfo(infoMap);

        // 形似ajax
        importExcel(source, new AbsFunction() {
            @Override
            public int getStart(Excel source) {
                Map info = source.getInfo();
                return Integer.valueOf((String)info.get("startNum"));
            }

            @Override
            public Excel getSource() {
                return source;
            }

            /*
            *
            * Map a bean
            * [attr : col] abc.properties
             * name : colName
             * id   : colId
             * ...
             *
             * key : id + name
             * if a row contains a key, then serialize it. else log this as a bad record by row number.
             *
             * save a bean
             * bean.setPro()
             * bean.save()
             *
            * */
        });
    }

    /**
     * 单例
     * @return
     */
    @Override
    public Excel createExcel() {
        Excel source = new ExcelFile();
        source.setName("src/work/test/resources/1.xls");
        return source;
    }

    @Override
    public Object importExcel(Excel source, Function process) {
        return readContent(getContent(source), process);
    }

    @Override
    public Excel exportExcel(Object data) {
        return null;
    }

    @Override
    public Excel convertExcel(Excel source, Object data) {
        return null;
    }

}
