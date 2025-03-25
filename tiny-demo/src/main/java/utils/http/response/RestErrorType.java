package utils.http.response;


/**
 * Created by lht on 2017/2/20.
 */
public interface RestErrorType {

    public  final int ROLE_PERMISSION_REST = 1000;

    public final int UI_PROCOTOL_REST = 2000;

    /**
     * 业务性错误，要求业务可明确处理的错误
     */
    public final int BIZ_ERROR = 600;

    /**
     * 技术性错误，业务无法处理的错误
     */
    public final int INTERNAL_SERVER_ERROR = 500;

}
