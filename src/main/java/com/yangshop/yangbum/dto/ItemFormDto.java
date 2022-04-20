package com.yangshop.yangbum.dto;


import com.yangshop.yangbum.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.yangshop.yangbum.entity.QItem.item;

@Getter@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message="상품명 입력")
    private String itemNm;

    @NotNull(message="가격 입력")
    private Integer price;

    @NotBlank(message = "이름")
    private String itemDetail;

    @NotNull(message= "재 고 입력")
    private Integer stockNumber;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }
}
