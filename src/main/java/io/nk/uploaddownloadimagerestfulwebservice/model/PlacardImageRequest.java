package io.nk.uploaddownloadimagerestfulwebservice.model;

import org.springframework.web.multipart.MultipartFile;


public class PlacardImageRequest {

    private String placardName;
    private MultipartFile placardImage;

    public PlacardImageRequest() {
    }

    public PlacardImageRequest(String placardName, MultipartFile placardImage) {
        this.placardName = placardName;
        this.placardImage = placardImage;
    }

    public String getPlacardName() {
        return placardName;
    }

    public void setPlacardName(String placardName) {
        this.placardName = placardName;
    }

    public MultipartFile getPlacardImage() {
        return placardImage;
    }

    public void setPlacardImage(MultipartFile placardImage) {
        this.placardImage = placardImage;
    }
}
