package com.awesome.structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;



/**
 * Created with IntelliJ IDEA. User: pradip.modi Date: 10/11/14 Time: 11:34 AM To change this
 * template use File | Settings | File Templates.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("productId")
    private int productId;
    @JsonProperty("imageURL")
    private String imageURL;
    @JsonProperty("description")
    private String description;
    @JsonProperty("seller")
    private String seller;
    @JsonProperty("sellerPrice")
    private int sellerPrice;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("createdAt")
    private Date createdAt;
    @JsonProperty("bidPrice")
    private int bidPrice;
    @JsonProperty("customer")
    private String customer;

}
