package ru.fedorov.model.calculator;

import org.springframework.stereotype.Component;
import ru.fedorov.entity.Film;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Component
public class Calculator {

    public float calculateAverageVote(List<Film> films) {
        if (films == null)
            return 0;

        float sum = 0;
        int count = 0;

        for (Film averageVoteByFilm : films) {
            if (averageVoteByFilm.getVoteCount() != 0) {
                sum += averageVoteByFilm.getAverageVote();
                count++;
            }
        }

        if (count == 0)
            return 0;
        return formatFloat(sum / count);
    }

    @SuppressWarnings("deprecation")
    private float formatFloat(float number) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setRoundingMode(RoundingMode.DOWN);
        return new Float(formatter.format(number));
    }
}
