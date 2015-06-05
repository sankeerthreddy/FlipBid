package com.awesome.flipbid.config;

import com.yammer.dropwizard.config.Configuration;
import lombok.Data;

import javax.validation.Valid;

/**
 * Created by sankeerth.reddy on 04/06/15.
 */

@Data
public class ServiceConfiguration extends Configuration {

    @Valid
    private MessagesConfiguration messages;

}