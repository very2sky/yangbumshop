package com.yangshop.yangbum.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yangshop.yangbum.constant.ItemSellStatus;
import com.yangshop.yangbum.entity.Item;
import com.yangshop.yangbum.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;
    @PersistenceContext
    EntityManager em;
    @Test
    @DisplayName("item_savetest")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("test item");
        item.setPrice(10000);
        item.setItemDetail("explain test stuck definitly");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());

        item.setUpdateTime(LocalDateTime.now());

        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList(){
        for(int i=1;i<=10;i++){
            Item item = new Item();
            item.setItemNm("test stock" + i);
            item.setPrice(10000+i);
            item.setItemDetail("test stock detail explain"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("?????? ?????? test")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("???????????????1");
        for(Item item :itemList){
            this.createItemList();

        }
    }

    @Test
    @DisplayName("?????????, ?????????????????? or ?????????")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList =
                itemRepository.findByItemNmOrItemDetail("test thing 1", "about more detail");
        for(Item item: itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("price less than test")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item: itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("price orderby desc test")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item item: itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery ????????? ????????? ?????? ???????????????")
    public void findByItemDetailByNative(){
        this.createItemList();
        List<Item> itemList= itemRepository.findByItemDetailByNative("????????? ????????????");
        for(Item item: itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("Querydsl ??????test")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem =QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "???????????????????????????"+"%"))
                .orderBy(qItem.price.desc());
        List<Item> itemList = query.fetch();
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    public void createItemList2(){
        for(int i=1;i<=5;i++){
            Item item = new Item();
            item.setItemNm("test stock" + i);
            item.setPrice(10000+i);
            item.setItemDetail("test stock detail explain"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
        for(int i=1;i<=5;i++){
            Item item = new Item();
            item.setItemNm("test stock" + i);
            item.setPrice(10000+i);
            item.setItemDetail("test stock detail explain"+i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("?????? querydsl ?????? ????????? 2")
    public void queryDslTest2(){
        this.createItemList2();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;

        String itemDetail = "test stock detail explaination";
        int price = 10003;
        String itemSellStat= "SELL";

        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0,5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements:" + itemPagingResult. getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem: resultItemList){
            System.out.println(resultItem.toString());
        }
    }
}