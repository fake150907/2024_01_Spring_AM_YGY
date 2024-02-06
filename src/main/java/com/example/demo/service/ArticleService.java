package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	// 서비스 메서드
	public Article getForPrintArticle(int loginedMemberId, int id) {
		Article article = articleRepository.getForPrintArticle(id);

		controlForPrintData(loginedMemberId, article);

		return article;
	}

	private void controlForPrintData(int loginedMemberId, Article article) {
		if (article == null) {
			return;
		}
		ResultData userCanModifyRd = userCanModify(loginedMemberId, article);
		article.setUserCanModify(userCanModifyRd.isSuccess());

		ResultData userCanDeleteRd = userCanDelete(loginedMemberId, article);
		article.setUserCanDelete(userCanDeleteRd.isSuccess());
	}

	public ResultData userCanDelete(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", Ut.f("%d번 글에 대한 삭제 권한이 없습니다", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 글이 삭제 되었습니다", article.getId()));
	}

	public ResultData userCanModify(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", Ut.f("%d번 글에 대한 수정 권한이 없습니다", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 글을 수정했습니다", article.getId()));
	}

	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);

		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 글이 생성되었습니다", id), "id", id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public List<Article> getArticles(int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;

		List<Article> articleRows = articleRepository.getArticles(limitFrom, itemsInAPage);

		return articleRows;
	}

	public List<Article> getForPrintArticles(int boardId, int page) {
		int itemsInAPage = getItemsInAPage();
		int limitFrom = (page - 1) * itemsInAPage;

		List<Article> articleRows = articleRepository.getArticleRows(boardId, limitFrom, itemsInAPage);

		return articleRows;
	}

	public int getItemsInAPage() {
		return 10;
	}

	public int getTotalPage() {
		int itemsInAPage = getItemsInAPage();

		int totalCnt = articleRepository.getArticlesTotalCount();
		int totalPage = (int) Math.ceil((double) totalCnt / itemsInAPage);
		return totalPage;
	}

	public List<Integer> getBoardTotalPage() {
		int itemsInAPage = getItemsInAPage();

		List<Integer> boardTotalCnt = articleRepository.getBoardTotalCount();
		List<Integer> boardTotalPage = new ArrayList<>();
		boardTotalPage.add((int) Math.ceil((double) boardTotalCnt.get(0) / itemsInAPage));
		boardTotalPage.add((int) Math.ceil((double) boardTotalCnt.get(1) / itemsInAPage));
		boardTotalPage.add((int) Math.ceil((double) boardTotalCnt.get(2) / itemsInAPage));

		return boardTotalPage;
	}

}
