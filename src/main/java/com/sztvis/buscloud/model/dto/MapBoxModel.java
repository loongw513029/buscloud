package com.sztvis.buscloud.model.dto;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/25 下午3:07
 */
public class MapBoxModel {
    private List<GpsViewModel> Maps;

    public List<GpsViewModel> getMaps() {
        return Maps;
    }

    public void setMaps(List<GpsViewModel> maps) {
        Maps = maps;
    }
}
