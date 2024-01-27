package io.renren.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.cf.annotation.PreventRepeat;
import io.renren.common.common.CommonService;
import io.renren.common.dto.*;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;
import io.renren.common.modules.circleuser.service.CircleUserService;
import io.renren.common.modules.flowRecord.entity.FlowRecordEntity;
import io.renren.common.modules.flowRecord.service.FlowRecordService;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import io.renren.common.modules.report.dto.ReportDTO;
import io.renren.common.modules.token.entity.TokenEntity;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@Api(tags="举报接口")
@CrossOrigin
public class ReportController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CircleService circleService;
    @Autowired
    private CircleUserService circleUserService;
    @Autowired
    private FlowRecordService recordService;


    @Login
    @PostMapping("addReport")
    @ApiOperation(value="举报")
    public Result addReport(@LoginUser UserEntity userEntity, @RequestBody AddReportDTO pagesUserForm){
        //表单校验
        ValidatorUtils.validateEntity(pagesUserForm);

        return commonService.addReport(userEntity,pagesUserForm);
    }
}
