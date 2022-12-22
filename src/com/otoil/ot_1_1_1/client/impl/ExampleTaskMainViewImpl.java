package com.otoil.ot_1_1_1.client.impl;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.ExampleTaskMainView;

import ru.ot.wevelns.client.NSSplitLayoutPanel;


public class ExampleTaskMainViewImpl implements ExampleTaskMainView
{

    private SimplePanel masterPanel = new SimplePanel();
    private SimplePanel detailPanel = new SimplePanel();
    private NSSplitLayoutPanel horizontalPanel = new NSSplitLayoutPanel();

    public ExampleTaskMainViewImpl()
    {
        init();
    }

    private void init()
    {
        horizontalPanel.setSize("100vw", "100vh");
        int width = Window.getClientWidth();
        horizontalPanel.addEast(detailPanel, width / 2);
        horizontalPanel.add(masterPanel);

        RootPanel.get("resultTable").add(horizontalPanel);
    }

    @Override
    public AcceptsOneWidget getMasterPanel()
    {
        return masterPanel;
    }

    @Override
    public Widget asWidget()
    {
        return null;
    }

    @Override
    public AcceptsOneWidget getDetailPanel()
    {
        return detailPanel;
    }

}
