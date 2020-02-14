package ru.fedorov.model.dataholder.loyaltyplant.vo.filmsinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmInfo {

    @JsonProperty("id")
    private long id;
    @JsonProperty("genre_ids")
    private int[] genreIds;
    @JsonProperty("vote_count")
    private int voteCount;
    @JsonProperty("vote_average")
    private float averageVote;

}
