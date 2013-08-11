package com.nci.cp.article.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nci.cp.article.dao.IArticleDao;
import com.nci.cp.article.model.Article;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.ds.paging.Pagination;



/**
 * @author  Shenq 
 * @version 0.1
 * @date    2013-07-16 
 */
public class ArticleService {

	private static Log log = LogFactory.getLog(ArticleService.class);
	
	private IArticleDao articleDao;

	public IArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(IArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	
	/**
	 * 添加文章
	 * @param article
	 * @return Article
	 * @throws DaoException 
	 */
	public Article createArticle(Article article) throws ServiceException{
		try {
			Article result = null;
			if(article!=null){
				result = (Article)this.articleDao.createEntity(article);
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" createArticle() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" createArticle() fail!");	
		}
	}
	
	/**
	 * 分页查询所有文章
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findAllArticleByPage(Pagination page) throws ServiceException{
		try {
			Pagination pg = this.articleDao.findEntitiesByPage(page);
			return pg;
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findAllArticleByPage() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findAllArticleByPage() fail!");	
		}
	}

	/**
	 *  查询文章详情
	 * @param article
	 * @return
	 * @throws ServiceException
	 */
	public Article findArticle(Article article) throws ServiceException{
		
		try {
			Article result = (Article) this.articleDao.findEntityById(article);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" findArticle() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" findArticle() fail!");	
		}
		
	}
	
	/**
	 * 删除文章
	 * @param article
	 * @return
	 * @throws ServiceException
	 */
	public boolean deleteArticle(Article article) throws ServiceException{
		
		try {
			return this.articleDao.deleteEntity(article);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" deleteArticle() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" deleteArticle() fail!");
		}
	}
	
	
	public Article updateArticle(Article article) throws ServiceException{
		
		try {
			Article result = null;
			if(article!=null){
				result = (Article)this.articleDao.updateEntity(article);
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(this.getClass().getName()+" updateArticle() fail! "+e.getMessage());
			throw new ServiceException(this.getClass().getName()+" updateArticle() fail!");	
		}
	}
	
}
