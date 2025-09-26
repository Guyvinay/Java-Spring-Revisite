package com.app.service;

import com.app.modal.Profile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {
    Profile saveProfile(Profile profile) throws IOException;

    Profile findById(String id) throws IOException;

    List<Profile> findAllProfiles() throws IOException;
}
