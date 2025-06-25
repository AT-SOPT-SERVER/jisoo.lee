package org.sopt.dto;

import java.util.List;

public record PostResponseData(
        String title,
        String content,
        String writer,
        List<String> comments,
        int likeCount
        ) {}
