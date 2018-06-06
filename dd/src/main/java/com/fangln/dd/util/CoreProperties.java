package com.fangln.dd.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Fangln on 2018/6/5.
 */
@Component
@PropertySource("classpath:config/core.properties")
@ConfigurationProperties(prefix = "miniapp")
@Data
public class CoreProperties {

    private static class SingletonHolder {
        private static CoreProperties singleton = new CoreProperties();
    }

    private CoreProperties() {
    }

    public static CoreProperties newInstance() {
        return SingletonHolder.singleton;
    }




    private int page_size;

    private String app_id;

    private String app_secret;

    private int token_expire;

}
