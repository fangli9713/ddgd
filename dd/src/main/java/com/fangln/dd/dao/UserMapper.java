package com.fangln.dd.dao;

import com.fangln.dd.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
public interface UserMapper {

    List<User> selectUsers(Map<String,Object> paramMap);

}
