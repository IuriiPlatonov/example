package com.otoil.ot_1_1_1.client;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailPresenter;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;
import com.otoil.ot_1_1_1.client.event.SendDetailIdEvent;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterPresenter;

import io.reactivex.disposables.CompositeDisposable;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;
import ru.ot.mvp.client.presenters.AbstractPresenter;


public class ExampleTaskMainPresenter
        extends AbstractPresenter<ExampleTaskMainModel, ExampleTaskMainView>
{

    private ExampleTaskMasterPresenter masterPresenter;
    private ExampleTaskDetailPresenter detailPresenter;

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public ExampleTaskMainPresenter(ExampleTaskClientFactory factory)
    {
        super(factory.getMainModel(), factory.getMainView());
        masterPresenter = new ExampleTaskMasterPresenter(factory);
        detailPresenter = new ExampleTaskDetailPresenter(factory);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);

        detailPresenter.start(view.getDetailPanel(), eventBus);
        masterPresenter.start(view.getMasterPanel(), eventBus);

    }

    @Override
    public void bind()
    {
        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());
        subscriptions.add(
            RxGwtEvent.observeOn(eventBus, GetDetailIdEvent.TYPE).subscribe(
                x -> eventBus.fireEvent(new SendDetailIdEvent(x.getId()))));
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
