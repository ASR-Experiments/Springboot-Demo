package dropwizard.springboot.dropwizard.to.springboot.controller;

import dropwizard.springboot.dropwizard.to.springboot.dto.UserDto;
import dropwizard.springboot.dropwizard.to.springboot.entity.UserEntity;
import dropwizard.springboot.dropwizard.to.springboot.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final UserCrudService userCrudService;
    @Autowired
    public RestController(UserCrudService userCrudService){
        this.userCrudService = userCrudService;
    }

    /**
     * Endpoint to greet user
     * @param name, i.e. name of the user. If empty then default value of Stranger will be returned
     * @return greeting message
     */
    @GetMapping("/hello-world")
    public String displayName(@RequestParam(name="name", required = false, defaultValue = "Stranger") String name){
        return "Hello! "+name;
    }

    /**
     * Endpoint to create/save a user
     * @param userDto, takes userDto fields required to create and save user
     * @return returns the entity of saved user
     */
    @PostMapping("/user")
    public UserEntity saveUser(@RequestBody UserDto userDto){
        return userCrudService.saveUser(userDto);
    }

    /**
     * Endpoint to update an existing user from its userId field
     * @param userId, required field to identify the user which needs to be updated
     * @param userDto, the fields which need to be updated for an id
     * @return returns the updated userEntity with updated fields
     */
    @PutMapping("/user/{userId}")
    public UserEntity updateUser(@PathVariable(name = "userId", required = true)Integer userId,
                                 @RequestBody UserDto userDto){
        return userCrudService.updateUser(userId, userDto);
    }

    /**
     * Endpoint to get a particular user from userId
     * @param userId, required field to identify the user which needs to be fetched
     * @return returns the fetched userEntity
     */
    @GetMapping("/user/{userId}")
    public UserEntity getUser(@PathVariable(name = "userId", required = true)Integer userId){
        return userCrudService.getUser(userId);
    }

    /**
     * Endpoint to get all existing users
     * @return list of userEntity
     */
    @GetMapping("/user")
    public List<UserEntity> getAllUsers(){
        return userCrudService.getAllUsers();
    }

    @DeleteMapping("/user/{userId}")
    public UserEntity deleteUser(@PathVariable(name = "userId", required = true)Integer userId){
        return userCrudService.deleteUser(userId);
    }
}
