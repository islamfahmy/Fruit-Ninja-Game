package Gui.Controller;

import Interfaces.Factory.ObjectCreator;
import Interfaces.GameActions;
import Interfaces.GameObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Mostafa Talaat
 */
public class Controller implements GameActions {
    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    @Override
    public GameObject createGameObject() {
        return null;
    }

    @Override
    public void updateObjectsLocations() {

    }

    @Override
    public void sliceObjects() {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void loadGame() {

    }

    @Override
    public void ResetGame() {

    }

    @Override
    public GameObject getRandomThrowable() {
        ObjectCreator objectCreator = new ObjectCreator();
        Random rand = new Random();
        int numberOfThrowables = 7; // number of supported throwables - 1 *starting from 0*
        return objectCreator.createObject(rand.nextInt(numberOfThrowables));
    }

    @Override
    public void removeUnwantedThrowable(ArrayList<GameObject> throwables) {
        for (GameObject throwable : throwables) {
            if (throwable.hasMovedOffScreen()) {
                throwables.remove(throwable);
            }
        }
    }
}