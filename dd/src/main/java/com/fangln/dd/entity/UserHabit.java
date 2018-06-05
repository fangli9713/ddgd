package com.fangln.dd.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Fangln on 2018/6/5.
 */
@Data
public class UserHabit {
    
   private Long id;// bigint(20) NOT NULL COMMENT 'id',
    private String habit_name;// varchar(50) DEFAULT NULL,
    private Long user_id;// bigint(20) DEFAULT NULL,
    private Date create_time;// datetime DEFAULT NULL,
    private Integer use_number;// tinyint(4) DEFAULT NULL,
    private Integer use_rate_unit;// tinyint(4) DEFAULT NULL COMMENT '频率单位:0:小时,1:天,2:周,3:月,4:年',
    private Integer use_rate_number;// tinyint(4) DEFAULT NULL COMMENT '频率的数量',

}
