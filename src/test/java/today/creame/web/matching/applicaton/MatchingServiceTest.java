package today.creame.web.matching.applicaton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import today.creame.web.matching.applicaton.model.MatchingStatisticsParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.PaidType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchingServiceTest {
    private MatchingJapRepository matchingJapRepository = mock(MatchingJapRepository.class);
    private MatchingServiceImpl matchingService = new MatchingServiceImpl(null, null, matchingJapRepository);

    @Test
    public void getConsultationHoursPerMonthTest() {

        MatchingStatisticsParameter parameter = new MatchingStatisticsParameter(90L, LocalDate.of(2023, 3, 1));
        List<Object[]> results = new ArrayList<>();

        Object[] objects = new Object[3];
        objects[0] = "202304";
        objects[1] = PaidType.POST;
        objects[2] = LocalTime.of(1, 30, 0);
        results.add(objects);

        objects = new Object[3];
        objects[0] = "202304";
        objects[1] = PaidType.PRE;
        objects[2] = LocalTime.of(1, 30, 0);
        results.add(objects);

        objects = new Object[3];
        objects[0] = "202305";
        objects[1] = PaidType.POST;
        objects[2] = LocalTime.of(0, 31, 0);
        results.add(objects);

        objects = new Object[3];
        objects[0] = "202306";
        objects[1] = PaidType.POST;
        objects[2] = LocalTime.of(1, 28, 0);
        results.add(objects);

        objects = new Object[3];
        objects[0] = "202306";
        objects[1] = PaidType.PRE;
        objects[2] = LocalTime.of(0, 28, 0);
        results.add(objects);

        when(matchingJapRepository.getConsultationHoursPerMonth(any(), any())).thenReturn(results);

        List<MatchingStatisticsResult> matchingStatisticsResults = matchingService.getConsultationHoursPerMonth(parameter);

        Assertions.assertThat(matchingStatisticsResults.size()).isEqualTo(8);
    }
}
