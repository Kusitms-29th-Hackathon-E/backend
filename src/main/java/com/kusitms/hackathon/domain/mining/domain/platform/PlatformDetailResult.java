package com.kusitms.hackathon.domain.mining.domain.platform;

import java.util.List;

public record PlatformDetailResult(
        String title,
        String platformUrl,
        List<String> tags
) {
}
