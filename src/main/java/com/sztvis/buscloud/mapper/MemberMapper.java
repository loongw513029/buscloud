package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.Tramloginlogfo;
import com.sztvis.buscloud.model.domain.Trammemberinfo;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/27 下午6:17
 */
public interface MemberMapper {

    /**
     * get memebrinfo poco by username
     * @param username
     * @return
     */
    @Select("select * from tramMemberInfo where username=#{username}")
    Trammemberinfo getMemberByUsername(String username);

    /**
     * get memberinfo poco by id
     * @param Id
     * @return
     */
    @Select("select * from tramMemberInfo where Id=#{Id}")
    Trammemberinfo getMemberById(Long Id);

    /**
     * insert one rows memberinfo data
     * @param memberInfo
     */
    @Insert("insert into tramMemberInfo(Guid,UserName,PassWord,PassWordSalt,OwnershipId,RoleId,RoleLv,ManageScope,Status,RealName,Code,Phone,Photo,CreateTime)values(#{Guid},#{UserName},#{PassWord},#{PassWordSalt},#{OwnershipId},#{RoleId},#{RoleLv},#{ManageScope},#{Status},#{RealName},#{Code},#{Phone},#{Photo},#{CreateTime})")
    void Insert(Trammemberinfo memberInfo);

    /**
     * edit memberinfo
     * @param trammemberinfo
     */
    @Update("update trammemberinfo set UserName=#{UserName}, OwnershipId=#{OwnershipId}, RoleId=#{RoleId}, RoleLv=#{RoleLv}, ManageScope=#{ManageScope}, Status=#{Status}, RealName=#{RealName}, Code=#{Code}, Phone=#{Phone},Photo=#{Photo} where Id=#{Id}")
    void Update(Trammemberinfo trammemberinfo);

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
    @Select("select a.Id,a.UserName,a.RoleId,b.RoleName,a.Phone,a.Photo,a.RealName,a.OwnershipId as DepartmentId,c.DepartmentType,a.RoleLv,a.ManageScope from TramMemberInfo as a left join TramRoleInfo as b on a.RoleId=b.Id left join TramDepartmentInfo as c on b.DepartmentId = c.Id where a.Id=#{Id}")
    CurrentUserInfo GetCurrentUserInfo(Long Id);

    /**
     * insert one login records
     * @param loginLogInfo
     */
    @Insert("insert into TramLoginLogInfo(UserId,LoginTime,LoginType,ClientId,ClientIp,AccessToken,RefreshToken)values(#{UserId},#{LoginTime},#{LoginType},#{ClientId},#{ClientIp},#{AccessToken},#{RefreshToken})")
    void InsertLoginLog(Tramloginlogfo loginLogInfo);

}
