package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Trammenuinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 下午1:36
 */
@Repository
public interface MenuMapper {

    @Select("select * from tramMenuInfo where parentId=#{parentId} order by OrderBy asc")
    List<Trammenuinfo> GetMenus(@Param("parentId") long parentId);
}
