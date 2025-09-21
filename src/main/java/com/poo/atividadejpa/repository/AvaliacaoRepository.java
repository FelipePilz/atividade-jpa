package com.poo.atividadejpa.repository;

import com.poo.atividadejpa.model.Avaliacao;
import com.poo.atividadejpa.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query(value = """
            SELECT v.*
            FROM avaliacao a
            JOIN video v ON v.id = a.video_id
            GROUP BY v.id
            ORDER BY AVG(a.nota) DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Video> findTop10RatedVideos();

}
