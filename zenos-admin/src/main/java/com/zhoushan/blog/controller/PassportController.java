package com.zhoushan.blog.controller;

import com.zhoushan.blog.business.annotation.BusinessLog;
import com.zhoushan.blog.business.service.SysUserService;
import com.zhoushan.blog.framework.property.AppProperties;
import com.zhoushan.blog.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhooo
 * @version V1.0
 * @Title: PassportController
 * @Package com.zhoushan.blog.controller
 * @Description: TODO
 * @date 7/12/2019 10:52 PM
 */
@Slf4j
@Controller
@RequestMapping(value = "/passport")
public class PassportController {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private SysUserService sysUserService;

    @BusinessLog("进入登录界面")
    @GetMapping("/login")
    public ModelAndView login(Model model) {
        model.addAttribute("enableKaptcha", appProperties.enableKaptcha);
        return ResultUtil.view("/login");
    }
}
