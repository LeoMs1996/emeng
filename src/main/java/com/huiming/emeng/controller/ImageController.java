package com.huiming.emeng.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huiming.emeng.bo.ValidateImageBO;
import com.huiming.emeng.common.ValidateCodeUtil;

/**
 * 测试  -- 验证码
 * 
 * @author Jack
 * @date 2017年5月15日
 */
@Controller
@RequestMapping("/image")
public class ImageController {

	
	
	@RequestMapping("/getRandNum")
	public void getRandNum(HttpServletRequest request,
									 HttpServletResponse response) {
		try {	
			
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires", 0);			
			ServletOutputStream os = response.getOutputStream();
			
			ValidateImageBO validateBO = ValidateCodeUtil.getValidateBO();

			request.getSession().setAttribute("validateCode",validateBO.getValue()+"");	
			if(ImageIO.write(validateBO.getImg(), "JPEG", os)) {
				response.flushBuffer(); 
                os.flush();    
                os.close();
                os=null;
			}
			System.out.println(validateBO.getValue());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
