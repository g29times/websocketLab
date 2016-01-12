package work.Excel.impl;

import work.Excel.api.Excel;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Excuse on 2016/1/11.
 */
public abstract class AbsExcelFile implements Excel {
    @Override
    public void setData(List data) {

    }

    @Override
    public List getData() {
        return null;
    }

    @Override
    public void setKey(Object key) {

    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public void setName(String fileName) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setInfo(Map info) {

    }

    @Override
    public Map getInfo() {
        return null;
    }

    @Override
    public File getExcel() {
        return null;
    }
}
