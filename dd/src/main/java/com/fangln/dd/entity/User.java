package com.fangln.dd.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fangln on 2018/6/5.
 */
@Data
public class User implements Serializable {
    
    private Long id;// bigint(20) NOT NULL,
    private String phone;// varchar(20) DEFAULT NULL,
    private String nickname;// varchar(100) DEFAULT NULL,
    private String openid;// varchar(128) DEFAULT NULL,
    private String unionid;// varchar(128) DEFAULT NULL,
    private String province;// varchar(45) DEFAULT NULL,
    private String city;// varchar(45) DEFAULT NULL,
    private String country;// varchar(45) DEFAULT NULL,
    private String head_img_url;// varchar(200) DEFAULT NULL,
    private Integer sex;// tinyint(4) DEFAULT NULL,
    private Date reg_time;// datetime DEFAULT NULL,
    private Integer status;
}
