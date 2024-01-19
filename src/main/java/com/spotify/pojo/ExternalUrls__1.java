
package com.spotify.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@Builder
@Jacksonized
public class ExternalUrls__1 {

    @JsonProperty("spotify")
    private String spotify;



}
