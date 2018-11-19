package kr.or.kosta.spring.sample.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * POJO (JavaBean)
 */

@Component("chef")
@Scope("prototype")
public class Chef {
	@Value("홍길동")
	private String name;
	
	public Chef() {
		super();
	}
	
	public Chef(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Chef [name=" + name + "]";
	}
	
	
}
