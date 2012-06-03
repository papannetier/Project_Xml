/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metiers;

import bean.*;
import dao.*;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class AssociationBO {
    
    
    
    private WineFoodDAO wineFoodDAO = null;

    public AssociationBO() {
        wineFoodDAO = new WineFoodDAO();

    }

//    public WineFood getById(Integer id) throws SQLException {
//        return wineFoodDAO.findById(id);
//    }
    
}
