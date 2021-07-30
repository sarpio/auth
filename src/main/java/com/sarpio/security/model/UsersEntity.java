package com.sarpio.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;

    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities;

    @OneToOne(mappedBy="usersEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private UserDetailEntity userDetails;

    public UsersEntity(UsersEntity usersEntity) {
        this.active = usersEntity.getActive();
        this.roleEntities = usersEntity.getRoleEntities();
        this.username = usersEntity.getUsername();
        this.id = usersEntity.getId();
        this.password = usersEntity.getPassword();
    }
}
