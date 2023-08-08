package today.creame.web.ranking.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.ranking.application.model.ConsultationProductRegisterParameter;
import today.creame.web.ranking.application.model.ConsultationProductResult;
import today.creame.web.ranking.application.model.ConsultationProductUpdateParameter;
import today.creame.web.ranking.domain.ConsultationProduct;
import today.creame.web.ranking.domain.ConsultationProductJpaRepository;
import today.creame.web.ranking.domain.Ranking;
import today.creame.web.ranking.domain.RankingJpaRepository;
import today.creame.web.ranking.exception.NotFoundConsultationProductException;
import today.creame.web.ranking.exception.NotFoundRankingException;


@Service
@RequiredArgsConstructor
public class ConsultationProductServiceImpl implements ConsultationProductService{

    private final ConsultationProductJpaRepository consultationProductJpaRepository;
    private final RankingJpaRepository rankingJpaRepository;
    @Override
    public ConsultationProductResult get(Long id) {
        ConsultationProduct consultationProduct = consultationProductJpaRepository.findById(id).orElseThrow(NotFoundConsultationProductException::new);
        return new ConsultationProductResult(consultationProduct);
    }

    @Transactional
    @Override
    public Long register(ConsultationProductRegisterParameter parameter) {
        Ranking ranking = rankingJpaRepository.findById(parameter.getRankingId()).orElseThrow(NotFoundRankingException::new);
        ConsultationProduct consultationProduct = consultationProductJpaRepository.save(new ConsultationProduct(ranking, parameter.getBudgetAmounts(), parameter.getConsultationAmount()));

        return consultationProduct.getId();
    }

    @Override
    public void update(ConsultationProductUpdateParameter parameter) {
        Ranking ranking = rankingJpaRepository.findById(parameter.getRankingId()).orElseThrow(NotFoundRankingException::new);
        ConsultationProduct consultationProduct = consultationProductJpaRepository.findById(parameter.getConsultationProductId()).orElseThrow(NotFoundConsultationProductException::new);
        consultationProduct.change(ranking, parameter.getBudgetAmounts(), parameter.getConsultationAmount());

        consultationProductJpaRepository.save(consultationProduct);
    }

    @Override
    public void delete(Long id) {
        ConsultationProduct consultationProduct = consultationProductJpaRepository.findById(id).orElseThrow(NotFoundConsultationProductException::new);

        consultationProductJpaRepository.delete(consultationProduct);
    }
}
