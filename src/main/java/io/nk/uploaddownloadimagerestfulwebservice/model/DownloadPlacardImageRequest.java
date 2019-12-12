package io.nk.uploaddownloadimagerestfulwebservice.model;

import java.util.Objects;

public class DownloadPlacardImageRequest {

    private String placardName;

    public DownloadPlacardImageRequest() {
    }

    public DownloadPlacardImageRequest(String placardName) {
        this.placardName = placardName;
    }

    public String getPlacardName() {
        return placardName;
    }

    public void setPlacardName(String placardName) {
        this.placardName = placardName;
    }

    @Override
    public String toString() {
        return placardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloadPlacardImageRequest that = (DownloadPlacardImageRequest) o;
        return Objects.equals(placardName, that.placardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placardName);
    }
}
