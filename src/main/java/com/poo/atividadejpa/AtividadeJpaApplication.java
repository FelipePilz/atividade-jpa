package com.poo.atividadejpa;

import com.poo.atividadejpa.model.*;
import com.poo.atividadejpa.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AtividadeJpaApplication {

    private static final Logger LOG = LoggerFactory.getLogger(AtividadeJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AtividadeJpaApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            CategoriaRepository categoriaRepository,
            VideoRepository videoRepository,
            AvaliacaoRepository avaliacaoRepository,
            VisualizacaoRepository visualizacaoRepository,
            UsuarioRepository usuarioRepository,
            PerfilRepository perfilRepository) {
        return args -> {
            initDb(categoriaRepository, usuarioRepository, videoRepository, perfilRepository, avaliacaoRepository, visualizacaoRepository);
            showQueries(videoRepository, avaliacaoRepository, categoriaRepository, visualizacaoRepository);
        };
    }

    private static void showQueries(VideoRepository videoRepository, AvaliacaoRepository avaliacaoRepository, CategoriaRepository categoriaRepository, VisualizacaoRepository visualizacaoRepository) {
        LOG.info("--------------------------");

        var nomeTitulo = "Missão";
        LOG.info("Buscando videos pelo título '{}' com ordenação...", nomeTitulo);
        List<Video> videos1 = videoRepository.findByTituloContainingOrderByTituloAsc(nomeTitulo);

        if (videos1.isEmpty()) {
            LOG.info("Nenhum vídeo encontrado com o título '{}'.", nomeTitulo);
        } else {
            videos1.forEach(video -> LOG.info(video.getTitulo()));
        }

        LOG.info("--------------------------");
        var nomeCategoria = "Drama";
        LOG.info("Buscando todos os vídeos da categoria '{}' ordenado pelo título...", nomeCategoria);

        var categoria = categoriaRepository.findByNome(nomeCategoria);
        if (categoria == null) {
            LOG.warn("Categoria '{}' não encontrada.", nomeCategoria);
        } else {
            var videos2 = videoRepository.findByCategoriaIdOrderByTituloAsc(categoria.getId());
            if (videos2.isEmpty()) {
                LOG.info("Nenhum vídeo encontrado na categoria '{}'.", nomeCategoria);
            } else {
                videos2.forEach(video -> LOG.info(video.getTitulo()));
            }
        }
        LOG.info("--------------------------");

        LOG.info("Buscando os top 10 vídeos mais bem avaliados...");
        var top10RatedVideos = avaliacaoRepository.findTop10RatedVideos();

        if (!top10RatedVideos.isEmpty()) {
            top10RatedVideos.forEach(video -> LOG.info(video.getTitulo()));
        } else {
            LOG.info("Nenhum vídeo encontrado.");
        }
        LOG.info("--------------------------");


        LOG.info("Buscando os top 10 vídeos mais assistidos...");

        var topMaisAssistidos = visualizacaoRepository.findTop10MaisAssistidos();

        if (!topMaisAssistidos.isEmpty()) {
            topMaisAssistidos.forEach(video -> LOG.info(video.getTitulo()));
        } else {
            LOG.info("Nenhum vídeo encontrado.");
        }

        LOG.info("--------------------------");
        LOG.info("Buscando o usuário que mais assistiu vídeos...");
        var usuarioMaisAtivo = visualizacaoRepository.findUsuarioMaisAtivo();

        if (usuarioMaisAtivo != null) {
            LOG.info(usuarioMaisAtivo.getNome());
        } else {
            LOG.info("Nenhum usuário encontrado.");
        }
        LOG.info("--------------------------");
    }

    private static void initDb(
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository,
            VideoRepository videoRepository,
            PerfilRepository perfilRepository,
            AvaliacaoRepository avaliacaoRepository,
            VisualizacaoRepository visualizacaoRepository
    ) {
        // ======== CATEGORIAS ========
        Categoria acao = new Categoria("Ação");
        Categoria comedia = new Categoria("Comédia");
        Categoria drama = new Categoria("Drama");
        Categoria ficcao = new Categoria("Ficção Científica");
        Categoria doc = new Categoria("Documentário");
        categoriaRepository.saveAll(List.of(acao, comedia, drama, ficcao, doc));

        // ======== USUÁRIOS ========
        Timestamp agora = Timestamp.valueOf(LocalDateTime.now());
        Usuario joao = new Usuario("João Silva", "joao@email.com", "123", agora);
        Usuario maria = new Usuario("Maria Santos", "maria@email.com", "123", agora);
        Usuario carlos = new Usuario("Carlos Oliveira", "carlos@email.com", "123", agora);
        Usuario ana = new Usuario("Ana Souza", "ana@email.com", "123", agora);
        Usuario pedro = new Usuario("Pedro Lima", "pedro@email.com", "123", agora);
        usuarioRepository.saveAll(List.of(joao, maria, carlos, ana, pedro));

        // ======== PERFIS (1 por usuário) ========
        Perfil perfilJoao = new Perfil("João Principal", joao);
        Perfil perfilMaria = new Perfil("Maria Principal", maria);
        Perfil perfilCarlos = new Perfil("Carlos Principal", carlos);
        Perfil perfilAna = new Perfil("Ana Principal", ana);
        Perfil perfilPedro = new Perfil("Pedro Principal", pedro);
        perfilRepository.saveAll(List.of(perfilJoao, perfilMaria, perfilCarlos, perfilAna, perfilPedro));

        // ======== VÍDEOS ========
        Video v1 = new Video("Missão Impossível", "Ação intensa", 120, acao);
        Video v2 = new Video("Missão Secreta", "Mais ação e adrenalina", 110, acao);
        Video v3 = new Video("Missão Final", "O grande final da missão", 130, acao);

        Video v4 = new Video("Comédia da Vida", "Risos garantidos", 90, comedia);
        Video v5 = new Video("Risadas sem Fim", "Comédia leve para todos", 95, comedia);
        Video v6 = new Video("Piada Final", "Humor inteligente", 100, comedia);

        Video v7 = new Video("Drama da Cidade", "Emocionante e intenso", 110, drama);
        Video v8 = new Video("Lágrimas de Inverno", "Drama comovente", 115, drama);
        Video v9 = new Video("História Proibida", "Segredos revelados", 120, drama);

        Video v10 = new Video("Ficção Galáctica", "Aventura espacial", 130, ficcao);
        Video v11 = new Video("O Futuro Chegou", "Tecnologia e suspense", 125, ficcao);
        Video v12 = new Video("Dimensão X", "Mundos paralelos", 140, ficcao);

        Video v13 = new Video("O Mundo Natural", "Documentário sobre a natureza", 80, doc);
        Video v14 = new Video("Histórias Reais", "Casos verídicos", 85, doc);
        Video v15 = new Video("Segredos da História", "Curiosidades históricas", 90, doc);

        videoRepository.saveAll(List.of(
                v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15
        ));

        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.addAll(List.of(
                new Avaliacao(perfilJoao, v1, 5, "Ótimo!"),
                new Avaliacao(perfilMaria, v1, 4, "Bom filme"),
                new Avaliacao(perfilCarlos, v2, 3, "Regular"),
                new Avaliacao(perfilAna, v2, 5, "Excelente"),
                new Avaliacao(perfilPedro, v3, 4, "Gostei"),
                new Avaliacao(perfilJoao, v3, 5, "Muito bom"),
                new Avaliacao(perfilMaria, v4, 2, "Fraco"),
                new Avaliacao(perfilCarlos, v4, 3, "Normal"),
                new Avaliacao(perfilAna, v5, 5, "Perfeito"),
                new Avaliacao(perfilPedro, v5, 4, "Bom"),
                new Avaliacao(perfilJoao, v6, 3, "Ok"),
                new Avaliacao(perfilMaria, v7, 5, "Emocionante"),
                new Avaliacao(perfilCarlos, v8, 4, "Gostei"),
                new Avaliacao(perfilAna, v9, 3, "Normal"),
                new Avaliacao(perfilPedro, v10, 5, "Incrível"),
                new Avaliacao(perfilJoao, v11, 4, "Bom"),
                new Avaliacao(perfilMaria, v12, 3, "Ok"),
                new Avaliacao(perfilCarlos, v13, 4, "Interessante"),
                new Avaliacao(perfilAna, v14, 5, "Excelente"),
                new Avaliacao(perfilPedro, v15, 3, "Regular")
        ));
        avaliacaoRepository.saveAll(avaliacoes);

        List<Visualizacao> visualizacoes = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 5; i++)
            visualizacoes.add(new Visualizacao(perfilJoao, v1, Timestamp.valueOf(now.minusHours(i * 2)), 100));
        for (int i = 0; i < 3; i++)
            visualizacoes.add(new Visualizacao(perfilMaria, v2, Timestamp.valueOf(now.minusHours(i * 3)), 100));
        for (int i = 0; i < 4; i++)
            visualizacoes.add(new Visualizacao(perfilCarlos, v3, Timestamp.valueOf(now.minusDays(i)), 100));
        for (int i = 0; i < 2; i++)
            visualizacoes.add(new Visualizacao(perfilAna, v4, Timestamp.valueOf(now.minusDays(i)), 100));
        for (int i = 0; i < 6; i++)
            visualizacoes.add(new Visualizacao(perfilPedro, v5, Timestamp.valueOf(now.minusHours(i)), 100));
        for (int i = 0; i < 1; i++)
            visualizacoes.add(new Visualizacao(perfilJoao, v6, Timestamp.valueOf(now.minusHours(i * 5)), 100));
        for (int i = 0; i < 3; i++)
            visualizacoes.add(new Visualizacao(perfilMaria, v7, Timestamp.valueOf(now.minusDays(i * 2)), 100));
        for (int i = 0; i < 5; i++)
            visualizacoes.add(new Visualizacao(perfilCarlos, v8, Timestamp.valueOf(now.minusHours(i)), 100));
        for (int i = 0; i < 4; i++)
            visualizacoes.add(new Visualizacao(perfilAna, v9, Timestamp.valueOf(now.minusHours(i)), 100));
        for (int i = 0; i < 7; i++)
            visualizacoes.add(new Visualizacao(perfilPedro, v10, Timestamp.valueOf(now.minusDays(i)), 100));
        visualizacaoRepository.saveAll(visualizacoes);

        LOG.info("Banco populado com dados de teste...");
    }

}
