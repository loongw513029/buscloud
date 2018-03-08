package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;
import com.sztvis.buscloud.model.dto.MemberViewModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午2:17
 */
public interface IMemberService {
    CurrentUserInfo Login(LoginParams loginParams);

    TramMemberInfo getMemberInfo(long userId);

    List<MemberViewModel> getMemberList(long userId, String seldepartmentIds, String keywords);

    void saveAndUpdateMember(TramMemberInfo memberInfo);

    void removeUser(String userIds);
}