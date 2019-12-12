package io.nk.uploaddownloadimagerestfulwebservice.controller;

import io.nk.uploaddownloadimagerestfulwebservice.model.*;
import io.nk.uploaddownloadimagerestfulwebservice.service.PlacardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlacardController {

    public static final Logger logger= LoggerFactory.getLogger(PlacardController.class);

    @Autowired
    private PlacardService placardService;

//to upload the placard images
    @PostMapping("/upload")
    public ResponseEntity<?> savePlacardImage(@RequestParam("placardName") String placardName, @RequestParam("placardImage") MultipartFile placardImage){
        PlacardImageRequest placardImageRequest=new PlacardImageRequest(placardName,placardImage);
        placardService.savePlacard(placardImageRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //to download the placard image with @RequestParam and to write the image locally on the project directory

    @GetMapping("/downloadFile")
    public ResponseEntity<PlacardImage> getPlacardImage(@RequestParam("placardName") String placardName){
        logger.info("Placard Image "+ placardName+" started downloading");

        PlacardImage placardImage=placardService.getPlacardImage(placardName);
        try {
           // byte[] data = placardImage.getPlacardImage();
           // ByteArrayInputStream bis = new ByteArrayInputStream(data);
            //BufferedImage bImage = ImageIO.read(bis);

            BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(placardImage.getPlacardImage()));
            ImageIO.write(bImage, "jpg", new File("output.jpg"));

            logger.info("Placard Image"+ placardImage.getPlacardName()+" is downloaded successfully");

        }catch (IOException e){
            e.getMessage();
        }

        return new ResponseEntity<>(placardImage, HttpStatus.OK);
    }

    //this doesn't work yet

//    @PostMapping("/downloadFile")
//    public ResponseEntity<PlacardImage> getPlacardImageWithRequestObject(@RequestBody DownloadPlacardImageRequest request){
//        logger.info("Placard Image "+ request.getPlacardName()+" started downloading");
//        String pName=request.getPlacardName();
//        PlacardImage placardImage=placardService.getPlacardImage(pName);
//
//        logger.info(request.getPlacardName()+" Downloading Placard Image Completed Successfully");
//        return new ResponseEntity<>(placardImage, HttpStatus.OK);
//    }


    //to download a list of placardImages in a PlacardImgResponse wrapper object

    @GetMapping("/downloadPlacard/{placardName}")
    public ResponseEntity<DownloadedPlacardImgResponse> getPlacardImageWithPathVariable(@PathVariable("placardName") List<String> placardName){
        logger.info("Placard Image "+ placardName+" started downloading");

        DownloadedPlacardImgResponse response=new DownloadedPlacardImgResponse();
        List<PlacardImage> imageList=new ArrayList<>();
        PlacardImage placardImage=new PlacardImage();

        for(String pName:placardName) {
            placardImage = placardService.getPlacardImage(pName);
            imageList.add(placardImage);
        }

        for(PlacardImage image:imageList) {
            response.setImages(imageList);
        }
        logger.info(placardName+" Downloading Placard Image Completed Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
