package fr.exercice.biere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.exercice.biere.bo.Biere;
import fr.exercice.biere.bo.Brasserie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class BiereDAOImpl implements BiereDAO {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;


	@Override
	public void insert(Biere biere) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("nom", biere.getNom());
		namedParameters.addValue("tyBiere", biere.getTyBiere());
		namedParameters.addValue("description", biere.getDescription());
		namedParameters.addValue("dgAlcool", biere.getDgAlcool());
		namedParameters.addValue("note", biere.getNote());
		namedParameters.addValue("idBrasserie", biere.getBrasserie().getIdBrasserie());

		jdbcTemplate.update("INSERT INTO BIERE (nom, tyBiere, description, dgAlcool, note, idBrasserie) VALUES (:nom,:tyBiere,:description,:dgAlcool,:note,:idBrasserie)", namedParameters, keyHolder);

		if (keyHolder != null && keyHolder.getKey() != null) {
			biere.setIdBiere(keyHolder.getKey().intValue());
		}

		// TODO : a supprimer
		System.out.println("insertion de " + biere);
	}

	@Override
	public List<Biere> getAll() {
		return jdbcTemplate.query("SELECT idBiere, nom, tyBiere, description, dgAlcool, note, idBrasserie FROM BIERE", new BiereRowMapper());
	}

	@Override
	public List<Biere> getAllBearBrasserie(long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);

		return jdbcTemplate.query("SELECT idBiere, nom, tyBiere, description, dgAlcool, note, idBrasserie FROM BIERE WHERE idBrasserie = :id", new BiereRowMapper());
	}


	/**
	 * Classe de mapping pour gérer l'association vers Brasserie
	 */
     class BiereRowMapper implements RowMapper<Biere> {
		@Override
		public Biere mapRow(ResultSet rs, int rowNum) throws SQLException {
			Biere a = new Biere();
			a.setIdBiere(rs.getInt("idBiere"));
			a.setNom(rs.getString("nom"));
			a.setDescription(rs.getString("description"));
			a.setDgAlcool(rs.getInt("dgAlcool"));
			a.setNote(rs.getInt("note"));

			//Association vers la brasserie
			Brasserie brasserie = BrasserieDAO.read(rs.getLong("ID BRASSERIE"));
			a.setBrasserie(brasserie);
			return a;
		}
	}
}
