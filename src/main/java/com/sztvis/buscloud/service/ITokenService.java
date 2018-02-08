package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.V3TokenInfo;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/2/7 下午5:08
 */
public interface ITokenService {

    void insertTokenInfo(V3TokenInfo tokenInfo);

    V3TokenInfo getTokenInfo(String departmentcode);

    void updateToken(String departmentcode,String accesstoken,String refreshtoken);
}
