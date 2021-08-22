package com.sarpio.security.model;

import com.sarpio.order.model.OrderEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.support.ResourceHolderSupport;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Schema(description = "id of existing client automatic generated", example = "1")
    private Long id;

    @Column(name = "password")
    @NotEmpty(message = "password can't be empty")
    @Schema(description = "User password")
    private String password;

    @Column(name = "username")
    @NotBlank(message = "username can't be blank")
    @Length(min = 3, max = 15)
    private String username;

    @Email(message = "Enter a valid email address.")
    private String email;

    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roleEntities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<OrderEntity> order;

    public UsersEntity(UsersEntity usersEntity) {
        this.active = usersEntity.getActive();
        this.roleEntities = usersEntity.getRoleEntities();
        this.username = usersEntity.getUsername();
        this.id = usersEntity.getId();
        this.password = usersEntity.getPassword();
        this.roleEntities = usersEntity.getRoleEntities();
        this.order = usersEntity.getOrder();
    }
}
