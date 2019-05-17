package Throwables.Fruits.SpecialFruits;

import Gui.Controller.Controller;
import javafx.scene.image.Image;

/**
 * @author Mostafa Talaat
 */
public class MagicBeans extends SpecialFruit {


    public MagicBeans() {
        super();
        super.setImg1(new Image("Resources/Magic_Bean.png", 75, 75, true, true));
        super.setImg2(new Image("Resources/Magic_Bean.png", 75, 75, true, true));
    }

    @Override
    public void slice(Controller controller) {
        if (controller.lives > 2) {
            controller.score += 25;
        } else {
            controller.lives++;
        }
        setSliced(true);
    }

}
