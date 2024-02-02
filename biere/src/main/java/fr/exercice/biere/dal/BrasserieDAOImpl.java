package fr.exercice.biere.dal;

import java.util.List;

import fr.exercice.biere.bo.Brasserie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class BrasserieDAOImpl implements BrasserieDAO{
    private final String FIND_BY_ID = "SELECT IDBRASSERIE, NOM FROM BRASSERIE WHERE idBrasserie= :id";
    RowMapper<Brasserie> rowMapper = (rs, i)->
            new Brasserie(
                    rs.getInt("idBrasserie"),
                    rs.getString("nom"),
                    rs.getString("adresse"),
                    rs.getString("coordGPS"),
                    rs.getDate("dtOuverture").toLocalDate()
            );

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void insert(Brasserie brasserie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("nom", brasserie.getNom());
        namedParameters.addValue("adresse", brasserie.getAdresse());
        namedParameters.addValue("coordGPS", brasserie.getCoordGPS());
        namedParameters.addValue("dtOuverture", brasserie.getDtOuverture());

        jdbcTemplate.update("INSERT INTO BRASSERIE (nom, adresse, coordGPS, dtOuverture) VALUES (:nom,:adresse,:coordGPS,:dtOuverture)", namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            brasserie.setIdBrasserie(keyHolder.getKey().intValue());
        }

        // TODO : a supprimer
        System.out.println("insertion de "+brasserie);
    }

    @Override
    public List<Brasserie> getAll() {
        return jdbcTemplate.query("SELECT idBrasserie, nom, adresse, coordGPS, dtOuverture FROM BRASSERIE", rowMapper);
    }

    @Override
    public Brasserie read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);

        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters,
                new BeanPropertyRowMapper<>(Brasserie.class));
    }
}
