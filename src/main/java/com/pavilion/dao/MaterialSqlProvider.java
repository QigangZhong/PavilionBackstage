package com.pavilion.dao;

import com.pavilion.model.Material;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

public class MaterialSqlProvider {
    private final String tb_material="material";

    public String getSearchCount(Map<String,Object> map){
        String cpscode=(String)map.get("param1");
        String cinvname=(String)map.get("param2");
        String cinvstd=(String)map.get("param3");
        String type=(String)map.get("param4");

        SQL sql=new SQL().SELECT("count(1)").FROM(tb_material);
        if(!StringUtils.isEmptyOrWhitespace(cpscode)){
            sql.WHERE(" cpscode like '%' || '"+cpscode+"' || '%' ");
        }
        if(!StringUtils.isEmptyOrWhitespace(cinvname)){
            sql.WHERE(" cinvname like '%' || '"+cinvname+"' || '%' ");
        }
        if(!StringUtils.isEmptyOrWhitespace(cinvstd)){
            sql.WHERE(" cinvstd like '%' || '"+cinvstd+"' || '%' ");
        }
        if(!StringUtils.isEmptyOrWhitespace(type)){
            sql.WHERE(" type like '%' || '"+type+"' || '%' ");
        }

        return sql.toString();
    }

    public String getSearchPagedMaterials(Map<String,Object> map){
        int offset=(int)map.get("param1");
        int limit=(int)map.get("param2");
        String cpscode=(String)map.get("param3");
        String cinvname=(String)map.get("param4");
        String cinvstd=(String)map.get("param5");
        String type=(String)map.get("param6");

        SQL sql=new SQL().SELECT("*").FROM(tb_material);
        if(!StringUtils.isEmptyOrWhitespace(cpscode)){
            sql.WHERE(" cpscode like '%' || '"+cpscode+"' || '%' ");
        }
        if(!StringUtils.isEmptyOrWhitespace(cinvname)){
            sql.WHERE(" cinvname like '%' || '"+cinvname+"' || '%' ");
        }
        if(!StringUtils.isEmptyOrWhitespace(cinvstd)){
            sql.WHERE(" cinvstd like '%' || '"+cinvstd+"' || '%' ");
        }
        if(!StringUtils.isEmptyOrWhitespace(type)){
            sql.WHERE(" type like '%' || '"+type+"' || '%' ");
        }

        sql.ORDER_BY("create_time desc,last_update_time desc");

        return sql.toString()+" limit "+offset+","+limit;
    }
}
