package com.fangln.dd.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by Fangln on 2018/6/5.
 */
@Data
public class UserHabitRecord {

    private Long id;// bigint(20) NOT NULL,
    private Long user_id;// bigint(20) DEFAULT NULL,
    private Long habit_id;// bigint(20) DEFAULT NULL,
    private Date create_time;// datetime DEFAULT NULL,
    private String remark;// varchar(100) DEFAULT NULL,
    private Date record_time;// datetime DEFAULT NULL,
}
