package work.Excel.api;

/**
 * Excel处理行为接口
 * Created by Excuse on 2016/1/11.
 */
public interface ExcelAPI {

    /** 处理进度 生命进程 准备 */
    int PROCESS_STAGE_PREPARE = 0;  // 准备
    /** 处理进度 生命进程 开始 */
    int PROCESS_STAGE_START = 1;
    /** 处理进度 生命进程 处理中 */
    int PROCESS_STAGE_PROCESS = 2;  // 处理中
    /** 处理进度 生命进程 结束 */
    int PROCESS_STAGE_END = 3;
    /** 处理进度 生命进程 关闭 */
    int PROCESS_STAGE_CLOSE = 4;    // 关闭

    /** 状态码 成功 */
    int STATUS_SUCCESS = 1; //
    /** 遇到异常 仍然可以处理 并记录 */
    int STATUS_ERROR = 0;
    /** 遇到错误 退出 */
    int STATUS_FAILED = -1;

    /**
     * 获取Excel文件
     * @return
     */
    Excel getExcel();

    /**
     * 导入Excel文件
     * @param source 源文件
     * @param process 回调函数
     * @return
     */
    Object importExcel(Excel source, Function process);

    /**
     * 导出为Excel文件
     * @return
     */
    Excel exportExcel(Object data);

    /**
     * 转换Excel文件
     * @param source 源文件
     * @param data
     * @return
     */
    Excel convertExcel(Excel source, Object data);

}
