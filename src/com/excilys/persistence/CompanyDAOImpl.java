package com.excilys.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.CompanyModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CompanyDAOImpl implements CompanyDAO {

	@Override
	public List<CompanyModel> listAll() {
	    List<CompanyModel> companyList = null;
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	        Statement st = (Statement) connection.createStatement();

	        String query = "SELECT * FROM company";
	        ResultSet rs = st.executeQuery(query);
	        
	        companyList = (new CompanyMapper()).mapAll(rs);
	        
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return companyList;
	}

	@Override
	public CompanyModel getById(long id) {
		CompanyModel companyModel = null;
    	Connection connection = DAOFactory.INSTANCE.getConnection();
	    try {
	        // create new statement
	    	int i = 1;
	        String query = "SELECT * FROM company WHERE id =?";
	        PreparedStatement st = null;
	        st = (PreparedStatement) connection.prepareStatement(query);
	        st.setLong(i++, id);
	        
	        ResultSet rs = st.executeQuery();
	        
	        companyModel = (new CompanyMapper()).mapOne(rs);
	            /*System.out.printf("%-5d | %-70s | %-25s | %-25s | %-1s \n", //
	                    rs.getLong(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getLong(5));*/
	        rs.close();
	        st.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    DAOFactory.INSTANCE.CloseConnection(connection);
	    return companyModel;
	}

}