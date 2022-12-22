package com.otoil.ot_1_1_1.client.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestDocumentCardBean
{
    private String dcmcrdId;
    private String name;
    private String orderNumber;

}
