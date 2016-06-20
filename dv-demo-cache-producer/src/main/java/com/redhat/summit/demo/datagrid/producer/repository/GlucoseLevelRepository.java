package com.redhat.summit.demo.datagrid.producer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.summit.demo.datagrid.domain.BloodPressureLevel;
import com.redhat.summit.demo.datagrid.domain.GlucoseLevel;
import com.redhat.summit.demo.datagrid.producer.db.DbConnectionFactory;

/**
 * Repository for {@link GlucoseLevel GlucoseLevels}.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class GlucoseLevelRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlucoseLevel.class);

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private static final String GL_KEY_PREFIX = "gl-";

	private static final DbConnectionFactory dbConnectionFactory = new DbConnectionFactory();

	/**
	 * Retrieves the {@link GlucoseLevelLevel} from the database.
	 * 
	 * @param connection
	 *            the {@link Connection} to use.
	 * @return a {@link Map} of {@link BloodPressureLevel BloodPressureLevels}.
	 */
	public Map<String, GlucoseLevel> getGlucoseLevel() {

		Map<String, GlucoseLevel> gls = new HashMap<>();

		String selectBpl = "SELECT * FROM glucose_level";

		try (Connection connection = dbConnectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(selectBpl);
			try (ResultSet rs = statement.executeQuery()) {

				while (rs.next()) {
					GlucoseLevel gl = new GlucoseLevel();

					int id = rs.getInt("id");
					gl.setId(id);

					gl.setPatientId(rs.getInt("personId"));
					Date dateRead = rs.getDate("dateREad");
					gl.setDateRead(df.format(dateRead));
					gl.setType(rs.getString("type"));
					gl.setValue(rs.getInt("value"));
					gl.setLabel(rs.getString("label"));
					gl.setNote(rs.getString("note"));

					gls.put(GL_KEY_PREFIX + Integer.toString(id), gl);
				}
			}

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			String message = "Error while fetching Blood Pressure Level data.";
			LOGGER.error(message, sqle);
			throw new RuntimeException(message, sqle);
		}

		return gls;
	}
}
