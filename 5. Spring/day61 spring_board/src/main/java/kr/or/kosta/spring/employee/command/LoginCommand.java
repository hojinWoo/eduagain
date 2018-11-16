package kr.or.kosta.spring.employee.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCommand {
	private String userId;
    private String password;
    private boolean rememberId;

}
