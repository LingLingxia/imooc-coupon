package com.imooc.coupon.vo;


import com.imooc.coupon.constant.CouponCategory;
import com.imooc.coupon.constant.DistributeTarget;
import com.imooc.coupon.constant.ProductLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

//优惠券模板，创建请求对象
//感觉这种不为空的参数也是 有直接用注解校验的
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRequest {
    private String name;
    private String logo;
    private String desc;
    private String category;
    private Integer productLine;
    private Long count;
    //创建用户
    private Long userId;
    private Integer target;
    private TemplateRule rule;
    //校验对象的合法性
    public boolean validate(){
        boolean stringValid = StringUtils.isNotEmpty(name)
                && StringUtils.isNotEmpty(logo)
                && StringUtils.isNotEmpty(desc);
        boolean enumValid = null != CouponCategory.of(category)
                && null != ProductLine.of(productLine)
                && null != DistributeTarget.of(target);
        boolean numValid = count > 0&& userId >0;
        return stringValid&&enumValid&&numValid&&rule.validate();
    }
}

