package com.fangln.dd.entity;

import lombok.Data;

import java.util.Calendar;

/**
 * Created by Fangln on 2018/6/6.
 */
@Data
public class UserToken {

    private Long user_id;
    private Long token_expire;//token有效期的时间戳

    public UserToken(Long user_id,Long token_expire){
        this.user_id=user_id;
        if(token_expire == null){
            Calendar cl=Calendar.getInstance();
            cl.add(Calendar.SECOND, 7200);
            long validTimeMills=cl.getTimeInMillis();
            token_expire = validTimeMills;
        }
        this.token_expire=token_expire;
    }
}
