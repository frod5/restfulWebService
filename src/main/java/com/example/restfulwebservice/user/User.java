package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo")
@Table(name = "USERS")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 임력해주세요")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자 등록일을 임력해주세요")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime joinDate;

//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 패스워드를 임력해주세요")
    private String password;

//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 주민번호를 임력해주세요")
    private String ssn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
}
