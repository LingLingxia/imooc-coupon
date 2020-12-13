package com.imooc.coupon.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.coupon.constant.CouponCategory;
import com.imooc.coupon.constant.DistributeTarget;
import com.imooc.coupon.constant.ProductLine;
import com.imooc.coupon.converter.CouponCategoryConverter;
import com.imooc.coupon.converter.DistributeTargetConverter;
import com.imooc.coupon.converter.ProductLineConverter;
import com.imooc.coupon.entity.serialization.CouponTemplateSerialize;
import com.imooc.coupon.vo.TemplateRule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//优惠券模板实体类定义 基础属性，规则属性
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="coupon_template")
//传给前端的序列化数据
@JsonSerialize(using = CouponTemplateSerialize.class)
public class CouponTemplate implements Serializable {

    //自增组件
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
  //  @Transient   默认是Basic的，加了@transient表示不要映射到数据表中
    private Integer id;

    @Column(name = "available",nullable = false)
    private Boolean available;

    @Column(name = "expired",nullable = false)
    private Boolean expired;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "logo",nullable = false)
    private String logo;

    //desc是数据库关键字不可以指定为类的名称
    @Column(name = "intro",nullable = false)
    private String desc;

    //优惠券分类
    @Column(name = "category",nullable = false)
    @Convert(converter = CouponCategoryConverter.class)
    private CouponCategory category;

    @Convert(converter = ProductLineConverter.class)
    @Column(name = "product_line",nullable = false)
    private ProductLine productLine;

    @Column(name = "coupon_count",nullable = false)
    private Integer count;



    //@EnableJpaAuditing   @EntityListeners(AuditingEntityListener.class)  自动生成维护
    //类似的注解有 @UpdatedTimestamp @CreatedBy @updatedBy
    @CreatedDate
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    //优惠券模板的编码
    @Column(name = "template_key",nullable = false)
    private String key;

    @Convert(converter = DistributeTargetConverter.class)
    @Column(name = "target",nullable = false)
    private DistributeTarget target;

    //之后实现从枚举类到数据库字段转换的方法
    @Column(name = "rule",nullable = false)
    @Convert(converter = TemplateRule.class)
    private TemplateRule rule;

    //需要填充的字段就出现在构造函数中

    public CouponTemplate(String name, String logo, String desc, String category,
                          Integer productLine, Integer count, Long userId, Integer target,
                          TemplateRule rule) {
        this.available = false;
        this.expired = false;
        this.name = name;
        this.logo = logo;
        this.desc = desc;
        this.category = CouponCategory.of(category);
        this.productLine = ProductLine.of(productLine);
        this.count = count;
        this.userId = userId;
        //优惠券唯一编码 = 4(产品线和类型) + 8(日期:20200101) + id(扩充为4位）
        this.key = productLine.toString()+category +
                new SimpleDateFormat("yyyMMdd").format(new Date());

        this.target = DistributeTarget.of(target);
        this.rule = rule;
    }
}
