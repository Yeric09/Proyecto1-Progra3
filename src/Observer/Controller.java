
package Observer;

public class Controller {

    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }

    public void move(int flecha) {
        model.move(flecha);
    }

    public void stopVer() {
        model.stopVer();
    }

    public void stopHor() {
        model.stopHor();
    }

}
