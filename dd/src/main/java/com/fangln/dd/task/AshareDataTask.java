package com.fangln.dd.task;

import com.fangln.dd.ashare.PSYUtil;
import com.fangln.dd.dao.AshareDao;
import com.fangln.dd.dto.Ashare;
import com.fangln.dd.dto.HistoryPrice;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Fangln on 2018/11/7.
 */
public class AshareDataTask extends QuartzJobBean {

    @Autowired
    private AshareDao ashareDao;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final List<Ashare> ashares = ashareDao.selectAshareList();
        if(CollectionUtils.isEmpty(ashares)){
            return;
        }
        for (Ashare a :ashares){
            final List<HistoryPrice> psyAndMA = PSYUtil.getPSYAndMA(a.getCode());
            if(CollectionUtils.isEmpty(psyAndMA)){
                continue;
            }
            for (HistoryPrice p:psyAndMA) {
                final List<HistoryPrice> historyPrices = ashareDao.selectAshareHistoryPriceList(p);
                if(!CollectionUtils.isEmpty(historyPrices))
                    continue;
                ashareDao.insertAshareHistoryPrice(p);
            }

        }
    }
}
