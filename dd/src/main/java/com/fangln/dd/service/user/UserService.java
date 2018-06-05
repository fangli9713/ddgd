package com.fangln.dd.service.user;

import com.fangln.dd.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
@Repository
public interface UserService {

    List<User> selectUserById(Long id);
    List<User> selectUsers(Map<String,Object> paramMap, int pageNum);

}
