package com.sztvis.buscloud.service.Impl;

        import com.sztvis.buscloud.mapper.DriverMapper;
        import com.sztvis.buscloud.model.domain.TramDriverInfo;
        import com.sztvis.buscloud.model.dto.DriverViewModel;
        import com.sztvis.buscloud.service.IDriverService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
@Service
public class DriverService implements IDriverService {
    @Autowired
    private DriverMapper driverMapper;
    @Override
    public List<DriverViewModel> getDriverList(String driverName, long departmentId, int offset, int limit) {
        List<DriverViewModel> list = this.driverMapper.getDriverList(driverName,departmentId,offset,limit);
        return list;
    }

    @Override
    public int getDriverListCount(String driverName, long departmentId) {
        return this.driverMapper.getDriverListCount(driverName,departmentId);
    }

    @Override
    public TramDriverInfo getDriverInfo(long id) {
        return this.driverMapper.getDriverInfo(id);
    }

    @Override
    public void SaveAndUpdateDriver(TramDriverInfo driverInfo) {
        if(driverInfo.getId() == 0)
            this.driverMapper.InsertDriverInfo(driverInfo);
        else
            this.driverMapper.UpdateDriverInfo(driverInfo);
    }

    @Override
    public void RemoveDrivers(List<String> ids) {
        this.driverMapper.RemoveDrivers(ids);
    }
}
