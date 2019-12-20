package ru.fedorov.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    private int id;

    private String name;
}
