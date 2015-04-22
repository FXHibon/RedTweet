package listener; /**
 * Created by Fx on 22/04/2015.
 */

import fr.epsi.tp.redtweet.bean.User;
import fr.epsi.tp.redtweet.dao.helper.DbHelper;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.awt.image.DataBuffer;

@WebListener()
public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private Logger logger = Logger.getLogger(ContextListener.class);

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // Add some users into DB
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Jedis jedis = DbHelper.getJedis();
            User user;

            user = new User();

            String data;
            for (int i = 0; i < 10; i++) {
                data = "test" + i;
                user.setUsername(data );
                user.setFirstName(data );
                user.setLastName(data );
                user.setPassword(data );
                jedis.hmset("user:" + user.getUsername(), user);
            }


            jedis.close();
        } catch (Exception e) {
            logger.error("unable to contact redis:", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
