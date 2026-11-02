package controller;

import model.CatalogModel;
import view.CatalogView;

// TODO: Implement ActionListener and actionPerformed to handle New clicks.
public class CatalogController {
    private CatalogView view;
    private CatalogModel model;

    public CatalogController(CatalogView view, CatalogModel model) {
        this.view = view;
        this.model = model;
        view.newButton.addActionListener(this);
    }
}
