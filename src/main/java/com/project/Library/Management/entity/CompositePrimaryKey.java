package com.project.Library.Management.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class CompositePrimaryKey implements Serializable {
    private String username;
    private String authority;

}
