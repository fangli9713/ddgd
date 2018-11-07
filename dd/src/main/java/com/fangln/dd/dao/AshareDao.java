package com.fangln.dd.dao;

import com.fangln.dd.dto.Ashare;
import com.fangln.dd.dto.HistoryPrice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Fangln on 2018/11/7.
 */
@Repository
public interface AshareDao {

    public List<Ashare> selectAshareList();

    public List<HistoryPrice> selectAshareHistoryPriceList(HistoryPrice historyPrice);

    public int insertAshareHistoryPrice(HistoryPrice historyPrice);
}
