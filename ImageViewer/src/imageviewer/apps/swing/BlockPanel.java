package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BlockPanel extends JPanel implements ImageDisplay{
    private Image image;
    private BufferedImage bufferedImage; 

    public BlockPanel() {
    }
    
    @Override
    public void paint(Graphics g) {
        Box box = new Box(bufferedImage);
        g.drawImage(bufferedImage, box.x, box.y, box.width, box.height, null);
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.bufferedImage = load(image.getName());
        repaint();
    }

    @Override
    public Image current() {
        return image;
    }
    
    private class Box {
        final int x;
        final int y;
        final int width;
        final int height;
        private final int px;
        private final int py;

        public Box(BufferedImage image) {
            this.px = getWidth();
            this.py = getHeight();
            this.width = calculateWidth(image.getWidth(),image.getHeight());
            this.height = calculateHeight(image.getWidth(),image.getHeight());
            this.x = (px-width)/2;
            this.y = (py-height)/2;
        }

        private int calculateWidth(double ix, double iy) {
            double pr = (double) px/py;
            double ir = ix/iy;
            return ir > pr ? px :(int) (1.0*ix * py / iy);
        }

        private int calculateHeight(double ix, double iy) {
            double pr = (double) px/py;
            double ir = ix/iy;
            return ir > pr ? (int) (1.0 * iy * px / ix): py;
        }
    }
    
}
