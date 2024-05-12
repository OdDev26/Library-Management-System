package com.project.Library.Management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@IdClass(CompositePrimaryKey.class)
public class Authority {
    @Id
    @Column(name = "username")
    private String username;
    @Id
    @Column(name = "authority")
    private String authority;

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
