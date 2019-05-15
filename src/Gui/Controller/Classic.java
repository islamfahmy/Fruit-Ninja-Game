package Gui.Controller;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class Classic implements Initializable {
   private Controller controller= new Controller();
    @FXML private Canvas canvas ;
    @FXML ImageView pause;
    @FXML ImageView resume;
    private GraphicsContext gc;
    private Image image = new Image("Resources/ConceptGreatWave1 (2).jpg",1280,720,false,false);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller.type = "classic";
        controller.lives = 3;
        resume.setVisible(false);
        gc=canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);

        Timeline timeline = new Timeline(new KeyFrame(new Duration(2000), actionEvent->{
            if(controller.throwables.size()<5) {
                controller.throwables.add(controller.getRandomThrowable());
                System.out.println(controller.throwables.size());
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline clock= new Timeline(new KeyFrame(new Duration(1000), actionEvent1->{
            controller.secs++;
            if (controller.secs == 60) {
                controller.secs = 0;
                controller.mins++;
            }
            if (controller.secs % 30 == 0 && controller.difficulty < 3){
                timeline.setRate(++controller.difficulty);
            }


        }));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();


        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                controller.drawAllThings(gc,controller.throwables,controller);
                if (!controller.removeUnwantedThrowable(controller.throwables))
                    controller.lives--;
                if(controller.lives==0){
                    timeline.stop();
                    clock.stop();
                    // todo: game over scene
                    System.out.println("GAME OVER");


                }

            }
        };
        timer.start();

        pause.setOnMouseClicked(event -> {
            pause.setVisible(false);
            resume.setVisible(true);
            timeline.stop();
            timer.stop();
            clock.stop();
            canvas.setEffect(new GaussianBlur(50));
        });

        resume.setOnMouseClicked(event -> {
            if(resume.isVisible()){
                resume.setVisible(false);
                canvas.setEffect(new GaussianBlur(-50));
                AtomicInteger seconds= new AtomicInteger();
                Timeline resume = new Timeline(new KeyFrame(new Duration(500), acttionEvent->{
                    controller.drawAllThings(gc,controller.throwables,controller);
                    seconds.getAndIncrement();
                    gc.setFill(Color.ORANGE);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(5);
                    Font theFont = Font.font("Gang Of Three", 200);
                    gc.setFont(theFont);
                    gc.fillText(String.valueOf(4-seconds.get()), 600, 350);
                    gc.strokeText(String.valueOf(4-seconds.get()), 600, 350);
                }));
                resume.setCycleCount(4);
                resume.play();
                resume.setOnFinished(event1 -> {
                    pause.setVisible(true);
                    timeline.play();
                    timer.start();
                    clock.play();
                });

            }
        });


    }

    public void handleMove(MouseEvent event){
        controller.slice(controller.throwables,event.getSceneX(),event.getSceneY(),controller);

        }

}


