/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original,int width, int height)
    {
        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0,width,height,null);
        g2.dispose();

        return scaledImage;
    }

}
