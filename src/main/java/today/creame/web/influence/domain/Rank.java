package today.creame.web.influence.domain;

import lombok.Getter;

import java.util.Map;

public enum Rank {
    WHITE("화이트", 0L) {
        @Override
        public Rank getNextRank() {
            return Rank.BLUE;
        }
    },
    BLUE("블루", 5000000L) {
        @Override
        public Rank getNextRank() {
            return Rank.RED;
        }
    },
    RED("레드", 13000000L) {
        @Override
        public Rank getNextRank() {
            return Rank.PURPLE;
        }
    },
    PURPLE("퍼플", 0L) {
        @Override
        public Rank getNextRank() {
            return null;
        }
    },
    BLACK("블랙", 0L) {
        @Override
        public Rank getNextRank() {
            return null;
        }
    },

    ;

    @Getter
    private String label;

    @Getter
    private Long nextRankAmount;

    Rank(String label, Long nextRankAmount) {
        this.label = label;
        this.nextRankAmount = nextRankAmount;
    }

    abstract public Rank getNextRank();
}
