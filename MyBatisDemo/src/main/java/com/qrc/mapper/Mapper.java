package com.qrc.mapper;

import com.qrc.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface Mapper {
    List<Brand> selectAll();
    Brand selectByID(int id);
//    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);
//    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map map);
    List<Brand> selectByConditionSingle(Brand brand);

    void add(Brand brand);
    int updateAll(Brand brand);
    int updateSome(Brand brand);
    void deleteByID(int id);
    void deleteByIDs(@Param("ids")int[] id);
    @Select("select id,brand_name as brandName, company_name as companyName,ordered,description,status from tb_brand where id = #{id}")
    Brand selectByID2(int id);
}
