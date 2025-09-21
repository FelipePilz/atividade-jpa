CREATE DATABASE IF NOT EXISTS streaming;
USE streaming;

CREATE TABLE categoria
(
    id   INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255)       NOT NULL UNIQUE,
    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

CREATE TABLE usuario
(
    id            INT AUTO_INCREMENT NOT NULL,
    nome          VARCHAR(255)       NOT NULL,
    email         VARCHAR(255)       NOT NULL UNIQUE,
    senha         VARCHAR(255)       NOT NULL,
    data_cadastro datetime           NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);

CREATE TABLE perfil
(
    id          INT AUTO_INCREMENT NOT NULL,
    nome_perfil VARCHAR(255)       NOT NULL UNIQUE,
    usuario_id  INT                NOT NULL,
    CONSTRAINT pk_perfil PRIMARY KEY (id),
    CONSTRAINT FK_PERFIL_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE video
(
    id           INT AUTO_INCREMENT NOT NULL,
    titulo       VARCHAR(255)       NOT NULL UNIQUE,
    descricao    VARCHAR(1000)      NULL,
    duracao      INT                NOT NULL,
    categoria_id INT                NOT NULL,
    CONSTRAINT pk_video PRIMARY KEY (id),
    CONSTRAINT FK_VIDEO_ON_CATEGORIA FOREIGN KEY (categoria_id) REFERENCES categoria (id)
);

CREATE TABLE avaliacao
(
    id         INT AUTO_INCREMENT NOT NULL,
    perfil_id  INT                NOT NULL,
    video_id   INT                NOT NULL,
    nota       INT                NOT NULL,
    comentario TEXT               NULL,
    CONSTRAINT pk_avaliacao PRIMARY KEY (id),
    CONSTRAINT FK_AVALIACAO_ON_PERFIL FOREIGN KEY (perfil_id) REFERENCES perfil (id),
    CONSTRAINT FK_AVALIACAO_ON_VIDEO FOREIGN KEY (video_id) REFERENCES video (id)
);

CREATE TABLE visualizacao
(
    id        INT AUTO_INCREMENT NOT NULL,
    perfil_id INT                NOT NULL,
    video_id  INT                NOT NULL,
    data_hora datetime           NOT NULL,
    progresso INT                NOT NULL,
    CONSTRAINT pk_visualizacao PRIMARY KEY (id),
    CONSTRAINT FK_VISUALIZACAO_ON_PERFIL FOREIGN KEY (perfil_id) REFERENCES perfil (id),
    CONSTRAINT FK_VISUALIZACAO_ON_VIDEO FOREIGN KEY (video_id) REFERENCES video (id)
);