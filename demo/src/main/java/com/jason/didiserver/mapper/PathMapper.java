package com.jason.didiserver.mapper;

import com.jason.didiserver.pojo.Path;
import com.jason.didiserver.pojo.PathInfo;
import com.jason.didiserver.pojo.Search;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PathMapper {

    @Select("select * from path where user_id=#{id} order by id")
    List<Path> findPathById(Integer id);

    //更新path
    @Update("update path set location =#{location},carriage= #{carriage},user_id=#{userId} where id=#{id}")
    void updatePath(String location, float carriage, Integer userId, Integer id);

    @Insert("insert into path(location,carriage,user_id)"+
            "values (#{location},#{carriage},#{userId})")
    void insertPath(String location, float carriage, Integer userId);

    List<PathInfo> findPathBySearch(Search search);
}
