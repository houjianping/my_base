package com.siyuan.enjoyreading.entity;

import java.util.List;

public class GridItem extends MultipleEntity {

    private int column;

    public List<RecommendPrasise> recommendPrasise;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public List<RecommendPrasise> getRecommendPrasise() {
        return recommendPrasise;
    }

    public void setRecommendPrasise(List<RecommendPrasise> recommendPrasise) {
        this.recommendPrasise = recommendPrasise;
    }
}
