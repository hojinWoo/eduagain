package kr.or.kosta.entity;

public class AccountException extends Exception{
	//String message;
	private int errorCode;
	
	public AccountException() {
		this("����ó�� �� ����ġ ���� ������ �߻��߽��ϴ�.", -9);
	}

	/**
	 * @param message	����ϰ��� �ϴ� �޽���
	 * @param errorCode	������ ������ �����ڵ�
	 */
	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @return	errorcode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}
}