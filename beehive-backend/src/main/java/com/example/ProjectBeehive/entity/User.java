package com.example.ProjectBeehive.entity;

//import com.example.ProjectBeehive.security.UserRole;
import com.example.ProjectBeehive.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "ID")
    private BigInteger ID;

    @Column(nullable = false, name = "USERNAME")
    private String username;
    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(nullable = false, name = "ROLES")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, name = "F_NAME")
    private String firstName;


    @Column(nullable = false, name = "L_NAME")
    private String lastName;

    @Column(nullable = false, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "BIRTHDAY")
    private Date birthday;

    // Override the getAuthorities method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the user's role to a GrantedAuthority object
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof com.example.ProjectBeehive.entity.User))
            return false;
        com.example.ProjectBeehive.entity.User user = (com.example.ProjectBeehive.entity.User) o;
        return Objects.equals(this.ID, user.ID) && Objects.equals(this.firstName, user.firstName)
                && Objects.equals(this.role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ID, this.firstName, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.ID + ", name='" + this.firstName + '\'' + ", role='" + this.role + '\'' + '}';
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }
    // Override other methods from UserDetails interface
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
