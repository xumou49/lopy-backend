package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.constant.StripeConstant;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.WebhookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Webhook API")
@RequestMapping(CommonConstant.API.V1_PATH + "/webhook")
@RestController
public class WebhookController {

    @Autowired
    private WebhookService webhookService;

    @PostMapping("/stripe")
    public RespVO<Void> handleStripeWebhook(HttpServletRequest request, @RequestBody String payload) {
        webhookService.handleStripeEvent(payload, request.getHeader(StripeConstant.SIGNATURE_HEADER));
        return RespVO.ok();
    }
}
