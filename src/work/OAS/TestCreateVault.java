/**
 * Copyright (c) 2014 Alibaba Cloud Computing
 */
package work.OAS;

import com.aliyun.oas.ease.ArchiveManager;
import com.aliyun.oas.ease.monitor.JobMonitor;
import com.aliyun.oas.model.result.UploadResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oas.OASFactory;
import com.aliyun.oas.core.AliyunOASClient;
import com.aliyun.oas.model.common.ClientConfiguration;
import com.aliyun.oas.model.common.ServiceCredentials;
import com.aliyun.oas.model.common.ServiceHost;
import com.aliyun.oas.model.exception.OASClientException;
import com.aliyun.oas.model.exception.OASServerException;
import com.aliyun.oas.model.request.CreateVaultRequest;
import com.aliyun.oas.model.result.CreateVaultResult;

import java.io.File;

/**
 * 创建vault demo程序
 * 每个用户最多创建10个vault
 *
 * @author jialan@alibaba-inc.com
 * @version $Id: TestCreateVault.java, v 0.1 2015-5-12 上午11:18:20 jialan Exp $
 */
public class TestCreateVault implements Configuration {
    //Create the logger
    private static final Logger logger = LoggerFactory.getLogger(TestCreateVault.class);
    //初始化认证信息
    private static ServiceCredentials credentials;
    //服务地址
    private static ServiceHost serviceHost;
    //客户端配置
    private static ClientConfiguration clientConfiguration;
    // 方法1
    private static AliyunOASClient aliyunOASClient;

    static {
        //初始化认证信息
        credentials = new ServiceCredentials(AccessKeyId,
                AccessKeySecret);
        //服务地址
        serviceHost = new ServiceHost(PROTOCOL_HTTP + RegionName_HangZhou + PUBLIC_DOMAIN, PORT_80);
        //客户端配置
        clientConfiguration = new ClientConfiguration();
        // 方法1
        aliyunOASClient = OASFactory
                .aliyunOASClientFactory(serviceHost, credentials, clientConfiguration).withLogger();
    }

    public static void main(String[] args) {
        // 方法2
        //        AliyunOASClient aliyunOASClient2 = OASFactory.aliyunOASClientFactory(credentials,
        //            "http://cn-hangzhou.oas.aliyuncs.com");

        //        AliyunOASClient aliyunOASClient2 = OASFactory.getEmptyAliyunOASClient().withLogger();
        //        aliyunOASClient2
        //            .withServiceCredentials(new ServiceCredentials(yourAccessId, yourAccessKey));
        //
        //        aliyunOASClient2.withHost("http://cn-hangzhou.oas.aliyuncs.com/");

        // 或者像下面这样，通过url创建AliyunOASClient对象
        //        AliyunOASClient aliyunOASClient = OASFactory.aliyunOASClientFactory(credentials,
        //            "http://cn-hangzhou.oas.aliyuncs.com").withLogger();

        // vaultName必须满足已下2个条件：
        // 1. 总长度在3~63之间（包括3和63）；
        // vaultName必须满足已下2个条件：
        // 1. 总长度在3~63之间（包括3和63）；
        // 2. 只允许包含以下字符：
        //         0-9(数字),
        //         a-z(小写英文字母),
        //         -(短横线),
        //         _(下划线)
        // 其中 短横线 和 下划线 不能作为vaultName的开头和结尾；
    }

    /** 建库 */
    @Test
    public void createVault() {
        // 发送创建vault请求
        // 创建Vault的名称，用CreateVaultRequest来指定
        CreateVaultRequest createRequest = new CreateVaultRequest().withVaultName(VaultName);

        // 发起创建Vault的请求
        // 如果有同名的vault存在，OAS会返回已有的vault的vaultId，而不会执行创建动作
        try {
            CreateVaultResult result = aliyunOASClient.createVault(createRequest);
            logger.info("Vault created vaultId={}", result.getVaultId());
            logger.info("Vault created vaultLocation={}", result.getLocation());
        } catch (OASClientException e) {
            logger.error("OASClientException Occured:", e);
        } catch (OASServerException e) {
            logger.error("OASServerException Occured:", e);
        }
    }
    /** 普通文件同步上传 建议查询采用同步方式 上传下载采用异步方式 */
    @Test
    public void TestArchiveManager() {
        ArchiveManager archiveManager = OASFactory.archiveManagerFactory(aliyunOASClient).withNumConcurrence(5).withMaxRetryTimePerRequest(3);

        File file = new File(Test_file);
        try {
            UploadResult uploadResult = archiveManager.upload(VaultName, file, "first test file");
            logger.info("File {} uploaded complete. ArchiveId={},md5={},treeEtag={}",
                    file.getAbsolutePath(),
                    uploadResult.getArchiveId(),
                    uploadResult.getContentEtag(),
                    uploadResult.getTreeEtag());
        } catch (OASClientException e) {
            logger.error("OASClientException Occured:", e);
        } catch (OASServerException e) {
            logger.error("OASServerException Occured:", e);
        }

    }
    /** 大文件上传 */
    @Test
    public void TestMultipartUpload() {
        ArchiveManager archiveManager = OASFactory.archiveManagerFactory(aliyunOASClient).withNumConcurrence(5).withMaxRetryTimePerRequest(3);

        File file = new File(Test_Bigfile);
        //获得uploadId
        //文件大小必须大于100MB，否则会抛异常提示用普通上传接口进行上传
        String uploadId = archiveManager.initiateMultipartUpload(VaultName, file, "Hello OAS!");
        //使用已有的uploadId进行再次上传时，支持该任务的续传。
        //String uploadId = "[yourUploadId]";
        System.out.println("Get uploadId=" + uploadId);
        UploadResult uploadResult = archiveManager.uploadWithUploadId(VaultName, file, uploadId);
        System.out.println("Archive ID=" + uploadResult.getArchiveId());
    }
    /** 下载
     * 1 用户 通过接口提交相应类型的Job；
     * 2 OAS 接收到Job并安排其执行；
     * 3 用户 将已完成Job的输出内容下载到本地。
     */
    @Test
    public void TestDownload() {
        ArchiveManager archiveManager = OASFactory.archiveManagerFactory(aliyunOASClient).withNumConcurrence(5).withMaxRetryTimePerRequest(3);

        // 提交提档任务
        JobMonitor jobMonitor = archiveManager.downloadAsync(VaultName, Test_fileId);
        // 执行Inventory
        archiveManager.downloadInventoryAsync(VaultName);
        // 下载Job输出
        archiveManager.downloadJobOutput(VaultName, jobMonitor.getJobId(),
                new File(Test_Destfile));

    }
}
