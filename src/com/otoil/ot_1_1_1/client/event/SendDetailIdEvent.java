package com.otoil.ot_1_1_1.client.event;


import lombok.Getter;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;


public class SendDetailIdEvent extends RxGwtEvent<SendDetailIdEvent>
{

    public static final Type<RxGwtEventHandler<SendDetailIdEvent>> TYPE = newType();

    @Getter
    private String id;

    public SendDetailIdEvent(String id)
    {
        super(TYPE);
        this.id = id;
    }

}
