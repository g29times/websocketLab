package work.Excel.impl;

import org.junit.Test;
import work.Excel.api.Excel;
import work.Excel.api.Function;
import work.Excel.bean.TestBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Excel �����߼� ʵ����
 * http://www.yeetrack.com/?p=961
 * Created by Excuse on 2016/1/11.
 */
public class ExcelUtil extends AbsFunction {

    @Test
    public void testImport() {
        // TODO ����ͨ��
        Excel source = getExcel();
        // ����ajax
        importExcel(source, () -> saveBeans());
    }

    @Override
    public Excel getExcel() {
        Excel source = new ExcelFile();
        source.setName("src/work/test/resources/1.xls");
        // ҵ����Ϣ
        Map infoMap = new HashMap<>();
        infoMap.put(source.START_NUM_STR, "1");
        infoMap.put(source.TITLE_NUM_STR, "1");
        source.setInfo(infoMap);
        //
        setSource(source);
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
