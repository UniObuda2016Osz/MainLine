package Environment;

import java.util.List;

/**
 * Created by nemet on 2016. 10. 26..
 */
public interface IRadar {
    //left - távolabbi bal pont
    //right - távolabbi jobb pont
    //center - az autón lévő pont
    List<WorldObject> getObjectsRecognisedByRadar(int leftX, int leftY, int rightX, int rightY, int centerX, int centerY);
}
