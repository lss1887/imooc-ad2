package com.imooc.ad.dao;

import com.imooc.ad.entity.Creative;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa比mybatis查询效率低
 * */
public interface CreativeRepository extends JpaRepository<Creative,Long> {
}
