package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.SiteSettingMapper;
import com.sztvis.buscloud.model.dto.SelectViewModel;
import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import com.sztvis.buscloud.model.dto.service.ChartViewModel;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.service.ILineService;
import com.sztvis.buscloud.service.ISiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static com.sztvis.buscloud.model.dto.service.ChartViewModel.*;
import static java.util.Locale.filter;

@Service
public class SiteSettingService implements ISiteSettingService {

    @Autowired
    private IBasicService iBasicService;

    @Autowired
    private ILineService iLineService;

    @Autowired
    private TimehelpService timehelpService;

    @Autowired
    private SiteSettingMapper SiteSettingMapper;
    private SiteSettingsInfo info;

    @Override
    public ChartViewModel GetAppCharts(long userId, long lineId) {
            Date date = new Date();
            String start = timehelpService.getSomeDay(date, 0, "yyyy-MM-dd");
            String end = timehelpService.getSomeDay(date, 0, "yyyy-MM-dd 23:59:59");
            Calendar calendar = Calendar.getInstance();
            ChartViewModel model = new ChartViewModel();
            List<Long> Device = this.iBasicService.getDeviceIdsByRoleLv(userId);
            List<SelectViewModel> list = this.iLineService.GetDropDownLine(userId);
            List<Long> LineArr=new  ArrayList<>();
            for (SelectViewModel r : list) {
                Long Id = Long.valueOf((r.getId()));
                LineArr.add(Id);
            }
            model.setTotalAlarmCount(this.SiteSettingMapper.GetAppCharts(5, Device, lineId, start, end));
            model.setTotalDeviceCount(this.SiteSettingMapper.GetAppCharts(1, LineArr, lineId, null, null));
            model.setTotalLineCount(this.SiteSettingMapper.GetAppCharts(3, LineArr, null, null, null));
            model.setTotalUnsafeCount(this.SiteSettingMapper.GetAppCharts(4, Device, lineId, start, end));
            model.setOnLineDeviceCount(this.SiteSettingMapper.GetAppCharts(2, LineArr, lineId, null, null));
            ChartListViewModel viewModel = new ChartListViewModel();
            List<ChartListViewModel> listview = new ArrayList<>();
            List<ChartData> listdate = new ArrayList<>();
            viewModel.setChartName("不安全行为趋势图");
            for (int i = 6; i >= 0; i--) {
                ChartData data = new ChartData();
                String date1 = timehelpService.getSomeDay(date, -i, "yyyy-MM-dd");
                String date2 = timehelpService.getSomeDay(date, -i + 1, "yyyy-MM-dd");
                String name = timehelpService.getSomeDay(date, -i, "MM-dd");
                int count = this.SiteSettingMapper.GetAppCharts(4, LineArr, lineId, date1, date2);
                data.setAxisName(name);
                data.setAxisValue(count);
                listdate.add(data);
            }
            viewModel.setData(listdate);
            listview.add(viewModel);
            ChartListViewModel viewMode2 = new ChartListViewModel();
            List<ChartData> listdate2 = new ArrayList<>();
            viewMode2.setChartName("车辆报警趋势图");
            for (int i = 6; i >= 0; i--) {
                ChartData data = new ChartData();
                String date1 = timehelpService.getSomeDay(date, -i, "yyyy-MM-dd");
                String date2 = timehelpService.getSomeDay(date, -i + 1, "yyyy-MM-dd");
                String name = timehelpService.getSomeDay(date, -i, "MM-dd");
                int count = this.SiteSettingMapper.GetAppCharts(5, LineArr, lineId, date1, date2);
                data.setAxisName(name);
                data.setAxisValue(count);
                listdate2.add(data);
            }
            viewMode2.setData(listdate2);
            listview.add(viewMode2);
            model.setChartList(listview);
            return model;
    }

    @Override
    public SiteSettingsInfo GetSiteSettings(List<String> key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SiteSettingsInfo model=new SiteSettingsInfo();
        List<SiteSettingsInfo> info = this.SiteSettingMapper.GetSiteSettings(StringHelper.listToString(key,','));
        Field[] field = model.getClass().getFields();
        for (int i = 0; i < field.length; i++) {
            Field f = field[i];
            if (f.getName() != "Id") {
                String k=f.getName();
                List<SiteSettingsInfo> info1 = info.stream().filter(item -> item.getKey().equals(f.getName())).collect(Collectors.toList());
                if (info1.size()>0) {
                    String name = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                    String ftype = f.getGenericType().toString();
                    if (ftype.equals("Int")) {
                        Method m = model.getClass().getMethod("set" + name, Integer.class);
                        m.invoke(model,Integer.valueOf(info1.get(i).getValue()));
                    }
                    if (ftype.equals("class java.lang.String")) {
                        Method m = model.getClass().getMethod("set" + name, String.class);
                        m.invoke(model,info1.get(0).getValue());
                    }
                    if (ftype.equals("Int")) {
                        Method m = model.getClass().getMethod("set" + name, Integer.class);
                        m.invoke(model,Integer.valueOf(info1.get(i).getValue()));
                    }
                    if (ftype.equals("double")) {
                        Method m = model.getClass().getMethod("set" + name, double.class);
                        m.invoke(model,info1.get(0).getValue());
                    }
                }
            }
        }
        return model;
    }

    @Override
    public void SaveSetting(String key, Object value){
        if (value == null){
            throw new NullPointerException("值不能为null");
        }
//        Field[] item = model.getClass().getFields();
//        if(Arrays.stream(item).allMatch(x->x.getName()==key)){
//            throw new NoSuchFieldError("未找到"+ key +"对应的配置项");
//        }
        Map<String,Object> set = this.SiteSettingMapper.GetGetSiteSettingsKey(key);
        if (set == null)
            this.SiteSettingMapper.InsertSaveSetting(key,value);
        else
            this.SiteSettingMapper.updateSaveSetting(key,value);
    }

    @Override
    public SiteSettingsInfo GetSave() {
        List<String> name = this.SiteSettingMapper.GetSavekey();
        List<Object> values = this.SiteSettingMapper.GetSavevalue();
        SiteSettingsInfo model = new SiteSettingsInfo();
        Field[] fields = model.getClass().getDeclaredFields();
        List<String> modelName = new ArrayList<>();
        List<String> modelType = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            // 获取属性的名字
            modelName.add(fields[i].getName());
            // 获取属性类型
            modelType.add(fields[i].getGenericType().toString());
        }
        for (String key : name){
            String type = modelType.get(modelName.indexOf(key));
            if (type.equals("class java.lang.String")) {
                try {
                    Method m = model.getClass().getMethod("set"+key,String.class);
                    String value = values.get(name.indexOf(key)).toString();
                    m.invoke(model,value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("int")){
                try {
                    Method m = model.getClass().getMethod("set"+key,int.class);
                    int value = Integer.parseInt(values.get(name.indexOf(key)).toString());
                    m.invoke(model,value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("double")){
                try {
                    Method m = model.getClass().getMethod("set"+key,double.class);
                    double value = Double.valueOf(values.get(name.indexOf(key)).toString());
                    m.invoke(model,value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return model;
    }
}
