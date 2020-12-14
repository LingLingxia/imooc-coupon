package com.imooc.coupon.dao;

import com.imooc.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CouponTemplateDao
    extends JpaRepository<CouponTemplate,Integer> {
    //根据模板名称查询模板
    CouponTemplate findByName(String name);

    //根据available 和 expired 查找模板记录  ,可用并且过期？？
    List<CouponTemplate> findAllByAvailableAndExpired(
            Boolean avaliable,
            Boolean expired
    );
    List<CouponTemplate> findAllByExpired(Boolean expired);
}
