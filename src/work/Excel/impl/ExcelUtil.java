package work.Excel.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import work.Excel.api.Excel;
import work.Excel.api.ExcelAPI;
import work.Excel.api.Function;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Excel �����߼� ʵ����
 * Created by Excuse on 2016/1/11.
 */
public class ExcelUtil implements ExcelAPI {

    @Test
    public void testImport() {
        Excel source = createExcel();

        Map infoMap = new HashMap<>();
        infoMap.put("startNum", "2");
        source.setInfo(infoMap);

        importExcel(source, new Function() {
            @Override
            public int getStart(Excel source) {
                Map info = source.getInfo();
                return Integer.valueOf((String)info.get("startNum"));
            }

            @Override
            public Excel getSource() {
                return source;
            }
        });
    }

    /**
     * ����
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

    private HSSFSheet getContent(Excel source) {
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

    private Object readContent(HSSFSheet hssfSheet, Function process) {
        if(hssfSheet == null)
            return STATUS_FAILED;
        // TODO process.do()
        try {
            int rowStart = hssfSheet.getFirstRowNum();
            int rowEnd = // hssfSheet.getLastRowNum();
            process.getStart(process.getSource());
            for (int i = rowStart; i <= rowEnd; i++) {
                HSSFRow row = hssfSheet.getRow(i);
                if (null == row) continue;
                int cellStart = row.getFirstCellNum();
                int cellEnd = row.getLastCellNum();

                for (int k = cellStart; k <= cellEnd; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (null == cell) continue;
                    //System.out.print("" + k + "  ");
                    //System.out.print("type:"+cell.getCellType());

                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // ����
                            if(HSSFDateUtil.isCellDateFormatted(cell)) { // ����
                                SimpleDateFormat format = new SimpleDateFormat(Excel.DATE_FORMAT);
                                System.out.print(format.format(cell.getDateCellValue())
                                        + "   ");
                            } else
                                System.out.print(cell.getNumericCellValue()
                                    + "   ");
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // �ַ���
                            System.out.print(cell.getStringCellValue()
                                    + "   ");
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            System.out.println(cell.getBooleanCellValue()
                                    + "   ");
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ
                            System.out.print(cell.getCellFormula() + "   ");
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // ��ֵ
                            System.out.println(" ");
                            break;
                        case HSSFCell.CELL_TYPE_ERROR: // ����
                            System.out.println(" ");
                            break;
                        default:
                            System.out.print("δ֪����   ");
                            break;
                    }

                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            return STATUS_ERROR;
        }
        return null;
    }
}
