package com.example.onlinecinemabackend.repository;

import com.example.onlinecinemabackend.entity.Film;
import com.example.onlinecinemabackend.entity.Genre;
import com.example.onlinecinemabackend.web.model.request.FilmFilterRequest;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

public interface FilmSpecification {

    static Specification<Film> withFilter(FilmFilterRequest filter){
        return Specification.where(byGenreNames(filter.getGenreNames()));
    }

    static Specification<Film> byGenreNames(Set<String> genreNames){
        return (root, query, criteriaBuilder) -> {
            if (genreNames.isEmpty()){
                return null;
            }
            Expression<String> exp = root.join("genres", JoinType.INNER).get(Genre.Fields.name);
            System.out.println(
                    exp
            );
            return exp.in(genreNames);
        };

    }


}
