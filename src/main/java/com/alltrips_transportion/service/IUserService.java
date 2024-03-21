package com.alltrips_transportion.service;

import java.util.List;
import java.util.Optional;

import com.alltrips_transportion.dto.UserDto;

public interface IUserService {
	public Optional<UserDto> findById(int id);
	
	public List<UserDto> feachAllUsers();
	
	public UserDto saveOrUpdate(UserDto userDto);
	
	public String delete(Integer id);

}
