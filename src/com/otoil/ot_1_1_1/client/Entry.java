package com.otoil.ot_1_1_1.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.otoil.ot_1_1_1.client.impl.ExampleTaskClientFactoryImpl;


public class Entry implements EntryPoint
{
    private ExampleTaskMainPresenter presenter;
    private EventBus eventBus;

    public Entry()
    {
        eventBus = new SimpleEventBus();
        ExampleTaskClientFactory factory = ExampleTaskClientFactoryImpl.INSTANCE;
        presenter = new ExampleTaskMainPresenter(factory);
    }

    @Override
    public void onModuleLoad()
    {
        presenter.start(null, eventBus);
    }
}
