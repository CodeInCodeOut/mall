package com.dayuanit.shop.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dayuanit.shop.utils.PicCodeUtil;


@Controller
@RequestMapping("/code")
public class ImageCodeController extends BaseController {
	
	@RequestMapping("/create")
	public void createImge(HttpServletRequest req, HttpServletResponse resp) {
		String code = PicCodeUtil.createCode(4);
		req.getSession().setAttribute("code", code);
		BufferedImage bimg = PicCodeUtil.creatImage(code);
		OutputStream os = null;
		try {
			resp.setContentType("image/jpeg");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			
			os = resp.getOutputStream();
			ImageIO.write(bimg, "png", os);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
