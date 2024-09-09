package com.project.nikiha.loser.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private List<T> items;
    private int totalCount;

    public Response(List<T> items) {
        this.items = items;
        this.totalCount = items != null ? items.size() : 0;
    }
}
