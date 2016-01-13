/**
 * Copyright (c) 2014 Alibaba Cloud Computing
 */
package work.OAS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oas.OASFactory;
import com.aliyun.oas.core.AliyunOASClient;
import com.aliyun.oas.model.common.ClientConfiguration;
import com.aliyun.oas.model.common.ServiceCredentials;
import com.aliyun.oas.model.exception.OASClientException;
import com.aliyun.oas.model.exception.OASServerException;
import com.aliyun.oas.model.request.DeleteVaultRequest;
import com.aliyun.oas.model.result.OASResult;

/**
 * 
 * @author jialan@alibaba-inc.com
 * @version $Id: TestGetVaultList.java, v 0.1 2015-5-20 下午3:42:09 jialan Exp $
 */
public class TestDeleteVault {
    //Create the logger
    private static final Logger logger = LoggerFactory.getLogger(TestDeleteVault.class);

    public static void main(String[] args) {
        String yourAccessId = "yc6swhL97Ea4lQ6K";
        String yourAccessKey = "X7PRLMTHpldIfLbHJGGunlTI7glw71";
        //测试数据，需要换成用户真实的VaultId
        String yourVaultId = "";

        // AccessId和AccessKey配置
        ServiceCredentials credentials = new ServiceCredentials(yourAccessId, yourAccessKey);

        // 配置服务地址，默认为 http://cn-hangzhou.oas.aliyuncs.com
        // ClientConfiguration clientConf = ClientConfiguration.createDefault();

        //用withHost方法，指定要连接的服务地址
        ClientConfiguration clientConf = new ClientConfiguration();

        // 用OAS工厂获得AliyunOASClient对象
        AliyunOASClient aliyunOASClient = OASFactory.getEmptyAliyunOASClient();
        aliyunOASClient.withHost("http://cn-hangzhou.oas.aliyuncs.com")
            .withServiceCredentials(credentials).withClientConfiguration(clientConf);

        // 注意：
        // 删除vault是高危动作，因此只能通过vaultId来删除
        DeleteVaultRequest deleteVaultRequest = new DeleteVaultRequest().withVaultId(yourVaultId);

        try {
            OASResult result = aliyunOASClient.deleteVault(deleteVaultRequest);
            logger.info("Delete Success! {} {}", result.getRequestId(), result.getDate());
        } catch (OASClientException e) {
            logger.error("OASClientException Occured:", e);
        } catch (OASServerException e) {
            logger.error("OASServerException Occured:", e);
        }

    }
}
