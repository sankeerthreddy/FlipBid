package com.awesome.flipbid.config;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Created by sankeerth.reddy on 04/06/15.
 */
@Data
public class MessagesConfiguration {

    @NotNull
    private String hello;

    @NotNull
    private String helloRajesh;
}
