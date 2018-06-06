package com.fangln.dd.service.user;

import com.fangln.dd.dao.UserHabitMapper;
import com.fangln.dd.entity.UserHabit;
import com.fangln.dd.util.JavaBeanUtil;
import com.fangln.dd.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userHabitService")
public class UserHabitServiceImpl implements  UserHabitService {

    @Autowired
    private UserHabitMapper userHabitMapper;

    @Override
    public List<Map<String, Object>> userHabitList(Long userId) {
        return userHabitList(userId,0);
    }

    @Override
    public List<Map<String, Object>> userHabitList(Long userId, int pageNum) {
        Map<String, Object> paramMap = new HashMap<>();
        PageUtil.initPageHelper(pageNum);
        paramMap.put("user_id",userId);
        List<UserHabit> userHabits = userHabitMapper.selectUserHabit(paramMap);
        if(CollectionUtils.isEmpty(userHabits)){
            return null;
        }

        List<Map<String, Object>> list = new ArrayList<>();
        for (UserHabit habit:userHabits) {
            list.add(JavaBeanUtil.toStringMap(habit));
        }
        return list;
    }

    @Override
    public Map<String, Object> userHabitDetail(Long userHabitId) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",userHabitId);
        List<UserHabit> userHabits = userHabitMapper.selectUserHabit(paramMap);
        if(CollectionUtils.isEmpty(userHabits)){
            return null;
        }
        return JavaBeanUtil.toStringMap(userHabits.get(0));
    }

    @Override
    public int addUserHabit(UserHabit userHabit) {
        return userHabitMapper.insertUserHabit(userHabit);
    }
}
