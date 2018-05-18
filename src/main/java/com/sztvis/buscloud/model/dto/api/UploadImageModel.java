package com.sztvis.buscloud.model.dto.api;

import java.util.List;

public class UploadImageModel {
    private String identity;
    private List<String> images;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
