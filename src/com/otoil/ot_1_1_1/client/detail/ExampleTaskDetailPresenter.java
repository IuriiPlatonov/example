package com.otoil.ot_1_1_1.client.detail;


import com.otoil.ot_1_1_1.client.ExampleTaskClientFactory;
import com.otoil.ot_1_1_1.client.event.SendDetailIdEvent;

import io.reactivex.disposables.CompositeDisposable;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;
import ru.ot.mvp.client.presenters.AbstractPresenter;


public class ExampleTaskDetailPresenter
        extends AbstractPresenter<ExampleTaskDetailModel, ExampleTaskDetailView>
{

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public ExampleTaskDetailPresenter(ExampleTaskClientFactory factory)
    {
        super(factory.getDetailModel(), factory.getDetailView());

    }

    @Override
    public void bind()
    {
        subscriptions.add(RxGwtEvent.observeOn(eventBus, SendDetailIdEvent.TYPE)
            .subscribe(x -> model.getObjectAttribute(x.getId())
                .subscribe(list -> view.addDataToDetailTable(list))));
    }

    @Override
    public void onCancel()
    {
        subscriptions.clear();
        super.onCancel();
    }

    @Override
    public void onStop()
    {
        subscriptions.clear();
        super.onStop();
    }
}
