package com.poo.atividadejpa.repository;

import com.poo.atividadejpa.model.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Long> {

    List<Video> findByTituloContainingOrderByTituloAsc(String titulo);

    @Query("SELECT v FROM Video v WHERE v.categoria.id = :categoriaId ORDER BY v.titulo ASC")
    List<Video> findByCategoriaIdOrderByTituloAsc(@Param("categoriaId") Integer categoriaId);

}
