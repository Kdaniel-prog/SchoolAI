package kiszel.daniel.temalabor.controllers;


import kiszel.daniel.temalabor.models.ERole;
import kiszel.daniel.temalabor.models.Role;
import kiszel.daniel.temalabor.models.User;
import kiszel.daniel.temalabor.payload.request.LoginRequest;
import kiszel.daniel.temalabor.payload.request.SignupRequest;
import kiszel.daniel.temalabor.payload.response.JwtResponse;
import kiszel.daniel.temalabor.payload.response.MessageResponse;
import kiszel.daniel.temalabor.repository.RoleRepository;
import kiszel.daniel.temalabor.repository.UserRepository;
import kiszel.daniel.temalabor.security.jwt.JwtUtils;
import kiszel.daniel.temalabor.security.services.UserDetailsImpl;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getName(),
				userDetails.getEmail(),
				roles));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody JSONObject param) {
		JSONObject params = new JSONObject(param);
		SignupRequest signUpRequest = new SignupRequest();
		signUpRequest.setEmail((String) params.get("email"));
		signUpRequest.setUsername((String) params.get("username"));
		signUpRequest.setPassword((String) params.get("password"));
		signUpRequest.setName((String) params.get("name"));
		signUpRequest.setRole(Boolean.parseBoolean(String.valueOf(params.get("isStudent"))));

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getName());

		Set<Role> roles = new HashSet<>();

		if (signUpRequest.getRole()) {
			Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(studentRole);
		} else {
			Role teacherRole = roleRepository.findByName(ERole.ROLE_TEACHER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(teacherRole);
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
