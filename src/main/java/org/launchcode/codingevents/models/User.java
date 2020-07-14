package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash   = encoder.encode(password);
    }

    public String getUsername(){ return username; }

    public boolean isMatchingPassword(String password){
        /* NOTE: The following two lines will not work because of how BCrypt works.
         * > String candidateHash = encoder.encode(password);
         * > return candidateHash.equals(pwHash);
         * So that is wy we use the line below.
         */
        return encoder.matches(password,pwHash);
    }
}
