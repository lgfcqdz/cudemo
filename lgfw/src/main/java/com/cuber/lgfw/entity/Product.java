package com.cuber.lgfw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String subtitle;
    private String description;
    private String detailHtml;

    private Long categoryId;
    private Long brandId;

    private BigDecimal price;
    private BigDecimal originalPrice;
    private BigDecimal costPrice;
    private BigDecimal weight;
    private BigDecimal volume;

    private Integer stockQuantity;
    private Integer lockedStock;
    private Integer soldQuantity;
    private Integer warningStock;

    private String sku;
    private String barcode;
    private String spu;

    private String mainImage;
    private String imageGallery; // JSON格式

    private Integer status;
    private Integer isHot;
    private Integer isNew;
    private Integer isRecommend;

    private Integer viewCount;
    private Integer reviewCount;
    private BigDecimal rating;
    private Integer salesVolume;

    private Long shippingTemplateId;
    private Integer isFreeShipping;

    private LocalDateTime publishTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}
