package com.fangln.dd.service.user;

import com.fangln.dd.entity.UserHabit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserHabitService {

    List<Map<String,Object>> userHabitList(Long userId);

    List<Map<String,Object>> userHabitList(Long userId,int pageNum);

    Map<String,Object> userHabitDetail(Long userHabitId);

    int addUserHabit(UserHabit userHabit);



}
