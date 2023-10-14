package com.alltrips_transportion.controller;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alltrips_transportion.dto.UserDto;
import com.alltrips_transportion.exception.ApiRequestException;
import com.alltrips_transportion.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/management")
@PreAuthorize("hasRole('MANAGER')")
@Tag(name = "Management")
@RequiredArgsConstructor
public class ManagementController {
	private final Logger LOG = LogManager.getLogger(ManagementController.class);
	private final UserService userService;

	@Operation(description = "Get endpoint for manager", summary = "This is a summary for management get endpoint", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403") }

	)
	@GetMapping("/test")
	public ResponseEntity<List<String>> get() {
		//return "GET:: management controller";
		return ResponseEntity.ok(Arrays.asList("first", "second"));
	}

	@GetMapping("/get/{id}")
	@PreAuthorize("hasAuthority('management:read')")
	public ResponseEntity<UserDto> findById(@PathVariable(required = false) Integer id) {
		Optional<UserDto> userDto = userService.findById(id);
		return new ResponseEntity<UserDto>(userDto.get(), HttpStatus.OK);
	}

	@GetMapping("/get/all")
	@PreAuthorize("hasAuthority('management:read')")
	public ResponseEntity<List<UserDto>> findAllUsers() {
		LOG.info("All user list end point");
		List<UserDto> userDtos = userService.feachAllUsers();
		return new ResponseEntity<>(userDtos, HttpStatus.OK);
	}

	@PostMapping
	public String post() {
		return "POST:: management controller";
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
		LOG.info("User update");
		Optional<UserDto> user = userService.findById(userDto.getId());

		if (user.get().getId() == null) {
			throw new ApiRequestException("Oops User ID not found, ID is required for update the data");
		} else {
			UserDto updateduser = userService.saveOrUpdate(userDto);
			return new ResponseEntity<>(updateduser, HttpStatus.OK);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserDto> patch(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
		Optional<UserDto>  userDto = userService.findById(id);
		if (userDto.isPresent()) {
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(UserDto.class, (String) key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, userDto.get(), value);
			});
			UserDto updateUser = userService.saveOrUpdate(userDto.get());

			return new ResponseEntity<>(updateUser, HttpStatus.OK);
		}
		LOG.info("User not found with this ID: {}", id);
		
		return null;
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('management:delete')")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		LOG.info("User Delete : {}", id);		
		
		return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
	}
}
