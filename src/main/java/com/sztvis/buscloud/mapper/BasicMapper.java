package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.BasicProvider;
import com.sztvis.buscloud.model.domain.TramBasicInfo;
import com.sztvis.buscloud.model.domain.TramRoleInfo;
import com.sztvis.buscloud.model.domain.TramMenuInfo;
import com.sztvis.buscloud.model.dto.BasicViewModel;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.response.RoleViewModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:32
 */

@Repository
public interface BasicMapper {

    @Select("select * from TramBasicInfo where id=#{Id}")
    TramBasicInfo getBasicInfoById(long Id);

    @Select("select customId from TramBasicInfo where type=#{type} and parentId>0 and customId<>''")
    List<Integer> getCustomIdsByType(int type);

    @Select("select * from TramBasicInfo where customId=#{customId} and parentId>0 and customId<>''")
    TramBasicInfo getBasicInfoByCustomId(int customId);

    @Select("select * from TramRoleInfo where parentId=#{parentId}")
    List<TramRoleInfo> getRoleList(long parentId);

    @Select("select * from TramMenuInfo where parentId=#{parentId}")
    List<TramMenuInfo> getMenuList(long parentId);

    @Select("select a.id,a.rolename,a.remark,a.parentId,b.MenuIds as roleIds from TramRoleInfo a left join tramrolemenurelinfo b on a.id=b.RoleId where a.Id=#{id}")
    RoleViewModel getRoleInfo(long id);

    @Insert("insert into TramRoleInfo(rolename,remark,parentid,departmentId,lv)values(#{rolename},#{remark},#{parentid},1,1)")
    void insertRoleInfo(TramRoleInfo roleInfo);

    @Update("update TramRoleInfo set rolename=#{rolename},remark=#{remark},parentid=#{parentid} where id=#{id}")
    void updateRoleInfo(TramRoleInfo roleInfo);


    @Insert("insert into tramrolemenurelinfo(roleId,menuids)values(#{roleId},#{roleIds})")
    void insertRoleRelInfo(@Param("roleId") long roleId, @Param("roleIds") String roleIds);

    @Delete("delete from tramrolemenurelinfo where roleId=#{roleId}")
    void deleteRoelRelInfo(long roleId);

    @Select("select id,alarmname as text from TramBasicInfo where parentid=#{parentId} and type=0 and isEnable=1")
    List<ComboTreeModel> getAlarmTypeListByParentId(long parentId);

    @SelectProvider(type = BasicProvider.class,method = "getBasicList")
    List<BasicViewModel> getBasicList(@Param("type") int type,@Param("keywords") String keywords);
}
