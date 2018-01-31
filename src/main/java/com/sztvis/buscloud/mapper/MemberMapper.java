package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.MemberProvider;
import com.sztvis.buscloud.model.domain.Tramloginlogfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.MemberViewModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/27 下午6:17
 */
@Repository
public interface MemberMapper {

    /**
     * get memebrinfo poco by username
     * @param username
     * @return
     */
    @Select("select * from tramMemberInfo where username=#{username}")
    TramMemberInfo getMemberByUsername(String username);

    /**
     * get memberinfo poco by id
     * @param Id
     * @return
     */
    @Select("select * from tramMemberInfo where Id=#{Id}")
    TramMemberInfo getMemberById(Long Id);

    /**
     * insert one rows memberinfo data
     * @param memberInfo
     */
    @Insert("insert into tramMemberInfo(Guid,UserName,PassWord,PassWordSalt,OwnershipId,RoleId,RoleLv,ManageScope,Status,RealName,Code,Phone,Photo,CreateTime)values(#{guid},#{username},#{password},#{passwordsalt},#{ownershipid},#{roleid},#{rolelv},#{managescope},#{status},#{realname},#{code},#{phone},#{photo},#{createtime})")
    void Insert(TramMemberInfo memberInfo);

    /**
     * edit memberinfo
     * @param trammemberinfo
     */
    @Update("update trammemberinfo set UserName=#{username}, OwnershipId=#{ownershipid}, RoleId=#{roleid}, RoleLv=#{rolelv}, ManageScope=#{managescope}, Status=#{status}, RealName=#{realname}, Code=#{code}, Phone=#{phone},Photo=#{photo} where Id=#{id}")
    void Update(TramMemberInfo trammemberinfo);

    /**
     * modify memberpwd by Id
     * @param newpwd
     * @param Id
     */
    @Update("update trammemberinfo set Password=#{newpwd} where Id=#{Id}")
    void ModifyPwd(String newpwd,Long Id);

    /**
     * get CurrentUserInfo by ID
     * @param Id
     * @return
     */
    @Select("select a.Id,a.guid as uuid,a.UserName,a.RoleId,b.RoleName,a.Phone,a.Photo,a.RealName,a.OwnershipId as DepartmentId,c.DepartmentType,a.RoleLv,a.ManageScope from TramMemberInfo as a left join TramRoleInfo as b on a.RoleId=b.Id left join TramDepartmentInfo as c on b.DepartmentId = c.Id where a.Id=#{Id}")
    CurrentUserInfo GetCurrentUserInfo(Long Id);

    /**
     * insert one login records
     * @param loginLogInfo
     */
    @Insert("insert into TramLoginLogInfo(UserId,LoginTime,LoginType,ClientId,ClientIp,AccessToken,RefreshToken)values(#{UserId},#{LoginTime},#{LoginType},#{ClientId},#{ClientIp},#{AccessToken},#{RefreshToken})")
    void InsertLoginLog(Tramloginlogfo loginLogInfo);

    /**
     *
     * @param departmentIds
     * @param departments
     * @param username
     * @return
     */
    @SelectProvider(type = MemberProvider.class,method = "getUserListSQL")
    List<MemberViewModel> getUserList(@Param("departmentIds") String departmentIds, @Param("departments") List<Long> departments, @Param("username") String username);

    @Delete("delete from TramMemberInfo where id in (#{userIds})")
    void remove(String userIds);

    @Select("select count(Id) from TramMemberInfo where username=#{username}")
    int getCountByUsername(String username);
}
