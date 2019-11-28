package ru.fedorov.model.vo.pages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Page {

    @JsonProperty("genre_ids")
    private int[] genreIds;
    @JsonProperty("vote_count")
    private float voteCount;
    @JsonProperty("vote_average")
    private float averageVote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Float.compare(page.getVoteCount(), getVoteCount()) == 0 &&
                Float.compare(page.getAverageVote(), getAverageVote()) == 0 &&
                Arrays.equals(getGenreIds(), page.getGenreIds());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getVoteCount(), getAverageVote());
        result = 31 * result + Arrays.hashCode(getGenreIds());
        return result;
    }
}
