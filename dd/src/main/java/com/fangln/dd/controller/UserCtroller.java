package com.fangln.dd.controller;

import com.fangln.dd.entity.User;
import com.fangln.dd.entity.UserHabit;
import com.fangln.dd.service.user.UserHabitService;
import com.fangln.dd.service.user.UserService;
import com.fangln.dd.util.MiniappUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
@Controller
public class UserCtroller {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserHabitService userHabitService;

    /**
     * 登录
     * @param request
     * @param response
     */
    @RequestMapping("mini/public/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        Map<String, Object> paramMap = null;
        userService.selectUsers(paramMap,1);

        User user1 = MiniappUtil.getUser(request, response);

        MiniappUtil.writeSuccess(response,user1);
    }


    /**
     * 首页
     * @param request
     * @param response
     */
    public void index(HttpServletRequest request, HttpServletResponse response){
        User user = MiniappUtil.getUser(request,response);

        List<Map<String, String>> maps = userHabitService.userHabitList(user.getId());

        MiniappUtil.writeSuccess(response,maps);
    }

    /**
     * 添加习惯
     * @param request
     * @param response
     */
    public void addHabit(HttpServletRequest request, HttpServletResponse response){


    }

    /**
     * 签到
     * @param request
     * @param response
     */
    public void signHabit(HttpServletRequest request, HttpServletResponse response){


    }

    /**
     * 查看习惯的详情
     * @param request
     * @param response
     */
    public void habitDetail(HttpServletRequest request, HttpServletResponse response){


    }

    /**
     * 用户中心的首页
     * @param request
     * @param response
     */
    public void userIndex(HttpServletRequest request, HttpServletResponse response){


    }

}
