package io.nk.uploaddownloadimagerestfulwebservice.controller;

import io.nk.uploaddownloadimagerestfulwebservice.model.PlacardImage;
import io.nk.uploaddownloadimagerestfulwebservice.model.PlacardImageRequest;
import io.nk.uploaddownloadimagerestfulwebservice.repository.PlacardImageRepository;
import io.nk.uploaddownloadimagerestfulwebservice.service.PlacardService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;

@RestController
public class PlacardController {

    public static final Logger logger= LoggerFactory.getLogger(PlacardImageRepository.class);

    @Autowired
    private PlacardService placardService;


    @PostMapping("/upload")
    public ResponseEntity<?> savePlacardImage(@RequestParam("placardName") String placardName, @RequestParam("placardImage") MultipartFile placardImage){
        PlacardImageRequest placardImageRequest=new PlacardImageRequest(placardName,placardImage);
        placardService.savePlacard(placardImageRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/download")
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


}
