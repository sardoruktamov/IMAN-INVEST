package uz.java.springdatajpa.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.model.Users;

import java.util.List;
import java.util.Map;

@Repository
public class UsersRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    public Page<Users> searchProducts(Map<String, String> params, int size, int page){
        String queryStr = "select u from Users u where 1=1 ";
        String countQueryStr = "select count(u.id) from Users u where 1=1 ";
        StringBuilder queryParams = buildParams(params);

        Query query = entityManager.createQuery(queryStr + queryParams);
        Query countQuery = entityManager.createQuery(countQueryStr + queryParams);

        query.setFirstResult(size * page); //offset
        query.setMaxResults(size);

        setParams(query, params);
        setParams(countQuery, params);

        long totalElements = (long) countQuery.getSingleResult(); //1 ta natija qaytadi

        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElements) ;
    }

    private StringBuilder buildParams(Map<String, String> params){
        StringBuilder queryParams = new StringBuilder();
        if (params.containsKey("firstName")){
            queryParams.append(" AND u.firstName = :firstName");
        }
        if (params.containsKey("lastName")){
            queryParams.append(" AND u.lastName = :lastName");
        }
        if (params.containsKey("birthDate")){
            queryParams.append(" AND u.birthDate = :birthDate");
        }
        return queryParams;
        //...
    }

    private void setParams(Query query, Map<String, String> params){
        if (params.containsKey("firstName")){
            query.setParameter("firstName", params.get("firstName"));
        }
        if (params.containsKey("lastName")){
            query.setParameter("lastName", params.get("lastName"));
        }
        if (params.containsKey("birthDate")){
            query.setParameter("birthDate", params.get("birthDate"));
        }
        //...
    }
}
