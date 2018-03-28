package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.UnSafeProvider;
import com.sztvis.buscloud.model.UnSafeListViewModel;
import com.sztvis.buscloud.model.UnSafeQuery;
import com.sztvis.buscloud.util.DayTypes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface UnSafeMapper {
    @SelectProvider(type = UnSafeProvider.class,method = "GetUnsafeListSQL")
    List<UnSafeListViewModel> GetUnsafeList(@Param("query")UnSafeQuery query, @Param("deviceIds") List<Long> deviceIds, @Param("types") DayTypes types);
}
