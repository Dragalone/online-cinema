package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Actor;
import com.example.onlinecinemabackend.entity.Director;
import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.web.dto.request.FilmFilterRequest;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Set;

public interface FilmSpecification {

    static Specification<Film> withFilter(FilmFilterRequest filter){
        return Specification.where(byTitle(filter.getTitle()))
                .and(byGenreNames(filter.getGenreNames()))
                    .and(byActorsNames(filter.getActorNames())
                        .and(byDirectorName(filter.getDirectorName())));
    }

    static Specification<Film> byTitle(String title){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(title)){
                return null;
            }
            return criteriaBuilder.equal(root.get(Film.Fields.title),title);
        };
    }

    static Specification<Film> byGenreNames(Set<String> genresNames){
        return (root, query, criteriaBuilder) -> {
            if (genresNames.isEmpty()){
                return null;
            }
            Expression<String> exp = root.join("genres", JoinType.INNER).get(Genre.Fields.name);
            return exp.in(genresNames);
        };

    }

    static Specification<Film> byActorsNames(Set<String> actorsNames){
        return (root, query, criteriaBuilder) -> {
            if (actorsNames.isEmpty()){
                return null;
            }
            Expression<String> exp = root.join("actors", JoinType.INNER).get(Actor.Fields.name);
            return exp.in(actorsNames);
        };

    }

    static Specification<Film> byDirectorName(String directorName){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(directorName)){
                return null;
            }
            return criteriaBuilder.equal(root.get(Film.Fields.director).get(Director.Fields.name),directorName);
        };

    }

}
