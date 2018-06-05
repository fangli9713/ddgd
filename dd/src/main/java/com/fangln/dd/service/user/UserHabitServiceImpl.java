package com.fangln.dd.service.user;

import com.fangln.dd.dao.UserHabitMapper;
import com.fangln.dd.entity.UserHabit;
import com.fangln.dd.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHabitServiceImpl implements  UserHabitService {

    @Autowired
    private UserHabitMapper userHabitMapper;

    @Override
    public List<Map<String, String>> userHabitList(Long userId) {
        return userHabitList(userId,0);
    }

    @Override
    public List<Map<String, String>> userHabitList(Long userId, int pageNum) {
        Map<String, Object> paramMap = new HashMap<>();
        PageUtil.initPageHelper(pageNum);
        paramMap.put("user_id",userId);
        List<UserHabit> userHabits = userHabitMapper.selectUserHabit(paramMap);
    }

    @Override
    public Map<String, String> userHabitDetail(Long userHabitId) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",userHabitId);
        List<UserHabit> userHabits = userHabitMapper.selectUserHabit(paramMap);

    }

    @Override
    public int addUserHabit(UserHabit userHabit) {
        userHabitMapper.insertUserHabit(userHabit);
    }
}
