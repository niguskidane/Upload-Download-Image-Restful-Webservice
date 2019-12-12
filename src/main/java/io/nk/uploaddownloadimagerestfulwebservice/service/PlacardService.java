package io.nk.uploaddownloadimagerestfulwebservice.service;

import io.nk.uploaddownloadimagerestfulwebservice.model.DBFile;
import io.nk.uploaddownloadimagerestfulwebservice.model.PlacardImage;
import io.nk.uploaddownloadimagerestfulwebservice.model.PlacardImageRequest;
import io.nk.uploaddownloadimagerestfulwebservice.repository.DBFilePlacardImageRepository;
import io.nk.uploaddownloadimagerestfulwebservice.repository.PlacardImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;

@Service
public class PlacardService {

    @Autowired
    private PlacardImageRepository placardImageRepository;

    @Autowired
    private DBFilePlacardImageRepository dbFilePlacardImageRepository;


    public void savePlacard(PlacardImageRequest request){
        placardImageRepository.save(request);
    }

    public PlacardImage getPlacardImage(String placardName){
        System.out.println("Getting Placard Image : "+ placardName);
        PlacardImage placardImage=placardImageRepository.getPlacardImage(placardName);
        return placardImage;
    }


    public DBFile getPlacardImageForDbFile(String placardName){
        System.out.println("Getting Placard Image for DBFile Object : "+ placardName);
        DBFile placardImage=dbFilePlacardImageRepository.getPlacardImage(placardName);
        return placardImage;
    }

}
