package today.creame.web.admin.application;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.admin.application.model.QnaCreateParameter;
import today.creame.web.admin.domain.Qna;
import today.creame.web.admin.domain.QnaAttachedFile;
import today.creame.web.admin.domain.QnaJapRepository;

@RequiredArgsConstructor
@Service
public class QnaServiceImpl implements QnaService {
    private final Logger log = LoggerFactory.getLogger(QnaServiceImpl.class);
    private final QnaJapRepository qnaJapRepository;

    @Transactional
    @Override
    public void create(QnaCreateParameter parameter) {
        Qna q = qnaJapRepository.save(Qna.createNew(
            parameter.getCategory(),
            parameter.getTitle(),
            parameter.getContent(),
            parameter.getAttachedFiles()
                .stream()
                .map(m -> new QnaAttachedFile(m.getFileId(), m.getFileUri()))
                .collect(Collectors.toList())
        ));

        log.debug("created QNA: {}", q);
    }
}