package today.creame.web.share.model;

public interface BaseRequest<T extends BaseParameter> {

    T toParameter();
}
