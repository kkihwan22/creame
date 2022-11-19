package today.creame.web.share.aspect.permit;

public enum PermitRuleType {
    TOKEN(new PermitRuleTokenHandler()),
    ME(new PermitRuleMeHandler()),
    INFLUENCE(new PermitRuleInfluenceHandler()),
    ADMIN(new PermitRuleAdminHandler()),
    ;

    private PermitRuleHandler handler;

    PermitRuleType(PermitRuleHandler handler) {
        this.handler = handler;
    }

    public void valid(Object param) {
        this.handler.handle(param);
    }
}
