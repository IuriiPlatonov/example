package com.otoil.ot_1_1_1.server.entities.treedoccard;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;

import ru.ep.sdo.Entity;
import ru.ep.sdo.annotations.Handler;
import ru.ep.sdo.annotations.Xml;
import ru.ep.sdo.handlers.SDOBlobFieldHandler;
import ru.ep.sdo.lob.blob.SDOBlob;
import ru.ot.gwt.sdo.server.beans.BeanConverter;

@Xml(name = "ROW")
public class TreeDocCard extends Entity
{

	public static final String PROPERTYNAME_DCMCRD_ID = "dcmcrdId";
	public static final String PROPERTYNAME_NAME = "docName";
	public static final String PROPERTYNAME_ORDER_NUMBER = "orderNumber";
	public static final String PROPERTYNAME_CHANGE_DATE = "changeDate";
	public static final String PROPERTYNAME_DCMCRD_DCMCRD_ID = "dcmcrdDcmcrdId";
	public static final String PROPERTYNAME_ICON = "icon";

	@Xml(name = "DCMCRD_ID")
	private BigDecimal dcmcrdId;

	@Xml(name = "NAME")
	private String docName;

	@Xml(name = "ORDER_NUMBER")
	private BigDecimal orderNumber;

	@Xml(name = "CHANGE_DATE")
	private Timestamp changeDate;

	@Xml(name = "DCMCRD_DCMCRD_ID")
	private BigDecimal dcmcrdDcmcrdId;

    @Handler(cls = SDOBlobFieldHandler.class)
    @Xml(name = "ICON")
    private SDOBlob icon;


	public BigDecimal getDcmcrdId()
	{
		return dcmcrdId;
	}

	public void setDcmcrdId(BigDecimal dcmcrdId)
	{
		BigDecimal oldValue = this.dcmcrdId;
		this.dcmcrdId = dcmcrdId;
		firePropertyChange(PROPERTYNAME_DCMCRD_ID, oldValue, dcmcrdId);
	}

	public String getDocName()
	{
		return docName;
	}

	public void setDocName(String docName)
	{
		String oldValue = this.docName;
		this.docName = docName;
		firePropertyChange(PROPERTYNAME_NAME, oldValue, docName);
	}

	public BigDecimal getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(BigDecimal orderNumber)
	{
		BigDecimal oldValue = this.orderNumber;
		this.orderNumber = orderNumber;
		firePropertyChange(PROPERTYNAME_ORDER_NUMBER, oldValue, orderNumber);
	}

	public Timestamp getChangeDate()
	{
		return changeDate;
	}

	public void setChangeDate(Timestamp changeDate)
	{
		Timestamp oldValue = this.changeDate;
		this.changeDate = changeDate;
		firePropertyChange(PROPERTYNAME_CHANGE_DATE, oldValue, changeDate);
	}

	public BigDecimal getDcmcrdDcmcrdId()
	{
		return dcmcrdDcmcrdId;
	}

	public void setDcmcrdDcmcrdId(BigDecimal dcmcrdDcmcrdId)
	{
		BigDecimal oldValue = this.dcmcrdDcmcrdId;
		this.dcmcrdDcmcrdId = dcmcrdDcmcrdId;
		firePropertyChange(PROPERTYNAME_DCMCRD_DCMCRD_ID, oldValue, dcmcrdDcmcrdId);
	}

	public SDOBlob getIcon()
	{
		return icon;
	}

	public void setIcon(SDOBlob icon)
	{
	    SDOBlob oldValue = this.icon;
		this.icon = icon;
		firePropertyChange(PROPERTYNAME_ICON, oldValue, icon);
	}

    public static BeanConverter<TreeDocCard, ResponseDocumentCardBean> converter()
    {
        return new BeanConverter<>(TreeDocCard.class,
                ResponseDocumentCardBean.class);
    }
}
