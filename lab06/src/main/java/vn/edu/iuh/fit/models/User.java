package vn.edu.iuh.fit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "middleName", length = 50)
    private String middleName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "passwordHash", nullable = false, length = 64)
    private String passwordHash;//bscrypt

    @Column(name = "registeredAt", nullable = false)
    private Instant registeredAt;

    @Column(name = "lastLogin")
    private Instant lastLogin;

    @Lob
    @Column(name = "intro",columnDefinition = "text")
    private String intro;

    @Lob
    @Column(name = "profile", columnDefinition = "text")
    private String profile;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<PostComment> comments  = new LinkedHashSet<>();

    public User(String firstName, String middleName, String lastName, String mobile, String email, String passwordHash, Instant registeredAt, Instant lastLogin, String intro, String profile) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
        this.intro = intro;
        this.profile = profile;
    }
}