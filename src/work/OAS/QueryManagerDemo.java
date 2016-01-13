/**
 * Copyright (c) 2014 Alibaba Cloud Computing
 */
package work.OAS;

import com.aliyun.oas.OASFactory;
import com.aliyun.oas.core.AliyunOASClient;
import com.aliyun.oas.ease.QueryManager;
import com.aliyun.oas.model.common.ClientConfiguration;
import com.aliyun.oas.model.common.ServiceCredentials;
import com.aliyun.oas.model.common.ServiceHost;

/**
 * 
 * @author jialan@alibaba-inc.com
 * @version $Id: QueryManagerDemo.java, v 0.1 2015年7月21日 下午2:38:31 jialan Exp $
 */
public class QueryManagerDemo implements Configuration {
    public static void main(String[] args) {
        ServiceCredentials credentials = new ServiceCredentials(AccessKeyId,
                AccessKeySecret);
        ServiceHost serviceHost = new ServiceHost("http://cn-hangzhou.oas.aliyuncs.com");
        ClientConfiguration clientConfiguration = new ClientConfiguration();

        AliyunOASClient aliyunOASClient = OASFactory.aliyunOASClientFactory(serviceHost,
            credentials, clientConfiguration);

        //方法1
        QueryManager queryManager = OASFactory.queryManagerFactory(credentials,
            "http://cn-hangzhou.oas.aliyuncs.com");
        //方法2
        QueryManager queryManager2 = OASFactory.queryManagerFactory(serviceHost, credentials,
            clientConfiguration);
        //方法3
        QueryManager queryManager3 = OASFactory.queryManagerFactory(aliyunOASClient);

    }
}
