package com.ilkinmehdiyev.restaurantwebappdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EqualsAndHashCode(of = "{id}")
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Setter
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private long id;

    @CreationTimestamp
    @Column(name = "CREATED_TIME", updatable = false)
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "UPDATED_TIME")
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    private LocalDateTime updatedTime;


}