package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.LineProvider;
import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.LineViewModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:23
 */
@Repository
public interface LineMapper {

    @Select("select * from TramLineInfo where departmentId=#{departmentId} order by sort asc")
    List<TramLineInfo> GetLinesByDepartmentId(long departmentId);

    @Select("select count(Id) from TramLineInfo where departmentId in #{departmentId}")
    Integer GetLineIdsByDepartmentIds(List<Long> departmentId);

    @SelectProvider(type = LineProvider.class,method = "getListSQL")
    List<LineViewModel> getList(@Param("departments") List<Long> departments, @Param("linename") String lineName, @Param("departmentId") long departmentId);

    @Select("select * from TramLineInfo where id=#{id}")
    TramLineInfo getLineInfo(long id);

    @SelectProvider(type = LineProvider.class,method = "getLineTreeSQL")
    List<ComboTreeModel> getLineTreeList(@Param("departments") List<Long> departments);

    @Insert("insert into TramLineInfo(guid,linecode,linename,departmentid,lineupmileage,linedownmileage,upsitenum,downsitenum)values(#{guid},#{linecode},#{lienname},#{departmentid},#{lineupmileage},#{linedownmileage},#{upsitenum},#{downsitenum})")
    void saveLine(TramLineInfo lineInfo);

    @Update("update TramLineInfo set linecode=#{linecode},linename=#{linename},departmentid=#{departmentid},lineupmileage=#{lineupmileage},linedownmileage=#{linedownmileage},upsitenum=#{upsitenum},downsitenum=#{downsitenum} where id=#{id}")
    void updateLine(TramLineInfo lineInfo);

    @Delete("delete from TramLineInfo where id in (#{lineIds})")
    void removeLine(String lineIds);

}
