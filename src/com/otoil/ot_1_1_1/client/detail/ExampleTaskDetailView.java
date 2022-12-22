package com.otoil.ot_1_1_1.client.detail;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;

public interface ExampleTaskDetailView extends IsWidget
{
    void addDataToDetailTable(List<AttributeNameBean> list);
}
