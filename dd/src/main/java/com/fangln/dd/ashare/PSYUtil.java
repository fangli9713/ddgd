package com.fangln.dd.ashare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangln.dd.dto.HistoryPrice;
import com.fangln.dd.util.http.HttpUtil;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Fangln on 2018/11/6.
 */
public class PSYUtil {

    final private static String BASE_URL = "https://api.shenjian.io/?appid=005271243858e97d099f0dff3d272cd0";


    public static void main(String[] args) {
        final List<HistoryPrice> psyAndMA = getPSYAndMA("002024");
        System.out.println(psyAndMA);
    }

    public static List<HistoryPrice>  getPSYAndMA(String code){

        StringBuilder builder = new StringBuilder(BASE_URL);
        builder.append("&code=").append(code);
        builder.append("&index=false&k_type=day&fq_type=qfq&start_date=");

        //计算开始日期 和 结束日期 倒推 20天

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        Date end = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR,-28);
        Date start = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        builder.append(sdf.format(start));
        builder.append("&end_date=").append(sdf.format(end));
        List<HistoryPrice> insertList = new ArrayList<>();

        try {
            final String s = HttpUtil.doRequest(builder.toString(), null, null);
            System.out.println(s);
            final Map<?,?> map = JSON.parseObject(s,Map.class);
            final Integer error_code = (Integer)map.get("error_code");
            if(error_code==null || error_code!=0){
                return null;
            }
            final JSONArray data = (JSONArray)map.get("data");
            final List<HistoryPrice> list = JSONObject.parseArray(data.toJSONString(), HistoryPrice.class);
            final LinkedList<HistoryPrice> dataList = new LinkedList<>(list);
            while (true){
                    final BigDecimal psy = getPSY(dataList);
                    if(psy==null)
                        break;
                final HistoryPrice map1 = dataList.removeLast();
                map1.setPsy(psy);
                map1.setAlias(map1.getCode());
                map1.setCode(code);
                insertList.add(map1);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertList;
    }

    public static BigDecimal getPSY(List<HistoryPrice> dataList){
        if(CollectionUtils.isEmpty(dataList) || dataList.size()<12){
            return null;
        }

        int up = 0;
        int total = 12;
        int count = total;
        LinkedList<BigDecimal> lt = new LinkedList<>();
        for (int i = dataList.size()-1; i>=0; i--) {
            if(count<1){
                break;
            }
            count -=1;

            final HistoryPrice data = dataList.get(i);

            BigDecimal clo = data.getClose();
            lt.addFirst(clo);
            //获取前一天的数据
            BigDecimal clo1 = null;
            if(i>1){
                final HistoryPrice data1 = dataList.get(i-1);
                clo1 = data1.getClose();
            }
            if(clo1!=null){
                final BigDecimal subtract = clo.subtract(clo1);
                //涨
                if(subtract.doubleValue()>0){
                    up+=1;
                }
            }
        }
        final BigDecimal psy = new BigDecimal((double)up / total).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
        return psy;
    }
}
