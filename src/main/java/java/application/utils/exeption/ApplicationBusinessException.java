package java.application.utils.exeption;

public class ApplicationBusinessException extends BaseException {

    private static final long serialVersionUID = -6834563825277899887L;

    public ApplicationBusinessException(String strCode) {
        super(strCode);
    }

    public ApplicationBusinessException(String strCode, String strMessage) {
        super(strCode, strMessage);
    }

    public ApplicationBusinessException(String strCode, String strMessage, Object... params) {
        super(strCode, strMessage, params);
    }
}
