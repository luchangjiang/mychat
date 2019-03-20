package com.grady.mychat.service;

import com.grady.mychat.common.msg.RestResponse;
import com.grady.mychat.model.WeiXinUser;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

/**
 * @program: mychat
 * @description: template message service
 * @author: luchangjiang
 * @create: 2019-03-05 16:20
 **/
public interface TemplateMessageService {
    RestResponse setIndustry(int id1, int id2);

    RestResponse getIndustry();

    RestResponse getAllPrivateTemplates();

    RestResponse getTemplateById(String templateId);

    RestResponse delTemplateById(String templateId);

    RestResponse sendTemplateMessage(String message);

}
