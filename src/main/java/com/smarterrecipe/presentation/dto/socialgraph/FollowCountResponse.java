package com.smarterrecipe.presentation.dto.socialgraph;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowCountResponse {
    private long followersCount;
    private long followingCount;
}
