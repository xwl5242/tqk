package com.quanchong.dataoke.job;

import com.quanchong.common.entity.service.DTKGood;
import com.quanchong.dataoke.service.DTKGoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StartUpRunner implements CommandLineRunner {

    @Autowired
    private DTKGoodService dtkGoodService;

    @Override
    public void run(String... args) throws Exception {
        List<DTKGood> list = dtkGoodService.gatherGoods();
        dtkGoodService.saveBatch(list);
    }
}
