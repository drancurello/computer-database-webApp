package com.excilys.computerDatabase.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerDatabase.connection.ConnectionMySQL;
import com.excilys.computerDatabase.mapper.ComputerMapper;
import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.model.Computer;

public class ComputerDAO implements CrudService<Computer> {

	@Override
	public Computer add(Computer obj) {

		String query = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";

		ResultSet resultId = null;
		PreparedStatement prepare = null;

		try {
			
			prepare = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, obj.getName());
			if (obj.getIntroducedTime() == null) {
				prepare.setString(2, null);
			} else {
				prepare.setDate(2, Date.valueOf(obj.getIntroducedTime()));
			}
			if (obj.getDiscontinuedTime() == null) {
				prepare.setString(3, null);
			} else {
				prepare.setDate(3, Date.valueOf(obj.getDiscontinuedTime()));
			}

			if (obj.getCompany() == null) {
				Company company = new Company();
				obj.setCompany(company);
				prepare.setString(4, null);
			} else {
				prepare.setInt(4, obj.getCompany().getId());
			}
			prepare.executeUpdate();
			resultId = prepare.getGeneratedKeys();

			if (resultId.next()) {
				obj.setId(resultId.getInt(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(resultId,null,prepare);
		} 

		return obj;
	}

	@Override
	public Computer update(Computer obj) {

		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ?  WHERE id = "
				+ obj.getId();
		
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, obj.getName());
			if (obj.getIntroducedTime() == null) {
				ps.setString(2, null);
			} else {
				ps.setDate(2, Date.valueOf(obj.getIntroducedTime()));
			}
			if (obj.getDiscontinuedTime() == null) {
				ps.setString(3, null);
			} else {
				ps.setDate(3, Date.valueOf(obj.getDiscontinuedTime()));
			}

			if (obj.getCompany() == null) {
				Company company = new Company();
				obj.setCompany(company);
				ps.setString(4, null);
			} else {
				ps.setInt(4, obj.getCompany().getId());
			}
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(null,null,ps);
		}

		return obj;
	}

	@Override
	public int delete(long id) {

		String query = "DELETE FROM computer WHERE id = " + id;
		int result = 0;

		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			result = stmt.executeUpdate(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(null,stmt,null);
		}

		return result;

	}

	@Override
	public Computer find(long id) {

		String query = "SELECT * FROM computer WHERE id = " + id;

		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				Computer computer = new Computer();
				computer = ComputerMapper.resultToComputer(rs);
				return computer;

			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(rs,stmt,null);
		}

		return null;
	}

	@Override
	public List<Computer> findAll() {

		String query = "SELECT * FROM computer";

		List<Computer> computerList = new ArrayList<Computer>();

		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Computer computer = new Computer();
				computer = ComputerMapper.resultToComputer(rs);
				computerList.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(rs,stmt,null);
		}

		return computerList;
	}

	@Override
	public List<Computer> findPage(int nPage, int nComputer) {

		int debut = nComputer * (nPage - 1);

		String query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + debut + "," + nComputer;

		List<Computer> computerList = new ArrayList<Computer>();

		ResultSet rs = null;
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Computer computer = new Computer();
				computer = ComputerMapper.resultToComputer(rs);
				computerList.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(rs,stmt,null);
		}
		return computerList;
	}
	
	public int getNbComputers()
	{
		String query = "SELECT COUNT(*) FROM computer";
		
		ResultSet rs = null;
		Statement stmt = null;
		int nbEntries = 0;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				nbEntries = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionMySQL.CloseConnection(rs,stmt,null);
		}
		
		return nbEntries;
		
	}

}
