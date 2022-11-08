package com.quan.gradepractice.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Users")
@Table(name = "users")
public class AppUser {

   

    @Id
    @SequenceGenerator(
        name = "appUser_sequence",
        sequenceName = "appUser_sequence",
        allocationSize = 1
        
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "appUser_sequence"
    )
    private Long id;

  
    @NotBlank(message = "userName cannot be blank")
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;


    @NotBlank(message = "password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    private Set<Role> roles = new HashSet<>();

    public AppUser( String userName, String password) {
        this.userName = userName;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public String toString() {
        return "AppUser [id=" + id + ", userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
    }
}
