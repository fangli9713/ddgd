package com.fangln.dd.service.user;

import com.fangln.dd.dao.UserMapper;
import com.fangln.dd.entity.User;
import com.fangln.dd.util.CoreProperties;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
@Service("userService")
public class UserServiceImpl implements  UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CoreProperties coreProperties;

    @Override
    public List<User> selectUserById(Long id) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        return selectUsers(paramMap,0);
    }

    @Override
    public List<User> selectUsers(Map<String, Object> paramMap, int pageNum) {
        final int pageSize = coreProperties.getPageSize();
        PageHelper.startPage(pageNum);
        System.out.println("pageSize="+pageSize);
        return userMapper.selectUsers(paramMap);
    }
}
