package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.UnSafeListViewModel;
import com.sztvis.buscloud.model.UnSafeQuery;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IUnSafeService {
    List<UnSafeListViewModel> GetUnsafeList(UnSafeQuery query);

    void CalcUnsafeIndex()throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException;
}
