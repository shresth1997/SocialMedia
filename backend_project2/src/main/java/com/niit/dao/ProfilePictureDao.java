package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {
	
ProfilePicture getProfilePic(String username);
void saveProfilePicture(ProfilePicture profilePicture);
}