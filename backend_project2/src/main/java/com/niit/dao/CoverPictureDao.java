package com.niit.dao;

import com.niit.model.CoverPicture;

public interface CoverPictureDao {

      CoverPicture getCoverPic(String username);
      
	void saveCoverPicture(CoverPicture coverPicture);

}
