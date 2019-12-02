package com.wcg.controller;

import com.wcg.util.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/*后台思路很简单，利用BufferedImage类创建一张图片，
 再用Graphics2D对图片进行绘制(生成随机字符，添加噪点，干扰线)即可。
 生成的验证码字符串要放到session中，
 用于接下来登陆的验证码验证(当然也是后台)。*/
@Controller
public class PhotoController {



 /* 获取验证码图片*/

 @RequestMapping("/getVerifyCode")
 public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {

  try {

   int width=200;

   int height=69;

   BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

//生成对应宽高的初始图片

   String randomText = VerifyCode.drawRandomText(width,height,verifyImg);

//单独的一个类方法，出于代码复用考虑，进行了封装。

//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符

   request.getSession().setAttribute("verifyCode", randomText);//验证码存储到session

   response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别

   OutputStream os = response.getOutputStream(); //获取文件输出流

   ImageIO.write(verifyImg,"png",os);//输出图片流

   os.flush();

   os.close();//关闭流

  } catch (IOException e) {

/*   this.logger.error(e.getMessage());*/
   e.printStackTrace();

  }

 }
}
