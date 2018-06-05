package com.fangln.dd.util;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Fangln on 2018/6/5.
 */
@Component
@PropertySource("classpath:config/core.properties")
@ConfigurationProperties(prefix = "miniapp")
public class CoreProperties {


    private int pageSize;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
