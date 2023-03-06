package today.creame.web.m2net.domain;

import lombok.Getter;
import today.creame.web.matching.domain.MatchingProgressStatus;

public enum M2netReasonCode {

    START_ARS(null),
    INSUFFICIENT(null),
    NOT_FOUND_CSRNO(null),
    NOT_IDLE(null),
    ABSE(null),
    TRY_OK(null),
    DISCONNECT(MatchingProgressStatus.START),
    CONNECT_CSR(MatchingProgressStatus.END),
    NO_ANSWER_CSR(null),
    INSUFFICIENT_CONN(MatchingProgressStatus.INSUFFICIENT);

    @Getter
    private MatchingProgressStatus matchingProgressStatus;

    M2netReasonCode(MatchingProgressStatus status) {
        this.matchingProgressStatus = status;
    }
}


/*
START_ARS : 회원이 최초 접속하여 ARS 를 듣기 시작하면 발생.(접속이벤트와 동일)
INSUFFICIENT : AG9 에서 회원이 상담사선택 후 연결전 회원잔액이 상담사의 차감단위 금액(예:1200)보다 적은 잔액인 경우 거절하며 발생.
NOT_FOUND_CSRNO : 회원이 선택(DTMF)한 상담사 번호로 검색한 결과 상담사가 없는 경우 발생. 자동선택(9)인 경우 대기중(상담가능) 상담사가 없는 경우.
NOT_IDLE : 회원이 선택(DMTF)한 상담사가 상담중인 경우
ABSE : 회원이 선택한 상담사가 부재중인 경우
TRY_OK : 회원이 선택한 상담사로 전화연결 시작(통신사로 전문발송완료)
DISCONNECT: 회원과 상담사 전화가 끊어진 경우. 상담사와 회원 쪽 CallID 로 2 회 발생할 수 있음.(1 회는 필수 이며 2 회는 보장성 없음)
CONNECT_CSR : 상담사와 회원의 전화연결이 완료됨. 차감 시작(보장성 있음)
NO_ANSWER_CSR : TRY_OK 이후 일정시간(30 초)동안 응답이 없는 경우. 받지 않는 경우 또는 받았다가 그냥 끊는 경우에 발생함. 세부 사유는 기간통신사에서 알려주지 않음.(SIP 연동규격)
INSUFFICIENT_CONN : 상담사 연결하여 상담중에 잔액 부족하면 멘트와 함께 해당 Push 메시지 발송

 */