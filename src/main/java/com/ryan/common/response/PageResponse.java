package com.ryan.common.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PageResponse<T> {
    private int page;
    private int size;
    private long totalPages;
    private long totalItems;
    private boolean hasNext;
    private boolean hasPrev;
    private List<T> items;

}
