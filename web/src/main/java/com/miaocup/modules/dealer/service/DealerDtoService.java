package com.miaocup.modules.dealer.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.miaocup.modules.dealer.dao.DealerDtoDao;
import com.miaocup.modules.dealer.entity.DealerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerDtoService extends CrudService<DealerDtoDao, DealerDto> {

    @Override
    public Page<DealerDto> findPage(Page<DealerDto> page, DealerDto dealerDto) {
        return super.findPage(page, dealerDto);
    }

}
