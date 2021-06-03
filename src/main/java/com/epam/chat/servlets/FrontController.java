package com.epam.chat.servlets;

import com.epam.chat.commands.Command;
import com.epam.chat.datalayer.DAOFactory;
import com.epam.chat.datalayer.DBType;
import com.epam.chat.datalayer.UserDAO;
import com.epam.chat.datalayer.connectionpool.ConnectionPool;
import com.epam.chat.datalayer.dto.Role;
import com.epam.chat.datalayer.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Front controller implementation
 */
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(FrontController.class);

    private static final long serialVersionUID = 1L;

    private DAOFactory daoFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        String dbTypeAsString = this.getServletConfig().getInitParameter("dbType");
        DBType dbType = DBType.valueOf(dbTypeAsString);
        this.daoFactory = DAOFactory.getInstance(dbType);
        testing();
    }

    private void testing() {
        UserDAO userDAO = daoFactory.getUserDAO();
        LOGGER.debug("All logged users:");
        userDAO.getAllLogged().forEach(u -> LOGGER.debug("   {}", u));

        LOGGER.debug("All kicked users:");
        userDAO.getAllKicked().forEach(u -> LOGGER.debug("   {}", u));

        LOGGER.debug("LoginAll:");
        LOGGER.debug("   Is Dima loggedIn: {}", userDAO.isLoggedIn(new User("Dima", Role.USER)));
        LOGGER.debug("   Is Vlad loggedIn: {}", userDAO.isLoggedIn(new User("Vlad", Role.USER)));
        LOGGER.debug("   Is man loggedIn: {}", userDAO.isLoggedIn(new User("man", Role.USER)));

        LOGGER.debug("KickedAll:");
        LOGGER.debug("   Is Dima kicked: {}", userDAO.isKicked(new User("Dima", Role.USER)));
        LOGGER.debug("   Is Vlad kicked: {}", userDAO.isKicked(new User("Vlad", Role.USER)));
        LOGGER.debug("   Is man kicked: {}", userDAO.isKicked(new User("man", Role.USER)));

        LOGGER.debug("Dima has role: {}", userDAO.getRole("Dima"));
        LOGGER.debug("Avatar:");
        User avatar = new User("Avatar", Role.USER);
        LOGGER.debug("  Is Avatar logged in: {}", userDAO.isLoggedIn(avatar));
        LOGGER.debug("  Is Avatar kicked: {}", userDAO.isKicked(avatar));
        userDAO.login(avatar);
        LOGGER.debug("Avatar logged in:");
        LOGGER.debug("  Is Avatar logged in: {}", userDAO.isLoggedIn(avatar));
        LOGGER.debug("  Is Avatar kicked: {}", userDAO.isKicked(avatar));
        userDAO.kick(new User("Denis", Role.ADMIN), avatar);
        LOGGER.debug("Avatar is kicked:");
        LOGGER.debug("  Is Avatar logged in: {}", userDAO.isLoggedIn(avatar));
        LOGGER.debug("  Is Avatar kicked: {}", userDAO.isKicked(avatar));
        userDAO.unkick(avatar);
        LOGGER.debug("Avatar is unkicked:");
        LOGGER.debug("  Is Avatar logged in: {}", userDAO.isLoggedIn(avatar));
        LOGGER.debug("  Is Avatar kicked: {}", userDAO.isKicked(avatar));

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try (Connection conn = connectionPool.takeConnection()) {
            PreparedStatement statement = conn.prepareStatement("delete from messages WHERE username='Avatar' OR body='Avatar'");
            statement.executeUpdate();
            statement = conn.prepareStatement("delete from users WHERE username='Avatar'");
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Processing GET requests
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);

    }

    /**
     * Processing POST requests
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new UnsupportedOperationException("Implement this method");
    }

    /**
     * Define command by her name from request
     *
     * @param commandName
     * @return chosen command
     */
    protected Command defineCommand(String commandName) {
        int i = 1;
        switch (i) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
        }
        throw new UnsupportedOperationException("Implement this method");
    }

}


