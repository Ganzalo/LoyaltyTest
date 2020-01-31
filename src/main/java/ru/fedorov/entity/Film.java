package ru.fedorov.entity;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "films")
@TypeDef(name = "int-array",
         typeClass = IntArrayType.class)
public class Film {

    @Id
    private long id;

    @Type( type = "int-array" )
    @Column(name = "genre_ids",
            columnDefinition = "integer[]"
    )
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
