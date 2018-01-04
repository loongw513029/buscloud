package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.CookieHelper;
import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.core.helper.SecureHelper;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.DepartmentMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.model.domain.Tramdepartmentinfo;
import com.sztvis.buscloud.model.domain.Trammemberinfo;
import com.sztvis.buscloud.model.dto.AppRoleModel;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;
import com.sztvis.buscloud.service.IMemberService;
import com.sztvis.buscloud.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private DeviceMapper deviceMapper;
    @Autowired
    private BasicService basicService;
    @Autowired
    private RedisService redisService;

    /**
     * 登录
     * @param loginParams
     * @return
     */
    @Override
    public CurrentUserInfo Login(LoginParams loginParams){
        if(StringHelper.isEmpty(loginParams.getUsername())||StringHelper.isEmpty(loginParams.getPassword())){
            throw new TramException("参数不完整！");
        }
        Trammemberinfo memberInfo = memberMapper.getMemberByUsername(loginParams.getUsername());
        if(memberInfo == null){
            throw new TramException("不存在当前用户名！");
        }
        String Password = SecureHelper.encryptToMD5(loginParams.getPassword()+memberInfo.getPasswordsalt());
        if(!Password.equals(memberInfo.getPassword())){
            throw new TramException("密码错误！");
        }
        CurrentUserInfo currentUserInfo = memberMapper.GetCurrentUserInfo(memberInfo.getId());
        Tramdepartmentinfo departmentinfo = departmentMapper.GetDepartmentInfo(currentUserInfo.getDepartmentId());
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
        String uuid=UUID.randomUUID().toString().replace("-","");
        //CookieHelper.addCookie();
        redisService.set(loginParams.getUsername(),currentUserInfo);
        return currentUserInfo;
    }
}
