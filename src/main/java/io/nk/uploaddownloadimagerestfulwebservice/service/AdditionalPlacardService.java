package io.nk.uploaddownloadimagerestfulwebservice.service;

import io.nk.uploaddownloadimagerestfulwebservice.model.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalPlacardService {

    @Autowired
    PlacardService placardService;

    private List<DBFile> getAllPlacardImagesFromDB(List<String> placardNames) {
        List<DBFile> imagesFiles = new ArrayList<>();
        DBFile image = new DBFile();
        for (String placardName : placardNames) {
            image = placardService.getPlacardImageForDbFile(placardName);
            if (image != null) {
                imagesFiles.add(image);
            }
        }
        return imagesFiles;
    }


    public List<ResponseEntity<Resource>> downLoadPlacardImageFiles(List<String> placardNames) {

        List<ResponseEntity<Resource>> responseEntities = new ArrayList<>();

        List<Resource> resources = new ArrayList<>();
        List<DBFile> images = getAllPlacardImagesFromDB(placardNames);

        for (DBFile image : images) {
            ResponseEntity<Resource> resource = ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; placardName=\"" + image.getPlacardName() + "\"")
                    .body(new ByteArrayResource(image.getPlacardImage()));
            responseEntities.add(resource);
        }
        return responseEntities;
    }

}
