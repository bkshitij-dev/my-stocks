package com.example.share.repository;

import com.example.share.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    Script findBySymbol(String symbol);
}
