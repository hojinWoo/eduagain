package kr.or.kosta.entity;
import java.util.Arrays;

public class AccountException extends Exception{
	//String message;
	private int errorCode;
	
	public AccountException() {
		this("����ó�� �� ����ġ ���� ������ �߻��߽��ϴ�.", -9);
	}

	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}
	
	
	
}