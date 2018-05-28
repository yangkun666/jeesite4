package com.miaocup.modules.dealer.web;

import com.google.common.collect.Lists;
import com.jeesite.common.entity.Page;
import com.miaocup.modules.dealer.entity.Dealer;
import com.miaocup.modules.dealer.entity.DealerDto;
import com.miaocup.modules.dealer.service.DealerDtoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("${adminPath}/dealer")
public class DealerDtoController {

    @Autowired
    private DealerDtoService dealerDtoService;

    /**
     * 获取数据
     */
    @ModelAttribute
    public DealerDto get(String id, boolean isNewRecord) {
        return new DealerDto();
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("biz:dealer:view")
    @RequestMapping(value = {"list", ""})
    public String list(DealerDto dealerDto, Model model) {
        model.addAttribute("dealerDto", dealerDto);
        return "modules/dealer/dealerList";
    }

    /**
     * 查询列表数据
     */
    @RequiresPermissions("biz:dealer:view")
    @RequestMapping(value = "listData",produces = "application/json")
    @ResponseBody
    public Page<Dealer> listData(DealerDto dealerDto, HttpServletRequest request, HttpServletResponse response) {
        Page<DealerDto> page = dealerDtoService.findPage(new Page<>(request, response), dealerDto);
        List<Dealer> dealerList = Lists.newArrayList();
        page.getList().forEach(dealerDto1 -> {
            Dealer dealer = new Dealer();
            BeanUtils.copyProperties(dealerDto1, dealer);
            dealerList.add(dealer);
        });
        Page<Dealer> dealerPage = new Page<>();
        dealerPage.setList(dealerList);
        dealerPage.setCount(page.getCount());
        dealerPage.setPageNo(page.getPageNo());
        dealerPage.setPageSize(page.getPageSize());
        return dealerPage;
    }

}
