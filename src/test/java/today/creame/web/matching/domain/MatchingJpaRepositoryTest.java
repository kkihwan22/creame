package today.creame.web.matching.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("local")
public class MatchingJpaRepositoryTest {

    @Autowired
    private MatchingJapRepository matchingJapRepository;

    @Test
    public void getConsultationHoursPerMonthTest() {
        Long influenceId = 90L;
        String targetDate = "202301";

        List<Object[]> objects = matchingJapRepository.getConsultationHoursPerMonth(influenceId, targetDate);
        List<MatchingStatisticsResult> matchingStatisticsResults = objects.stream().map(MatchingStatisticsResult::new).collect(Collectors.toList());
        Assertions.assertThat(objects).isNotNull();
        Assertions.assertThat(objects.size()).isEqualTo(10);
        Assertions.assertThat(matchingStatisticsResults.size()).isEqualTo(10);


    }
}
