package com.otoil.ot_1_1_1.client.dto;


import java.util.Date;

import com.otoil.ot_1_1_1.shared.DefaultTreeNamedBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDocumentCardBean extends DefaultTreeNamedBean
{
    private String id;
    private String name;
    private String orderNumber;
    private Date changeDate;
    private String parent;
    private String binaryData;
}
