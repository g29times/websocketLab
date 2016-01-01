package work.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarker���ɾ�̬html
 *
 * @author lpz
 */
public class TempHtmlAction {
    private Configuration config = null;

    /**
     * ���Ŀ¼�����ڣ����Զ�����
     *
     * @param path
     * @return boolean �Ƿ�ɹ�
     */
    private boolean creatDirs(String path) {
        File aFile = new File(path);
        if (!aFile.exists()) {
            return aFile.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * ģ�����ɾ�̬html�ķ���
     *
     * @param templateFileName(ģ���ļ���)
     * @param templateFilePath(ָ��ģ��Ŀ¼)
     * @param contextMap(���ڴ���ģ�������Objectӳ��)
     * @param htmlFilePath(ָ�����ɾ�̬html��Ŀ¼)
     * @param htmlFileName(���ɵľ�̬�ļ���)
     */
    @SuppressWarnings("unchecked")
    public void geneHtmlFile(String templateFileName, String templateFilePath, Map contextMap,
                             String htmlFilePath, String htmlFileName) {

        try {
            Template t = this.getFreeMarkerCFG(templateFilePath).getTemplate(templateFileName);
            // �����·������,��ݹ鴴����Ŀ¼
            this.creatDirs(htmlFilePath);
            File afile = new File(htmlFilePath + "/" + htmlFileName);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(afile)));
            t.process(contextMap, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            System.out.print(e.getMessage());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * ��ȡfreemarker�����ã�freemarker����֧��classpath,Ŀ¼���ServletContext��ȡ.
     *
     * @param templateFilePath ��ȡģ��·��
     * @return Configuration ����freemaker����������
     * @throws Exception
     */
    private Configuration getFreeMarkerCFG(String templateFilePath)
            throws Exception {
        if (null == this.config) {

//            this.config = new Configuration();
            try {
                this.config.setDirectoryForTemplateLoading(new File(
                        templateFilePath));
            } catch (Exception ex) {
                throw ex;
            }
        }
        return this.config;
    }

}