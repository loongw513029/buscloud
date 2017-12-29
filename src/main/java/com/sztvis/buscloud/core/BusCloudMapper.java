package com.sztvis.buscloud.core;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BusCloudMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
