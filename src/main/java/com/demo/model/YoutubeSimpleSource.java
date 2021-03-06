package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeSimpleSource implements Serializable {

    private static final long serialVersionUID = -1L;

    private String videoId;

    private String title;

//    private String thumbnails;
}
