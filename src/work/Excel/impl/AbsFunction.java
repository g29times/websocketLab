package work.Excel.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import work.Excel.api.Excel;
import work.Excel.api.ExcelAPI;
import work.Excel.api.Function;
import work.Excel.bean.TestBean;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Excuse on 2016/1/11.
 */
public class AbsFunction implements Function, ExcelAPI {

    private Excel source;

    @Override
    public Excel getExcel() { return null; }

    @Override
    public Object importExcel(Excel source, Function process) {
        return null;
    }

    @Override
    public Excel exportExcel(Object data) {
        return null;
    }

    @Override
    public Excel convertExcel(Excel source, Object data) {
        return null;
    }

    @Override
    public void run() {}

    public void setSource(Excel source) {
        this.source = source;
    }

    public void saveBeans() {
        // File properties, List data
        Iterator iterator = source.getData().iterator();
        while (iterator.hasNext()) {
            TestBean bean = new TestBean();
            for(Map.Entry entry : (Set<Map.Entry>)((Map) iterator.next()).entrySet()) {
                if(entry.getKey().equals("Col1"))
                    bean.setPro1(((Double)entry.getValue()).intValue());
                else if(entry.getKey().equals("Col2"))
                    bean.setPro2(((Double)entry.getValue()).toString());
            }
            System.out.println(bean.getPro1());
            System.out.println(bean.getPro2());
            System.out.println(bean.getPro3());
            bean.save();
        }
    }

    /** 获取起始行 */
    public int getStart(Excel source) {
        Map info = source.getInfo();
        return Integer.valueOf((String)info.get("startNum"));
    }

    /** 获取标题行 */
    public int getTitle(Excel source) {
        Map info = source.getInfo();
        return Integer.valueOf((String)info.get("titleNum"));
    }

    protected HSSFSheet getContent(Excel source) {
        File file = source == null ? new File(Excel.DEMO_FILE_PATH) : source.getExcel();
        if(file == null)
            return null;
        POIFSFileSystem poifsFileSystem;
        HSSFWorkbook hssfWorkbook;
        try {
            poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
            hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
        } catch (Exception e) {
            return null;
        }
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        return hssfSheet;
    }

    protected Object readContent(HSSFSheet hssfSheet, Function process) {
        if(hssfSheet == null)
            return STATUS_FAILED;

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
         * num\row   Col1   Col2
         * 1.0      11.0   12.0
         * 2.0      21.0   22.0
         * 3.0      31.0   32.0
         *
        * */
        /** Excel列表名序列 */
        List titleList = new ArrayList<>();
        /** Excel行 */
        List dataList = new LinkedList<>();
        /** Excel列 */
        Map dataEntry = null;

        try {
            int rowStart = hssfSheet.getFirstRowNum();
            int rowEnd = hssfSheet.getLastRowNum();

            for (int i = rowStart; i <= rowEnd; i++) {
                HSSFRow row = hssfSheet.getRow(i);
                if (null == row) continue;
                // TODO
                dataEntry = new HashMap<>();
                if(i == getStart(source))
                    System.out.print("Title : ");
                else
                    System.out.print("Value : ");

                int cellStart = row.getFirstCellNum();
                int cellEnd = row.getLastCellNum();

                for (int k = cellStart; k <= cellEnd; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (null == cell) continue;
                    //System.out.print("" + k + "  ");
                    //System.out.print("type:"+cell.getCellType());
                    // TODO

                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                            if(HSSFDateUtil.isCellDateFormatted(cell)) { // 日期
                                SimpleDateFormat format = new SimpleDateFormat(Excel.DATE_FORMAT);
                                System.out.print(format.format(cell.getDateCellValue())
                                        + "   ");
                            } else {
                                System.out.print(cell.getNumericCellValue()
                                        + "   ");
                                if(i == getTitle(source))
                                    titleList.add(cell.getNumericCellValue());
                                else if(i > getTitle(source))
                                    dataEntry.put(titleList.get(k), cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            System.out.print(cell.getStringCellValue()
                                    + "   ");
                            if(i == getTitle(source))
                                titleList.add(cell.getStringCellValue());
                            else if(i > getTitle(source))
                                dataEntry.put(titleList.get(k), cell.getStringCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            System.out.println(cell.getBooleanCellValue()
                                    + "   ");
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            System.out.print(cell.getCellFormula() + "   ");
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            System.out.println(" ");
                            break;
                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            System.out.println(" ");
                            break;
                        default:
                            System.out.print("未知类型   ");
                            break;
                    }

                }
                System.out.print("\n");
                if(dataEntry.size() > 0)
                    dataList.add(dataEntry);
            }

            // TODO process.do()
//            System.out.print("\n");
//            printCol(titleList);
//            System.out.print("\n");
//            printCol(dataList);

            source.setData(dataList);

//            Iterator iterator = dataList.iterator();
//            while (iterator.hasNext()) {
//                TestBean bean = new TestBean();
//                for(Map.Entry entry : (Set<Map.Entry>)((Map) iterator.next()).entrySet()) {
//                    if(entry.getKey().equals("Col1"))
//                        bean.setPro1(((Double)entry.getValue()).intValue());
//                    else if(entry.getKey().equals("Col2"))
//                        bean.setPro2(((Double)entry.getValue()).toString());
//                }
//                System.out.println(bean.getPro1());
//                System.out.println(bean.getPro2());
//                System.out.println(bean.getPro3());
//                bean.save();
//            }

            Thread thread = new Thread(process);
            thread.start();

        } catch (Exception e) {
            e.printStackTrace();
            return STATUS_ERROR;
        }

        return null;
    }

    private void printCol(Object e) {
        if(e instanceof List || e instanceof Set) {
            Iterator iterator = ((Collection)e).iterator();
            while (iterator.hasNext()) {
                System.out.println("Value=" + iterator.next());

            }
        } else if (e instanceof Map) {
            for(Map.Entry entry : (Set<Map.Entry>)((Map) e).entrySet()) {
                System.out.println("key=" + entry.getKey() + " and value=" + entry.getValue());

            }
        }

    }
}
