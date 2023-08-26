package today.creame.web.faq.application;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.faq.application.model.FaqRegisterParameter;
import today.creame.web.faq.application.model.FaqResult;
import today.creame.web.faq.domain.Faq;
import today.creame.web.faq.domain.FaqJpaRepository;
import today.creame.web.faq.exception.NotFoundFaqException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService{
    private final FaqJpaRepository faqJpaRepository;

    @Override
    public FaqResult getDetail(Long id) {
        Faq faq = faqJpaRepository.findByIdAndDeletedIsFalse(id).orElseThrow(NotFoundFaqException::new);
        return new FaqResult(faq);
    }

    @Transactional
    @Override
    public void register(FaqRegisterParameter parameter) {
        Faq faq = parameter.toEntity();
        List<Faq> originFaqs = faqJpaRepository.findAllByCategoryAndDeletedIsFalse(faq.getCategory());
        if(CollectionUtils.isEmpty(originFaqs)) {
            faq.changeOrderNumber(1);
            faqJpaRepository.save(faq);
        } else {
            List<Faq> sortFaqs = sortFaqs(faq, originFaqs);
            faqJpaRepository.saveAll(sortFaqs);
        }
    }

    @Transactional
    @Override
    public void update(FaqRegisterParameter parameter) {
        Faq faq = faqJpaRepository.findByIdAndDeletedIsFalse(parameter.getId()).orElseThrow(NotFoundFaqException::new);
        faq.changeFaq(parameter.getTitle(), parameter.getContent(), parameter.getCategory(), parameter.getOrderNumber());

        List<Faq> originFaqs = faqJpaRepository.findAllByCategoryAndDeletedIsFalse(faq.getCategory());
        if(CollectionUtils.isEmpty(originFaqs)) {
            faq.changeOrderNumber(1);
            faqJpaRepository.save(faq);
        } else {
            List<Faq> sortFaqs = sortFaqs(faq, originFaqs);
            faqJpaRepository.saveAll(sortFaqs);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Faq faq = faqJpaRepository.findByIdAndDeletedIsFalse(id).orElseThrow(NotFoundFaqException::new);
        faq.delete();
        faqJpaRepository.save(faq);
    }

    public List<Faq> sortFaqs(Faq faq, List<Faq> originFaqs){

        List<Faq> sortFaqs =
                originFaqs.stream()
                        .filter(p -> !p.getId().equals(faq.getId()))
                        .sorted(Comparator.comparing(faq1 -> faq1.getOrderNumber()))
                        .collect(Collectors.toList());

        List<Faq> saveFaqs = new ArrayList<>();
        if(Objects.nonNull(faq.getOrderNumber())){

            int orderNum = 1;
            boolean isFirst = true;
            for(Faq faq1 : sortFaqs){
                if(0 >= faq.getOrderNumber() && isFirst){
                    isFirst = false;
                    orderNum++;
                }

                if(orderNum == faq.getOrderNumber()){
                    orderNum++;
                }

                faq1.changeOrderNumber(orderNum);
                saveFaqs.add(faq1);
                orderNum++;
            }

            if(0 >= faq.getOrderNumber()){
                faq.changeOrderNumber(1);
                saveFaqs.add(faq);
            }else if(faq.getOrderNumber() >= orderNum){
                faq.changeOrderNumber(orderNum);
                saveFaqs.add(faq);
            }else{
                saveFaqs.add(faq);
            }
        }

        return saveFaqs;
    }
}
