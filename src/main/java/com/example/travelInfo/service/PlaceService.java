package com.example.travelInfo.service;


import com.example.travelInfo.domain.Comment;
import com.example.travelInfo.domain.Place;
import com.example.travelInfo.domain.Trip;
import com.example.travelInfo.domain.User;
import com.example.travelInfo.repositotories.CommentRepo;
import com.example.travelInfo.repositotories.PlaceRepository;
import com.example.travelInfo.repositotories.TripRepository;
import com.example.travelInfo.repositotories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class PlaceService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private TripRepository tripRepository;

    public boolean deletePlace(Place place){
        for(Comment comment : commentRepo.findAllByPlace(place)){
            commentRepo.delete(comment);
        }

        for(Trip trip : tripRepository.findAll()){
            trip.getPlaces().remove(place);
            tripRepository.save(trip);
        }

        for(User user : userRepository.findAll()){
            user.getPlaces().remove(place);
            userRepository.save(user);
        }

        for(String fileName : place.getFiles()){
            File file = new File(uploadPath+ "/" + fileName);
            file.delete();
        }
        place.getFiles().clear();

        placeRepository.delete(place);

        return true;
    }

    public void updatePlace(Place place, String type,
                            String latitude, String longitude,
                            String description, String address, String tags,
                            ArrayList<MultipartFile> files
    ){
        if(isChanged(type, place.getType())){
            place.setType(type);
        }

        if(isChanged(latitude, place.getLatitude())){
            place.setLatitude(latitude);
        }

        if(isChanged(longitude, place.getLongitude())){
            place.setLongitude(longitude);
        }

        if(isChanged(description, place.getDescription())){
            place.setDescription(description);
        }
        if(isChanged(address, place.getAddress())){
            place.setAddress(address);
        }

        //rewriting tags
        place.getTags().clear();
        String[] arrOfStr = tags.split(",");
        for (String a : arrOfStr){
            place.setTag(a);
        }


        if(sizeOf(files) != 0) {
            System.out.println(files.size());
            //deleting all old files
            for(String fileName : place.getFiles()){
                File file = new File(uploadPath+ "/" + fileName);
                file.delete();
            }
            place.getFiles().clear();

            //writing new files
            for(MultipartFile file : files){
                if (file != null && !file.getOriginalFilename().isEmpty()) {
                    File uploadDir = new File(uploadPath);

                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    String uuidFile = UUID.randomUUID().toString().replace("-", "");

                    try {
                        file.transferTo(new File(uploadPath + "/" + uuidFile));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    place.setFile(uuidFile);
                }
            }
        }
        placeRepository.save(place);
    }

    private boolean isChanged(String value1 , String value2){
        return (value1 != null && !value1.equals(value2)) ||
                (value2 != null && !value2.equals(value1));
    }

    public int sizeOf(ArrayList<MultipartFile> files){
        //check size of files
        int size = 0;
        for (MultipartFile file : files)
        {
            if (file != null && !file.isEmpty()) size++;
        }
        return size;
    }
}
