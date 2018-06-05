package com.fangln.dd.controller;

import com.fangln.dd.entity.User;
import com.fangln.dd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Fangln on 2018/6/5.
 */
@Controller
public class UserCtroller {
    
    @Autowired
    private UserService userService;

    @RequestMapping("mini/public/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        Map<String, Object> paramMap = null;
        userService.selectUsers(paramMap,1);
    }


    public void index(HttpServletRequest request, HttpServletResponse response){


    }

    public void addHabit(HttpServletRequest request, HttpServletResponse response){


    }

    public void signHabit(HttpServletRequest request, HttpServletResponse response){


    }

    public void habitDetail(HttpServletRequest request, HttpServletResponse response){


    }

    public void userIndex(HttpServletRequest request, HttpServletResponse response){


    }

}
