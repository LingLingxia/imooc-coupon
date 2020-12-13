package com.imooc.coupon.converter;

import com.imooc.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
//<x,y> => <实体属性类型，数据库类型>
//优惠券分类枚举属性转换器
public class CouponCategoryConverter implements AttributeConverter<CouponCategory,String> {

    //将实体属性X转换为Y存储到数据库中，插入更新时执行
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    //查询时执行，将Y转换为实体属性X
    @Override
    public CouponCategory convertToEntityAttribute(String s) {
        return CouponCategory.of(s);
    }
}
