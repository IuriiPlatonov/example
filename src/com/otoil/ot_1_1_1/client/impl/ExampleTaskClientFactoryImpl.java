package com.otoil.ot_1_1_1.client.impl;


import com.otoil.ot_1_1_1.client.ExampleTaskClientFactory;
import com.otoil.ot_1_1_1.client.ExampleTaskMainModel;
import com.otoil.ot_1_1_1.client.ExampleTaskMainView;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailModel;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailView;
import com.otoil.ot_1_1_1.client.detail.impl.ExampleTaskDetailModelImpl;
import com.otoil.ot_1_1_1.client.detail.impl.ExampleTaskDetailViewImpl;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterModel;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;
import com.otoil.ot_1_1_1.client.master.impl.ExampleTaskMasterModelImpl;
import com.otoil.ot_1_1_1.client.master.impl.ExampleTaskMasterViewImpl;

import ru.ot.mvp.client.history.AsyncPlaceController;


public enum ExampleTaskClientFactoryImpl implements ExampleTaskClientFactory
{

    INSTANCE;

    @Override
    public ExampleTaskMainModel getMainModel()
    {
        return new ExampleTaskMainModelImpl();
    }

    @Override
    public ExampleTaskMainView getMainView()
    {
        return new ExampleTaskMainViewImpl();
    }

    @Override
    public ExampleTaskMasterModel getMasterModel()
    {
        return new ExampleTaskMasterModelImpl();
    }

    @Override
    public ExampleTaskMasterView getMasterView()
    {
        return new ExampleTaskMasterViewImpl();
    }

    @Override
    public ExampleTaskDetailView getDetailView()
    {
        return new ExampleTaskDetailViewImpl();
    }

    @Override
    public ExampleTaskDetailModel getDetailModel()
    {
        return new ExampleTaskDetailModelImpl();
    }

    @Override
    public AsyncPlaceController getPlaceController()
    {
        return null;
    }

}
