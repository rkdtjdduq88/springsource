package com.spring.memo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Item;
import com.spring.memo.entity.ItemSellStatus;

@SpringBootTest
public class ItemRepositoryTest {
	
	// ItemRepository 잘 작성되었는지 테스트 : 단위 테스트
	@Autowired
	private ItemRepository repository;
	
	@Test
	public void itemCreateTest() {		
		Item item = new Item();
		item.setItemNm("순수 프리미엄");
		item.setPrice(29500);
		item.setStockNumber(55);
		item.setItemDetail("깨끗한 나라");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		repository.save(item);
		
		item = Item.builder()
						.itemNm("디아블로4")
						.price(90000)
						.stockNumber(666)
						.itemDetail("사양높음")
						.itemSellStatus(ItemSellStatus.SELL)
						.regTime(LocalDateTime.now())
						.updateTime(LocalDateTime.now())
						.build();
		
		Item newItem = repository.save(item);
		
		System.out.println(newItem);
	}
	
	// 조회
//	@Test
//	public void getItem() {
		
//		Optional<Item> item = repository.findById(1L);		
//		item.ifPresent(ele -> System.out.println(ele));
		
//		repository.findById(1L).ifPresent(ele -> System.out.println(ele));
//		
//		Item item = repository.findById(4L).orElseThrow(EntityNotFoundException::new);
//		System.out.println(item);
//	}
	
//	@Test
//	public void getItems() {
//			
//		List<Item> item = repository.findAll();
////		for (Item item2 : item) {
////			System.out.println(item2);
////		}		
//		
//		
//		item.forEach(item2 -> System.out.println(item2));
//	}
	
	// 상품명 조회
//	@Test
//	public void getItems() {
//			
////		List<Item> item = repository.findByItemNm("블루투스 스피커");
////		item.forEach(item2 -> System.out.println(item2));
//		
//		repository.findByItemNm("블루투스 스피커").forEach(item2 -> System.out.println(item2));
//	}
	
	// 상품명 or 상품상세 조회
//	@Test
//	public void getNameOrDetail() {
//		List<Item> list = repository.findByItemNmOrItemDetail("블루투스 스피커", "카라티");
//		list.forEach(item -> System.out.println(item));
//	}
	
	// 특정가격 이하인 상품조회
//	@Test
//	public void getPriceLsettThen() {		
//		repository.findByPriceLessThan(20000).forEach(item -> System.out.println(item));
//	}
	
	// 특정가격 이하인 상품조회를 내림차순으로 정렬	
//	@Test
//	public void getPriceLessThanOrder() {
//		repository.findByPriceLessThanOrderByPriceDesc(50000)
//				  .forEach(item -> System.out.println(item));
//	}
}




















