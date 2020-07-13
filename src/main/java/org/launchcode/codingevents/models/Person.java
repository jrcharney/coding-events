package org.launchcode.codingevents.models;

import javax.persistence.Entity;

@Entity
public class Person extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
}
