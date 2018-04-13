package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.core.helper.SecureHelper;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.DepartmentMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.model.domain.TramDepartmentInfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.AppRoleModel;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;
import com.sztvis.buscloud.model.dto.MemberViewModel;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IMemberService;
import org.apache.ibatis.executor.result.ResultMapException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午4:55
 */
@Service
public class MemberService implements IMemberService{

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private BasicService basicService;

    /**
     * 登录
     * @param loginParams
     * @return
     */
    @Override
    public CurrentUserInfo Login(LoginParams loginParams){
        System.out.println(loginParams.getPassword());
        if(StringHelper.isEmpty(loginParams.getUsername())||StringHelper.isEmpty(loginParams.getPassword())){
            throw new TramException("参数不完整！");
        }
        TramMemberInfo memberInfo = memberMapper.getMemberByUsername(loginParams.getUsername());
        if(memberInfo == null){
            throw new TramException("不存在当前用户名！");
        }
        String Password = SecureHelper.encryptToMD5(loginParams.getPassword()+memberInfo.getPasswordsalt());
        if(!Password.equals(memberInfo.getPassword())){
            throw new TramException("密码错误！");
        }
        CurrentUserInfo currentUserInfo = memberMapper.GetCurrentUserInfo(memberInfo.getId());
        TramDepartmentInfo departmentinfo = departmentMapper.GetDepartmentInfo(currentUserInfo.getDepartmentId());
        currentUserInfo.setDepartmentName(departmentinfo.getDepartmentname());
        currentUserInfo.setDeviceScopes(basicService.GetDeviceScopeByUserId(currentUserInfo.getId()));
        if(departmentinfo.getParentid()!=0){
            departmentinfo=departmentMapper.GetDepartmentInfo(departmentinfo.getParentid());
        }
        AppRoleModel roleModel =new AppRoleModel();
        roleModel.setAppName(departmentinfo.getAppname());
        roleModel.setHaveCan(departmentinfo.getIslookcan()==1);
        roleModel.setHaveGps(true);
        roleModel.setHaveVedio(departmentinfo.getIshavevedio()==1);
        currentUserInfo.setAppConf(roleModel);
        //Tramloginlogfo logInfo = new Tramloginlogfo();
        //logInfo.setAccesstoken();
                return currentUserInfo;
    }
    @Override
    public CurrentUserInfo Logins(LoginParams loginParams){
        TramMemberInfo memberInfo = memberMapper.getMemberByUsername(loginParams.getUsername());
        System.out.println(loginParams.getPassword());
        Object Md5 = new SimpleHash("MD5",loginParams.getPassword(),null,1024);
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()==false){
            System.out.println(Md5.toString());
            UsernamePasswordToken token = new UsernamePasswordToken(loginParams.getUsername(), Md5.toString()); // 获取当前的Subject
            //token.setRememberMe(true);
            //System.out.println(token.getPassword());
            try{
                currentUser.login(token);
                Session session = currentUser.getSession();
                session.setAttribute("username",loginParams.getUsername());
                System.out.println("验证完毕!!!!!!!!!!!!");
            }
            catch (IncorrectCredentialsException e){
                throw new ResultMapException("密码错误");
            }
            catch (AuthenticationException e){
                System.out.println(e.getMessage().toString());
                throw new ResultMapException("其他错误");
            }

        }
        CurrentUserInfo currentUserInfo = memberMapper.GetCurrentUserInfo(memberInfo.getId());
        TramDepartmentInfo departmentinfo = departmentMapper.GetDepartmentInfo(currentUserInfo.getDepartmentId());
        currentUserInfo.setDepartmentName(departmentinfo.getDepartmentname());
        currentUserInfo.setDeviceScopes(basicService.GetDeviceScopeByUserId(currentUserInfo.getId()));
        if(departmentinfo.getParentid()!=0){
            departmentinfo=departmentMapper.GetDepartmentInfo(departmentinfo.getParentid());
        }
        AppRoleModel roleModel =new AppRoleModel();
        roleModel.setAppName(departmentinfo.getAppname());
        roleModel.setHaveCan(departmentinfo.getIslookcan()==1);
        roleModel.setHaveGps(true);
        roleModel.setHaveVedio(departmentinfo.getIshavevedio()==1);
        currentUserInfo.setAppConf(roleModel);
        //Tramloginlogfo logInfo = new Tramloginlogfo();
        //logInfo.setAccesstoken();
        return currentUserInfo;
    }

    @Override
    public String getpwd(String username){
        TramMemberInfo info = memberMapper.getMemberByUsername(username);
        return info.getPassword();
    }

    @Override
    public TramMemberInfo getMemberInfo(long userId) {
        return this.memberMapper.getMemberById(userId);
    }

    @Override
    public List<MemberViewModel> getMemberList(long userId, String seldepartmentIds, String keywords) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return  this.memberMapper.getUserList(seldepartmentIds,departments,keywords);
    }

    @Override
    public void saveAndUpdateMember(TramMemberInfo memberInfo) {
        if (memberInfo.getId() == 0) {
            int count = this.memberMapper.getCountByUsername(memberInfo.getUsername());
            if(count>1){
                throw new TramException("已经存在该用户名!");
            }
            String passwordSalt = UUID.randomUUID().toString().replace("-", "");
            memberInfo.setPasswordsalt(passwordSalt);
            String newPwd = SecureHelper.encryptToMD5("123456" + passwordSalt);
            memberInfo.setPassword(newPwd);
            this.memberMapper.Insert(memberInfo);
        } else {
            this.memberMapper.Update(memberInfo);
        }
    }

    @Override
    public void removeUser(String userIds) {
        this.memberMapper.remove(userIds);
    }

    @Override
    public void ChangePassWord(long userId, String oldPwd, String newPwd)
    {
        TramMemberInfo memberInfo = this.memberMapper.getMemberById(userId);
        String oldPwdStr=SecureHelper.encryptToMD5(oldPwd.concat(memberInfo.getPasswordsalt()));
        if (oldPwdStr!=memberInfo.getPassword())
            throw new TramException("旧密码不正确");
        this.ChangePassWord(userId,newPwd);
    }

    public void ChangePassWord(long userId, String newPwd)
    {
        TramMemberInfo memberInfo = this.memberMapper.getMemberById(userId);
        String PassWord= SecureHelper.encryptToMD5(newPwd.concat(memberInfo.getPasswordsalt()));
        this.memberMapper.ChangePassWord(userId,PassWord);
    }

    public void ModifyUserPhoto(long userId, String filePath)
    {
        this.memberMapper.ModifyUserPhoto(userId,filePath);
    }

    @Override
    public List<String> getMemberUUIDbyDepartmentId(List<Long> departmentIds) {
        return this.memberMapper.getMemberUUIDbyDepartmentId(departmentIds);
    }

    @Override
    public List<String> getMemberUUIDByDeviceCode(String deviceCode) {
        List<Long> departments = this.iDepartmentService.getDepartmentInfoBydeviceCode(deviceCode,true);
        return this.getMemberUUIDbyDepartmentId(departments);
    }

}
