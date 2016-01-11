package work.Excel.impl;

import work.Excel.api.Excel;
import work.Excel.api.Function;

/**
 * Created by Excuse on 2016/1/11.
 */
public class AbsFunction implements Function {

    @Override
    public int getStart(Excel source) {
        return 0;
    }

    @Override
    public Excel getSource() {
        return null;
    }
}
