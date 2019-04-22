package com.example.demo.page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wangbin
 */
@Controller
@Api(tags = "页面")
@ApiIgnore
public class PageController {
    @ApiOperation("主页")
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/page/user")
    public ModelAndView userPage() {
        return new ModelAndView("user");
    }

    @GetMapping("/page/group")
    public ModelAndView groupPage() {
        return new ModelAndView("group");
    }

    @GetMapping("/page/process")
    public ModelAndView processPage() {
        return new ModelAndView("process");
    }

    @GetMapping("/page/task")
    public ModelAndView taskPage() {
        return new ModelAndView("task");
    }
}
