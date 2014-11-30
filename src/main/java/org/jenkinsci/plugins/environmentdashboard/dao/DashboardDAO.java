package org.jenkinsci.plugins.environmentdashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jenkinsci.plugins.environmentdashboard.entity.Build;
import org.jenkinsci.plugins.environmentdashboard.utils.DBConnection;

/**
 * This class is a data access object that handles interactions between the
 * application and the db.
 * 
 * @author Robert Northard
 */
public class DashboardDAO {

    // Create dashboard query
    private static String createDashboardTblQuery = "CREATE TABLE IF NOT EXISTS env_dashboard (envComp VARCHAR(255), jobUrl VARCHAR(255), "
            + "buildNum VARCHAR(255), buildStatus VARCHAR(255), envName VARCHAR(255), compName "
            + "VARCHAR(255), created_at TIMESTAMP,  buildJobUrl VARCHAR(255));";

    // Insert build query
    private static String insertBuildQuery = "INSERT INTO env_dashboard VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP,?);";

    // Delete old build query
    private static String deleteOldBuildQuery = "DELETE FROM env_dashboard where created_at <= current_timestamp - ";

    // Update build query
    private static String updateBuildQuery = "UPDATE env_dashboard SET buildStatus = ?, created_at = CURRENT_TIMESTAMP WHERE "
            + "envComp = ? AND joburl = ?;";

    /**
	 * 
	 */
    public DashboardDAO() {

    }

    /**
     * Create dashboard table
     * 
     * @return true if table created, else false.
     * @exception SQLException
     *                unable to execute create dashboard query.
     */
    public boolean createDashboardTable() throws SQLException {

        // Get DB connection
        Connection conn = DBConnection.getConnection();
        return conn.prepareStatement(DashboardDAO.createDashboardTblQuery)
                .execute();
    }

    /**
     * Add build to environment dashboard.
     * 
     * @param index
     *            the primary key in the env_dashboard table.
     * @param build
     *            the build being added to the dashboard.
     * @param componant
     *            the component being deployed/built
     * @param buildJobUrl
     *            the URL of the build job.
     * @return true if build added else false.
     * @throws SQLException
     *             unable to execute insert build query.
     */
    public boolean addBuild(String index, Build build, String componant,
            String buildJobUrl) throws SQLException {

        // Get DB Connection
        Connection conn = DBConnection.getConnection();
        PreparedStatement stat = conn
                .prepareStatement(DashboardDAO.insertBuildQuery);

        // Populate prepared statement.
        stat.setString(1, index);
        stat.setString(2, build.getUrl());
        stat.setString(3, build.getId());
        stat.setString(4, build.getResult());
        stat.setString(5, build.getEnvironment());
        stat.setString(6, componant);
        stat.setString(7, buildJobUrl);

        boolean result = stat.execute();
        DBConnection.closeConnection();

        return result;
    }

    /**
     * Update an existing logged build
     * 
     * @param index
     *            primary key in the env_dashboard table
     * @param build
     *            the build being added to the dashboard
     * @return true if build updated else false
     * @throws SQLException
     *             unable to execute update build query.
     */
    public boolean updateBuild(String index, Build build) throws SQLException {
        // Get DB Connection
        Connection conn = DBConnection.getConnection();
        PreparedStatement stat = conn
                .prepareStatement(DashboardDAO.updateBuildQuery);

        // Populate prepared statement.
        stat.setString(1, index);
        stat.setString(2, build.getResult());
        stat.setString(3, build.getUrl());

        boolean result = stat.execute();
        DBConnection.closeConnection();

        return result;
    }

    /**
     * Delete build older then specified days.
     * 
     * @param daysOld
     *            delete build in the last days i.e. 30
     * @return - true if builds deleted else false.
     * @throws SQLException
     *             unable to execute delete build query.
     */
    public boolean deleteBuild(int daysOld) throws SQLException {

        boolean result = false;

        if (daysOld > 0) {
            // Get DB Connection
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn
                    .prepareStatement(DashboardDAO.deleteOldBuildQuery
                            + daysOld);

            result = stat.execute();
            DBConnection.closeConnection();
        }

        return result;
    }

}