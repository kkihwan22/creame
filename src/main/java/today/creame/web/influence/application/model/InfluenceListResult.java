package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.domain.Rank;

import java.time.LocalDateTime;

@Getter
public class InfluenceListResult {

    private Long id;
    private String nickname;
    private String name;
    private String email;
    private String phoneNumber;
    private Rank rank;
    private Boolean isHotInfluence;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public InfluenceListResult(Long id, String nickname, String name, String email, String phoneNumber, Rank rank, Boolean isHotInfluence, LocalDateTime createdDt, LocalDateTime updatedDt) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.isHotInfluence = isHotInfluence;
        this.createdDt = createdDt;
        this.updatedDt = updatedDt;

    }
}