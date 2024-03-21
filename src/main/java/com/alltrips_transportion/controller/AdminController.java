package com.alltrips_transportion.controller;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alltrips_transportion.dto.UserDto;
import com.alltrips_transportion.service.UserService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	private final Logger LOG = LogManager.getLogger(AdminController.class);
	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public ResponseEntity<List<String>> get() {
		//return "GET:: management controller";
		return ResponseEntity.ok(Arrays.asList("first", "second"));
	}
    @GetMapping("/get/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable(required = false) Integer id) {
		Optional<UserDto> userDto = userService.findById(id);
		return new ResponseEntity<UserDto>(userDto.get(), HttpStatus.OK);
	}
    @PostMapping
    @Hidden
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }
}
