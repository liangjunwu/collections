package com.ljw.ssme.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
 
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 图片验证码工具类
 * 验证码显示使用 CaptchaUtil.create(response); 
 * 验证码校对使用 CaptchaUtil.verify(xxVo.getCaptcha());
 */
public class CaptchaUtil {
 
    protected static final Logger logger = Logger.getLogger(CaptchaUtil.class);
     
    private static final ThreadLocal<Random> threadLocalRandom = new ThreadLocal<Random>() {
 
        @Override
        protected Random initialValue() {
            return new Random();
        }
 
    };
 
    private static final String CAPTCHA_SESSION_KEY = "captcha";
 
    private static final char[] captchaChars = new char[]{'2', '3', '4', '5', '7', '8', '2', '3', '4', '5', '7', '8',
        'a', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm', 'n', 'p', 's', 't', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
     
    private static final String[] fonts = new String[]{"Serif", "Sanserif", "Monospace", "Dialog", "DialogInput"};
     
    private static final int[] styles = new int[]{Font.BOLD, Font.PLAIN , Font.ITALIC, Font.ROMAN_BASELINE};
     
    private static final Color bgColor = Color.WHITE;
 
    public static void create(HttpServletResponse response) throws IOException {
        Random random = threadLocalRandom.get();
 
        String captcha = "";
        for (int i = 0, length = 4; i < length; i++) {
            captcha += captchaChars[random.nextInt(captchaChars.length)];
        }
 
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_SESSION_KEY, MD5.hashToHexString(captcha.toLowerCase()));
 
        BufferedImage bi = new BufferedImage(120, 55, BufferedImage.TYPE_INT_ARGB);
        drawCaptcha(bi, captcha);
        fast_shear(bi);
 
        response.setContentType("image/png");
        OutputStream out;
        out = response.getOutputStream();
        ImageIO.write(bi, "png", out);
        out.close();
    }
 
    private static void drawCaptcha(BufferedImage bi, String captcha) {
        Random random = threadLocalRandom.get();
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(bgColor);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.setColor(Color.BLACK);
 
        int length = captcha.length();
        float sx = 12 + random.nextInt(21);
        float sy = 34 + random.nextInt(11);
 
        FontRenderContext frc = g.getFontRenderContext();
        Area area = new Area();
        for (int i = 0; i < length; i++) {
            int t = 36 + random.nextInt(3);
            Font font = new Font(fonts[random.nextInt(fonts.length)], styles[random.nextInt(styles.length)], t);
            GlyphVector glyphVector = font.createGlyphVector(frc, captcha.substring(i, i + 1));
            Shape glyph = glyphVector.getGlyphOutline(0, sx, sy);
            area.add(new Area(glyph));
            double w = glyph.getBounds2D().getMaxX() - glyph.getBounds2D().getMinX();
            sx += w - (w / 5.2);
        }
        g.fill(area);
        g.dispose();
    }
 
    private static void fast_shear(BufferedImage bi) {
        Random random = threadLocalRandom.get();
 
        Graphics2D g = bi.createGraphics();
        int w = bi.getWidth();
        int h = bi.getHeight();
 
        int ox = 10 + random.nextInt(11);
        int oy = 10 + random.nextInt(11);
        double sx = Math.PI * 2 * random.nextDouble() * 100;
        double sy = Math.PI * 2 * random.nextDouble() * 100;
        double rx = 0.5 + random.nextDouble() * 1.0;
        double rt = 1.5 - rx;
        double ry = rt / 3 + random.nextDouble() * rt / 3 * 2;
        double cx = w / rx / Math.PI / 2;
        double cy = h / ry / Math.PI / 2;
 
        for (int i = 0; i < h; i++) {
            double d = (double) (ox >> 1) * Math.sin((double) i / (double) cx + sx);
            g.copyArea(0, i, w, 1, (int) d, 0);
            g.setColor(bgColor);
            g.drawLine((int) d, i, 0, i);
            g.drawLine((int) d + w, i, w, i);
        }
        for (int i = 0; i <= w; i++) {
            double d = (double) (oy >> 1) * Math.sin((double) i / (double) cy + sy);
            g.copyArea(i, 0, 1, h, 0, (int) d);
            g.setColor(bgColor);
            g.drawLine(i, (int) d, i, 0);
            g.drawLine(i, (int) d + h, i, h);
        }
        g.dispose();
    }
 
    public static boolean verify(String captcha) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String value = (String) session.getAttribute(CAPTCHA_SESSION_KEY);
        if (value != null) {
            if (value.equals(MD5.hashToHexString(captcha.toLowerCase()))) {
                return true;
            }
        }
        return false;
    }
}
