package io.nk.uploaddownloadimagerestfulwebservice.repository;

import io.nk.uploaddownloadimagerestfulwebservice.model.DBFile;
import io.nk.uploaddownloadimagerestfulwebservice.model.PlacardImage;
import io.nk.uploaddownloadimagerestfulwebservice.model.PlacardImageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DBFilePlacardImageRepository {

    public static final Logger logger = LoggerFactory.getLogger(DBFilePlacardImageRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String INSERT_IMAGE = "insert into PLACARD_IMAGE " + "(P_NAME, P_IMAGE)" + "values" + "(?,?)";
    public static final String SELECT_IMAGE = "select P_NAME, P_IMAGE from PLACARD_IMAGE where P_NAME = ?";
    //P_NAME, P_IMAGE

    public void save(PlacardImageRequest request) {

        jdbcTemplate.update(INSERT_IMAGE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                try {
                    InputStream imageHHBlob = new ByteArrayInputStream(request.getPlacardImage().getBytes());

                    preparedStatement.setString(1, request.getPlacardName());
                    preparedStatement.setBinaryStream(2, imageHHBlob);

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }


    public DBFile getPlacardImage(String placardName) {

        DBFile placardImage;
        logger.info("Getting Placard Image : " + placardName);
        try {

            placardImage = jdbcTemplate.queryForObject(SELECT_IMAGE, new PlacardImageRowMapper(), new Object[]{placardName});

            if (placardImage.getPlacardName() != null) {
                logger.info("Placard Image is selected from Db : " + placardImage.getPlacardName().toString());
            }
            return placardImage;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No Placard Image Found " + e.getMessage());
            return null;
        }
    }

    public class PlacardImageRowMapper implements RowMapper<DBFile> {

        @Override
        public DBFile mapRow(ResultSet rs, int i) throws SQLException {
            DBFile placardImage = new DBFile();
            setPlacardName(placardImage, rs);
            setPlacardImage(placardImage, rs);
            placardImage.setFileType("image/jpeg");
            return placardImage;
        }

        private void setPlacardName(DBFile placardImage, ResultSet rs) {
            try {
                placardImage.setPlacardName(rs.getString("P_NAME"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void setPlacardImage(DBFile placardImage, ResultSet rs) throws SQLException {
            Blob blob = rs.getBlob("P_IMAGE");
            byte[] bytes = blob.getBytes(1l, (int) blob.length());
            placardImage.setPlacardImage(bytes);
        }

    }

}
