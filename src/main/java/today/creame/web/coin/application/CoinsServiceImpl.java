package today.creame.web.coin.application;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.coin.application.model.MyCoinStatResult;
import today.creame.web.coin.domain.CoinsHistory;
import today.creame.web.coin.domain.CoinsHistoryJpaRepository;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;

@RequiredArgsConstructor
@Service
public class CoinsServiceImpl implements CoinsService {
    private final Logger log = LoggerFactory.getLogger(CoinsServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final CoinsHistoryJpaRepository coinsHistoryJpaRepository;

    @Transactional
    @Override
    public void update(CoinsUpdateParameter parameter) {
        Member member = memberJpaRepository
            .findById(parameter.getMemberId())
            .orElseThrow(NotFoundMemberException::new);

        member.updateCoins(parameter.getType(), parameter.getCoins());
        CoinsHistory coinsHistory = new CoinsHistory(member, parameter.getType(), parameter.getCoins());
        coinsHistoryJpaRepository.save(coinsHistory);
    }

    @Override
    public MyCoinStatResult getCoinStatByMember(Long id) {
        Member member = memberJpaRepository.findById(id).orElseThrow(NotFoundMemberException::new);
        return new MyCoinStatResult(member);
    }
}
