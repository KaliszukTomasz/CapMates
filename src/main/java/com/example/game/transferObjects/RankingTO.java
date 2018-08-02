package com.example.game.transferObjects;

public class RankingTO {

    private Long id;
    private Integer points;

    public RankingTO() {

    }

    public RankingTO(Integer points) {
        super();
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}


