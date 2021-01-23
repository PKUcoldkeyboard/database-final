package com.cuterwrite.dbfinal.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.cuterwrite.dbfinal.dao.BookDAO;
import com.cuterwrite.dbfinal.dao.CategoryDAO;
import com.cuterwrite.dbfinal.elasticsearch.BookRepository;
import com.cuterwrite.dbfinal.entity.Book;
import com.cuterwrite.dbfinal.entity.EsBook;
import com.cuterwrite.dbfinal.service.BookSearchService;

import cn.hutool.core.util.StrUtil;



/**  
 * @author Pang S.Z.
 * @create 2021-01-22 15:19:08 
 */
@Service
public class BookSearchServiceImpl implements BookSearchService{
	
	@Autowired
	BookDAO bookDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ElasticsearchRestTemplate elasticsearchRestTemplate;
	@Autowired
	BookRepository repository;

	@Override
	public int importAll() {
		List<EsBook>esBooks=new ArrayList<>();
		List<Book>books=bookDAO.selectAll();
		for(Book book:books) {
			EsBook esBook=new EsBook();
			esBook.setAuthor(book.getAuthor());
			esBook.setBookName(book.getName());
			esBook.setDescription(book.getDescription());
			esBook.setPic(book.getPicture());
			esBook.setIsbn(book.getIsbn());
			esBook.setPress(book.getPress());
			esBook.setPublishDate(book.getPublishDate());
			esBook.setId(book.getId());
			esBook.setSale(book.getSale());
			esBook.setPrice(book.getPrice());
			Long category=book.getCategoryId();
			String categoryName=categoryDAO.selectByPrimaryKey(category).getName();
			esBook.setCategoryName(categoryName);
			esBooks.add(esBook);
		}
		Iterable<EsBook>iterable=repository.saveAll(esBooks);
		Iterator<EsBook>iterator=iterable.iterator();
		int result=0;
		while(iterator.hasNext()) {
			result++;
			iterator.next();
		}
		return result;
		
	}

	@Override
	public EsBook create(Long id) {
		EsBook esBook = new EsBook();
		Book book = bookDAO.selectByPrimaryKey(id);
		esBook.setAuthor(book.getAuthor());
		esBook.setBookName(book.getName());
		esBook.setDescription(book.getDescription());
		esBook.setPic(book.getPicture());
		esBook.setIsbn(book.getIsbn());
		esBook.setPress(book.getPress());
		esBook.setPublishDate(book.getPublishDate());
		esBook.setId(book.getId());
		esBook.setSale(book.getSale());
		esBook.setPrice(book.getPrice());
		Long category=book.getCategoryId();
		String categoryName=categoryDAO.selectByPrimaryKey(category).getName();
		esBook.setCategoryName(categoryName);
		return repository.save(esBook);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		if(ids.length>0) {
			List<EsBook>esBooks=new ArrayList<>();
			for(Long id:ids) {
				EsBook esBook=new EsBook();
				esBook.setId(id);
				esBooks.add(esBook);
			}
			repository.deleteAll(esBooks);
		}
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();		
	}

	@Override
	public Page<EsBook> search(String keyword,String categoryName, Integer pageNum, Integer pageSize, Integer sort) {
		Pageable pageable=PageRequest.of(pageNum, pageSize);
		NativeSearchQueryBuilder builder=new NativeSearchQueryBuilder();
		//分页
		builder.withPageable(pageable);
		//过滤
		if(!categoryName.equals("全部")) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			boolQueryBuilder.must(QueryBuilders.termQuery("categoryName.keyword", categoryName));
			builder.withFilter(boolQueryBuilder);
		}
		//搜索
		if (StrUtil.isEmpty(keyword)) {
			builder.withQuery(QueryBuilders.matchAllQuery());
		} else {
			List<FunctionScoreQueryBuilder.FilterFunctionBuilder>filterFunctionBuilders=new ArrayList<>();
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("description",keyword),
					ScoreFunctionBuilders.weightFactorFunction(1)));
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("author",keyword),
					ScoreFunctionBuilders.weightFactorFunction(2)));
			filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("bookName",keyword),
					ScoreFunctionBuilders.weightFactorFunction(10)));
			FunctionScoreQueryBuilder.FilterFunctionBuilder[]builders=new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
			
			filterFunctionBuilders.toArray(builders);
			
			FunctionScoreQueryBuilder functionScoreQueryBuilder=QueryBuilders.functionScoreQuery(builders)
					.scoreMode(FunctionScoreQuery.ScoreMode.SUM)
					.setMinScore(2);
			
			builder.withQuery(functionScoreQueryBuilder);
		}
		//排序
		if(sort==1){
            //按新品从新到旧
			builder.withSort(SortBuilders.fieldSort("publishDate").order(SortOrder.DESC));
        }else if(sort==2){
            //按销量从高到低
            builder.withSort(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        }else if(sort==3){
            //按价格从低到高
            builder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else if(sort==4){
            //按价格从高到低
            builder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }else if(sort==5){
            //按相关度
            builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
		NativeSearchQuery searchQuery = builder.build();
		SearchHits<EsBook>searchHits=elasticsearchRestTemplate.search(searchQuery, EsBook.class);
		if(searchHits.getTotalHits()<=0) {
			return new PageImpl<>(new ArrayList<EsBook>(),pageable,0);
		}
		List<EsBook> searchBookList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
		return new PageImpl<>(searchBookList,pageable,searchHits.getTotalHits());
	}

	

}
