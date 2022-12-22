package com.otoil.ot_1_1_1.client.event;


import lombok.Getter;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;


public class GetDetailIdEvent extends RxGwtEvent<GetDetailIdEvent>
{

    public static final Type<RxGwtEventHandler<GetDetailIdEvent>> TYPE = newType();
    
    @Getter
    private String id;

    public GetDetailIdEvent(String id)
    {
        super(TYPE);
        this.id = id;
    }
}
