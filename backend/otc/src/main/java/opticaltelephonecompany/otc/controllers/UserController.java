package opticaltelephonecompany.otc.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opticaltelephonecompany.otc.models.CallUser;
import opticaltelephonecompany.otc.models.RegistrationDto;
import opticaltelephonecompany.otc.services.AuthenticationService;
import opticaltelephonecompany.otc.services.ImageService;
import opticaltelephonecompany.otc.services.TokenService;
import opticaltelephonecompany.otc.services.UserService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;
    //private final ImageService imageService;

    public UserController(UserService userService, TokenService tokenService){
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/verify")
    public CallUser verifyIdentity(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        String username = "";
        CallUser user;

        if(token.substring(0,6).equals("Bearer")) {
            String strippedToken = token.substring(7);
            username = tokenService.getUsernameFromToken(strippedToken);
        }
        try {
            user = userService.getUserByUsername(username);
        } catch(Exception e) {
            user = null;
        }
        
        return user;
    }
    

    @GetMapping("/")
    public String helloUserController(){
        return "User access level";
    }

    @GetMapping("{id}")//url method argument is band with the Path variable if to the callId
    public ResponseEntity<CallUser> getUserById(@PathVariable("id") long userId){
        CallUser userDto = userService.getUserById(userId);
       return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<CallUser>> getAllUsers(){
        List<CallUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<CallUser> updateUser(@PathVariable("id") Long userId, @RequestBody CallUser updatedUser){
       CallUser userDto = userService.updateUser(userId, updatedUser);
       return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCall(@PathVariable("id") Long userId){
       userService.deleteUser(userId);
       return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("users")
    public String users(){
        return "my users";
    }
    
}
