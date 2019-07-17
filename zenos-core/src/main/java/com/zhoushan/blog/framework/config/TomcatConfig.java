package com.zhoushan.blog.framework.config;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhooo
 * @version V1.0
 * @Title: TomcatConfig
 * @Package com.zhoushan.blog.framework.config
 * @Description: TODO
 * @date 7/14/2019 11:02 PM
 */
@Configuration
public class TomcatConfig {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(
                connector -> {
                    Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
                    protocolHandler.setDisableUploadTimeout(false);
                }
        );
        return factory;
    }
}
