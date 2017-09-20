package com.dayuanit.shop.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PicCodeUtil {

	private static final String[] CODE_SOURCE = new String[] { "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y" };

	// 图片的宽度。
	private static int width = 160;
	// 图片的高度。
	private static int height = 40;
	// 验证码字符个数
	private static int codeCount = 4;
	// 验证码干扰线数
	private static int lineCount = 20;

	// 生成图片
	public static BufferedImage creatImage(String code) {
		int fontWidth = width / codeCount;// 字体的宽度
		int fontHeight = height - 5;// 字体的高度
		int codeY = height - 8;

		// 图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		// Graphics2D g = buffImg.createGraphics();
		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设置字体
		// Font font1 = getFont(fontHeight);
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		g.setFont(font);

		Random random = new Random();

		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		for (int i = 0; i < codeCount; i++) {
			String strRand = code.substring(i, i + 1);
			g.setColor(getRandColor(1, 255));
			// g.drawString(a,x,y);
			// a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
			g.drawString(strRand, i * fontWidth + 3, codeY);
		}
		
		return buffImg;
	}

	// 得到随机颜色
	private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255) {
			fc = 255;
		}

		if (bc > 255) {
			bc = 255;
		}

		Random random = new Random();

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

//	public static void write(OutputStream sos) throws IOException {
//		ImageIO.write(buffImg, "png", sos);
//	}
//
//	public BufferedImage getBuffImg() {
//		return buffImg;
//	}

	public static String createCode(int count) {
		Random random = new Random();
		String code = "";
		for (int i = 0; i < count; i++) {
			int index = random.nextInt(CODE_SOURCE.length);
			code += CODE_SOURCE[index];
		}
		System.out.println("code=" + code);
		return code;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			PicCodeUtil.createCode(4);
		}
	}

}
