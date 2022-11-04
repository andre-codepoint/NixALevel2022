package com.nixalevel.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Colors")
public class Colors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colors color = (Colors) o;
        return Objects.equals(name, color.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
