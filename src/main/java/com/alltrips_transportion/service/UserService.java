package com.alltrips_transportion.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alltrips_transportion.dto.UserDto;
import com.alltrips_transportion.exception.ApiRequestException;
import com.alltrips_transportion.repository.UserRepository;
import com.alltrips_transportion.security.user.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserService implements IUserService {

	@Autowired
	private final UserRepository repository;
	
//	@Override
//	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//		Optional<User> user = repository.findByEmail(userEmail);
//		
//        return user.get();
//		
//	}
	
	public Optional<UserDto> findById(int id){

		 Optional<User> user = repository.findById(id);
	     UserDto userDto = new UserDto();
	        if (!user.isPresent()){
	            throw new ApiRequestException("Oops Customer NOT FOUND ////");
	        }else {
	        	User u = user.get();        	
	        	userDto.setId(u.getId());
	        	userDto.setFirstname(u.getFirstname());
	        	userDto.setLastname(u.getLastname());
	        	userDto.setEmail(u.getEmail());
	        	userDto.setRole(u.getRole());
	        }

	        Optional<UserDto> uDto = Optional.ofNullable(userDto);
	        
	        return uDto;
	        
	}
	
	public List<UserDto> feachAllUsers(){		
		
		return repository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	
	}
	
	private UserDto convertEntityToDto(User user) {
		
		UserDto userDto = new UserDto();
    	userDto.setId(user.getId());
    	userDto.setFirstname(user.getFirstname());
    	userDto.setLastname(user.getLastname());
    	userDto.setEmail(user.getEmail());
    	userDto.setRole(user.getRole());
		
		return userDto;
	}

	public UserDto saveOrUpdate(UserDto userDto) {
		
		User existingUser = repository.findById(userDto.getId()).get();
		existingUser.setFirstname(userDto.getFirstname());
		existingUser.setLastname(userDto.getLastname());
		existingUser.setEmail(userDto.getEmail());
		existingUser.setRole(userDto.getRole());
		
		User updatedUser = repository.saveAndFlush(existingUser);
		UserDto updateUserDto = convertEntityToDto(updatedUser);
		
		return updateUserDto;
	}
	
	public String delete(Integer id) {
		
		Optional<User> user = repository.findById(id);
		if(!user.isPresent()) {
			throw new ApiRequestException("User not found");
		}else {
			 repository.deleteById(id);
		}
		
		return "User Removed !! " + id;
		 
	}

}
