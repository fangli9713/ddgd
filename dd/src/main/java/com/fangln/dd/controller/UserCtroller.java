package com.fangln.dd.controller;

import com.fangln.dd.entity.User;
import com.fangln.dd.entity.UserHabit;
import com.fangln.dd.service.user.UserHabitService;
import com.fangln.dd.service.user.UserService;
import com.fangln.dd.util.MiniappConstant;
import com.fangln.dd.util.MiniappUtil;
import com.fangln.dd.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
@Controller
public class UserCtroller {
    
//    @Autowired
//    private UserService userService;

    @Autowired
    private UserHabitService userHabitService;

    @RequestMapping("/")
    @ResponseBody
    public String welcome(HttpServletRequest request, HttpServletResponse response){
        return "welcome to foundfun";
    }
    /**
     * 登录
     * @param request
     * @param response
     */
    @RequestMapping("mini/public/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        User user = MiniappUtil.getUser(request, response);
        if(user!=null){
            final String token = UserTokenUtil.createToken(user.getId());
            Map<String,String> returnMap = new HashMap<String,String>();
            returnMap.put("token", token);
            MiniappUtil.writeString(response, returnMap,MiniappConstant.OP_SUCCESS,"欢迎");
        }
    }

    /**
     * 首页
     * @param request
     * @param response
     */
    @RequestMapping("mini/private/index")
    public void index(HttpServletRequest request, HttpServletResponse response){
        User user = MiniappUtil.getUser(request,response);
        if(user==null){
            return;
        }
        List<Map<String, Object>> maps = userHabitService.userHabitList(user.getId());
        if(CollectionUtils.isEmpty(maps)){
            MiniappUtil.writeEmpty(response);
            return;
        }
        MiniappUtil.writeSuccess(response,maps);
    }

    /**
     * 添加习惯
     * @param request
     * @param response
     */
    @RequestMapping("mini/private/addHabit")
    public void addHabit(HttpServletRequest request, HttpServletResponse response){
        User user = MiniappUtil.getUser(request,response);
        if(user==null){
            return;
        }
        final Map<?, ?> requestMap = MiniappUtil.getRequestMap(request);
        final String habit_name = (String)requestMap.get("habit_name");
        final String use_number = (String)requestMap.get("use_number");

        final String use_rate_unit = (String)requestMap.get("use_rate_unit");
        final String use_rate_number = (String)requestMap.get("use_rate_number");

        UserHabit userHabit = new UserHabit();
        userHabit.setCreate_time(new Date());
        userHabit.setHabit_name(habit_name);
        userHabit.setUser_id(user.getId());
        userHabit.setUse_number(Integer.valueOf(use_number));
        userHabit.setUse_rate_unit(Integer.valueOf(use_rate_unit));
        userHabit.setUse_rate_number(Integer.valueOf(use_rate_number));
        final int i = userHabitService.addUserHabit(userHabit);
        if(i>0){
            MiniappUtil.writeSuccess(response,null);
            return;
        }
        MiniappUtil.writeString(response,null,MiniappConstant.ERR_INTERNAL,"添加失败了");
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
