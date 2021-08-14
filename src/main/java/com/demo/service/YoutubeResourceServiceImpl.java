package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.demo.model.YoutubeSimpleSource;
import com.demo.model.YoutubeVideo;
import com.demo.util.AppUtil;
import com.demo.util.Auth;
import com.demo.util.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class YoutubeResourceServiceImpl {

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    private static final long CHANNEL_MAX_LIMIT = 200;

    private static final String API_KEY = "<<your API key here>>";

    private static final String ERR_NO_VIDEO_FOUND= "No video found by this channel id";

    public String getChannelVideo(String channelId) {
        setProxy();
        YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, request -> {
        }).setApplicationName("youtube-cmdline-search-sample").build();
        YouTube.Search.List search = null;
        try {
            search = youtube.search().list("id,snippet");
        } catch (IOException e) {
            Logger.error(e);
            return e.getMessage();
        }
        String apiKey = API_KEY;
        search.setKey(apiKey);
        search.setType("video");
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url),nextPageToken,pageInfo,prevPageToken");
        search.setMaxResults(50L);
        search.setChannelId(channelId);
        search.setOrder("date");
        SearchListResponse searchResponse;
        try {
            searchResponse = search.execute();
        } catch (IOException e) {
            Logger.error(e);
            return e.getMessage();
        }
        List<SearchResult> searchResultList = searchResponse.getItems();
        List<SearchResult> allRecord = new ArrayList<>();
        if (searchResultList != null) {
            PageInfo pageInfo = searchResponse.getPageInfo();
            while (true) {
                allRecord.addAll(searchResultList);
                if (pageInfo.getTotalResults() <= NUMBER_OF_VIDEOS_RETURNED) {
                    break;
                }
                search.setPageToken(searchResponse.getNextPageToken());
                try {
                    searchResponse = search.execute();
                } catch (IOException e) {
                    Logger.error(e);
                    return e.getMessage();
                }
                if (searchResponse == null ||
                        AppUtil.isNull(searchResponse.getItems())) {
                    break;
                }
                List<SearchResult> items = searchResponse.getItems();
                if (AppUtil.isNull(items)) {
                    break;
                }
                allRecord.addAll(items);
                if (items.size() < NUMBER_OF_VIDEOS_RETURNED) {
                    break;
                }
            }
            if (AppUtil.isNull(allRecord)) {
                return ERR_NO_VIDEO_FOUND;
            }

            List<YoutubeSimpleSource> objects = new ArrayList<>();
            allRecord.forEach(record -> {
                YoutubeSimpleSource source = new YoutubeSimpleSource();
                source.setVideoId(record.getId().getVideoId());
                source.setTitle(record.getSnippet().getTitle());
//                source.setThumbnails(record.getSnippet().getThumbnails().getDefault().getUrl());
                objects.add(source);
            });
            return JSONObject.toJSONString(objects);
        }
        return ERR_NO_VIDEO_FOUND;
    }

    public String getVideoByPlaylistId(String playlistId) {
        if (AppUtil.isNull(playlistId)) {
            return "";
        }
        setProxy();
        YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, request -> {
        }).setApplicationName("youtube-cmdline-search-sample").build();
        YouTube.PlaylistItems.List search = null;
        try {
            search = youtube.playlistItems().list("id,snippet");
        } catch (IOException e) {
            Logger.error(e);
            return "";
        }
        String apiKey = API_KEY;
        search.setKey(apiKey);
        search.setFields("items(snippet/title,snippet/thumbnails,snippet/resourceId/videoId),nextPageToken,pageInfo,prevPageToken");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        search.setPlaylistId(playlistId);
        PlaylistItemListResponse searchResponse;
        try {
            searchResponse = search.execute();
        } catch (IOException e) {
            Logger.error(e);
            return "";
        }
        List<PlaylistItem> searchResultList = searchResponse.getItems();
        List<PlaylistItem> allRecord = new ArrayList<>();
        if (searchResultList != null) {
            PageInfo pageInfo = searchResponse.getPageInfo();
            if (pageInfo.getTotalResults() <= NUMBER_OF_VIDEOS_RETURNED) {
                allRecord.addAll(searchResultList);
            } else {
                allRecord.addAll(searchResultList);
                while (true) {
                    search.setPageToken(searchResponse.getNextPageToken());
                    try {
                        searchResponse = search.execute();
                    } catch (IOException e) {
                        Logger.error(e);
                        return e.getMessage();
                    }
                    if (searchResponse == null ||
                            AppUtil.isNull(searchResponse.getItems())) {
                        break;
                    }
                    List<PlaylistItem> items = searchResponse.getItems();
                    if (AppUtil.isNull(items)) {
                        break;
                    }
                    allRecord.addAll(items);
                    if (items.size() < NUMBER_OF_VIDEOS_RETURNED ||
                            allRecord.size() > CHANNEL_MAX_LIMIT) {
                        break;
                    }
                }
            }
        }
        if (AppUtil.isNull(allRecord)) {
            return ERR_NO_VIDEO_FOUND;
        }
        // 获取所有的video id
        List<YoutubeSimpleSource> objects = new ArrayList<>();
        allRecord.forEach(record -> {
            YoutubeSimpleSource source = new YoutubeSimpleSource();
            source.setVideoId(record.getSnippet().getResourceId().getVideoId());
            source.setTitle(record.getSnippet().getTitle());
//            source.setThumbnails(record.getSnippet().getThumbnails().getDefault().getUrl());
            objects.add(source);
        });
        return JSONObject.toJSONString(objects);
    }

    public String getVideoDetailByVideoId(List<String> videoIds) {
        if (AppUtil.isNull(videoIds)) {
            return "";
        }
        setProxy();
        YouTube youtubeVideo = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, request -> {
        }).setApplicationName("youtube-cmdline-search-sample").build();
        YouTube.Videos.List search;
        try {
            search = youtubeVideo.videos().list("id,snippet");
        } catch (IOException e) {
            Logger.error(e);
            return "";
        }
        search.setFields("items(id,snippet/publishedAt,snippet/title,snippet/description,snippet/tags,snippet/channelId,snippet/thumbnails)");
        String apiKey = API_KEY;
        search.setKey(apiKey);
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        StringBuilder videoIdStr = new StringBuilder();
        if (videoIds.size()  > 1) {
            for (int i = 0; i < videoIds.size(); i++) {
                videoIdStr.append(videoIds.get(i));
                if (i != videoIds.size() - 1) {
                    videoIdStr.append(",");
                }
            }
        } else {
            videoIdStr.append(videoIds.get(0));
        }
        search.setId(videoIdStr.toString());
        VideoListResponse response;
        try {
            response = search.execute();
        } catch (IOException e) {
            Logger.error(e);
            return "";
        }
        List<Video> items = response.getItems();
        if (AppUtil.isNull(items)) {
            return null;
        }
        List<YoutubeVideo> resultList = new ArrayList<>();
        for (Video item : items) {
            List<String> tags = item.getSnippet().getTags();
            StringBuilder stringBuilder = new StringBuilder();
            if (AppUtil.isNotNull(tags)) {
                for (String tag : tags) {
                    stringBuilder.append(tag).append(",");
                }
            }
            String description = item.getSnippet().getDescription();
            if (description == null) {
                description = "";
            }
            YoutubeVideo newVideo = YoutubeVideo.builder()
                    .videoId(item.getId())
                    .keywords(stringBuilder.toString())
                    .mainBody(description.substring(0, Math.min(4990, description.length())))
                    .title(item.getSnippet().getTitle())
//                    .thumbnail(item.getSnippet().getThumbnails().getDefault().getUrl())
                    .releaseTime(new Date(item.getSnippet().getPublishedAt().getValue()))
                    .crawlerTime(new Date())
                    .build();
            resultList.add(newVideo);
        }
        return JSONObject.toJSONString(resultList);
    }

    private void setProxy() {
        String host = "127.0.0.1";
        String port = "1087";
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("https.proxyHost", host);
        System.setProperty("https.proxyPort", port);
    }

}
