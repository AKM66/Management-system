package com.mc.link;

import javax.swing.*;
import java.awt.*;

public class tools {

    //实现窗口居中
    public static void setPos(JFrame jFrame,int width,int height){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int x = (int)screenSize.getWidth()/2 - width/2;
        int y =(int)screenSize.getHeight()/2 - height/2;
    }

}
