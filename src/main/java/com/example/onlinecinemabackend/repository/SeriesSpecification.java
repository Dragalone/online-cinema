package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.*;

import com.example.onlinecinemabackend.web.model.request.SeriesFilterRequest;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Set;

public interface SeriesSpecification {
    static Specification<Series> withFilter(SeriesFilterRequest filter){
        return Specification.where(byTitle(filter.getTitle()))
                .and(byGenreNames(filter.getGenreNames()))
                    .and(byActorsNames(filter.getActorNames())
                        .and(byDirectorName(filter.getDirectorName())));
    }


    static Specification<Series> byTitle(String title){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)){
                return null;
            }
            return criteriaBuilder.equal(root.get(Series.Fields.title),title);
        };

    }

    static Specification<Series> byGenreNames(Set<String> genresNames){
        return (root, query, criteriaBuilder) -> {
            if (genresNames.isEmpty()){
                return null;
            }
            Expression<String> exp = root.join("genres", JoinType.INNER).get(Genre.Fields.name);
            return exp.in(genresNames);
        };

    }

    static Specification<Series> byActorsNames(Set<String> actorsNames){
        return (root, query, criteriaBuilder) -> {
            if (actorsNames.isEmpty()){
                return null;
            }
            Expression<String> exp = root.join("actors", JoinType.INNER).get(Actor.Fields.name);
            return exp.in(actorsNames);
        };

    }

    static Specification<Series> byDirectorName(String directorName){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(directorName)){
                return null;
            }
            return criteriaBuilder.equal(root.get(Series.Fields.director).get(Director.Fields.name),directorName);
        };

    }
}
