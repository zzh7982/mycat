package com.sbjava.cat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ralf
 */
@Configuration
@ConfigurationProperties(prefix = "wx")
@Data
public class WxProperties {

    private String appId;

    private String appSecret;

    private String mchId;

    private String mchKey;

    private String notifyUrl;

}
