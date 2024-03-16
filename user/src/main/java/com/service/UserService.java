package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.UserDTO;
import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	 private UserRepository userRepository;
	public ResponseEntity<String> addUser(UserDTO userDTO) {
     User user=new User();
     user.setName(userDTO.getName());
     user.setEmail(userDTO.getEmail());
     user.setDob(userDTO.getDob());
     userRepository.save(user);
     return new ResponseEntity<String>("Successfully Added",HttpStatus.CREATED);
	
	}
	public ResponseEntity<List<UserDTO>> getallUsers() {
		List<User> user=userRepository.findAll();
		List<UserDTO> userDTO=new ArrayList<>();
		for(User userdetails:user) {
			UserDTO userdetailsDTO=new UserDTO();
			userdetailsDTO.setId(userdetails.getId());
			userdetailsDTO.setName(userdetails.getName());
			userdetailsDTO.setEmail(userdetails.getEmail());
			userdetailsDTO.setDob(userdetails.getDob());
			userDTO.add(userdetailsDTO);
		}
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
}
