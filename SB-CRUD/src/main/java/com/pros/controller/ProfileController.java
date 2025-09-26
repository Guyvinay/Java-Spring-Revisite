package com.pros.controller;

import com.pros.modal.Profile;
import com.pros.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<Profile> saveProfiles(@RequestBody Profile profile) throws IOException {
        return new ResponseEntity<Profile>(profileService.saveProfile(profile), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("id")String id) throws IOException {
        return new ResponseEntity<Profile>(profileService.findById(id),HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<Profile>> GetAllProfiles() throws IOException {
        return new ResponseEntity<List<Profile>>(profileService.findAllProfiles(),HttpStatus.ACCEPTED);
    }

}
