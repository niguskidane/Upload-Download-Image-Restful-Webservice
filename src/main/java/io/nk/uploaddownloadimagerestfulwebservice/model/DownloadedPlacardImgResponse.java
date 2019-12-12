package io.nk.uploaddownloadimagerestfulwebservice.model;

import java.util.List;
public class DownloadedPlacardImgResponse {

   private List<PlacardImage> images;

    public DownloadedPlacardImgResponse() {
    }

    public DownloadedPlacardImgResponse(List<PlacardImage> images) {
        this.images = images;
    }

    public List<PlacardImage> getImages() {
        return images;
    }

    public void setImages(List<PlacardImage> images) {
        this.images = images;
    }
}
