package com.poo.atividadejpa;

import com.poo.atividadejpa.model.*;
import com.poo.atividadejpa.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class AtividadeJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtividadeJpaApplication.class, args);
    }

    /* Rodar na primeira vez
    @Bean
    CommandLineRunner initDatabase(
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository,
            PerfilRepository perfilRepository,
            VideoRepository videoRepository,
            AvaliacaoRepository avaliacaoRepository,
            VisualizacaoRepository visualizacaoRepository
    ) {
        return args -> {
            // ======== Categorias ========
            Categoria cat1 = new Categoria();
            cat1.setNome("Ação");
            Categoria cat2 = new Categoria();
            cat2.setNome("Comédia");
            Categoria cat3 = new Categoria();
            cat3.setNome("Drama");
            categoriaRepository.saveAll(List.of(cat1, cat2, cat3));

            // ======== Usuários ========
            Usuario u1 = new Usuario();
            u1.setNome("João");
            u1.setEmail("joao@email.com");
            u1.setSenha("123");
            u1.setDataCadastro(Timestamp.valueOf(LocalDateTime.now()));

            Usuario u2 = new Usuario();
            u2.setNome("Maria");
            u2.setEmail("maria@email.com");
            u2.setSenha("123");
            u2.setDataCadastro(Timestamp.valueOf(LocalDateTime.now()));

            usuarioRepository.saveAll(List.of(u1, u2));

            // ======== Perfis ========
            Perfil p1 = new Perfil();
            p1.setNomePerfil("João Principal");
            p1.setUsuario(u1);

            Perfil p2 = new Perfil();
            p2.setNomePerfil("Maria Principal");
            p2.setUsuario(u2);

            Perfil p3 = new Perfil();
            p3.setNomePerfil("João Secundário");
            p3.setUsuario(u1);

            perfilRepository.saveAll(List.of(p1, p2, p3));

            // ======== Vídeos ========
            Video v1 = new Video();
            v1.setTitulo("Missão Impossível");
            v1.setDescricao("Filme de ação cheio de adrenalina");
            v1.setDuracao(120);
            v1.setCategoria(cat1);

            Video v2 = new Video();
            v2.setTitulo("Comédia da Vida");
            v2.setDescricao("Filme engraçado para toda a família");
            v2.setDuracao(90);
            v2.setCategoria(cat2);

            Video v3 = new Video();
            v3.setTitulo("Drama da Cidade");
            v3.setDescricao("Uma história emocionante");
            v3.setDuracao(110);
            v3.setCategoria(cat3);

            videoRepository.saveAll(List.of(v1, v2, v3));

            // ======== Avaliações ========
            Avaliacao a1 = new Avaliacao();
            a1.setPerfil(p1);
            a1.setVideo(v1);
            a1.setNota(5);
            a1.setComentario("Sensacional!");

            Avaliacao a2 = new Avaliacao();
            a2.setPerfil(p2);
            a2.setVideo(v1);
            a2.setNota(4);
            a2.setComentario("Gostei bastante");

            Avaliacao a3 = new Avaliacao();
            a3.setPerfil(p3);
            a3.setVideo(v2);
            a3.setNota(3);
            a3.setComentario("Rendeu algumas risadas");

            avaliacaoRepository.saveAll(List.of(a1, a2, a3));

            // ======== Visualizações ========
            Visualizacao vis1 = new Visualizacao();
            vis1.setPerfil(p1);
            vis1.setVideo(v1);
            vis1.setDataHora(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
            vis1.setProgresso(100);

            Visualizacao vis2 = new Visualizacao();
            vis2.setPerfil(p1);
            vis2.setVideo(v2);
            vis2.setDataHora(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
            vis2.setProgresso(50);

            Visualizacao vis3 = new Visualizacao();
            vis3.setPerfil(p2);
            vis3.setVideo(v1);
            vis3.setDataHora(Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
            vis3.setProgresso(100);

            Visualizacao vis4 = new Visualizacao();
            vis4.setPerfil(p3);
            vis4.setVideo(v3);
            vis4.setDataHora(Timestamp.valueOf(LocalDateTime.now().minusHours(3)));
            vis4.setProgresso(100);

            visualizacaoRepository.saveAll(List.of(vis1, vis2, vis3, vis4));

            System.out.println("=== Banco populado com dados de teste ===");
        };
    }
     */

}
