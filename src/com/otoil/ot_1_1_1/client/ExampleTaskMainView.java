package com.otoil.ot_1_1_1.client;


import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;


public interface ExampleTaskMainView extends IsWidget
{
    AcceptsOneWidget getMasterPanel();

    AcceptsOneWidget getDetailPanel();
}
