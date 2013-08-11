package com.nci.cp.article.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import com.nci.cp.article.model.Article;
import com.nci.cp.article.service.ArticleService;
import com.nci.cp.core.exception.DaoException;
import com.nci.cp.core.exception.ServiceException;
import com.nci.cp.core.web.AbstractAction;
import com.nci.cp.ds.paging.Pagination;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class ArticleManagerAction extends AbstractAction {

	private Article article;
	
	private ArticleService articleService;
	
	private Pagination pagi;

	public Pagination getPagi() {
		return pagi;
	}

	public void setPagi(Pagination pagi) {
		this.pagi = pagi;
	}
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	

	 // 封装上传文件域的属性
	private File image;   
	// 封装上传文件类型的属性 
	private String imageContentType;   
	// 封装上传文件名的属性  
	private String imageFileName;   
	// 接受依赖注入的属性
	private String savePath;
	
	 /**
     * 返回上传文件的保存位置
     * 
     * @return
     */
    public String getSavePath() throws Exception{
        return ServletActionContext.getServletContext().getRealPath(savePath); 
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

	/**
	 *  显示文章普通页面
	 * @return
	 * @throws Exception
	 */
	public String showArticleIndex() throws Exception{
		
		// 分页查询
		if (pagi == null) {
			pagi = new Pagination();
		}
		
		pagi = articleService.findAllArticleByPage(pagi);
		return SUCCESS;
	}
	
	
	/**
	 * 显示文章列表
	 * @return
	 * @throws ServiceException 
	 */
	public String showArticleList() throws ServiceException{
		// 分页查询
		if (pagi == null) {
			pagi = new Pagination();
		}
		
		pagi = articleService.findAllArticleByPage(pagi);
		return SUCCESS;
	}
	
    /**
     * 跳转添加文章页面
     * @return
     */
	public String addArticle() {
		return SUCCESS;
	}
	
	/**
	 * 显示文章详情
	 * @return
	 * @throws ServiceException
	 */
	public String showArticleDetail() throws ServiceException{
		article = articleService.findArticle(article);
		return SUCCESS;
	}
	
	/**
	 * 创建文章
	 * @return
	 * @throws DaoException
	 */
	public String saveArticle() throws ServiceException{
		FileOutputStream fos = null;
		FileInputStream fis = null;
		String saveFileName="";
		int result=0;
		String contentType=this.imageFileName.substring(this.imageFileName.lastIndexOf("."));
		// 判断文件是否存在
		if(!this.getImage().exists())
		{
			ActionContext.getContext().put("lengthError", "文件为空或文件不存在！");
			result=1;
		}else{
			//上传前判断文件类型以及文件大小
			if (!this.imageFileName.toLowerCase().endsWith(".jpg")
					&& !this.imageFileName.toLowerCase().endsWith(".jpeg")
					&& !this.imageFileName.toLowerCase().endsWith(".gif")
					&& !this.imageFileName.toLowerCase().endsWith(".png")
					&& !this.imageFileName.toLowerCase().endsWith(".bmp")) {
				ActionContext.getContext().put("typeError",
						"文件类型只能是docx、doc、xls或xlsx！");
				result = 1;
			}
			
			//判断文件大小
			if(image.length()>5242880)
			{
				ActionContext.getContext().put("lengthError", "文件必须小于5M！");
				result=1;
			}else if(image.length()==0){
				ActionContext.getContext().put("lengthError", "文件必须大于0M！");
				result=1;
			}
		}
		if(result==0)
		{
			try {
				// 建立文件输出流
				System.out.println("保存文件文件夹-----"+getSavePath());
				System.out.println("文件名称"+this.imageFileName);
				System.out.println("文件类型"+this.imageContentType);
				File file = new File(getSavePath());
				if (!file.exists()) {
					file.mkdirs();
				}
				//保存图片时，在文件名后加随机数
				Random r=new Random();
				saveFileName=this.getImageFileName().substring(0,this.getImageFileName().lastIndexOf("."))+"_"+r.nextInt(10000)+contentType;
				fos = new FileOutputStream(getSavePath() + "\\"
						+ saveFileName);
				// 建立文件上传流
				fis = new FileInputStream(this.getImage());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
			} catch (Exception e) {
				System.out.println("文件上传失败");
				result=1;
				e.printStackTrace();
			} finally {
				close(fos, fis);
			}	
		}
		//如果文件上传成功，更新数据表记录信息
		if(result==0)
		{
			article.setTitlePic(saveFileName);
			articleService.createArticle(article);
			return SUCCESS;
		}
		else
			return INPUT;
		
		
		
		
	}
	
	/**
	 * 删除文章
	 * @return
	 * @throws ServiceException
	 */
	public String deleteArticle() throws ServiceException{
		
		if(article==null){
			return ERROR;
		}else{
			articleService.deleteArticle(getArticle());
			return SUCCESS;
		}
	}
	
	/**
	 * 修改文章跳转
	 * @return
	 * @throws ServiceException 
	 */
	public String showUpdateArticle() throws ServiceException{
		if(article==null){
			return ERROR;
		}else{
			article = articleService.findArticle(article);
			return SUCCESS;
		}
	}
	
	
	/**
	 * 修改文章
	 * @return
	 * @throws ServiceException 
	 */
	public String updateArticle() throws ServiceException{
		if(article!=null){
			Article before = this.articleService.findArticle(article);
			article.setTitlePic(before.getTitlePic());
			Article  result = this.articleService.updateArticle(article);
			if(result != null){
				return SUCCESS ;
			}else
				return ERROR;
			
		}else
		   return ERROR;
	}
	
	/**
	 * 关闭文件流
	 */
	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("FileInputStream关闭失败");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("FileOutputStream关闭失败");
				e.printStackTrace();
			}
		}
	}

	
}
