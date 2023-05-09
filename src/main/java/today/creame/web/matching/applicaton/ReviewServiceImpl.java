package today.creame.web.matching.applicaton;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.matching.applicaton.model.ReviewCreateParameter;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.MatchingReview;
import today.creame.web.matching.domain.MatchingReviewJapRepository;
import today.creame.web.matching.exception.IlligalAccessMatchingException;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final MatchingJapRepository matchingJapRepository;
    private final MatchingReviewJapRepository matchingReviewJapRepository;

    @Transactional
    @Override
    public void review(ReviewCreateParameter parameter) {
        Matching matching = matchingJapRepository.findById(parameter.getMatchingId()).orElseThrow(NotFoundMatchingException::new);
        Long id = SecurityContextSupporter.getId();
        Member member = matching.getMember();
        if (!id.equals(member.getId())) {
            log.error("matching member: {}, login member: {}", id, member.getId());
            throw new IlligalAccessMatchingException();
        }
        MatchingReview matchingReview = new MatchingReview(matching, parameter.getRate(), parameter.getCategory(), parameter.getReviewKinds(), parameter.getContent());
        matchingReviewJapRepository.save(matchingReview);
    }

    public void answer() {

    }
}