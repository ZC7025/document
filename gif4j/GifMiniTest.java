import com.gif4j.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test {

    /**
     * 将gif动图重新调整大小并且输出
     * @param src 源文件
     * @param dest 生成文件
     * @param width 生成文件的宽度
     * @param height 生成文件的高度
     * @throws IOException
     */
    public static void gifReSize(File src, File dest, int width, int height) throws IOException {
        GifImage srcImage = GifDecoder.decode(src);
        //主要调用的就是GifTransformer种的resize方法进行图片尺寸的调整
        GifImage resizeImage = GifTransformer.resize(srcImage, width, height, true);
        GifEncoder.encode(resizeImage, dest);
    }

    /**
     * 将gif动图重新调整大小并且输出
     * @param src 源文件
     * @param dest 生成文件
     * @param wQuotiety 调整的比例，比如80%就是0.8
     * @param hQuotiety 调整的比例，比如80%就是0.8
     * @throws IOException
     */
    public static void gifScale(File src, File dest, double wQuotiety, double hQuotiety) throws IOException {
        GifImage srcImage = GifDecoder.decode(src);
        GifImage scaleImg = GifTransformer.scale(srcImage, wQuotiety, hQuotiety, true);
        GifEncoder.encode(scaleImg, dest);
    }

    /**
     * 对gif图剪切，参数是坐标和宽高
     */
    public static void getGifImage(File srcImg, File destImg, int width,
                                   int height, boolean smooth) {
        try {
            GifImage gifImage = GifDecoder.decode(srcImg);// 创建一个GifImage对象
            GifImage resizedGifImage2 = GifTransformer.resize(gifImage, width, height, smooth);//1.缩放重新更改大小.
            GifEncoder.encode(resizedGifImage2, destImg,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 加水印，参数是文字
     */
    public static void shuiyin(File src, File dest, String text) throws IOException  {
        GifImage srcImg = GifDecoder.decode(src);
        TextPainter textPainter = new TextPainter(new Font("微软雅黑", Font.BOLD, 12));
        textPainter.setOutlinePaint(Color.WHITE);
        BufferedImage renderedWatermarkText = textPainter.renderString(text, true);
        Watermark watermark = new Watermark(renderedWatermarkText, Watermark.LAYOUT_TOP_LEFT);
        GifImage applyImg = watermark.apply(srcImg, true);
        GifEncoder.encode(applyImg, dest);
    }

}
