package kr.or.kosta.blog.article.dao;

import java.util.List;
import kr.or.kosta.blog.common.Params;

public interface ArticleDao {
	
	public boolean create(Article article) throws Exception;

	public boolean create(Article article, int pOrderId) throws Exception;
	
	public Article read(String article_id) throws Exception;
	
	public boolean update(Article article) throws Exception;
	
	public void delete(String article_id) throws Exception;

	public boolean isdelete(String article_id) throws Exception;
	
	public void countUp(String article_id) throws Exception;
	
	public List<Article> listByPage(Params params) throws Exception;
	
	public int countBySearch(Params params) throws Exception;
	
}
