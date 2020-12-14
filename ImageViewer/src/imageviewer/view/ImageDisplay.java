package imageviewer.view;

import imageviewer.model.Image;

public interface ImageDisplay {
    public void display(Image image);
    Image current();
}