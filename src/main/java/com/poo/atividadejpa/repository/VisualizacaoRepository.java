package com.poo.atividadejpa.repository;

import com.poo.atividadejpa.model.Usuario;
import com.poo.atividadejpa.model.Video;
import com.poo.atividadejpa.model.Visualizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisualizacaoRepository extends JpaRepository<Visualizacao, Integer> {


    @Query(value = """
            SELECT v.*
            FROM visualizacao vis
            JOIN video v ON v.id = vis.video_id
            GROUP BY v.id
            ORDER BY COUNT(vis.id) DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Video> findTop10MaisAssistidos();

    @Query(value = """
            SELECT u.*
            FROM visualizacao v
            JOIN usuario u ON u.id = v.perfil_id
            GROUP BY u.id
            ORDER BY COUNT(v.id) DESC
            LIMIT 1
            """, nativeQuery = true)
    Usuario findUsuarioMaisAtivo();

}
