package io.nk.uploaddownloadimagerestfulwebservice.model;

public class DBFile {

    private String placardName;
    private String fileType;
    private byte[] placardImage;

    public DBFile() {

    }

    public DBFile(String placardName, String fileType, byte[] placardImage) {
        this.placardName = placardName;
        this.fileType = fileType;
        this.placardImage = placardImage;
    }

    public String getPlacardName() {
        return placardName;
    }

    public void setPlacardName(String placardName) {
        this.placardName = placardName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getPlacardImage() {
        return placardImage;
    }

    public void setPlacardImage(byte[] placardImage) {
        this.placardImage = placardImage;
    }
}
