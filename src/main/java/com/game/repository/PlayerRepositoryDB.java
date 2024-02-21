package com.game.repository;

import com.game.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import jakarta.persistence.*;


@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {

    private final SessionFactory sessionFactory;

    public PlayerRepositoryDB() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
        properties.put(Environment.URL,"jdbc:mysql://localhost:3306");
        properties.put(Environment.USER,"user");
        properties.put(Environment.PASS,"user");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        sessionFactory = new Configuration().setProperties(properties)
                .buildSessionFactory();
    }

    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
        try(Session session = sessionFactory.openSession()){
        NativeQuery<Player> nativeQuery = session.createNativeQuery("select * from player", Player.class);
        return nativeQuery.list();
        }//доработать посмотри разбор
    }

    @Override
    public int getAllCount() {
        try(Session session = sessionFactory.openSession()){
        Query<Player> query = session.createNamedQuery("All_Players_Count", Player.class);
        return query.executeUpdate();}
    }//тоже доработай

    @Override
    public Player save(Player player) {
        return null;
    }

    @Override
    public Player update(Player player) {
        return null;
    }

    @Override
    public Optional<Player> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Player player) {

    }

    @PreDestroy
    public void beforeStop() {

    }
}