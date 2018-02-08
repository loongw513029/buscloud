package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.TokenMapper;
import com.sztvis.buscloud.model.domain.V3TokenInfo;
import com.sztvis.buscloud.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/2/7 下午5:10
 */
@Service
public class TokenService implements ITokenService {
    @Autowired
    private TokenMapper tokenMapper;
    @Override
    public void insertTokenInfo(V3TokenInfo tokenInfo) {
        this.tokenMapper.insertTokenInfo(tokenInfo);
    }

    @Override
    public V3TokenInfo getTokenInfo(String departmentcode) {
        return this.tokenMapper.getTokenInfo(departmentcode);
    }

    @Override
    public void updateToken(String departmentcode, String accesstoken, String refreshtoken) {
        this.tokenMapper.UpdateAccessToken(accesstoken,departmentcode,refreshtoken);
    }
}
