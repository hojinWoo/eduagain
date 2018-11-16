package kr.or.kosta.spring.sample.di;

import lombok.Data;

/**
 * POJO (JavaBean), Lombok 사용 > outline 확인 시에 생성자 및 set,get 등이 다 설정 되어 있다.
 * 일일이 하고 싶은 것만 만들 수도 있다 ex. @Setter, @Getter
 */

@Data
public class Chef2 {
	private String name;
	
}
