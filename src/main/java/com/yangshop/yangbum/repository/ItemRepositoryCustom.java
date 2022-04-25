package com.yangshop.yangbum.repository;

import com.yangshop.yangbum.dto.ItemSearchDto;
import com.yangshop.yangbum.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.yangshop.yangbum.dto.MainItemDto;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}