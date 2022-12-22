package com.otoil.ot_1_1_1.client;

import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailModel;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailView;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterModel;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;

import ru.ot.mvp.client.factory.ClientFactory;

public interface ExampleTaskClientFactory extends ClientFactory
{
    ExampleTaskMainModel getMainModel();

    ExampleTaskMainView getMainView();

    ExampleTaskMasterModel getMasterModel();

    ExampleTaskMasterView getMasterView();

    ExampleTaskDetailModel getDetailModel();
    
    ExampleTaskDetailView getDetailView();

}
