package com.grady.mychat.runner;

import com.grady.mychat.config.WeChatConfig;
import com.grady.mychat.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: mychat
 * @description: runner
 * @author: luchangjiang
 * @create: 2019-03-05 08:42
 **/
@Configuration
@Slf4j
public class WeChatRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        /*try {
            WeChatUtil.refreshAccessToken();
            log.info("初始化accessToekn值：{}",WeChatConfig.accessToken);
        }catch(Exception e){
            log.error("初始化accessToekn失败!",e);
        }*/
    }
    @Scheduled(initialDelay = 1000, fixedRate = 30*60*1000)
    public void refreshAccessToken(){
        WeChatUtil.refreshAccessToken();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        log.info("刷新accessToekn值：{}，刷新时间：{}",WeChatConfig.accessToken, df.format(new Date()));
    }
}
