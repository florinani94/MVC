package com.evozon.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by dianamohanu on 21/07/2016.
 */
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @NotNull
    private String authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
