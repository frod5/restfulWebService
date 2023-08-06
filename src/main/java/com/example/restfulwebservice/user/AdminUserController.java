package com.example.restfulwebservice.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {

        List<User> users = userRepository.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=1") 파라미터로 관리
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1") // Header로 관리
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json") // MINETYPE (accept 헤더)
    public MappingJacksonValue retrieveUserV1(@PathVariable Long id) {
        User user = userRepository.findOne(id);

        if(ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v2/users/{id}")
//@GetMapping(value = "/users/{id}/", params = "version=2") 파라미터로 관리
//@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")  // Header로 관리
@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json") // MINETYPE (accept 헤더)
    public MappingJacksonValue retrieveUserV2(@PathVariable Long id) {
        User user = userRepository.findOne(id);

        if(ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user,userV2);  //UserV2에 user값을 copy
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }
}
