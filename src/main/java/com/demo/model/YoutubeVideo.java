package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeVideo implements Serializable {

    private static final long serialVersionUID = -1L;

    private String videoId;

    private String keywords;

    private String mainBody;

    private String title;

//    private String thumbnail;

    private Date releaseTime;

    private Date crawlerTime;

}
