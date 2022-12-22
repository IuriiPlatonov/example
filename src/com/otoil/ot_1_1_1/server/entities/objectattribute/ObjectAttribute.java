package com.otoil.ot_1_1_1.server.entities.objectattribute;


import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;

import ru.ep.sdo.Entity;
import ru.ep.sdo.annotations.Xml;
import ru.ot.gwt.sdo.server.beans.BeanConverter;


@Xml(name = "ROW")
public class ObjectAttribute extends Entity
{

    public static final String PROPERTYNAME_NAME = "name";
    public static final String PROPERTYNAME_VALUE_STRING = "valueString";

    @Xml(name = "NAME")
    private String name;

    @Xml(name = "VALUE_STRING")
    private String valueString;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange(PROPERTYNAME_NAME, oldValue, name);
    }

    public String getValueString()
    {
        return valueString;
    }

    public void setValueString(String valueString)
    {
        String oldValue = this.valueString;
        this.valueString = valueString;
        firePropertyChange(PROPERTYNAME_VALUE_STRING, oldValue, valueString);
    }

    public static BeanConverter<ObjectAttribute, AttributeNameBean> converter()
    {
        return new BeanConverter<>(ObjectAttribute.class, AttributeNameBean.class);
    }
}
