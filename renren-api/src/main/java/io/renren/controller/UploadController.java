package io.renren.controller;

import io.renren.common.exception.ErrorCode;
import io.renren.common.oss.cloud.OSSFactory;
import io.renren.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
@Api(tags="图片上传接口")
public class UploadController {
    
  @PostMapping("updateimg")
  @ApiOperation("文件上传")
	public Result<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
		}

		//上传文件
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		//拿到文件存储地址
		String url = OSSFactory.build().uploadSuffix(file.getBytes(), extension);

		Map<String, Object> data = new HashMap<>(1);
		data.put("file", url);

		return new Result<Map<String, Object>>().ok(data);
	}

}