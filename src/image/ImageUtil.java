package image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtil {

    /**
     * 画像を読み込む
     *
     * @param filename ファイル名
     * @return BufferedImage型の画像
     */
    public static BufferedImage read(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(0, 0, 0);
    }

    /**
     * 画像を書き出す
     *
     * @param bi       BufferedImage型の画像
     * @param filename ファイル名
     */
    public static void write(BufferedImage bi, String filename) {
        try {
            String extension = filename.substring(filename.lastIndexOf(".") + 1);
            System.out.println(extension);
            ImageIO.write(bi, extension, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定したインデックスのRGB値を取得する
     *
     * @param bi BufferedImage型の画像
     * @param x  xインデックス
     * @param y  yインデックス
     * @return RGB値
     */
    public static int[] getRGB(BufferedImage bi, int x, int y) {
        int pixel = bi.getRGB(x, y);
        int red = pixel >> 16 & 0xff;
        int green = pixel >> 8 & 0xff;
        int blue = pixel & 0xff;
        return new int[]{red, green, blue};
    }

    /**
     * 指定したインデックスのARGB値を取得する
     *
     * @param bi BufferedImage型の画像
     * @param x  xインデックス
     * @param y  yインデックス
     * @return ARGB値
     */
    public static int[] getARGB(BufferedImage bi, int x, int y) {
        int pixel = bi.getRGB(x, y);
        int alpha = pixel >>> 24;
        int red = pixel >> 16 & 0xff;
        int green = pixel >> 8 & 0xff;
        int blue = pixel & 0xff;
        return new int[]{alpha, red, green, blue};
    }

    /**
     * 指定したインデックスにRGB値を設定する
     *
     * @param bi  BufferedImage型の画像
     * @param x   xインデックス
     * @param y   yインデックス
     * @param rgb RGB値
     */
    public static void setRGB(BufferedImage bi, int x, int y, int[] rgb) {
        int pixel = 0xff000000 | rgb[0] << 16 | rgb[1] << 8 | rgb[2];
        bi.setRGB(x, y, pixel);
    }

    /**
     * 指定したインデックスにARGB値を設定する
     *
     * @param bi   BufferedImage型の画像
     * @param x    xインデックス
     * @param y    yインデックス
     * @param argb ARGB値
     */
    public static void setARGB(BufferedImage bi, int x, int y, int[] argb) {
        int pixel = argb[0] << 24 | argb[1] << 16 | argb[2] << 8 | argb[3];
        bi.setRGB(x, y, pixel);
    }

    /**
     * 画像を表示する
     *
     * @param bi BufferedImage型の画像
     */
    public static void show(BufferedImage bi) {
        new Viewer(bi);
    }

    /**
     * 画像表示用のフレーム
     */
    private static class Viewer extends JFrame {
        /**
         * 表示する画像
         */
        BufferedImage bi;

        /**
         * コンストラクタ
         *
         * @param bi BufferedImage型の画像
         */
        public Viewer(BufferedImage bi) {
            this.bi = bi;
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(bi.getWidth(), bi.getHeight());
            setVisible(true);
            setResizable(false);
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(bi, 0, 0, null);
        }
    }
}