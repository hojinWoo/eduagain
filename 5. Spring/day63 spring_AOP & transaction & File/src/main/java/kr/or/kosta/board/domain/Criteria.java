package kr.or.kosta.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Criteria {

  private int pageNum;	//페이지 번호
  private int amount;	//한 페이지에 글 개수
  
  private String type;
  private String keyword;

  public Criteria() {
    this(1, 10);	
  }

  public Criteria(int pageNum, int amount) {
    this.pageNum = pageNum;
    this.amount = amount;
  }
  
  public String[] getTypeArr() {
    
    return type == null? new String[] {}: type.split("");
  }
}