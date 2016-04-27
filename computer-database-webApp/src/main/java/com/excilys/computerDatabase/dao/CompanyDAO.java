package com.excilys.computerDatabase.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerDatabase.dao.interfaceDAO.ICompanyDAO;
import com.excilys.computerDatabase.exceptions.ConnectionException;
import com.excilys.computerDatabase.model.Company;

/**
 * The Class CompanyDAO.
 */
@Component
@SuppressWarnings("unchecked")
public class CompanyDAO implements ICompanyDAO {
	
	@Autowired 
	private SessionFactory factory;
	
	/**
	 * @param the id of the company
	 * @return the number of companies deleted
	 * @throws ConnectionException 
	 */
	@Override
	public int delete(long id) {
		Query query = factory.getCurrentSession().createQuery(SqlQueries.DELETE_COMPANY);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	/**
	 * find a company by its id
	 * 
	 * @param the id of a company
	 * @return a company
	 * @throws ConnectionException 
	 */
	@Override
	public Company find(long id){
		
		Company company = new Company();
		Query query = factory.getCurrentSession().createQuery(SqlQueries.FIND_COMPANY);
		query.setParameter("id", id);
		List<Company> companies = query.list();
		
		if (companies.size() != 0) {
			return companies.get(0);
		}
		return company;
	}
	

	/** 
	 * find all the companies
	 * @return the list of all companies
	 * @throws ConnectionException 
	 */
	@Override
	public List<Company> findAll(){
		return factory.getCurrentSession().createQuery(SqlQueries.FIND_ALL_COMPANIES).list();
	}

}
