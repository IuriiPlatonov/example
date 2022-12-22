package com.otoil.ot_1_1_1.client.detail;


import java.util.List;

import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;

import io.reactivex.Single;
import ru.ot.mvp.client.presenters.Model;


public interface ExampleTaskDetailModel extends Model
{
    Single<List<AttributeNameBean>> getObjectAttribute(String id);
}
