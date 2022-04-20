package com.yangshop.yangbum.repository;

import com.yangshop.yangbum.entity.Item;
import com.yangshop.yangbum.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> , QuerydslPredicateExecutor<Item>,
ItemRepositoryCustom{
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
