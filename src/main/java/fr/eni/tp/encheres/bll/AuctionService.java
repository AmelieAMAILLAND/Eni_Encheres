package fr.eni.tp.encheres.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.eni.tp.encheres.bo.Article;
import fr.eni.tp.encheres.bo.ArticleState;
import fr.eni.tp.encheres.bo.Auction;
import fr.eni.tp.encheres.bo.Category;
import fr.eni.tp.encheres.bo.User;
import fr.eni.tp.encheres.bo.dto.SearchCriteria;
import fr.eni.tp.encheres.exception.BusinessException;

public interface AuctionService {
	Article findArticleById(int articleId);
	List<Article> findArticlesByName(String name);
	List<Article> findArticlesByCategory(Category category);
	List<Article> findArticlesByCategoryAndName(Category category, String name);
	List<Article> findArticles();
	List<Article> selectArticles(Article article, User user, boolean open, boolean current, boolean won, boolean currentVente, boolean notstarted, boolean finished, String buySale);
	Page<Article> selectArticles(SearchCriteria research, int userId, Pageable pageable);
	void sell(Article article) throws BusinessException;
	void deleteArticle(int articleId);
	void updateArticle(Article article) throws BusinessException;
	
	Category findCategoryById(int categoryId);
//	Category findCategoryByName(String name);
	List<Category> findCategories();
	void newCategory(Category category);
	void updateCategory(Category category);
	
	List<Auction> findAuctions(int userId, int articleId);
	List<Auction> findAuctionsByUser(int userId);
	List<Auction> findAllAuctions(int articleId);
	void newAuction(int articleId, int bidOffer, User userSession) throws BusinessException;
	void deleteAuction(Auction auction);
	
	LocalDateTime convertDate(LocalDate date, LocalTime time) throws BusinessException;
	void cancelArticle(Article article);

	void updateArticleState(ArticleState articleState, int articleId);


}
