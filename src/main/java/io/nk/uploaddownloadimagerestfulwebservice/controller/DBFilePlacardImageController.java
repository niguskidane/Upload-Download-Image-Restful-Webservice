package io.nk.uploaddownloadimagerestfulwebservice.controller;

import io.nk.uploaddownloadimagerestfulwebservice.model.DBFile;
import io.nk.uploaddownloadimagerestfulwebservice.service.AdditionalPlacardService;
import io.nk.uploaddownloadimagerestfulwebservice.service.PlacardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class DBFilePlacardImageController {

    public static final Logger logger= LoggerFactory.getLogger(DBFilePlacardImageController.class);

    @Autowired
    PlacardService placardService;

    @Autowired
    private AdditionalPlacardService additionalPlacardService;

    //To download a single placard image at a time and display it in postman

    @GetMapping("/downloadFile/{placardName}")
    public ResponseEntity<Resource> downLoadFile(@PathVariable("placardName") String placardName  ) {

        DBFile image = placardService.getPlacardImageForDbFile(placardName);

        //File type can be set here using this "image/jpeg" value

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; placardName=\"" + image.getPlacardName() + "\"")
                .body(new ByteArrayResource(image.getPlacardImage()));
    }

//   @RequestMapping(value="/downloadPlacardImages/{placardName}", method= RequestMethod.GET)
//    public ResponseEntity<Resource> downLoadPlacardImageFiles(@PathVariable("placardName") List<String> placardNames  ) {
//        List<Resource> resources=new ArrayList<>();
//        DBFile image=new DBFile();
//        for(String placardName:placardNames) {
//            image = placardService.getPlacardImageForDbFile(placardName);
//            if(image!=null){
//                return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; placardName=\"" + image.getPlacardName() + "\"")
//                        .body(new ByteArrayResource(image.getPlacardImage()));
//            }
//            continue;
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(new ByteArrayResource(null));
//    }
//
//
//    @RequestMapping(value="/downloadPlacards/{placardName}", method= RequestMethod.GET)
//    public List<ResponseEntity<Resource>> downLoadPlacardImageFilesNewWay(@PathVariable("placardName") List<String> placardNames  ) {
//        List<ResponseEntity<Resource>> responseEntityList= additionalPlacardService.downLoadPlacardImageFiles(placardNames);
//     return responseEntityList;
//    }
}
