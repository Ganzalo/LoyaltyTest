package ru.fedorov.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "films")
public class Film {

    @Id
    private long id;

    @Column(name = "genre_ids")
    private int[] genreIds;

    @Column(name = "vote_count")
    private int voteCount;

    @Column(name = "vote_average")
    private float averageVote;

    @Override
    public String toString() {
        return  "id = " + id +
                ", genreIds = " + Arrays.toString(genreIds) +
                ", voteCount = " + voteCount +
                ", averageVote = " + averageVote;
    }
}
