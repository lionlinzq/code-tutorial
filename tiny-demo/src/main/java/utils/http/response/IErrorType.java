package utils.http.response;

/**
 * 错误类型接口
 */
public interface IErrorType {

    int getErrorType();

    String getReason();

    ErrorTypeFamily getErrorTypeFamily();

    String getKey();

    default String toErrorInfo() {
        return String.valueOf(getErrorType());
    }
}
