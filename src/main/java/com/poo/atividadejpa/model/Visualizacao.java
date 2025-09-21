package com.poo.atividadejpa.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Visualizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Perfil perfil;

    @ManyToOne(optional = false)
    private Video video;

    @Column(nullable = false)
    private Timestamp dataHora;

    @Column(nullable = false)
    private Integer progresso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getProgresso() {
        return progresso;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }
}
