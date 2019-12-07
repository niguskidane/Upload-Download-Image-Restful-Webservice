package io.nk.uploaddownloadimagerestfulwebservice.model;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

public class PlacardImage {

    private String placardName;

    private byte[] placardImage;

    public PlacardImage() {
    }

    public PlacardImage(String placardName, byte[] placardImage) {
        this.placardName = placardName;
        this.placardImage = placardImage;
    }

    public String getPlacardName() {
        return placardName;
    }

    public void setPlacardName(String placardName) {
        this.placardName = placardName;
    }

    public byte[] getPlacardImage() {
        return placardImage;
    }

    public void setPlacardImage(byte[] placardImage) {
        this.placardImage = placardImage;
    }
}
