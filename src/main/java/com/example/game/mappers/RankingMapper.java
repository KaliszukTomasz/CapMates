package com.example.game.mappers;

import com.example.game.entity.Ranking;
import com.example.game.transferObjects.RankingTO;

public class RankingMapper {

    public RankingTO mapToRankingTO(Ranking ranking){

        RankingTO rankingTO = new RankingTO();
        rankingTO.setId(ranking.getId());
        rankingTO.setPoints(ranking.getPoints());

        return rankingTO;
    }


}
