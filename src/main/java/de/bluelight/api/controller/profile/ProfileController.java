package de.bluelight.api.controller.profile;

import de.bluelight.api.database.model.User;
import de.bluelight.api.database.service.UserService;
import de.bluelight.api.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(
        value = "/profile",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ProfileController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        if (!userService.isEmailAvailable(profileDTO.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseBuilder(BAD_REQUEST).error("The provided email is already taken!"));
        }
        User user = userService.createUser(profileDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(new ResponseBuilder(CREATED).data(response));
    }

    @GetMapping
    public ResponseEntity<String> getProfile() {
        return null;
    }

}
