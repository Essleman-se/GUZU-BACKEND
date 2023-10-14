package com.alltrips_transportion.dto;

import com.alltrips_transportion.security.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	  private Integer id;
	  private String firstname;
	  private String lastname;
	  private String email;
	  private Role role;

}
