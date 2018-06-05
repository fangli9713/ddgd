package com.fangln.dd.util;

import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;

public class PageUtil {

    public static void initPageHelper(int pageNum){
        CoreProperties properties = CoreProperties.newInstance();
        final int pageSize = properties.getPage_size();
        PageHelper.startPage(pageNum,pageSize);
    }
}
